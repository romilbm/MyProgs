package practice.leetcode;

import java.util.*;

public class AllOne {

    HashMap<String, Integer> map;
    LinkedList<String> ll;

    /** Initialize your data structure here. */
    public AllOne() {
        map = new HashMap<>();
        ll = new LinkedList<>();
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        System.out.println("Ins:" + key);
        Integer curVal;
        if (!map.containsKey(key)) {
            ll.addLast(key);
            map.put(key,1);
            printState();
            return;
        } else {
            curVal = map.get(key);
        }
        map.put(key,++curVal);
        ll.remove(key);
        String curMaxKey = getMaxKey();

        //existing max, min vals .. either null or some value
        Integer curMaxVal = (curMaxKey ==  null || curMaxKey == "") ? Integer.MIN_VALUE : map.get(curMaxKey);


        if ((curMaxVal <= curVal && curMaxKey != key) || (ll.size() == 1)) {
            ll.addFirst(key);
        }
        printState();

    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        System.out.println("rem:" + key);
        if (!map.containsKey(key)) {
            printState();
            return;
        }
        Integer curVal = map.get(key);

        if (curVal == 1) {
            map.remove(key);
            ll.remove(key);
            printState();
            return;
        }
        map.put(key,--curVal);
        ll.remove(key);

        String curMinKey = getMinKey();

        Integer curMinVal = (curMinKey ==  null || curMinKey == "") ? Integer.MAX_VALUE : map.get(curMinKey);

        if ((curMinVal >= curVal && curMinKey != key) || (ll.size() == 1)) {
            ll.addLast(key);
            printState();
        }

        printState();
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        return ll.isEmpty() ? "" : ll.getFirst();
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return ll.isEmpty() ? "" : ll.getLast();
    }

    private void printState() {
        System.out.println("map:" + map);
        System.out.println("ll:" + ll);
        System.out.println("min = " + getMinKey());
        System.out.println("max = " + getMaxKey());
        System.out.println();
    }

    public static void main(String[] args) {
        AllOne allOne = new AllOne();
        allOne.inc("h");
        allOne.inc("g");
        allOne.inc("h");
        allOne.inc("h");
        System.out.println(allOne.getMaxKey());
        allOne.inc("l");
        allOne.inc("c");
        allOne.inc("l");
        allOne.dec("h");
        allOne.inc("l");
        allOne.inc("c");
        allOne.inc("c");
        System.out.println(allOne.getMaxKey());
    }
}