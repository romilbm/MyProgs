package practice.leetcode;

import java.util.*;

/**
 * Created by romil on 7/12/17.
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        if (nums.length < 3) return ret;

        for (int i=0; i<nums.length-1; i++) {
            if (visited.contains(nums[i])) continue;
            int swap = nums[i];
            nums[i] = nums[nums.length-1];
            nums[nums.length-1] = swap;
            List<List<Integer>> temp = findTwo(nums, 0-swap);
            if (!temp.isEmpty()) {
                for (List<Integer> l: temp) {
                    l.add(swap);
                    ret.add(l);
                }
            }
            visited.add(swap);
        }

        return ret;
    }

    List<List<Integer>> findTwo(int[] nums, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        HashMap<Integer, Integer> allnums = new HashMap<>();
        for (int ctr = 0; ctr < nums.length-1; ctr++) {
            int i = nums[ctr];
            if (allnums.containsKey(i)) {
                int count = allnums.get(i);
                allnums.put(i, count+1);
            } else {
                allnums.put(i, 1);
            }
        }

        for (int ctr = 0; ctr < nums.length-1; ctr++) {
            int i = nums[ctr];
            Integer count = allnums.get(i);
            if (count == null || count == 0) continue;
            Integer search = target - i;

            if (allnums.containsKey(search) && allnums.get(search) > 0) {
                ArrayList<Integer> t = new ArrayList<>(Arrays.asList(i, target-i));
                allnums.remove(search);
                ret.add(t);
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        ThreeSum ts = new ThreeSum();
        int[] ip = new int[]{-1,0,1,2,-1,-4};
        System.out.println(ts.threeSum(ip));
    }
}
