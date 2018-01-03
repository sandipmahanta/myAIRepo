package my.personal.ai.games.eightpuzzle

import scala.collection.mutable.Map

class AStar(goal : Array[Array[Int]], size : Int) {
  val goalMap = getGoalMap()
  val openList
  def heuristic(board : Array[Array[Int]]): Int={
    var totalDistance : Int = 0
    for (i <- 0 until size){
      for ( j <- 0 until size) {
        val index = goalMap.get(board(i)(j)).get
        totalDistance = totalDistance + manhattanDistance(i,j,index._1,index._2)
        //totalDistance = totalDistance + diagonalDistance(i,j,index._1,index._2)
      }
    } 
    return totalDistance;
  }
  
  private def diagonalDistance(i : Int, j : Int, gi : Int, gj : Int): Int = {
    return math.max(math.abs(j- gj), math.abs(i - gi));
  }
  
  private def manhattanDistance(i : Int, j : Int, gi : Int, gj : Int): Int = {
    return math.abs(j- gj) + math.abs(i - gi);
  }

//  private def euclideanDistance(i : Int, j : Int, gi : Int, gj : Int): Int = {
//    return (Int)math.sqrt((j- gj) * (j- gj) + (i - gi)* (i - gi));
//  }
  
  private def getGoalMap() : Map[Int, (Int,Int)] = {
    val tempMap = Map(0 -> (0,0))
    for (i <- 0 until size){
      for ( j <- 0 until size) {
          tempMap.put(goal(i)(j), (i,j))
      }
    } 
    return tempMap
  }
  
  def bestMove(moves : List[Array[Array[Int]]]): Int = {
    var bestBoard = 1
    var bestVal = 99999
    var c = 1
    for (m <- moves){
      val curVal = heuristic(m)
      if (curVal < bestVal){
        bestVal = curVal
        bestBoard = c
        println("Heuristic ->"+curVal)
      }
      c += 1
    }
    return bestBoard
  }
}