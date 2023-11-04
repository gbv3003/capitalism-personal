package model.game_components
import model.game_components.Trick
import model.simulation_components.PlayerOrder
import scala.collection.View.Empty

/**  Players have a Strategy that enables move-related decisions.  All Strategies must support operations
 * to decide the initial move, pick an empty cell during the free play round, and pick a move options during 
 * normal play.
*/
trait Strategy {
  val name : String
  def pickCard(player_hand:Hand): (Option[Card])
  /** Chooses their lowest card to give to a player
    *
    */
  def giveCard (player_hand:Hand): Card ={
    player_hand.minBy(_.value)
  }
  /** Chooses a players highest card to get
    *
    */
  def wantCard (trading_player_hand:Hand): Card ={
    trading_player_hand.maxBy(_.value)
  }
}

import scala.language.postfixOps

/**  The default player behavior that all players begin the simulation with.
*/
class Default extends Strategy {
  val name = "Default/Lowest Card First"
  def pickCard (player_hand:Hand): Option[Card] ={

    val validCards = player_hand.filter(card => card.value >= Trick.lastCard.value)

    if validCards.nonEmpty then
      Some(validCards.minBy(_.value))
    else None

  }
}

class HighestCardFirst extends Strategy {
  val name = "Highest Card First"
  def pickCard (player_hand:Hand): Option[Card] = {

    // if lowerValueCards.isEmpty then
    //   cardsPlayable.maxBy(_.value)
    // else lowerValueCards.maxBy(_.value)
    
    
    val validCards = player_hand.filter(card => card.value >= Trick.lastCard.value)
    val nonBombCards = validCards.filter(card => card.value < 15)

    if nonBombCards.nonEmpty then
      Some(nonBombCards.maxBy(_.value))
    else if validCards.nonEmpty then 
      Some(validCards.maxBy(_.value))
    else None

}

}

class BombFirstThenLow extends Strategy {
  val name = "Bomb First, then Lowest-Card First"
  def pickCard (player_hand:Hand): Option[Card] = {    
    val bombCard = player_hand.find(_.value == 15)
    val validCards = player_hand.filter(card => card.value >= Trick.lastCard.value)

    if bombCard.isDefined then 
      Some(bombCard.get)
    else if validCards.nonEmpty then
      Some(validCards.minBy(_.value))
    else None
  }
}

class MaximizeSkipping extends Strategy {
  val name = "Maximize Skipping"
  def pickCard (player_hand:Hand): Option[Card] = {
    val skipCard = player_hand.find(_.value == Trick.lastCard.value)
    if skipCard.isDefined then 
      Some(skipCard.get)
    else None
   
  }
}
