package controller

import model._
import scala.swing._
import view._
import model.simulation_components.Dealer
import scala.collection.mutable


/**  Controller for an MVC architecture
  *  @param model Model for the MVC architecture 
  *  @param view View for MVC architecture
  */
class Controller(model: Model, view: View) {

  /**  Displays everything necessary to observe and understand the progress of the game
  */
  def showGameArea: String = {
    model.menu.showGameArea
  }

  /**  shows player names in the order they will play
  */
  def showPlayerOrder: String = {
    model.menu.showPlayerOrder
  }

  def changeStrategy(player:game_components.Player, strategy:game_components.Strategy): Unit = {
    player.setStrategy(strategy)
  }

  def random_toggle= Action("Shuffling?") {
    if Dealer.random then Dealer.random = false
    else Dealer.random = true
  }

  /**   moves the player who is currently "up" to the end of the playing order, and advances the "next" player to be "up"
  */
  def advanceOrder = Action("Advance") {
    model.menu.advancePlayerOrder
    view.update_PlayerOrder
  }

  /**  performs all actions necessary to prepare the game for the first move; can also be used to reset the game simulation
  */
  def initialize = Action("Initialize") {
    model.menu.initialize
    view.update_PlayerOrder
    view.update_GameArea
    view.update_Strategies
  }

  /**  at any point in the game simulation, determine whether any player has won the game, and return the winning player's name or "none"
  */
  def checkForWinner = Action("Winner?") {    
    view.showWinner(model.menu.checkForWinner)
  }

  def showgameText: String = {
    val sb = mutable.StringBuilder()
    sb ++= simulation_components.Ranks.show + "\n"
    sb ++= simulation_components.Scoreboard.show + "\n"

    sb.toString

  }

  /**  the player who is currently "up" in the player order performs all move actions, and the player order advances
  */
  def doMove = Action("Do Move") {
    val result = model.menu.doMove
    view.update_PlayerOrder
    view.update_GameArea
    if result then view.showWinner(model.menu.checkForWinner)     
  }
  
  /**  performs DO MOVE four times (or more specfically, the length of the player order), as well as CHECK FOR WINNER at appropriate times
  */
  def doTurn = Action("Do Turn") {    
    val result = model.menu.doTurn
    view.update_PlayerOrder
    view.update_GameArea
    if result then view.showWinner(model.menu.checkForWinner)   
  }

  def randomness = Action("Create Random"){
  
  }
  
  /**  performs DO TURN until the game is won
  */
  def doGame = Action("Do Game") {
    val result = model.menu.doGame
    view.update_PlayerOrder
    view.update_GameArea
    if result then view.showWinner(model.menu.checkForWinner)   
  }

  /**  terminates the application
  */
  def exit = Action("Exit") {
    sys.exit(0)
  }

}