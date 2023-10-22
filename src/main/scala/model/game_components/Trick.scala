package model.game_components
import model.game_components.Card
import scala.collection.mutable.ArrayBuffer

object Trick extends scala.collection.mutable.ArrayBuffer[Card]{


    def clearTrick: Unit = {
        this.clear
        var first_card = new Card("",0,"")
        this += first_card
    }

    def lastCard: Card = {
        this.last
    }

    def acceptCard(card:Card): Unit = {
        this += card
    }

}
