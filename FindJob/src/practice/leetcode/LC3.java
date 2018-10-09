package practice.leetcode;

import java.util.HashSet;

/**
 * Longest Substring Without Repeating Characters
 */
public class LC3 {
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) return 0;
        int[] occChars = new int[255];
        int sptr = 0, eptr = 0;
        int length = 0;
        int maxLen = 0;

        while (eptr < s.length()) {
            //try to increase length by 1
            if (occChars[s.charAt(eptr)] == 0) {
                length++;
                maxLen = Math.max(maxLen, length);
                occChars[s.charAt(eptr)]++;
                eptr++;
            } else {
                occChars[s.charAt(eptr)]++;
                //bring start ptr to a position where the char has only one occ or it reaches end ptr.
                do {
                    occChars[s.charAt(sptr)]--;
                    sptr++;
                }
                while (occChars[s.charAt(sptr)] != 1 && sptr != eptr);
                //re calculate the length
                length = eptr-sptr+1;
                eptr++;
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
//        System.out.println(lengthOfLongestSubstring("abcadbcbb"));
//        System.out.println(lengthOfLongestSubstring("pwwkew"));
//        System.out.println(lengthOfLongestSubstring("dvdf"));
        System.out.println(lengthOfLongestSubstring("nfpdmpi"));
    }

}
