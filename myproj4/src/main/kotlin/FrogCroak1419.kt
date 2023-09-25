import org.assertj.core.api.Assertions.assertThat
import kotlin.math.max

class FrogCroak1419 {
  fun minNumberOfFrogs(croakOfFrogs: String): Int {
    val croak = listOf('c', 'r', 'o', 'a', 'k')
    val charMap:Map<Char, MutableList<Int>>  = mutableMapOf(
      Pair('c', mutableListOf()),
      Pair('r', mutableListOf()),
      Pair('o', mutableListOf()),
      Pair('a', mutableListOf()),
      Pair('k', mutableListOf())
    )

    for (i in croakOfFrogs.toCharArray().indices) {
      if (!charMap.containsKey(croakOfFrogs[i])) return -1
      charMap[croakOfFrogs[i]]?.add(i)
    }

    charMap.values.forEach { if (it.size != charMap['c']?.size) return -1 }

    for (idx in 0..3) {
      val l1 = charMap[croak[idx]]
      val l2 = charMap[croak[idx+1]]
      if (!areValid(l1, l2)) return -1
    }

    var max = Int.MIN_VALUE
    var cp = 0
    var kp = 0
    var cnt = 0

    do {
      if (charMap['c']!![cp] < charMap['k']!![kp]) {
        // c here
        cnt++
        cp++
      } else {
        // k here
        cnt--
        kp++
      }
      max = max(cnt, max)
    } while (cp < charMap['c']!!.size && kp < charMap['k']!!.size)

    return max
  }

  private fun areValid(l1: MutableList<Int>?, l2: MutableList<Int>?): Boolean {
    if (l1.isNullOrEmpty()
      || l2.isNullOrEmpty()
      || l1.size != l2.size) return false

    for (i in l1.indices) {
      if (l1[i] >= l2[i]) return false
    }

    return true
  }
}

fun main(args: Array<String>) {
  val inputs = arrayListOf(
    // Pair("croakcroak",1),
    // Pair("croakcrook",-1),
    // Pair("crcoakroak",2),
    // Pair("crocakcroraoakk",2),
    Pair("ccccccccccrrccccccrcccccccccccrcccccccccrcccccccccccrcccccrcccrrcccccccccccccrocrrccccc" +
           "ccccrccrocccccrccccrrcccccccrrrcrrcrccrcoccroccrccccccccorocrocccrrrrcrccrcrcrcrccrcr" +
           "occccrccccroorcacrkcccrrroacccrrrraocccrrcrrccorooccrocacckcrcrrrrrrkrrccrcoacrcorcro" +
           "occacorcrccccoocroacroraoaarcoorrcrcccccocrrcoccarrorccccrcraoocrrrcoaoroccooccororrr" +
           "ccrcrocrrcorooocorarccoccocrrrocaccrooaaarrcrarooaarrarrororrcrcckracaccorarorocacrra" +
           "rorrraoacrcokcarcoccoorcrrkaocorcrcrcrooorrcrroorkkaaarkraroraraarooccrkcrcraocooaooc" +
           "raoorrrccoaraocoorrcokrararrkaakaooroorcororcaorckrrooooakcarokokcoarcccroaakkrrorora" +
           "crkraooacrkaraoacaraorrorrakaokrokraccaockrookrokoororoooorroaoaokccraoraraokakrookkr" +
           "oakkaookkooraaocakrkokoraoarrakakkakaroaaocakkarkoocokokkrcorkkoorrkraoorkokkarkakokk" +
           "kracocoaaaaakaraaooraokarrakkorokkoakokakakkcracarcaoaaoaoorcaakkraooaoakkrrroaoaoaar" +
           "kkarkarkrooaookkroaaarkooakarakkooaokkoorkroaaaokoarkorraoraorcokokaakkaakrkaaokaaaro" +
           "arkokokkokkkoakaaookkcakkrakooaooroaaaaooaooorkakrkkakkkkaokkooaakorkaroaorkkokaakaaa" +
           "aaocrrkakrooaaroroakrakrkrakaoaaakokkaaoakrkkoakocaookkakooorkakoaaaaakkokakkorakaaaa" +
           "oaarkokorkakokakckckookkraooaakokrrakkrkookkaaoakaaaokkaokkaaoakarkakaakkakorkaakkakk" +
           "kakaaoaakkkaoaokkkakkkoaroookakaokaakkkkkkakoaooakcokkkrrokkkkaoakckakokkocaokaakakaa" +
           "akakaakakkkkrakoaokkaakkkkkokkkkkkkkrkakkokkroaakkakaoakkoakkkkkkakakakkkaakkkkakkkrk" +
           "oak", 229)
  )

  inputs.forEach {
    assertThat(FrogCroak1419().minNumberOfFrogs(it.first)).isEqualTo(it.second)
  }

}