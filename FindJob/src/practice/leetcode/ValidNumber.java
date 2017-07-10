package practice.leetcode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidNumber {
    public boolean isNumber(String s) {
        s = s.trim();
        if (s.endsWith("e")) return false;
        String[] parts = s.split("e");

        if (parts.length == 0) return false;

        if (parts.length == 1) {
            return isMyNumber(parts[0], false);
        }

        if (parts.length > 2) {
            return false;
        }

        if (!isMyNumber(parts[0], true)) return false;

        if (parts[1].charAt(0) != '+' && parts[1].charAt(0) != '-') return false;

        return isMyNumber(parts[1].substring(1), true);

    }

    private boolean isMyNumber(String s, boolean isExp) {
        if (s.isEmpty()) return false;

        if (isExp && s.equals("0")) return false;

        if (isBeginNegative(s)) {
            //add +1 to account for the char - removed while matching
            int endOfDigits = isBeginDigits(s,1)+1;
            if (endOfDigits == 0) return false;
            if (endOfDigits >= s.length()
                    || s.endsWith(".")
                    || (isBeginPoint(s, endOfDigits) && isEndingNums(s, endOfDigits+1)))
            {
                return true;
            }

            return false;
        }

        if (!isExp && isBeginOx(s)) {
            if (isHexDigit(s, 2)) return true;
            return false;
        }


        if (isBeginPoint(s,0)) {
            if (isEndingNums(s, 1)) return true;
            return false;
        }

        int endOfDigits = isBeginDigits(s,0);
        if (endOfDigits == -1) return false;
        if (endOfDigits >= s.length()
                || s.endsWith(".")
                || (isBeginPoint(s, endOfDigits) && isEndingNums(s, endOfDigits+1)))
        {
            return true;
        }
        return false;
    }

    //1
    private boolean isBeginNegative(String s) {
        if (s.charAt(0) == '-') return true;
        return false;
    }

    //2
    private boolean isBeginOx(String s) {
        if (s.startsWith("Ox")) return true;
        return false;
    }

    //3
    private int isBeginDigits(String s, int index) {
        String patternString = "\\d+";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(s.substring(index));

        if (!matcher.lookingAt()) return -1;

        return matcher.end();
    }

    //5
    private boolean isBeginPoint(String s, int index) {
        return s.charAt(index) == '.';
    }

    //6
    private boolean isHexDigit(String s, int startIndex) {
        String patternString = "[[A-F]+|\\d+]+";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(s.substring(startIndex));

        return matcher.matches();
    }

    //7
    private boolean isEndingNums(String s, int startIndex) {
        String patternString = "\\d+";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(s.substring(startIndex));

        return matcher.matches();

    }

    public static void main(String[] args) {
        ValidNumber vn = new ValidNumber();
//        System.out.println(vn.isNumber("1"));
//        System.out.println(vn.isNumber("1.2"));
//        System.out.println(vn.isNumber("OxA12FE"));
//        System.out.println(vn.isNumber("-0.54"));
//        System.out.println(vn.isNumber("12345678"));
//        System.out.println(vn.isNumber("0.54e-1.2"));
        System.out.println(vn.isNumber("te1"));
//        System.out.println();
//        System.out.println(vn.isNumber("5e4e"));
//        System.out.println(vn.isNumber("ABC"));
//        System.out.println(vn.isNumber(".89."));
//        System.out.println(vn.isNumber("-1."));
    }

}
