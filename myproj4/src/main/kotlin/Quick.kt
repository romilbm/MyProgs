fun rotate(matrix: Array<IntArray>): Unit {
  val last = matrix.size-1
  for (outer in 0..last/2) {
    for (inner in outer until last-outer) {
      val save = matrix[outer][inner]
      matrix[outer][inner] = matrix[last-inner][outer] //d
      matrix[last-inner][outer] = matrix[last-outer][last-inner] //c
      matrix[last-outer][last-inner] = matrix[inner][last-outer] //b
      matrix[inner][last-outer] = save // a
    }
  }
}

fun groupThePeople(groupSizes: IntArray): List<List<Int>> {
  val occ = mutableMapOf<Int, MutableList<Int>>()
  groupSizes.forEachIndexed { i,v ->
    when(occ[v]) {
      null -> occ[v] = mutableListOf(i)
      else -> occ[v]!!.add(i)
    }
  }
  val res = mutableListOf<MutableList<Int>>()
  occ.keys.forEach {
    var pos = occ[it]
    if (it == pos?.size) {
      res.add(pos)
    } else {
      while (pos!!.isNotEmpty()) {
        res.add(pos.subList(0, it))
        pos = pos.subList(it, pos.size)
      }
    }
  }

  return res
}




fun main() {
  // val inputs = arrayOf(
  //   // "[[1,2,3],[4,5,6],[7,8,9]]",
  //   "[[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]]"
  // )
  //
  // inputs.forEach {
  //   val quest = it.extractIntArrays()
  //   println(quest.beautify())
  //   rotate(quest)
  //   println()
  //   println(quest.beautify())
  // }
  val inputs = arrayOf(
    "[3,3,3,3,3,1,3]",
    "[2,1,3,3,3,2]"
  )

  inputs.forEach {
    println(groupThePeople(it.extractIntArray()))
  }

}