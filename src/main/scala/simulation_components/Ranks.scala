package simulation_components

//******* RANK *******
object Ranks {
  def show = {

    val sb = new StringBuilder()
    var condition = 0
    for p <- PlayerOrder.toArray do
      if p.temp_score == 3 then
        sb ++= "President: " + p.name + "\n"
        condition = 1
    if condition == 0 then sb ++= "President: None" + "\n"
    condition = 0
    for p <- PlayerOrder.toArray do
      if p.temp_score == 2 then
        sb ++= "Vice President: " + p.name + "\n"
        condition = 1
    if condition == 0 then sb ++= "Vice President: None" + "\n"
    condition = 0
    for p <- PlayerOrder.toArray do
      if p.temp_score == 1 then
        sb ++= "Middle Man: " + p.name + "\n"
        condition = 1
    if condition == 0 then sb ++= "Middle Man: None" + "\n"
    condition = 0
    for p <- PlayerOrder.toArray do
      if p.temp_score == 0 then
        sb ++= "Bum: " + p.name + "\n"
        condition = 1
    if condition == 0 then sb ++= "Bum: None" + "\n"

    sb.toString.substring(0, sb.toString.length)

  }
}
