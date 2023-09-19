package game_components

//******* Card *******
/** A card from a deck with values 1-10 and J-A, of suits Clubs, Spades, Hearts, and Diamonds
  *
  * @constructor
  *   create a new card of a certain value
  * @param value
  *   the card's value
  */
class Card(val value: String) {
  /** Shows the card's value
    *
    */
  def show: String = {
    val sb = new StringBuilder("")
    sb ++= value
    sb.toString.substring(0, sb.toString.length)
  }
}
