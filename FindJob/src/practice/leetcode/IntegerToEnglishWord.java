package practice.leetcode;

import java.util.HashMap;

public class IntegerToEnglishWord {
    private static String[] groups = {"Billion", "Million", "Thousand", ""};
    private static HashMap<String, String> specialNames;

    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        if (specialNames == null) initSpecialNames();
        StringBuilder ret = new StringBuilder();
        String numstr = "" + num;
        if (numstr.length() == 1) return specialNames.get(numstr);

        StringBuilder sb = new StringBuilder();
        sb.append(numstr);
        sb.reverse();
        numstr = sb.toString();

        String[] numgrp = new String[4];
        int numgrpptr = 3;
        int startSub = 0;
        for (int i=0; i < numstr.length(); i++) {
            if (i % 3 == 2) {
                String s = convertToWord(numstr.substring(startSub, i+1));
                if (!s.isEmpty()) {
                    s += " " + groups[numgrpptr];
                    numgrp[numgrpptr] = s;
                }
                numgrpptr--;
                startSub = i+1;
            }
        }

        if (startSub < numstr.length()) {
            String s = convertToWord(numstr.substring(startSub));
            if (!s.isEmpty()) {
                s += " " + groups[numgrpptr];
                numgrp[numgrpptr] = s;
            }
        }

        for (int k = 0; k < 4; k++) {
            if (numgrp[k] != null) ret.append(numgrp[k] + " ");
        }

        return ret.toString().trim();
    }

    private String convertToWord(String s) {
        StringBuilder ret = new StringBuilder();

        if (s.length() == 3) {
            if (s.charAt(2) != '0') {
                char c = s.charAt(2);
                String hun = specialNames.get(c + "");
                ret.append(hun + " " + "Hundred ");
                s = s.substring(0,2);
            } else {
                s = s.substring(0,2);
            }
        }

        if (s.length() == 2) {
            if ((s.charAt(1) == '0')) {
                s = s.substring(0,1);
            } else if (s.charAt(1) == '1') {
                String ten = specialNames.get(new StringBuilder(s).reverse().toString());
                ret.append(ten + " ");
                s = "";
            } else {
                char c = s.charAt(1);
                String ten = specialNames.get(c + "0");
                ret.append(ten + " ");
                s = s.substring(0,1);
            }

        }

        if (s.length() == 1) {
            String last = specialNames.get(s);
            if (last != null) ret.append(last);
        }

        return ret.toString().trim();
    }

    private void initSpecialNames() {
        specialNames = new HashMap<>();
        specialNames.put("1", "One");
        specialNames.put("2", "Two");
        specialNames.put("3", "Three");
        specialNames.put("4", "Four");
        specialNames.put("5", "Five");
        specialNames.put("6", "Six");
        specialNames.put("7", "Seven");
        specialNames.put("8", "Eight");
        specialNames.put("9", "Nine");
        specialNames.put("10", "Ten");
        specialNames.put("11", "Eleven");
        specialNames.put("12", "Twelve");
        specialNames.put("13", "Thirteen");
        specialNames.put("14", "Fourteen");
        specialNames.put("15", "Fifteen");
        specialNames.put("16", "Sixteen");
        specialNames.put("17", "Seventeen");
        specialNames.put("18", "Eighteen");
        specialNames.put("19", "Nineteen");
        specialNames.put("20", "Twenty");
        specialNames.put("30", "Thirty");
        specialNames.put("40", "Forty");
        specialNames.put("50", "Fifty");
        specialNames.put("60", "Sixty");
        specialNames.put("70", "Seventy");
        specialNames.put("80", "Eighty");
        specialNames.put("90", "Ninety");
    }

    public static void main(String[] args) {
        IntegerToEnglishWord ie = new IntegerToEnglishWord();
//        System.out.println(ie.numberToWords(123));
//        System.out.println(ie.numberToWords(101));
//        System.out.println(ie.numberToWords(12345));
        System.out.println(ie.numberToWords(1000000));

    }
}
