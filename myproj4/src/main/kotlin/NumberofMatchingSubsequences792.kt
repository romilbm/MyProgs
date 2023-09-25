import org.assertj.core.api.Assertions.assertThat

/**
 * Given a string s and an array of strings words, return the number of words[i] that is a
 * subsequence of s.
 *
 * A subsequence of a string is a new string generated from the original string with some characters
 * (can be none) deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 *
 * Example 1:
 *
 * Input: s = "abcde", words = ["a","bb","acd","ace"]
 * Output: 3
 * Explanation: There are three strings in words that are a subsequence of s: "a", "acd", "ace".
 *
 * Example 2:
 *
 * Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 5 * 104
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 50
 * s and words[i] consist of only lowercase English letters.
 */
class NumberofMatchingSubsequences792 {
  val allSubs = mutableSetOf<String>()
  val invalid = mutableSetOf<String>()

  fun numMatchingSubseq(s: String, words: Array<String>): Int {
    return words.filter { isSubSeq(s, it) }.size
  }

  private fun isSubSeq(base: String, word: String): Boolean {
    if (word.isEmpty()) return true
    if (allSubs.contains(word)) return true
    if (invalid.contains(word)) return false
    if (base.isEmpty() && word.isNotEmpty()) return false
    if (word.length > base.length) return false
    if (word.length == base.length) return word == base
    var idx = -1
    for (charToCheck in word) {
      idx = base.indexOf(charToCheck, idx+1)
      if (idx == -1) {
        invalid.add(word)
        return false
      }
    }
    allSubs.add(word)
    return true
  }
}

fun main(args: Array<String>) {
  val input = listOf(
    Triple("abcde", arrayOf("a","bb","acd","ace"), 3),
    Triple("dsahjpjauf", arrayOf("ahjpjau","ja","ahbwzgqnuk","tnmlanowax"), 2)
  )

  input.forEach {
    assertThat(NumberofMatchingSubsequences792().numMatchingSubseq(it.first, it.second))
      .isEqualTo(it.third)
  }
}