package practice.leetcode;

public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        s = s.trim();
        if (s.isEmpty()) return true;

        int l = 0;
        int r = s.length()-1;

        while (l <= r) {

            while ((l < s.length()) && !(isValidChar(s.charAt(l)))) {
                l++;
            }
            while ((r >= 0) && !(isValidChar(s.charAt(r)))) {
                r--;
            }

            if (l>r) return true;
            char lc = s.charAt(l);
            char rc = s.charAt(r);

            if (lc>='a') {
                lc = (char) ('A'+(lc-'a'));
            }

            if (rc>='a') {
                rc = (char) ('A'+(rc-'a'));
            }

            System.out.println(lc + " " + rc);
            if (lc != rc) return false;
            l++;
            r--;
        }

        return true;
    }

    private boolean isValidChar(char l) {
        if (l >= 'A' && l <= 'Z') return true;
        if (l >= 'a' && l <= 'z') return true;
        if (l >= '0' && l <= '9') return true;
        return false;
    }

    public static void main(String[] args) {
        ValidPalindrome vp = new ValidPalindrome();

        vp.isPalindrome(".");
    }
}
