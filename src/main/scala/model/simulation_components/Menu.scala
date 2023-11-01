package model.simulation_components
import model.game_components.Card
import model.game_components.Trick
import scala.collection.mutable
/** A simulated component that displays all items neccesary for the menu
    *
    */
object Menu {

  /** Shows the current player order
    *
    */
  def showPlayerOrder: String = {
    PlayerOrder.show
  }
  /** Advances the player order by one player
    *
    */
  def advancePlayerOrder: Unit = {
    PlayerOrder.advance
  }

  def showStrategies: String = {
    "To be completed"
  }
  /** Shows the players' hands, their ranks, the last card that was played, and the scoreboard
    *
    */
  def showGameArea: String = {
    val sb = mutable.StringBuilder()
    sb ++= PlayerHands.show + "\n" + "\n"
    sb ++= Last_Card.show + "\n" + "\n"
    sb ++= Ranks.show + "\n"
    sb ++= Scoreboard.show + "\n"

    sb.toString

  }
  /** Resets the trick, player order, and the players' hands
    *
    */
  def initialize = {
    Trick.clearTrick
    PlayerOrder.reset
    Dealer.start
  }
  /** Checks to see if any player has won
    *
    */
  def checkForWinner: String = {
    Scoreboard.checkForWinner
  }
  /** Simulates one player move
    *
    */
  def doMove: Boolean = {
    while !(PlayerOrder.current.inRound) do
      PlayerOrder.advance
    MoveDirector.doMove(PlayerOrder.current, PlayerOrder.count(_.inRound))
  }

  import scala.util.control.NonLocalReturns.*
  /** Simulates four player moves
    *
    */
  def doTurn: Boolean = returning {
    for i <- 1 to PlayerOrder.length  do
      if doMove then throwReturn(true) // breaks on winner
    false
  }
  /** Simulates the whole game until a player has won
    *
    */
  def doGame: Boolean = returning {
    {
    for i <- 1 to 500 do
      if doMove then throwReturn(true)
    false
  }
  }
}
