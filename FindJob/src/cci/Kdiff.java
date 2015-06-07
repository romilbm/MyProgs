package cci;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Kdiff {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Set<BigInteger> bigIntSet = new HashSet<>();
        String firstLine = in.nextLine();
        String[] NK = firstLine.split(" ");
        Integer n = Integer.parseInt(NK[0]);
        BigInteger k = new BigInteger(NK[1]);

        int count = 0;
        String lineOfN = in.nextLine();
        String[] nums = lineOfN.split(" ");

        for(int i = 0; i < n; i++){
            bigIntSet.add(new BigInteger(nums[i]));
        }

        for(BigInteger x: bigIntSet){
            BigInteger possibleY = x.subtract(k);
            if(bigIntSet.contains(possibleY)) {
                count++;
            }
        }
        System.out.println(count);
    }
}
