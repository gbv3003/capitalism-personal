package model.simulation_components
import model.game_components.Player

object MoveDirector {
  var freePlayMoves = 0

  def reset: Unit = {
    freePlayMoves = 0
  }

  /**  Check whether this move is occuring in the round of moves after a player elimination and decrement counter as appropriate, 
   * then prompt player for right kind of move.  When player cannot make a move, remove them from the game and start a "free play round."
  */
  def doMove (player: Player, playersRemaining: Int): Unit = {
    println("you got here")
  }

}