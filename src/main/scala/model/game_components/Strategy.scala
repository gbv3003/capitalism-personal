package model.game_components
import model.game_components.Trick

/**  Players have a Strategy that enables move-related decisions.  All Strategies must support operations
 * to decide the initial move, pick an empty cell during the free play round, and pick a move options during 
 * normal play.
*/
trait Strategy {

}


import scala.util.control.NonLocalReturns.*
/**  The default player behavior that all players begin the simulation with.
*/
class Default extends Strategy {

  def pickCard (player_hand:Hand): Card ={
    for i <- player_hand do
        println("Doing strat, lol")
    player_hand.head
  }
}
