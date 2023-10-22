package view

import scala.swing._
import controller._

/**  Provides a basic Scala Swing GUI with buttons for game control commands and
 * textAreas for the PlayerOrder and GameArea.
  */
class SimpleView extends MainFrame with View {

  // Components

  // Player Order Panel
  object playerOrder extends TextArea
  object advancePO_Btn extends Button(">> Advance")
  object playerOrderPanel extends FlowPanel {
    contents += new Label("Player Order:")
    contents += playerOrder
    contents += advancePO_Btn
  }

  // Game control buttons Panel
  object initialize_Btn extends Button("Initialize")
  object checkWinner_Btn extends Button("Winner?")
  object doMove_Btn extends Button("Do Move")
  object doTurn_Btn extends Button("Do Turn")
  object doGame_Btn extends Button("Do Game")
  object gameControlsPanel extends BoxPanel(Orientation.Vertical) {
    contents ++= Seq(initialize_Btn, checkWinner_Btn, doMove_Btn, doTurn_Btn, doGame_Btn)
    border = Swing.EmptyBorder(10, 10, 10, 10)
  }

  import java.awt.Font
  object gameText extends TextArea {
    font = new Font ("Monospaced", Font.BOLD , 12)
  }
  object gameAreaPanel extends ScrollPane(gameText)

 
  object strategyPanel extends GridPanel(5, 5) {
    contents += new Label("Strategies")
    contents += new Label("All players use the default strategy")
  }

  // MainFrame contents panel
  import BorderPanel.Position._
  object borderPanel extends BorderPanel {
    layout += playerOrderPanel -> North
    layout += gameControlsPanel -> West
    layout += gameAreaPanel -> Center
    layout += strategyPanel -> South
  }
  
  title = "My GUI"
  contents = borderPanel
  centerOnScreen()

  size = new Dimension(600, 700)

  /** Prepare this View class for initial use by invoking the superclass init 
   * to store the reference to the controller, hook-up triggers to controller methods, and 
   * set visible.
   *  @param controller The MVC Controller
   */
  override def init(controller: Controller): Unit = {
    super.init(controller)

    update_PlayerOrder
    update_GameArea
    
    advancePO_Btn.action = controller.advanceOrder
    initialize_Btn.action = controller.initialize
    checkWinner_Btn.action = controller.checkForWinner
    doMove_Btn.action = controller.doMove
    doTurn_Btn.action = controller.doTurn
    doGame_Btn.action = controller.doGame
    
    visible = true
  }
  
  def update_PlayerOrder: Unit = {
    playerOrder.text = controller.get.showPlayerOrder
  }
  
  def update_GameArea: Unit = {
    gameText.text = controller.get.showGameArea
  }
  
  def showWinner(result: String): Unit = {
    Dialog.showMessage(this, result, title="And the winner is...")    
  }

}