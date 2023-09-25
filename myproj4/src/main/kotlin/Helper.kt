fun String.extractList(): List<Int?> {
  if (this == "[]") return emptyList()
  val data = this.substring(1, this.length-1)
  val values = data.removePrefix("[").removeSuffix("]").split(",")
  val list:MutableList<Int?> = mutableListOf()
  values.forEach {
    if (it == "null") list.add(null)
    else list.add(it.toInt())
  }
  return list
}

fun String.extractNonNullList(): List<Int> {
  if (this == "[]") return emptyList()
  val values = this.removePrefix("[").removeSuffix("]").split(",")
  val list:MutableList<Int> = mutableListOf()
  values.forEach {
    list.add(it.toInt())
  }
  return list
}

fun String.extractNonNullStringList(): List<String> {
  if (this == "[]") return emptyList()
  val values = this.removePrefix("[").removeSuffix("]").split(",")
  val list:MutableList<String> = mutableListOf()
  values.forEach {
    list.add(it.removePrefix("\"").removeSuffix("\""))
  }
  return list
}

fun String.extractIntArray(): IntArray {
  if(this == "[]") return IntArray(1)
  val sanitized = this.removePrefix("[").removeSuffix("]").split(",")
  val ret = IntArray(sanitized.size)
  var ctr = 0
  sanitized.forEach {
    ret[ctr++] = it.toInt()
  }
  return ret
}

fun String.extractIntArrays(): Array<IntArray> {
  if (this == "[]") return arrayOf()
  val data = this.substring(1, this.length-1)
  val values = data.split("],[")
  val ret = Array(values.size) { IntArray(1) }
  var ctr = 0
  values.forEach {
    ret[ctr++] = it.extractIntArray()
  }

  return ret
}

fun String.extractLists(): List<List<Int>> {
  if (this == "[]") return mutableListOf()
  val data = this.substring(1, this.length-1)
  val values = data.split("],[")
  val ret = mutableListOf<List<Int>>()
  values.forEach {
    ret.add(it.extractNonNullList())
  }

  return ret
}

fun String.extractCharArrays(): Array<CharArray> {
  if (this == "[]") return arrayOf()
  val data = this.substring(1, this.length-1)
  val values = data.split("],[")
  val ret = Array(values.size) { CharArray(1) }
  var ctr = 0
  values.forEach {
    ret[ctr++] = it.extractCharArray()
  }

  return ret
}

fun String.extractCharArray(): CharArray {
  if(this == "[]") return CharArray(1)
  val sanitized = this.removePrefix("[").removeSuffix("]").split(",")
  val ret = CharArray(sanitized.size)
  var ctr = 0
  sanitized.forEach {
    ret[ctr++] = it[1]
  }
  return ret
}

fun Array<CharArray>.beautify(): String {
  val stringBuilder = StringBuilder()
  this.forEach { row ->
    var prefix = ""
    row.forEach {
      stringBuilder.append("$prefix$it")
      prefix = ", "
    }
    stringBuilder.append("\n")
  }

  return stringBuilder.toString()
}

fun Array<IntArray>.beautify(): String {
  val stringBuilder = StringBuilder()
  this.forEach { row ->
    var prefix = ""
    row.forEach {
      stringBuilder.append("$prefix$it")
      prefix = ", "
    }
    stringBuilder.append("\n")
  }

  return stringBuilder.toString()
}

fun main() {
  val input = "[[\"A\",\"B\",\"C\",\"E\"],[\"S\",\"F\",\"C\",\"S\"],[\"A\",\"D\",\"E\",\"E\"]]"
  val ans = input.extractCharArrays()
  println(ans.beautify())

  val ip2 = "[[1,2,3],[4,5,6],[7,8,9]]"
  println(ip2.extractIntArrays().beautify())
}