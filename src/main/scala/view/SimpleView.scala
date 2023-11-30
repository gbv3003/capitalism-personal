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
import java.awt.Font
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
  object RandomCheckBox extends CheckBox("Enable Random Hands")
  object gameControlsPanel extends BoxPanel(Orientation.Vertical) {
    contents ++= Seq(initialize_Btn, checkWinner_Btn, doMove_Btn, doTurn_Btn, doGame_Btn)
    contents += RandomCheckBox
    border = Swing.EmptyBorder(10, 10, 10, 10)
  }
  var trickCard = new TrickPanel
  val deckSpaces = new BoxPanel(Orientation.Horizontal) {
    contents += trickCard   
    background = Color.green
    background = Color.green
    preferredSize = new Dimension(254,198)
  }
  
  val cardSpaces = new BorderPanel {
    layout += new Label("Player 1") -> West
    layout += new Label("Player 2") -> North
    layout += new Label("Player 3") -> East
    layout += new Label("Player 4") -> South
    layout += deckSpaces -> Center
    background = Color.green

  }
  object gameText extends TextArea {
    font = new Font ("Monospaced", Font.BOLD , 12)
  }

  object southPanel extends GridPanel(1,2){
    contents += strategyPanel
    contents += gameText

  }
 
  object strategyPanel extends GridPanel(5, 1) {
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
    layout += playerHands(1) -> North
    layout += playerHands(3) -> South
    layout += playerHands(0) -> West
    layout += playerHands(2) -> East
    background = Color.darkGray
  }

  // MainFrame contents panel
  import BorderPanel.Position._
  object borderPanel extends BorderPanel {
    layout += playerOrderPanel -> North
    layout += gameControlsPanel -> West
    layout += southPanel -> South
    layout += cardAreaPanel -> Center
  }
  //******* TRICKPANEL *******  
  class TrickPanel extends Panel {
    var image = javax.imageio.ImageIO.read(new java.io.File("resources/empty.jpg"))
    
    def changeCard(card : model.game_components.Card) : Unit = {
      if card.value == 0 then
        image = javax.imageio.ImageIO.read(new java.io.File("resources/empty.jpg"))

      else 
        if card.value < 15 then 
          image = javax.imageio.ImageIO.read(new java.io.File("resources/" + card.value + card.suit + ".jpg"))
        else image = javax.imageio.ImageIO.read(new java.io.File("resources/" + 2 + card.suit + ".jpg"))
      this.repaint() 
    }

    def showAsEmpty : Unit = {
      image = javax.imageio.ImageIO.read(new java.io.File("resources/empty.jpg"))
      this.repaint()
    }

    
    override def paint(g: Graphics2D) : Unit = {
      g.drawImage(image, 50, 48, null)
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
    
    def showCards(hand : model.game_components.Hand) : Unit = {
      images.clear
      for (card <- hand) {
        if card.value < 15 then 
          images += javax.imageio.ImageIO.read(new java.io.File("resources/" + card.value + card.suit + ".jpg"))
        else images += javax.imageio.ImageIO.read(new java.io.File("resources/" + 2 + card.suit + ".jpg"))
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

  size = new Dimension(800, 1000)

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
    RandomCheckBox.action = controller.random_toggle
    doGame_Btn.action = controller.doGame
    
    visible = true
  }
  
  def update_PlayerOrder: Unit = {
    playerOrder.text = controller.get.showPlayerOrder
  }
  
  def update_GameArea: Unit = {
    for p <- PlayerOrder.toArray do
      val pHandNum: Int = p.name.takeRight(1).toInt-1
      if p.hand.nonEmpty then 
        playerHands(pHandNum).showCards(p.hand.return_ordered)
      else playerHands(pHandNum).showAsEmpty
    trickCard.changeCard(model.game_components.Trick.lastCard)
    cardAreaPanel.repaint()
    gameText.text = controller.get.showgameText
    }

  def update_Strategies: Unit = {
     for i <- strategyPanel.strategyComboBoxes do
      i.selection.index = 0
  }
  def showWinner(result: String): Unit = {
    Dialog.showMessage(this, result, title="And the winner is...")    
  }

}