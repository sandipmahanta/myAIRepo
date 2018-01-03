package my.personal.ai.games.eightpuzzle

class EightGameBoard {
  val size = 3
  private val board = Array.ofDim[Int](size,size);
  
  def startGame(){
    val r = scala.util.Random
    val vals = r.shuffle(List(0,1,2,3,4,5,6,7,8)).iterator
    var c = 0 
    for (i <- 0 until size){
      for ( j <- 0 until size) {
        board(i)(j) = vals.next()
      }
    }
  }
  
  def displayGameGoal(){
    displayBoard(createGoal)
  }
  
  def createGoal(): Array[Array[Int]] = {
    val end = Array.ofDim[Int](size,size);
    var c = 0
    for (i <- 0 until size){
      for ( j <- 0 until size) {
        end(i)(j) = c
        c = c + 1
      }
    }
    return end
  }
  
  def display() {
    println("")
    displayBoard(board)
  }
  
  def getBoard() : Array[Array[Int]] = {
    board
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
  
  def move(index : Int){
    val zero = findZero(board)
    if (index == 1) {
      moveUp(board, zero._1, zero._2)
    } else if (index == 2) {
      moveDown(board, zero._1, zero._2)
    }  else  if (index == 3) {
      moveLeft(board, zero._1, zero._2)
    } else {
      moveRight(board, zero._1, zero._2)
    } 
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
  
  def nextPossibilities() : List[Array[Array[Int]]] = {
    val board1 = duplicate(board)
    val board2 = duplicate(board)
    val board3 = duplicate(board)
    val board4 = duplicate(board)
    val zero = findZero(board)
    moveUp(board1, zero._1, zero._2)
    moveDown(board2, zero._1, zero._2)
    moveLeft(board3, zero._1, zero._2)
    moveRight(board4, zero._1, zero._2)
    return List(board1, board2, board3, board4)
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
  
  private def duplicate(board : Array[Array[Int]]) : Array[Array[Int]] = {
    val temp = Array.ofDim[Int](size,size);
    for (i <- 0 until size){
      for ( j <- 0 until size) {
        temp(i)(j) = board(i)(j)
      }
    }
    return temp
  }
}
