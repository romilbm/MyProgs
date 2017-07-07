package practice.leetcode;

import java.util.*;

public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        if (s.isEmpty()) return false;
        HashSet<String> dict = new HashSet<>();
        for (String w:wordDict) {
            dict.add(w);
        }

        return myWordBreak(s, 0, dict, new HashMap<>());
    }

    private boolean myWordBreak(String s, int subStrStart, HashSet<String> dict, HashMap<Integer, Boolean> substrStartLookup) {
        if (subStrStart == s.length()) return true;

        if (substrStartLookup.containsKey(subStrStart)) return substrStartLookup.get(subStrStart);

        for (int i=subStrStart+1; i<=s.length(); i++) {
            String substr = s.substring(subStrStart, i);
            if (!dict.contains(substr)) continue;
            if (myWordBreak(s, i, dict, substrStartLookup)) {
                substrStartLookup.put(subStrStart, true);
                return true;
            }
        }
        substrStartLookup.put(subStrStart, false);
        return false;
    }

    public static void main(String[] args) {
        WordBreak wb = new WordBreak();
        String s = "leetcode";
        List<String> wordDict = new ArrayList<>(Arrays.asList("leet", "code"));
        System.out.println(wb.wordBreak(s, wordDict));
    }
}