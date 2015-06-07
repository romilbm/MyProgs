package cci;

import java.math.BigDecimal;

public class Solution3 {
    public static void main(String[] args) {
        BigDecimal bd = new BigDecimal(Float.toString(6.1249f));
        bd = bd.setScale(3, BigDecimal.ROUND_HALF_UP);
//        System.out.println(bd.floatValue());

        BigDecimal bd2 = new BigDecimal(Float.toString(bd.floatValue()));
        bd2 = bd2.setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(bd2.floatValue());
    }
}
