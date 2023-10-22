package model.game_components

import scala.collection.mutable.ArrayBuffer
import model.simulation_components.PlayerOrder

/** A hand of cards that a player possesses
  *
  * @constructor
  *   create a new hand of cards.
  */

class Hand extends scala.collection.mutable.ArrayBuffer[Card]{

  /** Adds a new card to a player's hand
    * 
    * @param new_card
    *   the new card to be put in someone's hand
    */
  def add(new_card: Card) = {
    this += new_card
  }

  def remove(played_card: Card) = {
    this -= played_card
  }
  /** Shows the hand of a certain player
    *
    */
  def show: String = {
    // iterate through hand, build string and return string
    val sb = new StringBuilder()
    for card <- this
    do sb ++= card.show + ", "
    sb.toString.substring(0, sb.toString.length)
  }

}
