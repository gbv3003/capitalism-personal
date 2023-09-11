package simulation_components

object PlayerHands {
  def show : String = {
    val sb = new StringBuilder()
    sb ++= "Player Hands: " + " \n"
    for p <- PlayerOrder.toArray
      do sb ++= p.show + " \n"
    sb.toString.substring(0, sb.toString.length-2)
    
  }
}
