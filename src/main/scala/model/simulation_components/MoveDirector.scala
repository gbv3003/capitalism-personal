package model.simulation_components
import model.game_components.Player
import model.game_components.Trick
import model.game_components.Card

object MoveDirector {

  var skip_next: Boolean = false

  /**  Checks if the next player is skipped or not
  */
  def doMove (player: Player, playersRemaining: Int): Boolean = {
    if !(skip_next) then 
      val last_card = Trick.LastCard
        
      
      val card_played = player.chooseCardPlay()
      player.playCard(card_played)
      println(player.name +  " played " + card_played.name)
      if last_card.value == card_played.value then skip_next = true
    
    else 
      println(PlayerOrder.head.name)
      println("was Skipped!")
      skip_next = false
      
    PlayerOrder.advance
    true
  }

  def doMoveNoSkip (player: Player, playersRemaining: Int): Boolean = {      
    val card_played = player.chooseCardPlay()
    player.playCard(card_played)
    println(player.name +  " played " + card_played.name)        
    PlayerOrder.advance
    println(PlayerOrder.show)
    true
  }
}