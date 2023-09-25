import kotlin.math.max
import kotlin.math.min

class BrowserHistory1472(homepage: String) {
  private val history: MutableList<String> = mutableListOf()
  private var cp = 0
  private var max = 0

  init {
    history.add(homepage)
  }

  fun visit(url: String) {
    cp++
    max = cp
    history.add(cp,url)
  }

  fun back(steps: Int): String {
    val idx = max(0, cp-steps)
    cp = idx
    return history[idx]
  }

  fun forward(steps: Int): String {
    val idx = min(max, cp+steps)
    cp = idx
    return history[idx]
  }

}