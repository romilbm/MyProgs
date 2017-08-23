package practice.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class MyOwnClass {
    public int findMin(int[] nums) {
        if (nums.length == 1) return nums[0];


        int lo = 0;
        int hi = nums.length - 1;

        while (lo == hi || Math.abs(lo-hi) != 1) {
            int mid = (lo + hi) / 2;
            if (nums[lo] < nums[mid] && nums[mid] > nums[hi]) {
                if (nums[lo] < nums[hi]) {
                    hi = mid;
                } else {
                    lo = mid;
                }
            } else if (nums[lo] > nums[mid] && nums[mid] < nums[hi]) {
                hi = mid;
            } else {
                break;
            }
        }

        if (lo == hi)
            return nums[lo];

        return Math.min(nums[lo], nums[hi]);
    }

    public double myPow(double x, int n) {
        if (n == 0 || x == 1) return 1;
        if (n == 1) return x;

        int e = n;
        double ans = x;
        if (n < 0 && n != Integer.MIN_VALUE) {
            e = -n;
        } else if (n == Integer.MIN_VALUE){
            e = Integer.MAX_VALUE;
            ans *= x;
        }
        Deque<Double> stack = new ArrayDeque<>();

        while (e > 1) {
            if (e % 2 == 0) {
                stack.push(1.0);
                e = e / 2;
            } else {
                stack.push(x);
                e = (e-1) / 2;
            }
        }

        double base = x;
        while (stack.peek() != null) {
            ans =  base * base * stack.pop();
            base = ans;
        }

        if (n < 0) return 1/ans;
        return ans;
    }

    public static void main(String[] args) {
        MyOwnClass moc = new MyOwnClass();

//        System.out.println(moc.findMin(new int[] {3,1,2}));
        System.out.println(moc.myPow(2, -2147483647));
//        System.out.println(-5%2);
    }

}
