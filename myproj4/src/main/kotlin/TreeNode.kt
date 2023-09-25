data class TreeNode(
  var `val`: Int,
  var left: TreeNode? = null,
  var right: TreeNode? = null,
)

fun String.constructTree() : TreeNode? {
  val values = this.extractList()
  val rootNode: TreeNode?
  if (values.isEmpty()) return null
  val allNodes:MutableList<TreeNode?> = mutableListOf()
  values.map {
    if (it == null) allNodes.add(null)
    else allNodes.add(TreeNode(it))
  }
  rootNode = allNodes[0]

  for (i in values.indices) {
    val currNode = allNodes[i] ?: continue
    val lidx = (i*2)+1
    val ridx = (i*2)+2

    if (lidx < values.size && values[lidx] != null) currNode.left = allNodes[lidx]
    if (ridx < values.size && values[ridx] != null) currNode.right = allNodes[ridx]
  }
  return rootNode
}