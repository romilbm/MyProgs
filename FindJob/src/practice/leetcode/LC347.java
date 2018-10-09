package practice.leetcode;

import java.util.*;

public class LC347 {
    ArrayList<HashSet<Integer>> freqList = new ArrayList<>();
    HashMap<Integer, Integer> freqMap = new HashMap<>();

    public List<Integer> topKFrequent(int[] nums, int k) {
        for (int i:nums) {
            HashSet<Integer> curSet;
            if (freqMap.containsKey(i)) {
                int currFreq = freqMap.get(i);
                freqMap.put(i, currFreq+1);
                curSet = freqList.get(currFreq-1);
                curSet.remove(i);
                addToList(i, currFreq);
            } else {
                freqMap.put(i, 1);
                addToList(i, 0);
            }
        }

        List<Integer> ret = new ArrayList<>();
        for (int ctr = freqList.size()-1; ctr >= 0; ctr--) {
            HashSet<Integer> iSet = freqList.get(ctr);
            for (int i: iSet) {
                ret.add(i);
                if (ret.size() == k) return ret;
            }
        }
        return ret;
    }

    private void addToList(int num, int currFreq) {
        HashSet<Integer> highSet;
        if (freqList.size() > currFreq) {
            highSet = freqList.get(currFreq);
            highSet.add(num);
        } else {
            highSet = new HashSet<>();
            highSet.add(num);
            freqList.add(currFreq, highSet);
        }

    }


    public static void main(String[] args) {
        LC347 lc347 = new LC347();
        System.out.println(lc347.topKFrequent(new int[] {1,1,1,2,2,3}, 2));
//        Integer.MAX_VALUE;
    }
}
