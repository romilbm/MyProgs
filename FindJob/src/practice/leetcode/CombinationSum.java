package practice.leetcode;

import java.util.*;


public class CombinationSum {
    Map<Integer, Set<List<Integer>>> map = new HashMap<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> cand = new ArrayList<>();
        for (int c: candidates) cand.add(c);
        Collections.sort(cand);
        Set<List<Integer>> ans = combo(cand, target, 0);
        List<List<Integer>> ret = new ArrayList<>();
        for (List<Integer> l: ans) {
            ret.add(l);
        }

        return ret;
    }

    private Set<List<Integer>> combo(List<Integer> can, int t, int start) {
        Set<List<Integer>> ret = new HashSet<>();
        if (t < 0 || can.get(0) > t || start >= can.size()) return ret;
        if (map.containsKey(t)) return map.get(t);

        for (int i=start; i<can.size(); i++) {
            int num = can.get(i);
            if (t < num) continue;
            int sub = num;
            List<Integer> toAdd = new ArrayList<>();
            while (t-sub > 0) {
                toAdd.add(num);
                Set<List<Integer>> subset = combo(can, t-sub, i+1);
                for (List<Integer> l: subset) {
                    l.addAll(toAdd);
                    ret.add(l);
                }
                sub += num;
            }
            if (num == t) {
                ret.add(new ArrayList(Arrays.asList(num)));
            }
        }

        map.put(t, ret);
        return ret;
    }

    public static void main(String[] args) {
        CombinationSum cs = new CombinationSum();
        int[] ip = new int[] {1};
        List<List<Integer>> ans = cs.combinationSum(ip,2);
        System.out.println(ans);
    }

}
