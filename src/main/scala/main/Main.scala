package _main

import model._
import view._
import controller._

/**  Game simulation for the GridMaster4 game, a turn-based, 4-player game.
  */
object GridMaster4 {
  
  @main def main(): Unit = {
      
    val model = new Model
    val view  = new SimpleView   
    val controller = new Controller(model, view)
    
    view.init(controller)    
  }
  
}