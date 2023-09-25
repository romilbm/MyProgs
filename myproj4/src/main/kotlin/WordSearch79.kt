import org.assertj.core.api.Assertions.assertThat

/**
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * Output: true
 * Example 2:
 *
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * Output: true
 * Example 3:
 *
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * Output: false
 *
 *
 * Constraints:
 *
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board and word consists of only lowercase and uppercase English letters.
 *
 */
class WordSearch79 {
  fun exist(board: Array<CharArray>, word: String): Boolean {
    for (i in board.indices) {
      for (j in board[i].indices) {
        if (board[i][j] == word[0]) {
          val visited = mutableSetOf<Pair<Int, Int>>()
          // visited.add(Pair(i,j))
          if (found(board, word, i, j, 0, visited)) return true
        }
      }
    }

    return false
  }

  private fun found(
    board: Array<CharArray>,
    word: String,
    i: Int,
    j: Int,
    wctr: Int,
    visited: MutableSet<Pair<Int, Int>>
  ): Boolean {
    if (wctr == word.length) return true
    if (i < 0 || j < 0 || i >= board.size || j >= board[i].size) return false
    if (visited.contains(Pair(i, j))) return false
    if (word[wctr] != board[i][j]) return false

    visited.add(Pair(i, j))
    for (i1 in -1..1) {
      for (j1 in -1..1) {
        if (i1*j1 != 0) continue
        if (i1 == j1) continue
        if (found(board, word, i+i1, j+j1, wctr+1, visited)) return true
      }
    }

    visited.remove(Pair(i,j))

    return false
  }
}

fun main() {
  val inputs = arrayOf(
    Triple("[[\"A\",\"B\",\"C\",\"E\"],[\"S\",\"F\",\"C\",\"S\"],[\"A\",\"D\",\"E\",\"E\"]]", "SEE", true),
    Triple("[[\"A\",\"B\",\"C\",\"E\"],[\"S\",\"F\",\"C\",\"S\"],[\"A\",\"D\",\"E\",\"E\"]]", "ABCCED", true),
    Triple("[[\"A\",\"B\",\"C\",\"E\"],[\"S\",\"F\",\"C\",\"S\"],[\"A\",\"D\",\"E\",\"E\"]]", "ABCB", false),
    Triple("[[\"A\",\"B\",\"C\",\"E\"],[\"S\",\"F\",\"E\",\"S\"],[\"A\",\"D\",\"E\",\"E\"]]", "ABCESEEEFS", true),
    // Pair("", ""),
  )
  inputs.forEach {
    assertThat(WordSearch79().exist(it.first.extractCharArrays(), it.second)).isEqualTo(it.third)
  }

  println("All Good!")
}