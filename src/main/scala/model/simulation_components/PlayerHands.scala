package model.simulation_components

/** A simulation component that displays each player's hand
  *
  */
object PlayerHands {
  /** Shows each player's hand
    *
    * @constructor 
    *   creates a hand
    */
  def show: String = {
    val sb = new StringBuilder()
    sb ++= "Player Hands: " + " \n"
    for p <- PlayerOrder.toArray.sortBy(_.name) do 
      sb ++= p.show + " \n"
    sb.toString.substring(0, sb.toString.length - 1)
  }
}
