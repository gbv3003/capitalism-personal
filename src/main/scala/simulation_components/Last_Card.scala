package simulation_components

import game_components.Card
import game_components.Player

object Last_Card {
  def show : String = {
    val sb = new StringBuilder()
    sb ++= "Last Card in Trick: " +  PlayerOrder.current.hand.discard_show + " \n"
    sb.toString.substring(0, sb.toString.length - 2)
    
  }
}