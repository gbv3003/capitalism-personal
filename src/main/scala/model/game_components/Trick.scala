package model.game_components
import model.game_components.Card
import scala.collection.mutable.ArrayBuffer

object Trick extends scala.collection.mutable.ArrayBuffer[Card]{


    def ClearTrick: Unit = {
        this.clear
        var first_card = new Card("",0,"")
        this += first_card
    }

    def LastCard: Card = {
        this(this.length-1)
    }

    def AcceptCard(card:Card): Unit = {
        this += card
    }

}
