package simulation_components
import game_components.Card
import scala.collection.mutable

object Menu {

  def showPlayerOrder: String = {
    return PlayerOrder.show
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

}
