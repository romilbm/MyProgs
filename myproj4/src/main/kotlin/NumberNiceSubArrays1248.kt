import org.assertj.core.api.Assertions.assertThat

/**
 * Given an array of integers nums and an integer k. A continuous subarray is called nice if there
 * are k odd numbers on it.
 * Return the number of nice sub-arrays.
 *
 * Example 1:
 *
 * Input: nums = [1,1,2,1,1], k = 3
 * Output: 2
 * Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
 * Example 2:
 *
 * Input: nums = [2,4,6], k = 1
 * Output: 0
 * Explanation: There is no odd numbers in the array.
 * Example 3:
 *
 * Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * Output: 16
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 50000
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 */
fun numberOfSubarrays(nums: IntArray, k: Int): Int {
  return atMost(nums, k) - atMost(nums, k-1)
}

fun atMost(nums: IntArray, k: Int) : Int {
  var currCount = 0
  var windows = 0
  var st = 0

  for (end in nums.indices) {
    if (nums[end] % 2 == 1) {
      currCount++
    }
    while (currCount > k) {
      if (nums[st] % 2 == 1) currCount--
      st++
    }
    windows += end-st+1
  }

  return windows
}

fun main() {
  val inputs = arrayOf(
    Triple("[1,1,2,1,1]", 3, 2),
    Triple("[2,4,6]", 1, 0),
    Triple("[2,2,2,1,2,2,1,2,2,2]", 2, 16),
  )

  inputs.forEach { input ->
    assertThat(numberOfSubarrays(input.first.extractIntArray(), input.second))
      .isEqualTo(input.third)
  }

}