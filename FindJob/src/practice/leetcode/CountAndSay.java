package practice.leetcode;

/**
 * Created by romil on 7/17/17.
 */
public class CountAndSay {
    public String countAndSay(int n) {
        if (n == 1) return "1";

        String before = countAndSay(n-1);
        StringBuffer say = new StringBuffer();
        char curr = before.charAt(0);
        int count = 0;
        for(int i=0; i<before.length(); i++) {
            if (before.charAt(i) == curr) {
                count++;
            } else {
                say.append("" + count);
                say.append("" + curr);
                curr = before.charAt(i);
                count=1;
            }
        }

        say.append("" + count);
        say.append("" + curr);
        return say.toString();
    }

    public static void main(String[] args) {
        CountAndSay cns = new CountAndSay();
        System.out.println(cns.countAndSay(8));
    }
}
