package practice.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class LC15 {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int l=0, r=nums.length-1;
        List<List<Integer>> ret = new ArrayList<>();
        HashSet<List<Integer>> set = new HashSet<>();

        while (r-l >= 2) {
            //move l to right
            int tsum = -nums[l];
            int m = l+1;
            while (m < r) {
                List<Integer> subl = new ArrayList<>();
                int sum = nums[m] + nums[r];
                if (sum > tsum) {
                    while (m < r && nums[r] == nums[r-1]) {r--;}
                    r--;
                } else if (sum < tsum){
                    while (m < r && nums[m] == nums[m+1]) {m++;}
                    m++;
                } else {
                    subl.add(nums[l]);
                    subl.add(nums[m]);
                    subl.add(nums[r]);
                    if (!set.contains(subl)) {
                        set.add(subl);
                        ret.add(subl);
                    }
                    while (m < r && nums[r] == nums[r-1]) {r--;}
                    while (m < r && nums[m] == nums[m+1]) {m++;}
                    r--;
                    m++;
                }
            }
            l++;
        }
        return ret;
    }

    public static void main(String[] args) {
        LC15 lc15 = new LC15();
        int[] ip = {-2,0,1,1,2};
//        int[] ip = {-1,0,1,2,-1,-4};
        System.out.println(lc15.threeSum(ip));
    }
}


