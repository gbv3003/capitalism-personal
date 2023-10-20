package model.simulation_components

import model.game_components.Card
import model.game_components.Player
import scala.collection.mutable.ArrayBuffer


/** A simulated Dealer who gives cards out to players
  *
  */
object Dealer {
  /** Begins the game by giving out a standard hand to each player
    *
    */
  def start = {
    val suits : ArrayBuffer[String] = ArrayBuffer("hearts","clubs","spades","diamonds")
    val names : ArrayBuffer[String] = ArrayBuffer("2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace").reverse
    val values : ArrayBuffer[Int] = ArrayBuffer(15,3,4,5,6,7,8,9,10,11,12,13,14).reverse

    for i <- 0 until 4 do
      for j <- 0 until 13 do 
         PlayerOrder.current.hand.add(new Card(names(j),values(j),suits(i)))
      PlayerOrder.advance

  }
}
