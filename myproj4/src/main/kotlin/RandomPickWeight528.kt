import java.util.concurrent.ThreadLocalRandom
import kotlin.math.floor
import kotlin.math.pow

class RandomPickWeight528(w: IntArray) {
  private val base = mutableListOf<Int>()
  init {
    val sum:Double = w.sum().toDouble()
    for (i in w.indices) {
      val ratio:Double = (w[i]/sum)
      for (ctr in 0 until floor(ratio * MAX_SUM).toInt()) {
        base.add(i)
      }
    }
  }

  fun pickIndex(): Int {
    return base[ThreadLocalRandom.current().nextInt(0, base.size-1)]
  }

  companion object {
    val MAX_SUM = 10.0.pow(7.0)
  }
}
fun main(args: Array<String>) {
  println(RandomPickWeight528(intArrayOf(1,3)).pickIndex())
}