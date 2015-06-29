package exercise;

import org.apache.commons.lang3.tuple.MutablePair;

public class Stringfun {

    private int a;
    private int[] cache;

    public Stringfun(int a){
        this.a = a;
        cache = new int[a];
    }

    public Stringfun(Stringfun in){
        this.a = in.getA();
    }

    public void setA(int a){
        this.a = a;
    }

    public int getA(){
        return a;
    }

    public static void main(String[] args) {
        String a = "abcdef";

        String[] p = a.split("g");
//        for(String s:p)
//            System.out.println(s);
//        System.out.println(p.length);

        char[] c = a.toCharArray();

        for(int i=0; i<c.length; i++){
            char d = c[i];
            d = (char) (d + 2);
            c[i] = d;
        }

        String s = new String(c);

//        System.out.println(s);

        int[][] mat = new int[][] {
                new int[] {},
                new int[] {}
        };

        String s1 = "abc";
        System.out.println("Test subs: " + s1.substring(0,1));

        for(int i=0; i<=s1.length(); i++){
            System.out.println(s1.substring(0,i) + "d" + s1.substring(i));
        }


//        System.out.println(mat.length);


        MutablePair<Integer, Integer> main = new MutablePair<>();
        main.setLeft(10);
        main.setRight(20);
        changepair(main);

//        System.out.println(main.getLeft());

        Stringfun sf = new Stringfun(10);
        System.out.println(sf.getA());

        changesf(sf);

        System.out.println(sf.getA());

        Stringfun sf2 = changesf2(sf);
        System.out.println(sf.getA());
        System.out.println(sf2.getA());

    }

    private static Stringfun changesf2(Stringfun sf) {
        Stringfun sf2 = new Stringfun(sf);
        sf2.setA(40);
        return sf2;
    }

    private static void changesf(Stringfun sf) {
        Stringfun sf2 = sf;
        sf2.setA(20);
    }

    private static void changepair(MutablePair<Integer, Integer> main) {
        MutablePair mp = main;
        mp.setLeft(30);
    }


}
