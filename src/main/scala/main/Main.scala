package main

import model._

object GUI {
  
  @main def main(): Unit = {
      
    val model = new Model
    val view  = new View   
    val controller = new Controller(view, model)
    
    view.init(controller)    
  }
  
}
