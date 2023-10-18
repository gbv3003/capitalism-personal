package model.game_components
import model.game_components.Card
import scala.collection.mutable.ArrayBuffer

object Trick extends scala.collection.mutable.ArrayBuffer[Card]{

    def clear_trick: Unit = {
        this.clear
    }

    def LastCard: Card = {
        this.last
    }

    def acceptcard(card:Card): Unit = {
        this += card
    }

}
