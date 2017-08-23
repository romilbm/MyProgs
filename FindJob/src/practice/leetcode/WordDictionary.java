package practice.leetcode;

import java.util.*;
public class WordDictionary {

    public static class TNode {
        char val;
        Set<TNode> children;
        public TNode(char c) {
            this.val = c;
            children = new HashSet<TNode>();
        }
    }

    TNode root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TNode(';');
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TNode curr = root;
        for (int i=0; i<word.length(); i++) {
            TNode newc = null;
            for (TNode c: curr.children) {
                if (c.val == word.charAt(i)) {
                    newc = c;
                    break;
                }
            }
            if (newc == null) {
                newc = new TNode(word.charAt(i));
                curr.children.add(newc);
            }
            curr = newc;
        }
        curr.children.add(new TNode('$'));
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        if (word.isEmpty()) return false;

        return mySearch(word, root);
    }

    private boolean mySearch(String word, TNode curr) {
        if (curr == null) return false;
        Set<TNode> children = curr.children;

        if (word.isEmpty()) {
            for (TNode c: children) {
                if (c.val == '$') {
                    return true;
                }
            }
            return false;
        }

        char fc = word.charAt(0);

        if (fc == '.') {
            for (TNode c: children) {
                boolean sr = mySearch(word.substring(1),c);
                if (sr == true) return true;
            }
            return false;
        } else {
            for (TNode c: children) {
                if (c.val == fc) return mySearch(word.substring(1),c);
            }
            return false;
        }
    }

    public static void main(String[] args) {
        WordDictionary wd = new WordDictionary();
        wd.addWord("a");
        wd.addWord("ab");
        wd.search("a");
    }
}
