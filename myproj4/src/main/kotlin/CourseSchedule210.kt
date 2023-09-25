/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must
 * take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses. If there are many valid
 * answers, return any of them. If it is impossible to finish all courses, return an empty array.
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
 * course 0. So the correct course order is [0,1].
 *
 * Example 2:
 *
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished
 * both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 *
 * Example 3:
 *
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 *
 *
 * [[1,0],[1,2],[0,1]]
 *
 * Constraints:
 *
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * All the pairs [ai, bi] are distinct.
 */

class CourseSchedule210 {
  private val taken = mutableSetOf<Int>()
  private val depOn = mutableListOf<MutableSet<Int>>()
  private val depFor = mutableListOf<MutableSet<Int>>()
  private val order = mutableListOf<Int>()
  fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
    for (i in 0 until numCourses) {
      depOn.add(mutableSetOf())
      depFor.add(mutableSetOf())
    }

    prerequisites.filter { it.isNotEmpty() }.forEach {
      depOn[it[0]].add(it[1])
      depFor[it[1]].add(it[0])
    }

    while (order.size < numCourses) {
      val canTake = mutableSetOf<Int>()
      for (i in depOn.indices) {
        if(depOn[i].isEmpty()) canTake.add(i)
      }
      if ((canTake-taken).isEmpty()) return intArrayOf()

      canTake.filter { !taken.contains(it) }.forEach { toTake ->
        order.add(toTake)
        val removeFrom = depFor[toTake]
        removeFrom.forEach {
          depOn[it].remove(toTake)
        }
        taken.add(toTake)
      }
    }

    return order.toIntArray()
  }
}

fun main(args: Array<String>) {
  val inputs = listOf(
    Pair(2, arrayOf(intArrayOf(1,0),)),
    Pair(4, arrayOf(intArrayOf(1,0), intArrayOf(2,0), intArrayOf(3,1), intArrayOf(3,2))),
    Pair(2, arrayOf(intArrayOf(0,1), intArrayOf(1,0))),
    Pair(3, arrayOf(intArrayOf(1,0), intArrayOf(1,2), intArrayOf(0,1))),
    Pair(1, arrayOf(intArrayOf(),)),
  )

  inputs.forEach { input ->
    val res = CourseSchedule210().findOrder(input.first, input.second)
    res.forEach { print("$it, ") }
    println()
  }
}