package practice.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class SubSeqPinterest {
    public static boolean isSubsequence(String s, String t) {
        if (s.length() == 0) return true;
        return findSubSeq(s, t, 0, 0);

    }

    private static boolean findSubSeq(String s, String t, int sptr, int tptr) {
        if (sptr == s.length()) return true;

        while (tptr < t.length() && s.charAt(sptr) != t.charAt(tptr)) {
            tptr++;
        }
        if (tptr == t.length()) return false;
        return findSubSeq(s, t, sptr+1, tptr+1);
    }

    public static void main(String[] args) {
        System.out.println(isSubsequence("afd", "abdafc"));
    }

}
