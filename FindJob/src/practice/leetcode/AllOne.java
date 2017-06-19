package practice.leetcode;

import java.util.*;

public class AllOne {

    HashMap<String, Node> map;
    Node head;
    Node tail;

    public static class Node {
        int val;
        String name;
        Node prev;
        Node next;
        public Node(String name, int val) {
            this.val = val;
            this.name = name;
        }
    }

    /** Initialize your data structure here. */
    public AllOne() {
        map = new HashMap<>();
    }

    private void insertBeforeHead(Node n) {
        if (head == null) {
            throw new IllegalStateException("Cannot insert before head when it is null");
        }

        head.prev = n;
        n.next = head;
        n.prev = null;
        head = n;
    }

    private void insertAfterTail(Node n) {
        if (tail == null) {
            tail = n;
            head = n;
            return;
        }

        tail.next = n;
        n.prev = tail;
        n.next = null;
        tail = n;
    }

    private void insertBeforeTail(Node n) {
        if (tail == null) {
            throw new IllegalStateException("Cannot insert before tail when it is null");
        }
        tail.prev.next = n;
        n.prev = tail.prev;
        n.next = tail;
        tail.prev = n;
    }

    private void remove(Node n) {
        Node prev = n.prev;
        Node next = n.next;
        n.prev = null;
        n.next = null;

        if (n == head) {
            head = next;
        }

        if (n == tail) {
            tail = prev;
        }

        if (prev != null) {
            prev.next = next;
        }

        if (next != null) {
            next.prev = prev;
        }
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        System.out.println("inc " + key);
        Node n;
        if (!map.containsKey(key)) {
            n = new Node(key,1);
            map.put(key,n);
            insertAfterTail(n);
            return;
        } else {
            n = map.get(key);
        }

        remove(n);
        n.val++;

        if (n.val > head.val) {
            insertBeforeHead(n);
        } else {
            insertBeforeTail(n);
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        System.out.println("dec " +  key);
        if (!map.containsKey(key)) {
            return;
        }
        Node n = map.get(key);

        n.val--;
        remove(n);
        if (n.val == 0) {
            map.remove(key);
            return;
        }

        if (n.val < tail.val) {
            insertAfterTail(n);
        } else if (n.val > head.val){
            insertBeforeHead(n);
        } else {
            insertBeforeTail(n);
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        return head == null ? "" : head.name + "";
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return tail == null ? "" : tail.name + "";
    }

    private void printState() {
        System.out.println(printLL());
        System.out.println();
    }

    private String printLL() {
        Node c = head;
        StringBuilder sb = new StringBuilder("");
        do {
            sb.append(c.name + ":" + c.val + ", ");
            c = c.next;
        } while (c != null);
        return sb.toString();
    }

    public static void main(String[] args) {
        AllOne allOne = new AllOne();
        allOne.inc("h");
        allOne.printState();
        allOne.inc("g");
        allOne.printState();
        allOne.inc("h");
        allOne.printState();
        allOne.inc("h");
        allOne.printState();
        System.out.println(" ==== " + allOne.getMaxKey());
        allOne.inc("l");
        allOne.printState();
        allOne.inc("c");
        allOne.printState();
        allOne.inc("l");
        allOne.printState();
        allOne.dec("h");
        allOne.printState();
        allOne.dec("h");
        allOne.printState();
        allOne.inc("l");
        allOne.printState();
        allOne.inc("c");
        allOne.printState();
        allOne.inc("c");
        allOne.printState();
        System.out.println(" ==== " + allOne.getMaxKey());
    }
}