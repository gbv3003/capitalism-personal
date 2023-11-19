package _main

import model._
import view._
import controller._
import scala.collection.mutable.ArrayBuffer
import scala.util.Random


/**  Game simulation for Capitalism, a four-player card game
  */
object Capitalism {
  
  @main def main(): Unit = {
      
    val model = new Model
    val view  = new SimpleView   
    val controller = new Controller(model, view)

    //println(test_strategy(new game_components.GarrettHybrid,1))
    
    view.init(controller)    

  }

  // def test_strategy(strat:model.game_components.Strategy,numRuns:Int): String = {
  //   var numWins: Int = 0

  //   for i <- 0 until numRuns do 
  //     model.simulation_components.Menu.initialize

  //     val randomStrategies = ArrayBuffer(new model.game_components.HighestCardFirst, new model.game_components.BombFirstThenLow, new model.game_components.MaximizeSkipping, new model.game_components.Default)

  //     // Set a random player to use the specific strategy
  //     val randomIndex = Random.nextInt(model.simulation_components.PlayerOrder.length)
  //     model.simulation_components.PlayerOrder(randomIndex).setStrategy(strat)

  //     // Set random strategies for the other players
  //     model.simulation_components.PlayerOrder.filterNot(_ == model.simulation_components.PlayerOrder(randomIndex)).foreach { player =>
  //       val randomStrategy = Random.shuffle(randomStrategies.toList).head
  //       player.setStrategy(randomStrategy)
  //     }

  //     model.simulation_components.Menu.doGame
  //     val winner = model.simulation_components.Menu.checkForWinner
  //     val wantedPlayer = model.simulation_components.PlayerOrder.toArray.find(_.name == winner)
  //     wantedPlayer match 
  //       case Some(player) => if player.strategy.name == strat.name then numWins += 1
  //       case None => None
      
  //   return (numWins.toFloat/numRuns.toFloat).toString

  // }
  
}