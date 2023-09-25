import org.assertj.core.api.Assertions.assertThat

fun longestPalindrome(s: String): String {
  if(s.length == 1) return s

  var ml = 1
  var mp = s.substring(0,1)
  var p1 = 0
  while(p1 < s.length-1) {
    var p2 = p1+1
    if (s[p1] != s[p2]) {
      p2++
    }

    if (p2 == s.length || s[p1] != s[p2]) {
      p1++
      continue
    }

    var tp1 = p1

    while(tp1 >= 0 && p2 < s.length && s[tp1] == s[p2]) {
      tp1--
      p2++
    }

    val cpl = p2-tp1+1-2

    if(cpl > ml) {
      ml = cpl
      mp = s.substring(Math.max(tp1,0), Math.min(p2+1, s.length))
    }

    p1++
  }

  return mp
}

fun main() {
  val ip = arrayOf(
    Pair("babad", "bab"),
    Pair("cbbd", "bb"),
  )

  ip.forEach {
    assertThat(longestPalindrome(it.first)).isEqualTo(it.second)
  }
}