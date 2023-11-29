package view

import scala.swing._
import scala.swing.event._
import controller._
import model.simulation_components.PlayerOrder
import model.game_components.Default
import model.game_components.HighestCardFirst
import model.game_components.BombFirstThenLow
import model.game_components.MaximizeSkipping
import model.game_components.Strategy
import BorderPanel.Position._
import java.awt.geom.Rectangle2D
import java.awt.geom.Ellipse2D
import java.awt.Color
import scala.collection.mutable.ArrayBuffer
import java.awt.image.BufferedImage
import scala.swing.Orientation


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
  var flippedCard = new CardPanel
  val hiddenDeck = new HiddenCardPanel
  val deckSpaces = new BoxPanel(Orientation.Horizontal) {
    //contents += flippedCard   
    background = Color.green
    contents += hiddenDeck
    background = Color.green
    preferredSize = new Dimension(254,198)
  }
  import java.awt.Font
  object gameText extends TextArea {
    font = new Font ("Monospaced", Font.BOLD , 12)
  }
  object gameAreaPanel extends ScrollPane(gameText)

  
  val cardSpaces = new BorderPanel {    
    layout += new Label("P1 play") -> West      
    layout += new Label("P2 play") -> North
    layout += new Label("P3 play") -> East
    layout += new Label("P4 play") -> South
    layout += deckSpaces -> Center      
    background = Color.green

  }
 
  object strategyPanel extends GridPanel(5, 5) {
   contents += new Label("Strategies")

    val strategyComboBoxes = collection.mutable.ListBuffer.empty[ComboBox[Strategy]] // Use the Strategy type

    for (p <- model.simulation_components.PlayerOrder.toArray.sortBy(_.name)) do
      val playerLabel = new Label(p.name + ": ")
      val strategies = Seq(new Default, new HighestCardFirst, new BombFirstThenLow, new MaximizeSkipping) 
      val strategyComboBox = new ComboBox(strategies) {
        renderer = ListView.Renderer(_.name) 
        preferredSize = new Dimension(180, 20)
      }

      strategyComboBoxes += strategyComboBox

      val playerPanel = new FlowPanel() {
        contents += playerLabel
        contents += strategyComboBox
      }
      listenTo(strategyComboBox.selection)
      reactions += {
        case SelectionChanged(`strategyComboBox`) => 
          val wantedPlayer = PlayerOrder.toArray.find(_.name == p.name)
          wantedPlayer match
            case Some(player) => player.setStrategy(strategyComboBox.selection.item)
            case None => None
    
      }
      
      contents += playerPanel

  }

  val playerHands = new PlayerHands
 
  object cardAreaPanel extends BorderPanel{
    layout += cardSpaces -> Center
    layout += playerHands(0) -> West
    layout += playerHands(1) -> North
    layout += playerHands(2) -> East
    layout += playerHands(3) -> South
    background = Color.darkGray
  }

  // MainFrame contents panel
  import BorderPanel.Position._
  object borderPanel extends BorderPanel {
    layout += playerOrderPanel -> North
    layout += gameControlsPanel -> West
    layout += gameAreaPanel -> East
    layout += strategyPanel -> South   
    layout += cardAreaPanel -> Center
  }
  //******* CARDPANEL *******  
  class CardPanel extends Panel {

    var image = javax.imageio.ImageIO.read(new java.io.File("resources/empty.jpg"))
    
    def showAsEmpty : Unit = {
      image = javax.imageio.ImageIO.read(new java.io.File("resources/empty.jpg"))
      this.repaint()
    }
    
    /*def changeCard(card : model.game_components.Card) : Unit = {
      image = javax.imageio.ImageIO.read(new java.io.File("resources/" + card.value + card.suit + ".jpg"))
      this.repaint() 
    }
    */
    
    override def paint(g: Graphics2D) : Unit = {
      g.drawImage(image, 50, 48, null)
    }
  }

  //******* HIDDENCARDPANEL *******   
  class HiddenCardPanel extends Panel {

    var image = javax.imageio.ImageIO.read(new java.io.File("resources/back.jpg"))
     
    def showAsEmpty : Unit = {
      image = javax.imageio.ImageIO.read(new java.io.File("resources/empty.jpg"))
      this.repaint()
    }
    
    def showAsHidden : Unit = {
      image = javax.imageio.ImageIO.read(new java.io.File("resources/back.jpg"))
      this.repaint()
    }
    
    override def paint(g: Graphics2D) : Unit = {
      g.drawImage(image, 54, 48, null)
    }
  }     
  
  //******* PLAYERHANDPANEL ******* 
  class PlayerHandPanel(orientation : Char) extends Panel {

    preferredSize = new Dimension(72,96)

    var images = new ArrayBuffer[BufferedImage]
    images += javax.imageio.ImageIO.read(new java.io.File("resources/empty.jpg"))
    
    def showAsEmpty : Unit = {
      images.clear
      images += javax.imageio.ImageIO.read(new java.io.File("resources/empty.jpg"))
      this.repaint()
    }
    
    def showCards(cards : ArrayBuffer[model.game_components.Card]) : Unit = {
      images.clear
      for (card <- cards) {
        images += javax.imageio.ImageIO.read(new java.io.File("resources/" + card.value + card.suit + ".jpg"))
      }
      var i = 0
      for (i <- 1 to 5){
        images += javax.imageio.ImageIO.read(new java.io.File("resources/" + i + "s" + ".jpg"))
      }
      super.repaint() 
    }
    
    override def paint(g: Graphics2D) : Unit = {
      var offset = 36
      for (image <- images) {
        if (orientation == 'v') g.drawImage(image, 0, offset, null)
        else g.drawImage(image, offset+72, 0, null)
        offset += 30
      }      
    }    
  } 

    //******* PLAYERHANDS ******* 
  class PlayerHands extends ArrayBuffer[PlayerHandPanel] {

    this += new PlayerHandPanel('v')
    this += new PlayerHandPanel('h')
    this += new PlayerHandPanel('v')
    this += new PlayerHandPanel('h') 
    
    def reset : Unit = {    
      for panel <- this yield panel.showAsEmpty      
    }
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
    update_frame
    //update_GameArea
    
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

  def update_Strategies: Unit = {
     for i <- strategyPanel.strategyComboBoxes do
      i.selection.index = 0
  }
  def showWinner(result: String): Unit = {
    Dialog.showMessage(this, result, title="And the winner is...")    
  }
  def update_frame : Unit = {
    //MainFrame.repaint()
  }

}