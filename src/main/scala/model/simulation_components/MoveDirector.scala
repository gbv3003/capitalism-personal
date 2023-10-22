package model.simulation_components
import model.game_components.Player
import model.game_components.Trick
import model.game_components.Card

object MoveDirector {

  var skip_next: Boolean = false
  var bomb_played: Boolean = false
  var move_counter: Int = 0

  /**  Checks if the next player is skipped or not
  */
  def doMove (player: Player, playersRemaining: Int): Boolean = {
    move_counter += 1
    
    if !(skip_next) then  
      if bomb_played then 
        Trick.clearTrick
        bomb_played = false

      val last_card = Trick.lastCard
      
      val card_played = player.chooseCardPlay()
      player.playCard(card_played)
      
      if player.hand.isEmpty then 
        println(Menu.showGameArea)
        println("END OF PLAY, player out turn # " + move_counter)

      if last_card.value == card_played.value then skip_next = true
      if card_played.value == 15 then 
        for i <- 1 to PlayerOrder.length - 1  do 
        PlayerOrder.advance
        bomb_played = true
        

    
    else if skip_next then 
      skip_next = false
    
    PlayerOrder.advance
    false
  }

}