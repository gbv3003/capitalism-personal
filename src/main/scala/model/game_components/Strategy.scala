package model.game_components
import model.game_components.Trick
import model.simulation_components.PlayerOrder

/**  Players have a Strategy that enables move-related decisions.  All Strategies must support operations
 * to decide the initial move, pick an empty cell during the free play round, and pick a move options during 
 * normal play.
*/
trait Strategy {
  def pickCard(player_hand:Hand): (Card)
}

import scala.language.postfixOps

/**  The default player behavior that all players begin the simulation with.
*/
class Default extends Strategy {
  def pickCard (player_hand:Hand): Card ={
    player_hand.minBy(_.value)
  }
}
