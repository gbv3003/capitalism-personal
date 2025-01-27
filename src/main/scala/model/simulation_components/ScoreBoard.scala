package model.simulation_components
/** A simulated component that contains all of the players scores
  *
  */
object Scoreboard {
  /** Shows all of the players' scores
    *
    */
  def show = {
    val sb = new StringBuilder("Scores:\n")
    for p <- PlayerOrder.toArray.sortBy(_.name)
    do sb ++= p.name + " = " + p.score + ", "

    sb.delete(sb.toString.length - 2, sb.toString.length)
    sb ++= "\n"

    if PlayerOrder.current.score == 10
    then sb ++= PlayerOrder.current.name + " Wins!"

    sb.toString
  }
  /** Checks to see if a player has won the game
    *
    */
  def checkForWinner = {
    var winner = "none"
    for p <- PlayerOrder.toArray do 
      if p.score >= 10 
        then winner = p.name
    winner

  }
}
