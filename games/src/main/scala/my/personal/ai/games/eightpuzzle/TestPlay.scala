package my.personal.ai.games.eightpuzzle
import scala.collection.mutable.Set
object TestPlay {
  def main(args : Array[String]){
    println("Starting Test play")
    val test = new EightGameBoard()
    test.startGame()
    test.displayBoard(test.start.board)
//    test.displayBoard(test.end.board)
//    test.display()
//    val goal = test.createGoal()
    val astar = new AStar(3)
    val n = astar.runAStar(test.start, test.end, false)
    
    //val s = astar.runAStar(test.start, test.end, false)
    //println(n +" vs "+s)
//    
//    println("\nEND GOAL\n")
//    test.displayGameGoal()
//    val openStates :  Set[State] = Set(test.start)
//    val s1 : State = test.start.duplicate()
//    println(s1.hashCode())
//    println(test.start.hashCode())
//    println("TRUE Vs "+s1.equals(test.start))
//    println("TRUE Vs "+openStates.contains(s1))
//    openStates.add(s1)
//    s1.gscore =10
//    s1.fscore =100
//    println(openStates.contains(s1))
//    
//    println(openStates.contains(test.start))
//    println(openStates.contains(test.end))
    
  }
}