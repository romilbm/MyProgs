/**
 * A peak element is an element that is strictly greater than its neighbors.
 *
 * Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.
 *
 * You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.
 *
 * You must write an algorithm that runs in O(log n) time.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 *
 * Example 2:
 *
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 5
 * Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * -231 <= nums[i] <= 231 - 1
 * nums[i] != nums[i + 1] for all valid i.
 */

class PeakElement162 {
  fun findPeakElement(nums: IntArray): Int {
    var l = 0
    var r = nums.size-1

    while(l<=r) {
      val mid = l + ((r-l)/2)
      val peak = isPeak(nums, mid)
      if (peak.second) return peak.first
      if (peak.first > mid) l = mid+1
      else r = mid-1
    }

    return if (nums.first() > nums.last()) 0
           else nums.lastIndex
  }

  private fun isPeak(nums: IntArray, idx: Int): Pair<Int, Boolean> {
    if (idx-1 >= 0 && nums[idx-1] >= nums[idx]) return Pair(idx-1, false)

    if (idx+1 < nums.size && nums[idx+1] >= nums[idx]) return Pair(idx+1, false)

    return Pair(idx, true)
  }
}

fun main(args: Array<String>) {
  val inputs = arrayListOf(
    intArrayOf(1,2),
    intArrayOf(1,2,1,3,5,6,4),
    intArrayOf(1,2,3,1)
  )
  inputs.forEach {
    println(PeakElement162().findPeakElement(it))
  }
}