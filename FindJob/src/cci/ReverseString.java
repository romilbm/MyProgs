package cci;

public class ReverseString {
    public static void main(String[] args) {

        String str = "romil";
        char[] s = str.toCharArray();

        int prevptr = -1;
        int currptr = 0;

        while(currptr != s.length){
            int tempptr = prevptr;
            prevptr = currptr;
            s[prevptr] = s[currptr];
            prevptr = tempptr;
            currptr++;
        }

        System.out.println(s);

    }
}
