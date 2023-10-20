package model.simulation_components

import model.game_components.Card
import model.game_components.Trick
import model.game_components.Player
/** A simulation component that contains the last played card in the trick
  *
  */
object Last_Card {
  /** Shows the last played card
    *
    */
  def show: String = {
    val sb = new StringBuilder()
    if Trick.LastCard.value != 0 then 
      sb ++= "Last Card in Trick: " + Trick.LastCard.name + " \n"
      sb.toString.substring(0, sb.toString.length - 2)
    else     
      sb ++= "Last Card in Trick:  \n"
      sb.toString.substring(0, sb.toString.length - 2)


  }
}
