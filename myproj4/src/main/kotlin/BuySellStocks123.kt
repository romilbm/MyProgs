import org.assertj.core.api.Assertions.assertThat

fun maxProfit(prices: IntArray): Int {
  if (prices.size == 1) return 0
  val dp = MutableList(prices.size) { Pair(0, 0) }
  var max = Int.MIN_VALUE

  for (ptr in 1 until prices.size) {
    for (rnr in 0 until ptr) {
      if (prices[rnr] >= prices[ptr] || dp[rnr].second == 2) {
        dp[ptr] = dp[ptr].getMax(dp[rnr])
        continue
      }
      dp[ptr] = dp[ptr].getMax(Pair(prices[ptr] - prices[rnr] + dp[rnr].first, dp[rnr].second+1))
    }
    max = max.coerceAtLeast(dp[ptr].first)

  }

  return max
}

private fun Pair<Int, Int>.getMax(other: Pair<Int, Int>): Pair<Int, Int> {
  if(this.first == other.first && this.second == other.second) return this

  if (this.first < other.first) return other
  if (this.first > other.first) return this

  if (this.second < other.second) return this
  if (this.second > other.second) return other

  throw IllegalArgumentException()
}

fun main() {
  val inputs = arrayOf(
    // Pair("[3,3,5,0,0,3,1,4]", 6),
    // Pair("[1,2,3,4,5]", 4),
    // Pair("[7,6,4,3,1]", 0),
    Pair("[14,9,10,12,4,8,1,16]", 19)
  )

  val irir = arrayOf(
    intArrayOf(1,2,3),
    intArrayOf(4,5,6)
  )

  println("hi ${irir[1][2]}")

  // inputs.forEach {
  //   assertThat(maxProfit(it.first.extractIntArray())).isEqualTo(it.second)
  // }
}