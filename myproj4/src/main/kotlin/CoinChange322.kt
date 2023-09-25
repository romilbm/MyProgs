import org.assertj.core.api.Assertions.assertThat

fun coinChange(coins: IntArray, amount: Int): Int {
  return getCoins(coins.sortedArrayDescending(), amount, mutableMapOf()).second
}

fun getCoins(coins: IntArray, amount: Int, cache: MutableMap<Int, Pair<Boolean, Int>>):
  Pair<Boolean, Int> {
  if (amount == 0) return Pair(true, 0)
  if (cache.containsKey(amount)) return cache[amount]!!

  var min = Int.MAX_VALUE
  var found = false
  for (element in coins) {
    if (amount < element) continue
    val ans = getCoins(coins, amount-element, cache)
    if (ans.first) {
      val count = 1 + ans.second
      min = min.coerceAtMost(count)
      found = true
    }
  }

  var ret = Pair(true, min)
  if (!found) ret = Pair(false, -1)
  cache[amount] = ret
  return ret
}

fun main() {
  val inputs = arrayOf(
    // Triple("[1,2,5]", 11, 3),
    // Triple("[2]", 3, -1),
    // Triple("[1]", 0, 0),
    Triple("[186,419,83,408]", 6249, 20)
  )

  inputs.forEach {
    assertThat(coinChange(it.first.extractIntArray(), it.second)).isEqualTo(it.third)
  }
}