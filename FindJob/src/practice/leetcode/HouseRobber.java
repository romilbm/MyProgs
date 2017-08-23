package practice.leetcode;

import java.util.HashMap;

public class HouseRobber {

    HashMap<Integer, Integer> cache = new HashMap<>();

    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        return findMaxRob(nums, 0);
    }

    private int findMaxRob(int[] nums, int st) {
        if (st >= nums.length) return 0;

        if (cache.containsKey(st)) return cache.get(st);

        int max = nums[st] + findMaxRob(nums, st+2);
        st++;
        if (st < nums.length) {
            int temp = nums[st] + findMaxRob(nums, st+2);
            max = Math.max(temp, max);
        }

        cache.put(st, max);

        return max;
    }

    public static void main(String[] args) {
        HouseRobber hr = new HouseRobber();
        int[] ip = new int[] {1,2,1,0};
        int ans = hr.rob(ip);
        System.out.println(ans);
        String s = "";
        s.toCharArray();


    }
}
