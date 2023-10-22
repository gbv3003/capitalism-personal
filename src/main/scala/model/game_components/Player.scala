package model.game_components

/** A simulated player who plays capitalism
  *
  * @constructor
  *   create a new person with a name.
  * @param name
  *   the person's name
  */
class Player(val name: String) {
  var hand = new Hand
  var score = 0
  var temp_score = -1
  var inRound : Boolean = true
  var strategy : Strategy = new Default

  /** Shows the player's name and hand
    *
    */
  def show: String = {
    val sb = new StringBuilder()
    sb ++= name + " Hand: " + hand.show + "\n"
    sb.toString.substring(0, sb.toString.length - 2)
  }

  /** Plays a card in a players hand
    *
    * @param card
    *   the card to be played
    */
  def playCard(card_played: Card): Unit = {
    hand.remove(card_played)
    Trick.acceptCard(card_played)
  }

  def chooseCardPlay(): Card = {
    strategy.pickCard(hand)
  }

  /** Chooses a card to be traded to another player from a players hand. This
    * will always be the lowest non-two card in their hand.
    *
    * @return
    *   card the card choosen to be played
    */
  def chooseCardGive(): Card = {
    strategy.giveCard(hand)
  }

  /** Chooses a card that the player wants from the other player. This will
    * always be the highest card in the other players hand, with "two" cards
    * being the highest card.
    *
    * @return
    *   card the card choosen to be played
    */
  def chooseCardWant(opp_hand:Hand): Card ={
    strategy.wantCard(opp_hand)
  }

  /** Gives a card to another player from a players hand
    *
    * @param card_out
    *   the card to be given to another player
    * @param card_in
    *   the card to be gained from another player
    * @param player
    *   the player that receives the card
    */
  def tradeCard(player: Player, card_out: Card, card_in: Card): Unit = {
    hand.remove(card_out)
    player.hand.add(card_out)

    hand.add(card_in)
    player.hand.remove(card_in)

  }
}
