package practice.leetcode;

import java.util.*;

public class WordBreak {
    HashMap<Integer, HashSet<String>> lengthMap = new HashMap<>();
    ArrayList<Integer> lengths = new ArrayList<>();

    public boolean wordBreak(String s, List<String> wordDict) {

        for (String word : wordDict) {
            int len = word.length();
            if (lengthMap.containsKey(len)) {
                HashSet<String> lenWords = lengthMap.get(len);
                lenWords.add(word);
            } else {
                HashSet<String> lenWords = new HashSet<>();
                lenWords.add(word);
                lengthMap.put(len, lenWords);
                lengths.add(len);
            }
        }

        Collections.sort(lengths);

        for (int length : lengths) {
            if (length > s.length()) return false;

            if (length == s.length()) {
                if (checkWord(s.substring(0, length))) return true;
            } else {
                if (checkWord(s.substring(0, length)) && checkWord(s.substring(length))) return true;
            }
        }

        return false;
    }

    private boolean checkWord(String substr) {
        HashSet<String> lenWords = lengthMap.get(substr.length());
        if (lenWords == null) return false;
        if (!lenWords.contains(substr)) return false;
        return true;
    }

    public static void main(String[] args) {
        WordBreak wb = new WordBreak();
        String s = "b";
        List<String> wordDict = new ArrayList<>(Arrays.asList("a"));
        System.out.println(wb.wordBreak(s, wordDict));
//        System.out.println("a".substring(1));
    }
}