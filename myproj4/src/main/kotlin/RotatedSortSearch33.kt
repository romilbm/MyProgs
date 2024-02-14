import org.assertj.core.api.Assertions.assertThat

fun search(nums: IntArray, target: Int): Int {
    if (nums.isEmpty()) return -1
    if (nums.size == 1) {
        if (nums[0] == target) return 0
        return -1
    }

    var l = 0
    var r = nums.size-1
    while (l <= r) {
        val mid = (l+r)/2
        println("$l, $r, $mid")
        var newL = l
        var newR = r
        var ansL = -1
        var ansR = -1
        if (nums[l] <= nums[mid]) {
            println("left side is binSearch")
            ansL = binSearch(nums, l, mid, target)
            println("ans = $ansL")
            newL = mid+1
        } else {
            newR = mid
        }

        if (ansL != -1) {
            println("Came here: $ansL")
            return ansL
        }
        println("didn't return ans = $ansL")

        if (mid+1 < nums.size && nums[mid] <= nums[r]) {
            println("right side is binSearch")
            ansR = binSearch(nums, mid+1, r, target)
            println("ans = $ansR")
            newR = mid
        } else {
            newL = mid+1
        }

        if (ansR != -1) {
            println("Came here: $ansR")
            return ansR
        }

        l = newL
        r = newR
        println("updated $l, $r")
    }

    return -1
}

fun binSearch(
    nums: IntArray,
    s: Int,
    e: Int,
    tgt: Int
) : Int {
    if (s > e) return -1
    if (s == e) {
        if (nums[s] == tgt) return s
        return -1
    }
    var l=s
    var r=e
    while (l <= r) {
        val mid = (l+r)/2
        println("$l, $r, $mid")
        if (nums[mid] == tgt) return mid
        if (tgt < nums[mid]) r = mid-1
        if (tgt > nums[mid]) l = mid+1
    }

    return -1
}

fun main() {
    val i = arrayOf(
        Triple("[3,1]", 1, 1),
        Triple("[4,5,6,7,0,1,2]", 0, 4),
        Triple("[4,5,6,7,0,1,2]", 3, -1),
        Triple("[1]", 0, -1),
    )

    for (ip in i) {
        assertThat(search(ip.first.extractIntArray(), ip.second)).isEqualTo(ip.third)
    }
    val de = ArrayDeque<Pair<Int, Int>>()


    val intervals: Array<IntArray> = arrayOf(
        intArrayOf(5, 1),
        intArrayOf(4, 1),
        intArrayOf(3, 1),
    )
    val sortedWith = intervals.sortedWith(compareBy { it[0] })

    val sumArray: Array<IntArray> = Array(10) { intArrayOf() }

    val p = Pair(1, 2)

    val occMap = sortedMapOf<Int, Int>()
    occMap[1] = 1
    occMap.keys.max()
    println()

    val f = intArrayOf()
    f.sort()
    val nums=  setOf<Int>()
    val visited = mutableSetOf<Int>()
    val setToPermute = nums.filter { !visited.contains(it) }.toSet()

}