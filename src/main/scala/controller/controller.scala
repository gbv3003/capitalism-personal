package main

import scala.swing._
import model.Model

class Controller(view: View, model: Model) {

  def hello = Action("Hello") {
    view.textArea.text = "Show area"
  }

  def Show_Game_Area = Action("Show Game Area"){
    view.textArea.text = "Show area"
  }
        

  def exit = Action("Exit") {
    sys.exit(0)
  }
}
