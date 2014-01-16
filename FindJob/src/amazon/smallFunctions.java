package amazon;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by romil on 1/14/14.
 */
public class smallFunctions {

    public static int[] sortHalfSorted(int [] a){
        List<Integer> input = new LinkedList<Integer>();
        int firstHalfPtr = 0;
        int secondHalfPtr = (a.length/2);
        for(int n:a){
            input.add(n);
        }
        while(secondHalfPtr<input.size()){
            if(input.get(secondHalfPtr) < input.get(firstHalfPtr)){
                input.add(firstHalfPtr,input.remove(secondHalfPtr));
                firstHalfPtr++;
                secondHalfPtr++;
            } else if(input.get(secondHalfPtr) == input.get(firstHalfPtr)) {
                input.add(firstHalfPtr+1,input.remove(secondHalfPtr));
                firstHalfPtr++;
            } else {
                firstHalfPtr++;
            }

            if(firstHalfPtr == secondHalfPtr){
                secondHalfPtr++;
            }
        }
        int i=0;
        for(int n:input){
            a[i]=n;
            i++;
        }
        return a;
    }

    public static void main(String args[]){
        int ip [] = {-7,-3,1,7,-5,-2,3,8};
        ip = smallFunctions.sortHalfSorted(ip);
        for(int i:ip){
            System.out.print(i + " ");
        }

    }
}
