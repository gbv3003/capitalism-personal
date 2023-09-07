package simulation_components

import game_components.Player

//******* PLAYER_ORDER *******
object PlayerOrder extends scala.collection.mutable.Queue[Player] {

  this += new Player("Player 1")
  this += new Player("Player 2")
  this += new Player("Player 3")
  this += new Player("Player 4")

  def advance: Unit = {
    this += this.dequeue
  }

  def show: String = {
    val sb = new StringBuilder("")
    for p <- this.toArray yield sb ++= p.name + ", "
    sb.toString.substring(0, sb.toString.length - 2)
  }

  def current: Player = {
    return this.head
  }
}