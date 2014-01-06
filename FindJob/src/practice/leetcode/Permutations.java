package practice.leetcode;

import java.util.*;

/**
 * Created by romil on 1/5/14.
 */
public class Permutations {
    Set<ArrayList<Integer>> allCombos = new HashSet<ArrayList<Integer>>();

    public static ArrayList<ArrayList<Integer>> permuteUnique(int[] num){
        HashSet<ArrayList<Integer>> allCombos = new HashSet<ArrayList<Integer>>();

        if(num.length == 1){
            ArrayList<Integer> combination = new ArrayList<Integer>();
            combination.add(num[0]);
            allCombos.add(combination);
        } else {
            for(int i = 0; i < num.length; i++){
                int[] numsToSend = new int[num.length - 1];
                for(int j=0, k=0; j< num.length; j++){
                    if(j != i){
                        numsToSend[k] = num[j];
                        k++;
                    }
                }
                ArrayList<ArrayList<Integer>> recCombos = permuteUnique(numsToSend);
                for(ArrayList<Integer> combination : recCombos){
                    combination.add(num[i]);
                }
                allCombos.addAll(recCombos);
            }
        }
        return new ArrayList<ArrayList<Integer>>(allCombos);
    }

    public ArrayList<ArrayList<Integer>> permuteUnique2(int[] num){
        permute(num, 0, num.length-1);
        return new ArrayList<ArrayList<Integer>>(allCombos);
    }

    private void permute(int[] a, int i, int n){
        int j;
        if (i == n){
            ArrayList<Integer> combo = new ArrayList<Integer>();
            for(int t:a){
                combo.add(t);
            }
            allCombos.add(combo);
        } else {
            for (j = i; j <= n; j++){
                a = swap(a,i,j);
                permute(a, i+1, n);
                a = swap(a,i,j); //backtrack
            }
        }
    }

    private int[] swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        return a;
    }

    public static void main(String args[]){
        int[] input = {3,3,0,0,2,3,2};
        //ArrayList<ArrayList<Integer>> soln = permuteUnique(input);
        Permutations p = new Permutations();
        ArrayList<ArrayList<Integer>> soln = p.permuteUnique2(input);
        System.out.println(soln);
        System.out.println(soln.size());
    }
}
