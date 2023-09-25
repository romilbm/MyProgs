import org.assertj.core.api.Assertions.assertThat

class `3Sum15` {
  val cache = mutableMapOf<Int, List<MutableList<Int>>>()

  fun threeSum(nums: IntArray): List<List<Int>> {
    val occ = mutableMapOf<Int, Int>()
    nums.forEach {
      occ[it] = occ.getOrDefault(it, 0) + 1
    }
    val ans = mutableListOf<MutableList<Int>>()
    occ.keys.forEach {
      occ[it] = occ[it]!!-1
      val twoSums = twoSum(occ, 0-it)
      occ[it] = occ[it]!!+1
      twoSums?.forEach { ts ->
        ts.add(it)
        ans.add(ts)
      }
    }

    return ans
  }

  private fun twoSum(occ: MutableMap<Int, Int>, sum: Int): List<MutableList<Int>>? {
    if (cache.containsKey(sum)) return cache[sum]
    val ans = mutableListOf<MutableList<Int>>()
    occ.keys.forEach {
      if (occ.containsKey(sum-it) && occ[sum-it]!! > 0) {
        ans.add(mutableListOf(it, sum-it))
        occ[it] = occ[it]!!-1
        occ[sum-it] = occ[sum-it]!!-1
      }
    }

    return ans
  }
}

fun main() {
  val inputs = mutableListOf(
    Pair("[-1,0,1,2,-1,-4]", "[[-1,-1,2],[-1,0,1]]"),

  )

  inputs.forEach {
    assertThat(`3Sum15`().threeSum(it.first.extractIntArray()))
      .isEqualTo(it.second.extractLists())
  }
}
