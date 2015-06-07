package cci;

public class squareroot {

    static float PRECISION = 0.000001f;

    public static float findsqrt(float num, float l, float r){
        float leftDiff = Math.abs((l*l) - num);
        float rightDiff = Math.abs((r * r) - num);

        if(leftDiff <= PRECISION){
           return l;
        }

        if(rightDiff <= PRECISION){
            return r;
        }

        if(leftDiff < rightDiff){
            return findsqrt(num, l, (l+r)/2);
        } else if(rightDiff < leftDiff) {
            return findsqrt(num, (l+r)/2, r);
        } else {
            return (l+r)/2;
        }
    }

    int findStartLimit(float num){
        int t = (int)num/2;

        return 0;
    }

    public static void main(String[] args) {
        float num = 9.13f;
        float ans = findsqrt(num, 3, 4);
        System.out.println(ans);
        System.out.println(ans*ans);
    }
}
