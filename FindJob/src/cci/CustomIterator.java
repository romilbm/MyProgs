package cci;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class CustomIterator implements Iterator<Integer>{
    ArrayList<Integer> listOfCombinedElements  = new ArrayList<>();
    Iterator<Integer> combinedIterator;
    
    public CustomIterator(Iterator<Integer>[] input) {
        while(elementsLeft(input)) {
            for(Iterator i: input) {
                if(i.hasNext()){
                    listOfCombinedElements.add((Integer) i.next());
                }
            }
        }
        
        combinedIterator = listOfCombinedElements.iterator();
    }

    private boolean elementsLeft(Iterator[] input) {
        boolean answer = false;
        
        for(Iterator i: input){
            if(i.hasNext()) {
                return true;
            }
        }
        
        return answer;
    }

    @Override
    public boolean hasNext() {
        return combinedIterator.hasNext();
    }

    @Override
    public Integer next() {
        return combinedIterator.next();
    }

    @Override
    public void remove() {

    }

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(10, 20));
        ArrayList<Integer> b = new ArrayList<>(Arrays.asList(30));
        ArrayList<Integer> c = new ArrayList<>(Arrays.asList(40, 50, 60));

        Iterator<Integer>[] input = new Iterator[] {a.iterator(), b.iterator(), c.iterator()};

        CustomIterator customIterator = new CustomIterator(input);

        while(customIterator.hasNext()) {
            System.out.println(customIterator.next());
        }
    }
}



