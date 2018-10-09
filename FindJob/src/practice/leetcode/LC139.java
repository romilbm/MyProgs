package practice.leetcode;

import java.util.*;

public class LC139 {
    HashMap<Integer, Boolean> cache = new HashMap<>();

    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> dict = new HashSet<>();
        dict.addAll(wordDict);

        return isValid(s, dict, 0, 1);
    }

    private boolean isValid(String s, HashSet<String> dict, int l, int r) {
        if (r > s.length()) return true;
        if (cache.containsKey(r)) return cache.get(r);

        while (r <= s.length()) {
            String sub = s.substring(l,r);
            if (dict.contains(sub)) {
                boolean rest = isValid(s, dict, r, r+1);
                if (rest) {
                    cache.put(r, true);
                    return true;
                } else {
                    cache.put(r, false);
                }
            }
            r++;
        }
        return false;
    }

    public static void main(String[] args) {
        LC139 lc139 = new LC139();
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        ArrayList<String> wd = new ArrayList<>(Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"));
        System.out.println(lc139.wordBreak(s, wd));

        System.out.println(s.substring(0,s.length()));
    }
}
