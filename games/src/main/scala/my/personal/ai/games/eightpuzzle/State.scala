package my.personal.ai.games.eightpuzzle

import scala.collection.mutable.Seq
class State {
  private val size = 3
  var board = Array.ofDim[Int](size,size);
  var flag : Int = 0
  var fscore: Int = 99999
  var gscore: Int = 99999
  
  override def equals(that: Any): Boolean = that match{
    case that: State =>  {
      this.board.deep == that.board.deep
    }
    case _ => false
  }
  
  override def hashCode: Int = {
    val prime = 31
    var result = 1
    for (i <- 0 until size){
      for ( j <- 0 until size) {
        result = prime * result + board(i)(j) * i * j;
      }
    }         
    return  result
  }
  
  private def diagonalDistance(i : Int, j : Int, gi : Int, gj : Int): Int = {
    return math.max(math.abs(j- gj), math.abs(i - gi));
  }
  
  private def manhattanDistance(i : Int, j : Int, gi : Int, gj : Int): Int = {
    return math.abs(j- gj) + math.abs(i - gi);
  }
  
  def getPossibleMoves() : List[State] = {
    val board1 = duplicate()
    val board2 = duplicate()
    val board3 = duplicate()
    val board4 = duplicate()
    val zero = findZero(board)
    var res : List[State] = List()
    if (moveUp(board1.board, zero._1, zero._2)){
      res = res.+:(board1)
    }
    if (moveDown(board2.board, zero._1, zero._2)){
      res = res.+:(board2)
    }
    if (moveLeft(board3.board, zero._1, zero._2)){
      res = res.+:(board3)
    }
    if (moveRight(board4.board, zero._1, zero._2)){
      res = res.+:(board4)
    }
    return res
  }
  
  def duplicate() : State = {
    val temp = new State();
    for (i <- 0 until size){
      for ( j <- 0 until size) {
        temp.board(i)(j) = this.board(i)(j)
      }
    }
    return temp
  }
  
  private def moveUp(board : Array[Array[Int]], i : Int, j: Int) : Boolean = {
    if (i == 0) {
      return false
    }
    val temp = board(i-1)(j) 
    board(i-1)(j) = board(i)(j)
    board(i)(j) = temp
    return true
  }
  
  private def moveDown(board : Array[Array[Int]], i : Int, j: Int) : Boolean = {
    if (i == size -1) {
      return false
    }
    val temp = board(i+1)(j) 
    board(i+1)(j) = board(i)(j)
    board(i)(j) = temp
    return true
  }
  
  private def moveLeft(board : Array[Array[Int]], i : Int, j: Int) : Boolean = {
    if (j == 0) {
      return false
    }
    val temp = board(i)(j-1) 
    board(i)(j-1) = board(i)(j)
    board(i)(j) = temp
    return true
  }
  
  private def moveRight(board : Array[Array[Int]], i : Int, j: Int) : Boolean = {
    if (j == size -1) {
      return false
    }
    val temp = board(i)(j+1) 
    board(i)(j+1) = board(i)(j)
    board(i)(j) = temp
    return true
  }
  
  private def findZero(board : Array[Array[Int]]): (Int,Int) = {
    for (i <- 0 until size) {
      for ( j <- 0 until size) {
        if (board(i)(j) == 0) {
          return (i,j)
        }
      }
    } 
    return (0,0)
  }
}