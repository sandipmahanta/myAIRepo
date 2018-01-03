package my.personal.ai.games.eightpuzzle

object TestPlay {
  def main(args : Array[String]){
    println("Starting Test play")
    val test = new EightGameBoard()
    test.startGame()
    test.display()
    val goal = test.createGoal()
    val astar = new AStar(goal, test.size)
    for (i <- 0 until 5){
      val next = astar.bestMove(test.nextPossibilities())
      println("Next->"+next)
      test.move(next)  
      test.display()
    }
    
    println("\nEND GOAL\n")
    test.displayGameGoal()
    
    
  }
}