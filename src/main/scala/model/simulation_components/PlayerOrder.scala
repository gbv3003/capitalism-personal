package model.simulation_components

import model.game_components.Player

//******* PLAYER_ORDER *******
/** A simulation component which creates a queue of all four players
  *
  */
object PlayerOrder extends scala.collection.mutable.Queue[Player] {

  reset

  def reset: Unit = {
    this.clear
    this += new Player("Player 1")
    this += new Player("Player 2")
    this += new Player("Player 3")
    this += new Player("Player 4")
  }
  

  /** Advances the player order
    *
    */
  def advance: Unit = {
    this += this.dequeue
  }
  /** Shows the entire player order
    *
    */
  def show: String = {
    val sb = new StringBuilder("")
    for p <- this.toArray yield sb ++= p.name + ", "
    sb.toString.substring(0, sb.toString.length - 2)
  }
  /** Returns the name of current player
    * 
    * @return 
    *   name of current player
    */
  def current: Player = {
    return this.head
  }

}
