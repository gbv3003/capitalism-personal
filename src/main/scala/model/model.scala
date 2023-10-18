package main

class Model {
  private val message = "Hello World"
  
  private val show_game = "Player Hands:  \n" +
            "Player 1 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 3, 2, \n" +
            "Player 2 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 3, 2, \n" +
            "Player 3 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 3, 2, \n" +
            "Player 4 Hand: Ace, King, Queen, Jack, 10, 9, 8, 7, 6, 5, 4, 3, 2, \n" +
            "\n" +
            "Last Card in Trick: \n" +
            "\n" +
            "President: None\n" +
            "Vice President: None\n" +
            "Middle Man: None\n" +
            "Bum: None\n" +
            "\n" +
            "Scores:\n" +
            "Player 1 = 0, Player 2 = 0, Player 3 = 0, Player 4 = 0\n" + "\n"
  def getArea = show_game
  def getMessage = message
}

