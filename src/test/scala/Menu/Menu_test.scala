package Menu

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should._

class Menu_test extends AnyFunSpec with Matchers {
   describe("The Capitalism Simulation") {
    describe("has a menu") {

      //******* SHOW PLAYER ORDER *******
      it("can show the player order") {     
        val expectedResult = "Player 1, Player 2, Player 3, Player 4"
        Menu_Display.showPlayerOrder() should be(expectedResult)  
      }

      //******* ADVANCE ORDER *******
      it("can advance the player order") {     
        val expectedResult = "Player 2, Player 3, Player 4, Player 1"
        Menu_Display.advancePlayerOrder() should be(expectedResult)  
      }

      //******* SHOW GAME AREA *******
      it("can show the game area, including the cards in each player's hand, the previous card that was played in the trick, the current rank of each player, and the total scores of each player. ") {

        val expectedResult = 
          "Player Hands: \n" +
          "Player 1 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 3, 2\n"+
          "Player 2 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 3, 2\n"+
          "Player 3 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 3, 2\n"+
          "Player 4 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 3, 2\n"+
          "\n" +
          "Last Card in Trick: \n"+
          "\n" +
          "President: None\n" +
          "Vice President: None\n" +
          "Middle Man: None\n" +
          "Bum: None\n" +
          "\n"+
          "Scores:\n" +
          "Player 1 = 0, Player 2 = 0, Player 3 = 0, Player 4 = 0\n" 
            
        Menu_Display.showGameArea() should be(expectedResult)
      }  
    }
  }
}