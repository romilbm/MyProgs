
data class Node(val url: String, var next: Node? = null, var prev: Node? = null)

data class Tab(val url: String = "roblox.com") {

  // Overall intergrattion test
  // Script:
  // Do K visits
  // Go M fwd
  // Go N back from there (test for new fwd history)
  // Then,K more visits
  // more fwd and back
  private var curr = Node(url, null, null)


  // test if url is correlctly set - first time
  fun visit(destination: String) {
    val page = Node(destination)
    curr.next = page
    page.prev = curr
    curr = page
  }

  // do n visits and test
  // - (n-1) back
  // - (n) back
  // - n+1 back
  fun back(steps: Int) : String {
    for (i in 1..steps) {
      if (curr.prev == null) break
      curr = curr.prev!!
    }

    return curr.url
  }

  // do n visits and test
  // - (n-1) fwd
  // - (n) fwd
  // - n+1 fwd
  fun forward(steps: Int): String {
    for (i in 1..steps) {
      if (curr.next == null) break
      curr = curr.next!!
    }

    return curr.url
  }

  fun getCurrentPage(): Node = curr
}


class Browser {
  private val tabs: MutableMap<String, Pair<Tab, Boolean>> = mutableMapOf()
  private val historyLength: Int = 100
  private var currCnt: Int = 0

  fun open(uuid: String) {
    val newTab = Pair(Tab(), true)
    tabs[uuid] = newTab
  }

  fun open() {
    val uuid = "" //
    val newTab = Pair(Tab(), true)
    tabs[uuid] = newTab
  }

  fun close(uuid: String) {
    if (!tabs.containsKey(uuid)) throw IllegalArgumentException("")
    tabs[uuid] = Pair(tabs[uuid]!!.first, false)

    // tabs.remove(uuid)
  }

  fun visit(uuid: String, url: String) {
    if (!tabs.containsKey(uuid) && !tabs[uuid]!!.second) throw IllegalArgumentException("")

    tabs[uuid]!!.first.visit(url)
    currCnt++
    if (currCnt == historyLength) {
      // backup to disk
    }
  }


}