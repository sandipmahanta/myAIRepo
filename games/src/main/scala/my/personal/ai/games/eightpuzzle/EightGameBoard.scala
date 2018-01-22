package my.personal.ai.games.eightpuzzle

class EightGameBoard {
  private val size = 3
  val start = new State()
  val end = new State()
  
  def startGame(){
    val r = scala.util.Random
    val vals = r.shuffle(List(0,1,2,3,4,5,6,7,8)).iterator
    val board = Array.ofDim[Int](size,size);
    for (i <- 0 until size){
      for ( j <- 0 until size) {
        board(i)(j) = vals.next()
      }
    }
    start.board = board
    end.board = createGoal()
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
  
  def displayBoard(board : Array[Array[Int]]){
    val slab = "|---||---||---|"
    println(slab)
    for (i <- 0 until size){
      for ( j <- 0 until size) {
        print("| "+board(i)(j)+" |")  
      }
      println("\n"+slab+"")
    } 
    println()
  }
}
