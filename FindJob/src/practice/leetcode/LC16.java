package practice.leetcode;

import java.util.*;

public class LC16 {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int l = 0;
        int leastSum = 0;

        int closest = Integer.MAX_VALUE;
        while (l+2 <= nums.length-1) {
            int sum = 0;
            int m = l+1;
            int r = nums.length-1;
            while (m < r) {
                sum = nums[l] + nums[m] + nums[r];
                if (sum == target) return sum;

                if (sum < target) {
                    while (m < r && nums[m] == nums[m+1]) { m++; }
                    m++;
                } else {
                    while (r > m && nums[r] == nums[r-1]) { r--; }
                    r--;
                }
                if (Math.abs(sum-target) < closest) {
                    closest = Math.abs(sum-target);
                    leastSum = sum;
                }
            }
            l++;
        }
        return leastSum;
    }

    public static void main(String[] args) {
        LC16 lc16 = new LC16();
        System.out.println(lc16.threeSumClosest(new int[] {1,2,4,8,16,32,64,128}, 82));
    }
}
