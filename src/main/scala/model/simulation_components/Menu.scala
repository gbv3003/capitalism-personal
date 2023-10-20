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
    Trick.ClearTrick
    PlayerOrder.reset
    Dealer.start
  }

  def checkForWinner: String = {
    Scoreboard.checkForWinner
  }

  def doMove: Boolean = {
    return MoveDirector.doMoveNoSkip(PlayerOrder.current, PlayerOrder.length)
  }

  import scala.util.control.NonLocalReturns.*
  def doTurn: Boolean = returning {
    for i <- 1 to PlayerOrder.length do
      if doMove then throwReturn(true) // breaks on winner
    false
  }

  def doGame: Boolean = returning {
    for i <- 1 to 400 do
      if doMove then throwReturn(true)
    false
  }
}
