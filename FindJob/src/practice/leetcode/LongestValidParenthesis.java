package practice.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.Assert.assertEquals;

/**
 * Created by romil on 6/27/17.
 */
public class LongestValidParenthesis {
    int longestValidParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int longest = 0;
        int l = 0;
        int tl = 0;
        boolean isNested = false;
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')' && stack.isEmpty()) {
                l += tl;
                if (l > longest) {
                    longest = l;
                }
                tl = 0;
                l = 0;
                continue;
            }

            if (c == ')') {
                int j = stack.pop();
                if (isNested) {
                    tl += i-j+1;
                } else {
                    tl += i-j+1;
                    l += i-j+1;
                }
            }

            if (c == '(') {
                if (stack.isEmpty()) {
                    isNested = false;
                    if (tl > 0) {
                        l += tl;
                        tl = 0;
                    }
                } else {
                    isNested = true;
                }
                stack.push(i);

            }
        }

        l+=tl;

        if (l > longest) {
            longest = l;
        }

        return longest;

    }

    public static void main(String[] args) {
        LongestValidParenthesis lvp = new LongestValidParenthesis();
        int ans = 0;
//        ans = lvp.longestValidParentheses("");
//        assertEquals(0, ans);
//
//        ans = lvp.longestValidParentheses("(()");
//        assertEquals(2, ans);
//
//        ans = lvp.longestValidParentheses(")()())");
//        assertEquals(4, ans);
//
//        ans = lvp.longestValidParentheses("()(())");
//        assertEquals(6, ans);
//
//        ans = lvp.longestValidParentheses("(()))(()())");
//        assertEquals(6, ans);

        ans = lvp.longestValidParentheses("(()()");
        assertEquals(4, ans);
    }
}
