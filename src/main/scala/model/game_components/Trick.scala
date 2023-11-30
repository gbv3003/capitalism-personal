package model.game_components
import model.game_components.Card
import scala.collection.mutable.ArrayBuffer
/** A game component that contains all cards played in a round
    *
    */
object Trick extends scala.collection.mutable.ArrayBuffer[Card]{

    /** Clears the trick for that round
    *
    */
    def clearTrick: Unit = {
        this.clear
        var first_card = new Card("",0,"")
        this += first_card
    }
    /** Returns the last card played in the trick
    *
    */
    def lastCard: Card = {
        if this.nonEmpty then 
            this.last
        else new Card("",0,"")
    }
    /** Accepts a card to be added to the trick
    *   @param card
    *       the card to be accepted
    */
    def acceptCard(card:Card): Unit = {
        this += card
    }

}
