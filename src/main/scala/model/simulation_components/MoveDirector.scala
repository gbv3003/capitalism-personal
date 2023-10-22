package model.simulation_components
import model.game_components.Player
import model.game_components.Trick
import model.game_components.Card
import scala.collection.mutable.ArrayBuffer

object MoveDirector {

  var skip_next: Boolean = false
  var bomb_played: Boolean = false
  var move_counter: Int = 0
  var playerOutOrder: ArrayBuffer[Player] = ArrayBuffer()

  /**  Checks if the next player is skipped or not
  */
  def doMove (player: Player, playersRemaining: Int): Boolean = {
    move_counter += 1

    if playersRemaining == 1 then 
      playerOutOrder += player
      startNewRound()

    else if !(skip_next) then  
      if bomb_played then 
        Trick.clearTrick
        bomb_played = false

      val last_card = Trick.lastCard
      try
      {
      val card_played = player.chooseCardPlay()
      player.playCard(card_played)
      if player.hand.isEmpty then
        player.inRound = false
        playerOutOrder += player
        

      if last_card.value == card_played.value then skip_next = true
      if card_played.value == 15 then 
        for i <- 1 to PlayerOrder.length - 1  do 
        PlayerOrder.advance
        bomb_played = true
      }
      catch
      {
      case e: Exception => None
      }
      
    else if skip_next then 
      skip_next = false
    
    PlayerOrder.advance
    false
  }

  def startNewRound() = {
    val pointsDistribution = Array(3, 2, 1, 0)
    for i <- 0 until playerOutOrder.length do 
      val player = playerOutOrder(i)
      player.hand.clear()
      player.inRound = true
      player.temp_score = pointsDistribution(i)
      player.score = player.score + pointsDistribution(i)

    val president = playerOutOrder(0)
    val vicePresident = playerOutOrder(1)
    val middleMan = playerOutOrder(2)
    val bum = playerOutOrder(3)

    Trick.clearTrick
    Dealer.start

    president.tradeCard(bum,president.chooseCardGive(),president.chooseCardWant(bum.hand))
    president.tradeCard(bum,president.chooseCardGive(),president.chooseCardWant(bum.hand))
    
    vicePresident.tradeCard(middleMan,vicePresident.chooseCardGive(),vicePresident.chooseCardWant(middleMan.hand))

    playerOutOrder = ArrayBuffer()

  }

}