package game_components

//******* Card *******
class Card(val value: String) {

  def show: String = {
    val sb = new StringBuilder("")
    sb ++= value
    sb.toString.substring(0, sb.toString.length)
  }
}
