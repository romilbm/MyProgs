package practice.leetcode;

import java.util.*;

public class WordDistance {

    private HashMap<String, ArrayList<Integer>> lookup;

    public WordDistance(String[] words) {
        lookup = new HashMap<>();
        populateLookup(words);
    }

    public int shortest(String word1, String word2) {
        ArrayList<Integer> l1 = lookup.get(word1);
        ArrayList<Integer> l2 = lookup.get(word2);

        int sd = Math.abs(l1.get(0) - l2.get(0));
        int pu = 0;
        int l1ptr = 0;
        int l2ptr = 0;

        int[] temp = new int[l1.size() + l2.size()];
        int tempptr = 0;

        while (l1ptr < l1.size() && l2ptr < l2.size()) {
            int cu;
            if (l1.get(l1ptr) < l2.get(l2ptr)) {
                temp[tempptr] = l1.get(l1ptr);
                l1ptr++;
                cu = 1;
            } else {
                temp[tempptr] = l2.get(l2ptr);
                l2ptr++;
                cu = 2;
            }
            if (tempptr != 0 && cu != pu) {
                int d = Math.abs(temp[tempptr-1] - temp[tempptr]);
                if (d < sd) {
                    sd = d;
                }
            }
            pu = cu;
            tempptr++;
        }

        int lastd = Integer.MAX_VALUE;
        if (l1ptr < l1.size() && pu == 2) {
            lastd = Math.abs(temp[tempptr-1] - l1.get(l1ptr));
        } else if (l2ptr < l2.size() && pu == 1) {
            lastd = Math.abs(temp[tempptr-1] - l2.get(l2ptr));
        }
        if (lastd < sd) return lastd;
        return sd;
    }

    private void populateLookup(String[] words) {
        for(int i=0; i < words.length;i++) {
            ArrayList<Integer> posns = lookup.get(words[i]);
            if (posns == null) {
                posns = new ArrayList<>();
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
        int s;
        s = obj.shortest("makes","coding");
        System.out.println(s);
        s = obj.shortest("practice","coding");
        System.out.println(s);

    }
}
