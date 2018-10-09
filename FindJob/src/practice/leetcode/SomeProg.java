package practice.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SomeProg {

    public static class FreqPair implements Comparable<FreqPair> {
        Integer num;
        Integer freq;

        public FreqPair(Integer n, Integer f) {
            num = n;
            freq = f;
        }

        public int compareTo(FreqPair fp) {
            return (this.freq - fp.freq);
        }
    }

    static void customSort(int[] arr) {
        ArrayList<Integer> ip = new ArrayList<>();
        for (int i: arr) {
            ip.add(i);
        }
        Collections.sort(ip);
        ArrayList<FreqPair> fpList = new ArrayList<>();
        HashMap<Integer, Integer> m = new HashMap<>();

        for (Integer i: ip) {
            
        }
        for (int i = 0; i<ip.size(); ) {
            int curr = ip.get(i);
            int freq = 1;
            boolean flag = false;
            for (int j = i+1; j<ip.size(); j++) {
                int next = ip.get(j);
                if (curr == next) {
                    freq++;
                } else {
                    flag = true;
                    i = j;
                    break;
                }
            }
            if (!flag) {
                i++;
            }
            fpList.add(new FreqPair(curr, freq));
        }

        Collections.sort(fpList);

        for (FreqPair fp: fpList) {
            for (int i = 0; i < fp.freq; i++) {
                System.out.println(fp.num);
            }
        }
    }

    public static void main(String[] args) {
        int[] ip = {1,2,3,4,5,6,7,8,9};
        customSort(ip);

    }
}
