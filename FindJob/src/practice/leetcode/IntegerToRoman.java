package practice.leetcode;

/**
 * Created by romil on 8/12/17.
 */
public class IntegerToRoman {
    public String intToRoman(int n) {
        int[] num = new int[] {1000, 500, 100, 50, 10, 5, 1};
        String[] rom = new String[] {"M","D","C","L","X","V","I"};

        StringBuilder sb = new StringBuilder();

        for (int i=0; i<7 && n>0; i++) {
            int div = num[i];
            int rep = (n/div);
            if (rep > 0 && rep < 4) {
                int ctr = rep;
                while (ctr > 0) {
                    sb.append(rom[i]);
                    ctr--;
                }
                i--;
            } else if (rep == 4) {
                sb.append(rom[i]);
                sb.append(rom[i-1]);
            } else {
                div = i%2 == 0 ? num[i]-num[i+2] : num[i]-num[i+1];
                rep = n/div;
                if (rep != 0) {
                    sb.append(i%2 == 0 ? rom[i+2] : rom[i+1]);
                    sb.append(rom[i]);
                }
            }
            n -= (rep * div);
        }

        return sb.toString();
    }

    public static void main(String args[]) {
        IntegerToRoman s = new IntegerToRoman();
        String ans = s.intToRoman(19);
        System.out.println(ans);
    }
}
