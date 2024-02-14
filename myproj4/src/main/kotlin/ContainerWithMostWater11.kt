import java.util.Comparator
import kotlin.math.abs

fun maxArea(height: IntArray): Int {
    val heightMap = mutableMapOf<Int, MutableList<Int>>()
    var maxHeight = 0

    for (h in height.indices) {
        val curr = heightMap[height[h]] ?: mutableListOf()
        curr.add(h)
        maxHeight = maxHeight.coerceAtLeast(height[h])
    }

    heightMap.toSortedMap(Comparator.reverseOrder())
    val maxHeightIds = heightMap[maxHeight]
    var finalMaxArea = 0

    for (h in heightMap.keys) {
        val heightIds = heightMap[h]
        finalMaxArea = getMaxArea(maxHeightIds!!, heightIds!!, h).coerceAtLeast(finalMaxArea)
    }

    return getMaxArea(maxHeightIds!!, maxHeightIds, maxHeight).coerceAtLeast(finalMaxArea)
}

fun getMaxArea(maxHeightIds: MutableList<Int>, heightIds: MutableList<Int>, height: Int): Int {
    var maxArea = 0

    for (i1 in maxHeightIds) {
        for (i2 in heightIds) {
            val area = abs(i1-i2) * height
            maxArea = maxArea.coerceAtLeast(area)
        }
    }

    return maxArea
}
