import org.assertj.core.api.Assertions.assertThat

fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
  val ia = IntArray(matrix.size) { i -> matrix[i][0] }
  val (pos, found) = binsearch(ia, target)
  if (found) return true
  val (_, ffound) = binsearch(matrix[pos], target)
  return ffound

}

private fun binsearch(arr: IntArray, t: Int): Pair<Int, Boolean> {
  var l = 0
  var r = arr.size - 1
  while (l <= r) {
    val m: Int = (l-r)/2 + r
    if (arr[m] == t) return Pair(m, true)
    if (t < arr[m]) r = m-1
    else l = m+1
  }

  if (t < arr[l] && l > 0) return Pair(l-1, false)
  return Pair(l, false)
}

fun main() {
  val inputs = arrayOf(
    Triple("[[1,3,5,7],[10,11,16,20],[23,30,34,60]]", 3, true),
    Triple("[1,3,5,7],[10,11,16,20],[23,30,34,60]", 21, false)
  )

  inputs.forEach {
    assertThat(searchMatrix(it.first.extractIntArrays(), it.second)).isEqualTo(it.third)
  }
}