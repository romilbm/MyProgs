import kotlin.math.pow

data class ListNode(
  var `val`: Int,
  var next: ListNode? = null
)


fun foo() {
  val a:String? = null
  val len = a?.length ?: Int.MAX_VALUE
  val ans: Int = 10.0.pow(2).toInt()
  ans.toInt()
  // for (i in 10 downTo 1)
}

fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
  var p1 = l1
  var p2 = l2
  var head: ListNode? = null
  var curr: ListNode?
  var prev: ListNode? = null
  var carry = 0
  while (p1 != null || p2 != null) {
    val sum = carry + (p1?.`val` ?: 0) + (p2?.`val` ?: 0)
    curr = ListNode(sum % 10)
    carry = sum / 10
    if (head == null) head = curr
    if (prev != null) prev.next = curr
    prev = curr
    p1 = p1?.next
    p2 = p2?.next
  }

  if (carry > 0) {
    prev?.next = ListNode(1)
  }

  return head
}