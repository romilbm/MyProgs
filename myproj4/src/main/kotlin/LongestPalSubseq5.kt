import org.assertj.core.api.Assertions.assertThat
fun longestPalindrome(s: String): String {
  if(s.length <= 1) return s

  var ctr = 0
  var max = 0
  var ans = ""

  while (ctr<s.length) {
    var (lf, rf) = helper(ctr, ctr, s)
    var lenf = rf-lf+1

    helper(ctr, ctr+1, s).let {
      if ((it.second - it.first + 1) > lenf) {
        lf = it.first
        rf = it.second
        lenf = rf-lf+1
      }
    }

//    if (ctr+1 < s.length && s[ctr] == s[ctr+1]) {
//      helper(ctr, ctr+1, s).let {
//        if ((it.second - it.first + 1) > lenf) {
//          lf = it.first
//          rf = it.second
//          lenf = rf-lf+1
//        }
//      }
//    }

    if (max < lenf) {
      max = lenf
      ans = s.substring(lf, rf+1)
    }
    ctr++
  }

  return ans
}



private fun helper(
  left: Int,
  right: Int,
  s: String,
) : Pair<Int, Int> {
  var l = left
  var r = right
  while(l>=0 && r<s.length && s[l] == s[r]) {
    l--
    r++
  }
  l++
  r--

  return Pair(l, r)
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