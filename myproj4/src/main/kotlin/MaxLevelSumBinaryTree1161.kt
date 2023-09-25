import org.assertj.core.api.Assertions.assertThat

/**
 * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
 *
 * Return the smallest level x such that the sum of all the values of nodes at level x is maximal.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,7,0,7,-8,null,null]
 * Output: 2
 * Explanation:
 * Level 1 sum = 1.
 * Level 2 sum = 7 + 0 = 7.
 * Level 3 sum = 7 + -8 = -1.
 * So we return the level with the maximum sum which is level 2.
 * Example 2:
 *
 * Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
 * Output: 2
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -105 <= Node.val <= 105
 */

class MaxLevelSumBinaryTree1161 {
  fun maxLevelSum(root: TreeNode?): Int {
    val q = ArrayDeque<TreeNode>()
    var max = Int.MIN_VALUE
    var maxLevel = 0
    var level = 1
    root?.let {
      q.addLast(it)
      max = root.`val`
      maxLevel = 1
    }

    while (q.isNotEmpty()) {
      var sum = 0
      q.forEach { sum += it.`val` }
      if (sum > max) {
        max = sum
        maxLevel = level
      }
      val nextLevelNodes = mutableSetOf<TreeNode>()
      while (q.isNotEmpty()) {
        val node = q.removeFirst()
        node.left?.let { nextLevelNodes.add(it) }
        node.right?.let { nextLevelNodes.add(it) }
      }
      q.addAll(nextLevelNodes)
      level++
    }
    return maxLevel
  }
}

fun main(args: Array<String>) {
  val inputs = arrayOf(
    Pair("[1,7,0,7,-8,null,null]", 2),
    Pair("[1]", 1),
  )

  inputs.forEach {
    assertThat(MaxLevelSumBinaryTree1161().maxLevelSum(it.first.constructTree()))
      .isEqualTo(it.second)
  }
}