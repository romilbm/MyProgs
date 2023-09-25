class EqualRowColPair2352 {
  fun equalPairs(grid: Array<IntArray>): Int {
    val gridTranspose = Array(grid.size) { _ -> IntArray(grid.size) }
    for (i in grid.indices) {
      for (j in grid[i].indices) {
        gridTranspose[j][i] = grid[i][j]
      }
    }

    val rowMetas = grid.meta()
    val colMetas = gridTranspose.meta()
    var ret = 0

    for (rowMeta: Pair<Set<Int>, Int> in rowMetas) {
      for (colMeta: Pair<Set<Int>, Int> in colMetas) {
        if ((rowMeta.first - colMeta.first).isEmpty()
          && rowMeta.second == colMeta.second
        ) ret++
      }
    }

    return ret
  }

  private fun Array<IntArray>.meta(): List<Pair<Set<Int>, Int>>{
    val allMetas = mutableListOf<Pair<Set<Int>, Int>>()
    this.forEach { row ->
      run {
        val numSet = HashSet<Int>()
        var sum = 0
        for (i in row.indices) {
          numSet.add(row[i])
          sum += ((i+1) * row[i])
        }
        allMetas.add(Pair(numSet, sum))
      }
    }
    return allMetas
  }
}

fun main(args: Array<String>) {
  // IntArray(3)
  val tr: List<String> = mutableListOf()
  val grid = arrayOf(intArrayOf(3, 2, 1), intArrayOf(1, 7, 6), intArrayOf(2, 7, 7))
  val ans = EqualRowColPair2352().equalPairs(grid)
  println(ans)
}