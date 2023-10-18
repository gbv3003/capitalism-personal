package simulation_components
package game_components

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should._
import javax.smartcardio.Card

class Menu_test extends AnyFunSpec with Matchers {
  describe("The Capitalism Simulation") {
    describe("has a menu") {

      // ******* SHOW PLAYER ORDER *******
      it("can show the player order") {
        val expectedResult = "Player 1, Player 2, Player 3, Player 4"
        model.simulation_components.Menu.showPlayerOrder should be(expectedResult)
      }

      // ******* ADVANCE ORDER *******
      it("can advance the player order") {
        val expectedResult1 = "Player 1, Player 2, Player 3, Player 4"
        model.simulation_components.Menu.showPlayerOrder should be(expectedResult1)
        val expectedResult2 = "Player 2, Player 3, Player 4, Player 1"
        model.simulation_components.Menu.advancePlayerOrder
        model.simulation_components.Menu.showPlayerOrder should be(expectedResult2)
      }

      // ******* SHOW GAME AREA *******
      it(
        "can show the game area, including the cards in each player's hand, the previous card that was played in the trick, the current rank of each player, and the total scores of each player. "
      ) {
        model.simulation_components.Menu.advancePlayerOrder
        model.simulation_components.Menu.advancePlayerOrder
        model.simulation_components.Menu.advancePlayerOrder

        val expectedResult =
          "Player Hands:  \n" +
            "Player 1 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 3, 2, \n" +
            "Player 2 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 3, 2, \n" +
            "Player 3 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 3, 2, \n" +
            "Player 4 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 3, 2, \n" +
            "\n" +
            "Last Card in Trick: \n" +
            "\n" +
            "President: None\n" +
            "Vice President: None\n" +
            "Middle Man: None\n" +
            "Bum: None\n" +
            "\n" +
            "Scores:\n" +
            "Player 1 = 0, Player 2 = 0, Player 3 = 0, Player 4 = 0\n" + "\n"

        model.simulation_components.Menu.showGameArea should be(expectedResult)
      }
      it(
        "can INITIALIZE to cause the game board to be cleared and player data reset"
      ) {

        // first, we put the game into an artificial, mid-simulation state

        model.simulation_components.PlayerOrder.current.score += 1
        model.simulation_components.PlayerOrder.advance
        model.simulation_components.PlayerOrder.current.score += 1
        model.simulation_components.PlayerOrder.current.score += 1
        model.simulation_components.PlayerOrder.current.temp_score = 3

        val expectedResult1 =
          "Player Hands:  \n" +
            "Player 1 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 3, 2, \n" +
            "Player 2 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 3, 2, \n" +
            "Player 3 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 3, 2, \n" +
            "Player 4 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 3, 2, \n" +
            "\n" +
            "Last Card in Trick: \n" +
            "\n" +
            "President: Player 2\n" +
            "Vice President: None\n" +
            "Middle Man: None\n" +
            "Bum: None\n" +
            "\n" +
            "Scores:\n" +
            "Player 1 = 1, Player 2 = 2, Player 3 = 0, Player 4 = 0\n" + "\n" 

        model.simulation_components.Menu.showGameArea should be(expectedResult1)

        // now that we know the simulation is "un-initialized...
        model.simulation_components.Menu.initialize // new feature

        val expectedResult2 =
          "Player Hands:  \n" +
            "Player 1 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 3, 2, \n" +
            "Player 2 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 3, 2, \n" +
            "Player 3 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 3, 2, \n" +
            "Player 4 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 3, 2, \n" +
            "\n" +
            "Last Card in Trick: \n" +
            "\n" +
            "President: None\n" +
            "Vice President: None\n" +
            "Middle Man: None\n" +
            "Bum: None\n" +
            "\n" +
            "Scores:\n" +
            "Player 1 = 0, Player 2 = 0, Player 3 = 0, Player 4 = 0\n" + "\n"

        model.simulation_components.Menu.showGameArea should be(expectedResult2)

      }

      // ******* CHECK FOR WINNER *******
      it("can CHECK FOR WINNER to show whether a player has won the game") {

        model.simulation_components.Menu.checkForWinner should be("none")

        model.simulation_components.PlayerOrder.current.score += 10
        model.simulation_components.Menu.checkForWinner should be("Player 1")
      }

      // ******* DO MOVE *******
      it(
        "can DO MOVE, causing the player at the front of the player order to place their lowest card in the trick pile"
      ) {

        model.simulation_components.Menu.initialize
        model.simulation_components.Menu.doMove // new feature (***** first move *****)

        val expectedResult1 =
          "Player Hands:  \n" +
            "Player 1 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 2, \n" +
            "Player 2 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 3, 2, \n" +
            "Player 3 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 3, 2, \n" +
            "Player 4 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 3, 2, \n" +
            "\n" +
            "Last Card in Trick: 3\n" +
            "\n" +
            "President: None\n" +
            "Vice President: None\n" +
            "Middle Man: None\n" +
            "Bum: None\n" +
            "\n" +
            "Scores:\n" +
            "Player 1 = 0, Player 2 = 0, Player 3 = 0, Player 4 = 0\n" + "\n"

        model.simulation_components.Menu.showGameArea should be(expectedResult1)
        val expectedResult_PO_1 = "Player 2, Player 3, Player 4, Player 1"
        model.simulation_components.Menu.showPlayerOrder should be(expectedResult_PO_1)

        // Handle the first moves by the other 3 players
        // Note that all moves are hard coded to the lowest non-two card (twos are bombs)
        val expectedResult2 =
          "Player Hands:  \n" +
            "Player 1 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 2, \n" +
            "Player 2 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 2, \n" +
            "Player 3 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 3, 2, \n" +
            "Player 4 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 2, \n" +
            "\n" +
            "Last Card in Trick: 4\n" +
            "\n" +
            "President: None\n" +
            "Vice President: None\n" +
            "Middle Man: None\n" +
            "Bum: None\n" +
            "\n" +
            "Scores:\n" +
            "Player 1 = 0, Player 2 = 0, Player 3 = 0, Player 4 = 0\n" + "\n"

        val expectedResult_PO_2 = "Player 1, Player 2, Player 3, Player 4"

        model.simulation_components.Menu.doMove // Player 2's move
        model.simulation_components.Menu.doMove // Player 4's move *** PLAYER 3 SKIPPED ***
        model.simulation_components.Menu.doMove // Player 2's move *** PLAYER 1 SKIPPED ***

        model.simulation_components.Menu.showGameArea should be(expectedResult2)
        model.simulation_components.Menu.showPlayerOrder should be(expectedResult_PO_2)

      }

      it("can DO MOVE when the last card played was a bomb") {
        model.simulation_components.Menu.initialize

        val expectedResult3 =
          "Player Hands:  \n" +
            "Player 1 Hand: King, 9, 5, \n" +
            "Player 2 Hand: Ace, 10, 6, 2, \n" +
            "Player 3 Hand: Jack, 7, 3, 2, \n" +
            "Player 4 Hand: Queen, 8, 4, 2, \n" +
            "\n" +
            "Last Card in Trick: 2\n" +
            "\n" +
            "President: None\n" +
            "Vice President: None\n" +
            "Middle Man: None\n" +
            "Bum: None\n" +
            "\n" +
            "Scores:\n" +
            "Player 1 = 0, Player 2 = 0, Player 3 = 0, Player 4 = 0\n" + "\n"

        val expectedResult_PO_3 = "Player 1, Player 2, Player 3, Player 4"

        for i <- 1 to 36
        do // Only 15 cards left in player's hands, meaning that 36 turns have been played up to this point
          model.simulation_components.Menu.doMove

        // Note: The card before was an Ace, so player 1's only option was a bomb.
        model.simulation_components.Menu.doMove // new feature (***** move played after a bomb is played *****)

        model.simulation_components.Menu.showGameArea should be(expectedResult3)
        model.simulation_components.Menu.showPlayerOrder should be(expectedResult_PO_3)

        val expectedResult4 =
          "Player Hands:  \n" +
            "Player 1 Hand: King, 9, \n" +
            "Player 2 Hand: Ace, 10, 6, 2, \n" +
            "Player 3 Hand: Jack, 7, 3, 2, \n" +
            "Player 4 Hand: Queen, 8, 4, 2, \n" +
            "\n" +
            "Last Card in Trick: 5\n" +
            "\n" +
            "President: None\n" +
            "Vice President: None\n" +
            "Middle Man: None\n" +
            "Bum: None\n" +
            "\n" +
            "Scores:\n" +
            "Player 1 = 0, Player 2 = 0, Player 3 = 0, Player 4 = 0\n" + "\n"

        model.simulation_components.Menu.doMove // new feature (***** move played after a bomb is played *****)

        model.simulation_components.Menu.showGameArea should be(expectedResult4)
      }
      it("can DO MOVE when the player has to skip their turn") {
        model.simulation_components.Menu.initialize

        val expectedResult3 =
          "Player Hands:  \n" +
            "Player 1 Hand: Ace, Jack, 7, 2, 2 \n" +
            "Player 2 Hand: Queen, 8, 4, 3, 3, \n" +
            "Player 3 Hand: King, 9, 5, 4, 2, 2 \n" +
            "Player 4 Hand: 10, 6, 4, 4, 3, 3, \n" +
            "\n" +
            "Last Card in Trick: Ace\n" +
            "\n" +
            "President: Player 1\n" +
            "Vice President: Player 3\n" +
            "Middle Man: Player 2\n" +
            "Bum: Player 4\n" +
            "\n" +
            "Scores:\n" +
            "Player 1 = 3, Player 2 = 1, Player 3 = 2, Player 4 = 0\n" + "\n"

        val expectedResult_PO_3 = "Player 2, Player 3, Player 4, Player 1"

        for i <- 1 to 72
        do // Only 19 cards left in player's hands, meaning that 72 turns have been played up to this point (Including the first 51 turns of round one)
          model.simulation_components.Menu.doMove

        model.simulation_components.Menu.showGameArea should be(expectedResult3)
        model.simulation_components.Menu.showPlayerOrder should be(expectedResult_PO_3)

        val expectedResult4 =
          "Player Hands:  \n" +
            "Player 1 Hand: Ace, Jack, 7, 2, 2 \n" +
            "Player 2 Hand: Queen, 8, 4, 3, 3, \n" +
            "Player 3 Hand: King, 9, 5, 4, 2, \n" +
            "Player 4 Hand: 10, 6, 4, 4, 3, 3, \n" +
            "\n" +
            "Last Card in Trick: 2\n" +
            "\n" +
            "President: Player 1\n" +
            "Vice President:  \n" +
            "Middle Man: Player 2\n" +
            "Bum: Player 4\n" +
            "\n" +
            "Scores:\n" +
            "Player 1 = 3, Player 2 = 1, Player 3 = 2, Player 4 = 0\n" + "\n"

        // Player 2 turn skipped due to them not having a card higher than an Ace or a bomb. Thus, player 3 plays his bomb.
        model.simulation_components.Menu.doMove // new feature (***** move played when a player has to skip their turn *****)

        model.simulation_components.Menu.showGameArea should be(expectedResult4)
      }
      it(
        "can DO MOVE when the second to last player plays their last card, ending the round and starting a new round with updated board (to include trades)"
      ) {
        model.simulation_components.Menu.initialize

        val expectedResult3 =
          "Player Hands:  \n" +
            "Player 1 Hand: 2, \n" +
            "Player 2 Hand: \n" +
            "Player 3 Hand: \n" +
            "Player 4 Hand: 2, \n" +
            "\n" +
            "Last Card in Trick: 2\n" +
            "\n" +
            "President: None\n" +
            "Vice President: None\n" +
            "Middle Man: None\n" +
            "Bum: None\n" +
            "\n" +
            "Scores:\n" +
            "Player 1 = 0, Player 2 = 0, Player 3 = 0, Player 4 = 0\n" + "\n"

        val expectedResult_PO_3 = "Player 1, Player 2, Player 3, Player 4"

        for i <- 1 to 50 do model.simulation_components.Menu.doMove
        // Only 2 cards left in player's hands, meaning that 50 turns have been played up to this point

        model.simulation_components.Menu.showGameArea should be(expectedResult3)
        model.simulation_components.Menu.showPlayerOrder should be(expectedResult_PO_3)

        val expectedResult4 =
          "Player Hands:  \n" +
            "Player 1 Hand: Ace, Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 2, 2, \n" +
            "Player 2 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 3, 3, \n" +
            "Player 3 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 2, 2, \n" +
            "Player 4 Hand: King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 4, 3, 3, \n" +
            "\n" +
            "Last Card in Trick: \n" +
            "\n" +
            "President: Player 1\n" +
            "Vice President: Player 3\n" +
            "Middle Man: Player 2\n" +
            "Bum: Player 4\n" +
            "\n" +
            "Scores:\n" +
            "Player 1 = 3, Player 2 = 1, Player 3 = 2, Player 4 = 0\n" + "\n"

        println(expectedResult4)

        model.simulation_components.Menu.doMove // new feature (***** MOVE when the second to last player plays their last card in the first round *****)

        model.simulation_components.Menu.showGameArea should be(expectedResult4)
      }

      // ******* DO TURN *******
      it(
        "can DO TURN to perform DO MOVE four times, unless game has been won"
      ) {

        model.simulation_components.Menu.initialize
        val expectedResult1 =
          "Player Hands:  \n" +
            "Player 1 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 2, \n" +
            "Player 2 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 2, \n" +
            "Player 3 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 3, 2, \n" +
            "Player 4 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 2, \n" +
            "\n" +
            "Last Card in Trick: 4\n" +
            "\n" +
            "President: None\n" +
            "Vice President: None\n" +
            "Middle Man: None\n" +
            "Bum: None\n" +
            "\n" +
            "Scores:\n" +
            "Player 1 = 0, Player 2 = 0, Player 3 = 0, Player 4 = 0\n" + "\n"

        model.simulation_components.Menu.doTurn // Player 2 Plays a "4"

        model.simulation_components.Menu.showGameArea should be(expectedResult1)

        val expectedResult_turn2 =
          "Player Hands:  \n" +
            "Player 1 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 2, \n" +
            "Player 2 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 2, \n" +
            "Player 3 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 3, 2, \n" +
            "Player 4 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 4, 2, \n" +
            "\n" +
            "Last Card in Trick: 5\n" +
            "\n" +
            "President: None\n" +
            "Vice President: None\n" +
            "Middle Man: None\n" +
            "Bum: None\n" +
            "\n" +
            "Scores:\n" +
            "Player 1 = 0, Player 2 = 0, Player 3 = 0, Player 4 = 0\n" + "\n"

        model.simulation_components.Menu.doTurn // Player 4 plays a "5"
        model.simulation_components.Menu.showGameArea should be(expectedResult_turn2)

      }

      // ******* DO GAME *******
      it("can DO GAME to perform DO TURN until game is won") {

        model.simulation_components.Menu.initialize
        val expectedResult =
          "Player Hands:  \n" +
            "Player 1 Hand: \n" +
            "Player 2 Hand: 3 \n" +
            "Player 3 Hand: \n" +
            "Player 4 Hand: \n" +
            "\n" +
            "Last Card in Trick: 3\n" +
            "\n" +
            "President: Player 3\n" +
            "Vice President: Player 1\n" +
            "Middle Man: Player 2\n" +
            "Bum: Player 4\n" +
            "\n" +
            "Scores:\n" +
            "Player 1 = 10, Player 2 = 2, Player 3 = 10, Player 4 = 2\n" +
            "Player 3 Wins!" + "\n"

        model.simulation_components.Menu.doGame // Four rounds of play, with player 1 and player 3 switching off between president and vice president each round
        // Note: Player 3 Wins because he reached 10 points first, and he got out before player 1.
        model.simulation_components.Menu.showGameArea should be(expectedResult)

      }
    }
  }
}
