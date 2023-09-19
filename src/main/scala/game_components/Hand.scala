package game_components

import scala.compiletime.ops.string
import scala.collection.mutable.ArrayBuffer

/** A hand of cards that a player possesses
  *
  * @constructor
  *   create a new hand of cards.
  */

class Hand {
  var hand = ArrayBuffer[Card]()
  var discard_pile = ArrayBuffer[Card]()
  /** Adds a new card to a player's hand
    * 
    * @param new_card
    *   the new card to be put in someone's hand
    */
  def add(new_card: Card) = {
    hand += new_card
  }

  def play(played_card: Card) = {
    discard_pile += played_card
    hand -= played_card
  }
  /** Shows the hand of a certain player
    *
    */
  def show: String = {
    // iterate through hand, build string and return string
    val sb = new StringBuilder()
    for card <- hand
    do sb ++= card.show + ", "
    sb.toString.substring(0, sb.toString.length)
  }
  def discard_show: String = {
    // iterate through hand, build string and return string
    val sb = new StringBuilder()
    for card <- discard_pile
    do sb ++= card.show
    sb.toString.substring(0, sb.toString.length)
  }
}
