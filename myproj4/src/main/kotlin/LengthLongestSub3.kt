import org.assertj.core.api.Assertions.assertThat

fun lengthOfLongestSubstring(s: String): Int {
  var l = 0
  var r = 0
  val chars = mutableSetOf<Char>()
  var ans = 0
  while (r<=s.length-1) {
    if (!chars.contains(s[r])) {
      chars.add(s[r])
      ans = ans.coerceAtLeast(r - l + 1)
      r++
    } else {
      do {
        chars.remove(s[l])
        l++
      } while(s[l-1] != s[r])
    }
  }

  return ans
}

fun main() {
  val inputs = arrayOf(
    Pair("abcabcbb", 3),
    Pair("bbbbbb", 1),
    Pair("pwwkew", 3),
    Pair("", 0),
    Pair("a", 1),
    Pair("tmmzuxt", 5),
  )

  inputs.forEach {
    assertThat(lengthOfLongestSubstring(it.first)).isEqualTo(it.second)
  }


}