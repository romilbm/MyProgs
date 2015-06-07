package cci;

/*
 * To execute Java, please define "static void main" on a class
 * named Parenthesis.
 *
 * If you need more classes, simply define them inline.
 */

class Parenthesis {
    public static void main(String[] args) {
        Parenthesis s = new Parenthesis();
        s.parenthesis("", 3, 3, 0);
    }

    void parenthesis(String prefix, int o, int c, int balance) {
        if(o == 0 && c == 0 && balance == 0) {
            System.out.println(prefix);
        } else if (o >= 0 && c >= 0) {
            if (balance == 0) {
                prefix += "(";
                balance--;
                parenthesis(prefix, o-1, c, balance);
            } else if (balance < 0) {
                parenthesis(prefix + "(", o-1, c, balance-1);
                parenthesis(prefix + ")", o, c-1, balance+1);
            }
        }
    }
}