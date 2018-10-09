package practice.leetcode;

import java.util.Arrays;

public class LC681 {

    int[] max = {2, 9, 0, 5, 9};

    public String nextClosestTime(String time) {
        char[] ret = time.toCharArray();
        int[] nos = extractNos(time);
        nct(ret, time.length()-1, nos);
        return String.valueOf(ret);
    }

    private void nct(char[] time, int i, int[] nos) {
        if (time[i] == ':') {
            nct(time, i-1, nos);
            return;
        }

        if (i < 0) return;

        int p = Character.getNumericValue(time[i]);

        if (i == 1 && p == 3 && Character.getNumericValue(time[i-1]) == 2) {
            time[i] = Character.forDigit(nos[0], 10);
            return;
        }

        if (p == max[i]) {
            nct(time, i-1, nos);
            time[i] = Character.forDigit(nos[0], 10);
        } else {
            int nextMax = getNextMax(nos, p, i);
            if (nextMax > p) {
                time[i] = Character.forDigit(nextMax, 10);
            } else {
                nct(time, i-1, nos);
                time[i] = Character.forDigit(nos[0], 10);
            }
        }
    }

    private int[] extractNos(String time) {
        int ret[] = new int[5];
        for (int i = 0; i < time.length(); i++) {
            if (i == 2) {
                ret[i] = 10;
                continue;
            }
            ret[i] = Integer.parseInt(time.charAt(i) + "");
        }

        Arrays.sort(ret);
        return ret;
    }

    private int getNextMax(int[] nos, int n, int posn) {
        for (int i : nos) {
            if (i > n && i <= max[posn]) return i;
        }
        return n;
    }

    public static void main(String[] args) {
        LC681 lc681 = new LC681();
        System.out.println(lc681.nextClosestTime("23:59"));
    }

}
