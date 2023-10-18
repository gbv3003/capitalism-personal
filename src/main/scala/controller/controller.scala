package main

import scala.swing._

class Controller(view: View, model: Model) {

  def hello = Action("Hello") {
    view.textArea.text = model.getMessage
  }

  def Show_Game_Area = Action("Show Game Area"){
    view.textArea.text = model.getArea
  }
        

  def exit = Action("Exit") {
    sys.exit(0)
  }
}
