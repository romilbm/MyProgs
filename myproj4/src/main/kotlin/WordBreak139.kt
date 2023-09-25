import org.assertj.core.api.Assertions.assertThat

fun wordBreak(s: String, wordDict: List<String>): Boolean {
  return wordBreak(s, HashSet(wordDict), 0, mutableMapOf())
}

fun wordBreak(s: String, wordSet: Set<String>, st:Int, cache: MutableMap<Int, Boolean>): Boolean {
  if (st == s.length) return true
  if (cache.containsKey(st)) return cache[st]!!
  var ptr = st+1
  while (ptr <= s.length) {
    val subWord = s.substring(st, ptr)
    if (wordSet.contains(subWord) && wordBreak(s, wordSet, ptr, cache)) {
      cache[st] = true
      return true
    }
    ptr++
  }
  cache[st] = false
  return false
}

fun main() {
  val inputs = arrayOf(
    Triple("leetcode", "[\"leet\",\"code\"]", true),
    Triple("applepenapple", "[\"apple\",\"pen\"]", true),
    Triple("catsandog", "[\"cats\",\"dog\",\"sand\",\"and\",\"cat\"]", false),
    Triple("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
             "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
           "[\"a\",\"aa\",\"aaa\",\"aaaa\",\"aaaaa\",\"aaaaaa\",\"aaaaaaa\",\"aaaaaaaa\"," +
             "\"aaaaaaaaa\",\"aaaaaaaaaa\"]", false),
  )

  inputs.forEach {
    assertThat(wordBreak(it.first, it.second.extractNonNullStringList())).isEqualTo(it.third)
  }
}