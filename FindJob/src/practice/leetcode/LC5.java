package practice.leetcode;

import java.util.HashMap;

/**
 * Longest Palindromic Substring
 */
public class LC5 {

    public static class Pair {
        int l, r;

        public Pair(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    public static class LPRes {
        int len;
        int l;
        int r;

        public LPRes(int len, int l, int r) {
            this.len = len;
            this.l = l;
            this.r = r;
        }

        @Override
        public String toString() {
            return len + " " + l + " " + r;
        }
    }
    private int lo, maxLen;

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2)
            return s;

        for (int i = 0; i < len-1; i++) {
            extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
            extendPalindrome(s, i, i+1); //assume even length.
        }
        return s.substring(lo, lo + maxLen);
    }

    private void extendPalindrome(String s, int j, int k) {
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }
        if (maxLen < k - j - 1) {
            lo = j + 1;
            maxLen = k - j - 1;
        }
    }
//    public static String longestPalindrome(String s) {
//        if (s == null || s.isEmpty()) return "";
//        int l = 0, r = s.length()-1;
////        HashMap<Pair, LPRes> map = new HashMap<>();
//        LPRes lpRes = lp(s, l, r);
//        return s.substring(lpRes.l,lpRes.r+1);
//    }
//
//    private static LPRes lp(String s, int l, int r) {
//        int ll = l;
//        int lr = r;
//        while (ll <= lr) {
//            if (isPalindrome(s,ll,lr)) {
//                break;
//            } else {
//                lr--;
//            }
//        }
//
//        int rl = l;
//        int rr = r;
//        while (rl <= rr) {
//            if (isPalindrome(s,rl,rr)) {
//                break;
//            } else {
//                rl++;
//            }
//        }
//
//        if ()
//        return null;
//    }

//    private static LPRes lp(String s, int l, int r, HashMap<Pair, LPRes> map, int max) {
//        if (l == r ) return new LPRes(1, l,r);
//        if (r-l+1 <= max) return new LPRes(0, l,r);
//
//        int ol = l;
//        int or = r;
//
//        Pair key = new Pair(ol,or);
//        if (map.containsKey(key)) return map.get(key);
//
//        if (isPalindrome(s, ol, or)) {
//            LPRes lpRes = new LPRes(or-ol+1, ol, or);
//            map.put(key, lpRes);
//            return lpRes;
//        }
//
//        LPRes left = lp(s, l, r-1, map, max);
//        max = Math.max(max, left.len);
//        LPRes right = lp(s, l+1, r, map, max);
//        if (left.len > right.len) {
//            map.put(key, left);
//            return left;
//        } else {
//            map.put(key, right);
//            return right;
//        }
//
//    }

    private static boolean isPalindrome(String s, int l, int r) {
        while (l <= r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        System.out.println(longestPalindrome("babadada"));
        LC5 lc5 = new LC5();
        System.out.println(lc5.longestPalindrome("babaddtattarrattatddetartrateedredividerb"));
//        System.out.println(longestPalindrome("babaddtattarrattatddetartrateedredividerb"));
//        System.out.println(longestPalindrome("babaddtattarrattatdde"));
    }
}
