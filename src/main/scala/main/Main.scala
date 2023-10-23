package _main

import model._
import view._
import controller._

/**  Game simulation for Capitalism, a four-player card game
  */
object Capitalism {
  
  @main def main(): Unit = {
      
    val model = new Model
    val view  = new SimpleView   
    val controller = new Controller(model, view)
    
    view.init(controller)    
  }
  
}