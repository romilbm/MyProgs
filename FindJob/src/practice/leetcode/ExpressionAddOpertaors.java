package practice.leetcode;

import java.util.*;

public class ExpressionAddOpertaors {
    HashMap<String,Set<String>> cache = new HashMap<>();
    char[] ops = {'+', '-', '*'};

    public List<String> addOperators(String num, int target) {
        ArrayList<String> ret = new ArrayList<>();
        if (num.isEmpty()) return ret;

        Set<String> exprs = getAllExpr(num);

        for (String exp : exprs) {
            Integer eval = evaluate(exp);
            if (eval != null && eval == target) ret.add(exp);
        }

        return ret;
    }

    private Set<String> getAllExpr(String num) {
        Set<String> ret = new HashSet<>();
        if (num.isEmpty()) return ret;

        if (cache.containsKey(num)) return cache.get(num);

        for (int i=1; i <= num.length()-1; i++) {
            Set<String> baseexp = new HashSet<>();
            baseexp.add(num.substring(0, i) + ops[0]);
            baseexp.add(num.substring(0, i) + ops[1]);
            baseexp.add(num.substring(0, i) + ops[2]);

            Set<String> rest = getAllExpr(num.substring(i));

            ret.addAll(combineExpr(baseexp, rest));
        }
        ret.add(num);

        cache.put(num, ret);
        return ret;
    }

    private Set<String> combineExpr(Set<String> base, Set<String> exp) {
        Set<String> ret = new HashSet<>();

        for (String b:base) {
            for (String e: exp) {
                ret.add(b+e);
            }
        }

        return ret;
    }

    private Integer evaluate(String s) {
        Integer sum = 0;

        char op = '.';
        int i=0;
        int start = 0;
        while (i<s.length() && s.charAt(i) != '+' && s.charAt(i) != '*' && s.charAt(i) != '-') i++;

        String firstOp = s.substring(start, i);

        if (firstOp.length() != 0 && firstOp.charAt(0) != '0' && firstOp.length() <= 9) {
            sum = Integer.parseInt(firstOp);
        } else {
            return null;
        }

        if (i >= s.length()) return sum;
        op = s.charAt(i);
        i++;
        start = i;


        while (i <= s.length()) {
            if (i < s.length() && s.charAt(i) != '+' && s.charAt(i) != '*' && s.charAt(i) != '-') {
                i++;
                continue;
            }

            String op2 = s.substring(start, i);
            Integer oper2;
            if (op2.length() != 0 && op2.charAt(0) != '0') {
                oper2 = Integer.parseInt(op2);
            } else {
                return null;
            }

            switch(op) {
                case '+':
                    sum += oper2;
                    break;
                case '-':
                    sum -= oper2;
                    break;
                case '*':
                    sum *= oper2;
                    break;
            }
            if (i < s.length()) op = s.charAt(i);
            start = i+1;
            i++;
        }

        return sum;
    }

    public static void main(String[] args) {
        ExpressionAddOpertaors eao = new ExpressionAddOpertaors();

        System.out.println(eao.addOperators("3456237490", 9191));
    }
}
