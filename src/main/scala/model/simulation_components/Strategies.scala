package model.simulation_components

/** A simulation component that displays each player's hand
  *
  */
object Strategies {
  /** Shows each player's strategies
    *
    */
  def show: String = {
    val sb = new StringBuilder()
    sb ++= "Player Strategies:" + "\n"
    for p <- PlayerOrder.toArray.sortBy(_.name) do 
      sb ++= p.name + ": " + p.strategy.name + "\n"
    sb.toString.substring(0, sb.toString.length - 1)

  }
}
