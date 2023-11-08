package model.simulation_components
import model.game_components.Player
import model.game_components.Trick
import model.game_components.Card
import scala.collection.mutable.ArrayBuffer
/** A simulated component that moves players
    *
    */
object MoveDirector {
  var skip_next: Boolean = false
  var bomb_played: Boolean = false
  var move_counter: Int = 0
  var playersInRound: Array[Player] = PlayerOrder.toArray
  var playerOutOrder: ArrayBuffer[Player] = ArrayBuffer().empty

  def reset =
    skip_next = false
    bomb_played = false
    move_counter = 0
    playerOutOrder = ArrayBuffer().empty

  /**  Checks if the next player is skipped or not
  */
  def doMove (player: Player, playersRemaining: Int): Boolean = {
    move_counter += 1
    var return_bool = false

    var playable_cards: ArrayBuffer[Card] = ArrayBuffer().empty
    for p <- PlayerOrder.toArray do
      p.chooseCardPlay() match
        case Some(card) => playable_cards += card
        case None => None

    if playable_cards.isEmpty && playersRemaining>1 then 
      Trick.acceptCard(new Card("",0,""))


    else if playersRemaining == 1 then 
      playerOutOrder += player
      return_bool = startNewRound()

    else if !(skip_next) then  
      if bomb_played then 
        Trick.clearTrick
        bomb_played = false

      val last_card = Trick.lastCard
      val card_played = player.chooseCardPlay()
      card_played match
        case Some(card) =>
          player.playCard(card)
          if player.hand.isEmpty then
            player.inRound = false
            playerOutOrder += player
            

          if last_card.value == card.value then 
            skip_next = true

          if card.value == 15 then 
            for i <- 1 to PlayerOrder.length - 1  do PlayerOrder.advance
            bomb_played = true
            if player.hand.isEmpty then Trick.acceptCard(new Card("",0,""))

        case None => 
          None
      
    else if skip_next then 
      skip_next = false

    
    PlayerOrder.advance
    return_bool
  }
  /** Starts a new round
  *
  */
  def startNewRound(): Boolean = {
    val pointsDistribution = Array(3, 2, 1, 0)
    for i <- 0 until playerOutOrder.length do 
      val player = playerOutOrder(i)
      player.hand.clear()
      player.inRound = true
      player.score = player.score + pointsDistribution(i)
      if player.score >= 10 then 
        playerOutOrder = ArrayBuffer().empty
        return true
      player.temp_score = pointsDistribution(i)



    val president = playerOutOrder(0)
    val vicePresident = playerOutOrder(1)
    val middleMan = playerOutOrder(2)
    val bum = playerOutOrder(3)

    Trick.clearTrick
    Dealer.start

    president.tradeCard(bum,president.chooseCardGive(),president.chooseCardWant(bum.hand))
    president.tradeCard(bum,president.chooseCardGive(),president.chooseCardWant(bum.hand))
    
    vicePresident.tradeCard(middleMan,vicePresident.chooseCardGive(),vicePresident.chooseCardWant(middleMan.hand))

    playerOutOrder = ArrayBuffer().empty
    false

  }

}