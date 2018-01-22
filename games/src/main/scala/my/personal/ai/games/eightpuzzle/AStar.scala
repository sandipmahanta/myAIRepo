package my.personal.ai.games.eightpuzzle

import scala.collection.mutable.Map
import scala.collection.mutable.Set

class AStar(size : Int) {
  
  def runAStar(start : State, end: State, isNaive : Boolean) : Int = {
    start.gscore = 0
    var steps = 1
    val openStates :  Set[State] = Set(start)
    val closedStates :  Set[State] = Set()
    val goalMap = createGoalMap(end.board)
    val startMap = createGoalMap(start.board)
    while (!openStates.isEmpty && steps<100000){
      var min = -1
      var cur :State = null
      for (open <- openStates){
        if (open.fscore < min || min == -1) { 
          min = open.fscore
          cur = open
        }
      }
      println("STEP #"+steps)
      displayBoard(cur.board)
      openStates.remove(cur)
      for (newState <- cur.getPossibleMoves()){
        if (!closedStates.contains(newState)){
          val h = if (isNaive) naiveHeuristic(newState.board, goalMap) else heuristic(newState.board, goalMap)
        //val g = cur.gscore + 1 //TODO replace 1 with correct logic
          //val g = if (isNaive) naiveHeuristic(newState.board, startMap) else heuristic(newState.board, startMap)
          val g = 0
          val f = g + h 
          val existingState = openStates.find(newState.equals(_))
          if (existingState.isEmpty || existingState.get.fscore <= f){
            newState.fscore = f
            newState.gscore = g
            if (newState.equals(end)){
              openStates.add(newState)
              println("#COMPLETED#")
              displayBoard(newState.board)
              return g
            } else if (existingState.isEmpty) {
              openStates.add(newState)
            }
          }
        }
      }
      closedStates.add(cur)
      steps += 1 
    }
    println("#Closed states "+closedStates.size)
    println("#Opened states "+openStates.size)
    println("Did not find best solution")
    return steps
  }
  
  private def heuristic(board : Array[Array[Int]], goalMap : Map[Int, (Int,Int)]): Int={
    var totalDistance : Int = 0
    for (i <- 0 until size){
      for ( j <- 0 until size) {
        val index = goalMap.get(board(i)(j)).get
        totalDistance = totalDistance + manhattanDistance(i,j,index._1,index._2)
      }
    } 
    return totalDistance;
  }
  
  private def naiveHeuristic(board : Array[Array[Int]], goalMap : Map[Int, (Int,Int)]): Int={
    var totalDistance : Int = 0
    for (i <- 0 until size){
      for ( j <- 0 until size) {
        val index = goalMap.get(board(i)(j)).get
        //totalDistance = totalDistance + manhattanDistance(i,j,index._1,index._2)
        //totalDistance = totalDistance + diagonalDistance(i,j,index._1,index._2)
        if (index._1 != i || index._2 != j){
          totalDistance = totalDistance + 1
        }
      }
    } 
    return totalDistance;
  }
  
  private def manhattanDistance(i : Int, j : Int, gi : Int, gj : Int): Int = {
    return math.abs(j- gj) + math.abs(i - gi)
  }
  

//  private def euclideanDistance(i : Int, j : Int, gi : Int, gj : Int): Int = {
//    return (Int)math.sqrt((j- gj) * (j- gj) + (i - gi)* (i - gi));
//  }
  
  private def createGoalMap(goal : Array[Array[Int]]) : Map[Int, (Int,Int)] = {
    val tempMap = Map(0 -> (0,0))
    for (i <- 0 until size){
      for ( j <- 0 until size) {
          tempMap.put(goal(i)(j), (i,j))
      }
    } 
    return tempMap
  }
  
  def displayBoard(board : Array[Array[Int]]){
    val slab = "|---||---||---|"
    println(slab)
    for (i <- 0 until size){
      for ( j <- 0 until size) {
        print("| "+board(i)(j)+" |")  
      }
      println("\n"+slab+"")
    } 
  }
}