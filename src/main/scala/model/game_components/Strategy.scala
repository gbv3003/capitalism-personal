package model.game_components
import model.game_components.Trick
import model.simulation_components.PlayerOrder

/**  Players have a Strategy that enables move-related decisions.  All Strategies must support operations
 * to decide the initial move, pick an empty cell during the free play round, and pick a move options during 
 * normal play.
*/
trait Strategy {
  val name : String
  def pickCard(player_hand:Hand): (Card)
  def giveCard(player_hand:Hand): (Card)
  def wantCard(player_hand:Hand): (Card)
}

import scala.language.postfixOps

/**  The default player behavior that all players begin the simulation with.
*/
class Default extends Strategy {
  val name = "Default/Lowest Card First"
  def pickCard (player_hand:Hand): Card ={
    player_hand.filter(card => card.value >= Trick.lastCard.value).minBy(_.value)
  }
  /** Gives a card to a player
    *
    */
  def giveCard (player_hand:Hand): Card ={
    player_hand.minBy(_.value)
  }
  /** Determines what card a player wants based on their strategy
    *
    */
  def wantCard (trading_player_hand:Hand): Card ={
    trading_player_hand.maxBy(_.value)
  }

}

class HighestCardFirst extends Strategy {
  val name = "Highest Card First"
  def pickCard (player_hand:Hand): Card = {
    new Card("",0,"")
  }
  /** Gives a card to a player
    *
    */
  def giveCard (player_hand:Hand): Card ={
    new Card("",0,"")
  
  }
  /** Determines what card a player wants based on their strategy
    *
    */
  def wantCard (trading_player_hand:Hand): Card ={
    new Card("",0,"")
  }

}

class BombFirstThenLow extends Strategy {
  val name = "Bomb First, then Lowest-Card First"
  def pickCard (player_hand:Hand): Card = {
    new Card("",0,"")
  }
  /** Gives a card to a player
    *
    */
  def giveCard (player_hand:Hand): Card ={
    new Card("",0,"")
  
  }
  /** Determines what card a player wants based on their strategy
    *
    */
  def wantCard (trading_player_hand:Hand): Card ={
    new Card("",0,"")
  }

}

class MaximizeSkipping extends Strategy {
  val name = "Maximize Skipping"
  def pickCard (player_hand:Hand): Card = {
    new Card("",0,"")
  }
  /** Gives a card to a player
    *
    */
  def giveCard (player_hand:Hand): Card ={
    new Card("",0,"")
  
  }
  /** Determines what card a player wants based on their strategy
    *
    */
  def wantCard (trading_player_hand:Hand): Card ={
    new Card("",0,"")
  }

}
