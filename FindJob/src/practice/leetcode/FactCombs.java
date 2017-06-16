package practice.leetcode;

import java.util.*;

/**
 * Created by romil on 6/7/17.
 */
public class FactCombs {

    HashMap<Integer,Set<List<Integer>>> cache = new HashMap<>();

    public List<List<Integer>> getFactors(int n) {
        Set<List<Integer>> ans = new HashSet<>();
        List<List<Integer>> ret = new ArrayList<>();

        if (n == 2) return ret;

        ans = myGetFactors(n);

        for (List<Integer> li : ans) {
            ret.add(li);
        }
        return ret;
    }

    public Set<List<Integer>> myGetFactors(int n) {
        Set<List<Integer>> facts = new HashSet<>();
        if (n == 1) return facts;
        if (n == 2) {
            List<Integer> fact = new ArrayList<>();
            fact.add(2);
            facts.add(fact);
            return facts;
        }
        if(cache.containsKey(n)) return cache.get(n);

        int sqrt = (int)Math.sqrt(n);
        for (int i=2; i<=sqrt ; i++) {
            if (n % i != 0) continue;
            int j = n/i;
            List<Integer> oneLast = new ArrayList<>();
            oneLast.add(i);
            oneLast.add(j);
            facts.add(oneLast);

            Set<List<Integer>> newfacts = myGetFactors(j);
            for (List<Integer> newfact : newfacts) {
                newfact.add(i);
                Collections.sort(newfact);
                facts.add(newfact);
            }
        }

        return facts;
    }

    public static void main(String[] args) {
        FactCombs fc = new FactCombs();

        System.out.println(fc.getFactors(2));
    }
}
