package model.simulation_components

import model.game_components.Card
import model.game_components.Player

/** A simulated Dealer who gives cards out to players
  *
  */
object Dealer {
  /** Begins the game by giving out a standard hand to each player
    *
    */
  def start = {
    val card1 = new Card("Ace",14)
    val card2 = new Card("King",13)
    val card3 = new Card("Queen",12)
    val card4 = new Card("Jack",11)
    val card5 = new Card("10",10)
    val card6 = new Card("9",9)
    val card7 = new Card("8",8)
    val card8 = new Card("7",7)
    val card9 = new Card("6",6)
    val card10 = new Card("5",5)
    val card11 = new Card("4",4)
    val card12 = new Card("3",3)
    val card13 = new Card("2",15)

    for iteration <- 1 until 5 do
      PlayerOrder.current.hand.add(card1)
      PlayerOrder.current.hand.add(card2)
      PlayerOrder.current.hand.add(card3)
      PlayerOrder.current.hand.add(card4)
      PlayerOrder.current.hand.add(card5)
      PlayerOrder.current.hand.add(card6)
      PlayerOrder.current.hand.add(card7)
      PlayerOrder.current.hand.add(card8)
      PlayerOrder.current.hand.add(card9)
      PlayerOrder.current.hand.add(card10)
      PlayerOrder.current.hand.add(card11)
      PlayerOrder.current.hand.add(card12)
      PlayerOrder.current.hand.add(card13)

      PlayerOrder.advance

  }
}
