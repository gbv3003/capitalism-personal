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
    Dealer.start
    val sb = mutable.StringBuilder()
    sb ++= PlayerHands.show + "\n" + "\n"
    sb ++= Last_Card.show + "\n" + "\n"
    sb ++= Ranks.show + "\n"
    sb ++= Scoreboard.show + "\n"

    sb.toString
  }

  def initialize = {
    Trick.clear
    PlayerOrder.reset
    MoveDirector.reset
  }

  def checkForWinner: String = {
    Scoreboard.checkForWinner
  }

  def doMove: Unit = {
  }

  def doTurn: Unit = {
  }

  def doGame: Unit = {
  }
}
