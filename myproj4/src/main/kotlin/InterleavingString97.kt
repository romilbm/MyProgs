import org.assertj.core.api.Assertions.assertThat

fun isInterleave(s1: String, s2: String, s3: String): Boolean {
  return isInterleave(s1, s2, s3, mutableMapOf())
}

private fun isInterleave(s1: String, s2: String, s3: String,
  cache: MutableMap<Pair<String, String>, Boolean>): Boolean {
  if (s1.isEmpty() && s2.isEmpty() && s3.isEmpty()) return true
  if (s1.isEmpty()) return s2 == s3
  if (s2.isEmpty()) return s1 == s3
  if ((s1.length + s2.length) != s3.length) return false

  val key1 = Pair(s1, s2)
  if (cache.containsKey(key1)) return cache[key1]!!

  val key2 = Pair(s2, s1)
  if (cache.containsKey(key2)) return cache[key2]!!

  var ans1 = false
  var ans2 = false
  if (s1[0] == s3[0]) ans1 = isInterleave(s1.substring(1), s2, s3.substring(1), cache)
  if (!ans1 && s2[0] == s3[0]) ans2 = isInterleave(s1, s2.substring(1), s3.substring(1), cache)
  val ans = ans1 || ans2
  cache[key1] = ans
  cache[key2] = ans

  return ans
}

fun main() {
  val ip = arrayOf(
    Pair(Triple("aabcc", "dbbca", "aadbbcbcac"), true),
    Pair(Triple("aabcc", "dbbca", "aadbbbaccc"), false),
    Pair(Triple("accbaabaaabbcbaacbababacaababbcbabaababcaabbbbbcacbaa",
                "cabaabcbabcbaaaacababccbbccaaabaacbbaaabccacabaaccbbcbcb",
                "accbcaaabbaabaaabbcbcbabacbacbababaacaaaaacbabaabbcbccbbabbccaaaaabaabcabbc" +
                  "aabaaabbcbcbbbcacabaaacccbbcbbaacb"), true),
    Pair(Triple("bbbbbabbbbabaababaaaabbababbaaabbabbaaabaaaaababbbababbbbbabbbbababbabaabababbbaabababababbbaaababaa",
                "babaaaabbababbbabbbbaabaabbaabbbbaabaaabaababaaaabaaabbaaabaaaabaabaabbbbbbbbbbbabaaabbababbabbabaab",
                "babbbabbbaaabbababbbbababaabbabaabaaabbbbabbbaaabbbaaaaabbbbaabbaaabababbaaaaaabababbababaababbababbbababbbbaaaabaabbabbaaaaabbabbaaaabbbaabaaabaababaababbaaabbbbbabbbbaabbabaabbbbabaaabbababbabbabbab"), false),
  )

  val ts = System.currentTimeMillis()
  ip.forEach {
    assertThat(isInterleave(it.first.first, it.first.second, it.first.third)).isEqualTo(it.second)
  }
  val te = System.currentTimeMillis()

  val secs = te.minus(ts)/1000

  println("time: $secs")
}