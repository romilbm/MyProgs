import org.assertj.core.api.Assertions.assertThat

fun minimumTotal(triangle: List<List<Int>>): Int {
  return minimumTotal(triangle, 0, 0, mutableMapOf())
}

fun minimumTotal(triangle: List<List<Int>>, i: Int, j: Int,
  cache: MutableMap<Pair<Int, Int>, Int>): Int {
  if (j == triangle.size) return 0

  val key = Pair(j, i)
  if (cache.containsKey(key)) return cache[key]!!

  val ans = triangle[j][i] + minimumTotal(triangle, i, j + 1, cache)
    .coerceAtMost(minimumTotal(triangle, i + 1, j + 1, cache))
  cache[key] = ans

  return ans
}

fun main() {
  val inputs = arrayOf(
    Pair("[[2],[3,4],[6,5,7],[4,1,8,3]]", 11),
    Pair("[[-10]]", -10),
  )

  inputs.forEach {
    assertThat(minimumTotal(it.first.extractLists())).isEqualTo(it.second)
  }
}