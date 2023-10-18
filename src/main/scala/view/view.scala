package main

import scala.swing._

class View extends MainFrame {

  var _controller: Option[Controller] = None

  // Components
  import java.awt.Font
  object textArea extends TextArea {
    font = new Font ("Monospaced", Font.BOLD , 12)
  }

  /** View.init
    * @param controller
    */
  def init(controller: Controller): Unit = {
    _controller = Some(controller)
    title = "My GUI"
    contents = textArea
    centerOnScreen()

    menuBar = new MenuBar {
      contents += new Menu("Milestone 1") {
        contents += new MenuItem(controller.Show_Game_Area)
        contents += new MenuItem("Advance Player Order")
        contents += new MenuItem("Show Player Order")
        contents += new Separator
        contents += new MenuItem(controller.exit) // end Exit menuItem
      } // end Menu 1
    } // end MenuBar

    size = new Dimension(500, 500)
    visible = true
  }

}
