package model.simulation_components
import model.game_components.Card
import model.game_components.Trick
import scala.collection.mutable

object Menu {

  def showPlayerOrder: String = {
    PlayerOrder.show
  }

  def advancePlayerOrder: Unit = {
    PlayerOrder.advance
  }

  def showGameArea: String = {
    val sb = mutable.StringBuilder()
    sb ++= PlayerHands.show + "\n" + "\n"
    sb ++= Last_Card.show + "\n" + "\n"
    sb ++= Ranks.show + "\n"
    sb ++= Scoreboard.show + "\n"

    sb.toString

  }

  def initialize = {
    Trick.clearTrick
    PlayerOrder.reset
    Dealer.start
  }

  def checkForWinner: String = {
    Scoreboard.checkForWinner
  }

  def doMove: Boolean = {
    while !(PlayerOrder.current.inRound) do
      PlayerOrder.advance
    MoveDirector.doMove(PlayerOrder.current, PlayerOrder.count(_.inRound))
  }

  import scala.util.control.NonLocalReturns.*
  def doTurn: Boolean = returning {
    for i <- 1 to PlayerOrder.length do
      val doMovebool = doMove
      if doMovebool then throwReturn(true) // breaks on winner
    false
  }

  def doGame: Boolean = returning {
    for i <- 1 to 500 do
      if doMove then throwReturn(true)
    false
  }
}
