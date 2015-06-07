package cci;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class test {
    public int next5value = 0;

    void resetVals() {
        next5value = 0;
    }

    String printNumbers(int f, int t) {
        if(f == 0 && t == 0) {
            return "-1";
        }
        StringBuffer s = new StringBuffer("");
        for(int ctr = 0; ctr<f; ctr++) {
            s.append("5");
        }

        for(int ctr = 0; ctr<t; ctr++) {
            s.append("3");
        }

        return s.toString();
    }

    void increment5value() { next5value += 5; }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        test s = new test();
        int t = in.nextInt();

        for(int i = 0; i < t; i++) {

            int N = in.nextInt();
            int numberOf3s = 0, numberOf5s = 0;

            do {

                numberOf3s = s.next5value;
                numberOf5s = N - numberOf3s;
                s.increment5value();
            } while (s.next5value <= N && numberOf5s % 3 != 0);

            if(numberOf5s % 3 != 0)
                numberOf5s = 0;

            String ans = s.printNumbers(numberOf5s, numberOf3s);
            System.out.println(ans);

            s.resetVals();
        }
    }
}