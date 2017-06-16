package practice.leetcode;

import java.util.HashMap;

/**
 * Created by romil on 6/11/17.
 */
public class MaxSubArray {
    public static int maxSubArrayLen(int[] nums, int k) {
        int sum = 0, max = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            if (sum == k) {
                max = i + 1;
            }
            else if (map.containsKey(sum - k)) {
                max = Math.max(max, i - map.get(sum - k));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int [] num = {3, 1, -1, 5, -2};
        System.out.println(maxSubArrayLen(num, 3));
    }
}
