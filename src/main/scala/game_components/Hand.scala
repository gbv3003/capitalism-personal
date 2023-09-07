package game_components

import scala.compiletime.ops.string
import scala.collection.mutable.ArrayBuffer

class Hand {
  var hand = ArrayBuffer[Card]()
  var discard_pile = ArrayBuffer[Card]()

  def add(new_card:Card) = {
    hand += new_card 
  }
  def play(played_card:Card) = {
    discard_pile += played_card
    hand -= played_card
  }
  def show: String = {
    //iterate through hand, build string and return string
    val sb = new StringBuilder()
    for card <- hand 
        do sb ++= card.show + ", " 
    sb.toString.substring(0, sb.toString.length)
  }
  def discard_show: String = {
    //iterate through hand, build string and return string
    val sb = new StringBuilder()
    for card <- discard_pile
        do sb ++= card.show
    sb.toString.substring(0, sb.toString.length)
  }
}