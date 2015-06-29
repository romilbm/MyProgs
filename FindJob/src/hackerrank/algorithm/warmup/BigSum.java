package hackerrank.algorithm.warmup;

import java.util.*;

public class BigSum {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        in.nextLine();
        String integers[] = in.nextLine().split(" ");
        long sum = 0;

        for(String intAsString : integers){
            sum += Integer.parseInt(intAsString);
        }

        System.out.println(sum);

    }
}