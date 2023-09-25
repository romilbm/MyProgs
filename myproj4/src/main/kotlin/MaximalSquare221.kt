import java.lang.Math.pow
import kotlin.math.pow

fun maximalSquare(matrix: Array<CharArray>): Int {
  for (i in 0 until matrix.size) {
    for (j in 0 until matrix[i].size) {

    }
  }

  return 0
}

private fun largest(matrix: Array<CharArray>, i: Int, j: Int, cmax: Int): Int {
  if (i == matrix.size || j == matrix[i].size) return 0
  val rem = (matrix.size - i).coerceAtMost(matrix[i].size - j).toDouble().pow(2.0)
  if (rem <= cmax) return 0

  if (matrix[i][j] != '1') return 0

  var ei = i
  var ej = j
  var size = 2
  while (ei < matrix.size && ej < matrix[ei].size) {
    ei = i + size
    ej = j + size

  }

  return 0
}