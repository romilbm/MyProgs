import org.assertj.core.api.Assertions
import kotlin.math.max

fun rob(nums: IntArray): Int {
  if (nums.isEmpty()) return 0
  if (nums.size == 1) return nums[0]

  val st1 = getMax(nums, 0)
  val st2 = getMax(nums, 1)

  return max(st1, st2)
}

private fun getMax(nums: IntArray, st: Int) : Int {
  val sums = IntArray(nums.size) { 0 }
  var ptr = st
  var maxAmt = 0

  while (ptr < nums.size) {
    sums[ptr] = nums[ptr]
    if (ptr == st) {
      maxAmt = max(maxAmt, sums[ptr])
      ptr += 2
    } else {
      var prev1 = 0
      var prev2 = 0
      if (ptr-2 >= 0) {
        prev1 = sums[ptr-2]
      }
      if (ptr-3 >= 0) {
        prev2 = sums[ptr-3]
      }

      sums[ptr] += max(prev1, prev2)
      maxAmt = max(maxAmt, sums[ptr])
      ptr++
    }
  }

  return maxAmt
}

fun main() {
  val inputs = arrayOf(
    Pair("[1,2,3,1]", 4),
    Pair("[2,7,9,3,1]", 12),
    Pair("[1,3,1,3,100]", 103)
  )

  inputs.forEach {
    Assertions.assertThat(rob(it.first.extractIntArray())).isEqualTo(it.second)
  }


}