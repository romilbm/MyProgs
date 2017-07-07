package practice.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

public class AlienOrder {

    public static class TrieNode {
        char c;
        HashMap<Character, Integer> childIndexMap;
        ArrayList<TrieNode> child;
        TrieNode(char c) {
            this.c = c;
        }
    }

    public static class LLNode {
        char c;
        LLNode prev;
        LLNode next;
        LLNode(char c) {
            this.c = c;
        }
        LLNode(TrieNode tn) {
            this.c = tn.c;
        }
    }
    public String alienOrder(String[] words) {
        //init the trie
//
//        TrieNode root = new TrieNode(';');
//
//        LLNode start = new LLNode(root);
//        LLNode end = new LLNode(root);
//        start.next = end;
//        end.prev = start;
//
//        buildTrie(words, root);
//
//        Deque<TrieNode> q = new ArrayDeque<>();
//        HashMap<Character, LLNode> lookup = new HashMap<>();
//        q.add(root);
//
//        while (!q.isEmpty()) {
//            TrieNode curr = q.poll();
//
//            //get the children.. their order is determined.
//            ArrayList<TrieNode> child = curr.child;
//            boolean canIns = true;
//
//            if (child.size() < 2) {
//                canIns = false;
//            }
//            int lo = 0;
//            for (int hi = 0; hi<child.size(); hi++) {
//                q.offer(child.get(hi));
//
//                if (canIns && lookup.containsKey(child.get(hi).c)) {
//                    if (hi-lo-1 > 0) insertBefore(lo, hi, child, lookup);
//                    lo = hi;
//                }
//
//            }
//
//            if (canIns) end = insertAtEnd(lo, child, end, lookup);
//
//
//
//        }

        return "";
    }

    private void buildTrie(String[] words, TrieNode root) {
        for (String s: words) {
            TrieNode curr = root;
            for (int i = 0; i < s.length(); i++) {
                if (curr.childIndexMap.containsKey(s.charAt(i))) {
                    curr = curr.child.get(curr.childIndexMap.get(s.charAt(i)));
                } else {
                    TrieNode n = new TrieNode(s.charAt(i));
                    curr.child.add(n);
                    curr.childIndexMap.put(s.charAt(i), curr.child.size()-1);
                    curr = n;
                }
            }
        }
    }
}
