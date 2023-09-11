package game_components

//******* PLAYER *******
class Player(val name: String) {
  var hand = new Hand
  var score = 0
  var temp_score = -1
  var rank = "None"
  def show: String = {
    val sb = new StringBuilder()
    sb ++= name + " Hand: " + hand.show + "\n"
    sb.toString.substring(0, sb.toString.length - 2)
  }
}
