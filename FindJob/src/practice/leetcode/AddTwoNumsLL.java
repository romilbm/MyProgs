package practice.leetcode;

public class AddTwoNumsLL {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;

        ListNode ret = null;
        ListNode curr = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int v1 = l1 != null ? l1.val : 0;
            int v2 = l2 != null ? l2.val : 0;
            int basesum = v1+v2+carry;
            int sum = basesum % 10;
            carry = basesum / 10;

            if (ret == null) {
                ret = new ListNode(sum);
                curr = ret;
            } else {
                curr.next = new ListNode(sum);
                curr = curr.next;
            }
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] ll1 = new int[] {};
        int[] ll2 = new int[] {};


    }

    private ListNode getLLFromArray(int[] l) {
        ListNode ll = null;

        for (int i: l) {

            if (ll == null) {
                ll = new ListNode(i);
            }
            ll = new ListNode(i);

        }

        return null;
    }
}
