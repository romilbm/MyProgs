package practice.leetcode;

import java.util.Arrays;

public class LC33 {
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length-1;

        while (l < r) {
            int m = l + ((r-l)/2);
            if (nums[m] >= nums[l]) {
                //left in order and right out of order
                int lbin = binSearch(nums, target, l, m);
                if (lbin != -1) return lbin;
                l = m+1;
            } else if (nums[m] <= nums[r]) {
                //right in order
                int rbin = binSearch(nums, target, m, r);
                if (rbin != -1) return rbin;
                r = m-1;
            } else {
                //not pivoted
                return binSearch(nums, target, l, r);
            }
        }
        if (l == r && nums[l] == target) return l;
        return -1;
    }

    private int binSearch(int[] nums, int target, int l, int r) {
        if (l>r) return -1;

        int m = l + ((r-l)/2);

        if (nums[m] == target) return m;

        if (nums[m] < target) return binSearch(nums, target, m+1, r);
        else return binSearch(nums, target, l, m-1);

    }

    public static void main(String[] args) {
//        LC33 lc33 = new LC33();
//        int[] nums = {3,1};
//        System.out.println(lc33.search(nums, 1));

        char[] ip = {'c', 'b', 'a'};
        Arrays.sort(ip);
        System.out.println(ip);

    }
}
