package model.simulation_components

import model.game_components.Card
import model.game_components.Player
import scala.collection.mutable.ArrayBuffer
import scala.util.Random


/** A simulated Dealer who gives cards out to players
  *
  */
object Dealer {
  /** Begins the game by giving out a standard hand to each player
    *
    */
  var random: Boolean = false
  def start = {
    val suits : ArrayBuffer[String] = ArrayBuffer("h","c","s","d")
    val names : ArrayBuffer[String] = ArrayBuffer("2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace").reverse
    val values : ArrayBuffer[Int] = ArrayBuffer(15,3,4,5,6,7,8,9,10,11,12,13,14).reverse
    var deck :  ArrayBuffer[Card] = ArrayBuffer().empty
    for i <- 0 until 4 do
        for j <- 0 until 13 do 
          deck += new Card(names(j),values(j),suits(i))

    val numCardsperHand: Int = (deck.length/PlayerOrder.length).toInt

    if random then 
      for i <- 0 until PlayerOrder.length do
        for j <- 0 until numCardsperHand do 
          val randomIndex = Random.nextInt(deck.length)
          val card = deck.remove(randomIndex)
          PlayerOrder.current.hand.add(card)
        PlayerOrder.advance

    else
      for i <- 0 until PlayerOrder.length do
        for card <- deck.take(numCardsperHand) do 
          PlayerOrder.current.hand.add(card)
        deck = deck.drop(numCardsperHand)
        PlayerOrder.advance
      

  }
}
