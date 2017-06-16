package practice.leetcode;

import java.util.*;

public class WordDistance {

    private final int MAX = Integer.MAX_VALUE-1;
    private HashMap<String, ArrayList<Integer>> lookup;

    public WordDistance(String[] words) {
        lookup = new HashMap<String, ArrayList<Integer>>();
        populateLookup(words);
    }

    public int shortest(String word1, String word2) {
        ArrayList<Integer> pos1 = lookup.get(word1);
        ArrayList<Integer> pos2 = lookup.get(word2);

        ArrayList<Integer> smaller;
        ArrayList<Integer> larger;
        if (pos1.size() > pos2.size()) {
            smaller = pos2;
            larger = pos1;
        } else {
            larger = pos2;
            smaller = pos1;
        }

        int start = smaller.get(0);
        int ctr = 0;
        int min = MAX;
        while (ctr < larger.size()) {
            int diff = Math.abs(start - larger.get(ctr));
            if(diff < min) {
                min = diff;
            }
            if (larger.get(ctr) > start) break;
            ctr++;
        }

        return getMinDist(smaller, larger, min);
    }

    private int getMinDist(ArrayList<Integer> smaller, ArrayList<Integer> larger, int min) {
        if (smaller.size() == 1) return min;

        HashSet<Integer> lSet = new HashSet<Integer>();
        lSet.addAll(larger);

        for (int j = 1; j < smaller.size(); j++) {
            int minToCheck = min - 1;
            while (minToCheck > 0) {
                if (lSet.contains(smaller.get(j) - minToCheck)
                        || lSet.contains(smaller.get(j) + minToCheck)) {
                    min = minToCheck;
                }
                minToCheck--;
            }

            if (min == 1) return 1;
        }
        return min;
    }

    private void populateLookup(String[] words) {
        for(int i=0; i < words.length;i++) {
            ArrayList<Integer> posns = lookup.get(words[i]);
            if (posns == null) {
                posns = new ArrayList<Integer>();
                posns.add(i);
                lookup.put(words[i], posns);
                continue;
            } else {
                posns.add(i);
            }
        }
    }

    public static void main(String[] args) {
        String [] words = {"practice","makes","perfect","coding","makes"};
        WordDistance obj = new WordDistance(words);
        int s = obj.shortest("makes","coding");
        System.out.println(s);
    }
}
