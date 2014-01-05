package practice.leetcode;

import java.util.ArrayList;

/**
 * Created by romil on 1/3/14.
 */
public class PascalTriangle {
    public ArrayList<ArrayList<Integer>> generate(int numRows) {
        ArrayList<ArrayList<Integer>> triangle = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> prevRow = new ArrayList<Integer>();
        for(int currentRowCntr = 0; currentRowCntr < numRows; currentRowCntr++){
            ArrayList<Integer> currentRow = new ArrayList<Integer>();
            for(int currentRowIndex = 0; currentRowIndex <= currentRowCntr; currentRowIndex++){
                if(currentRowIndex == 0 || currentRowIndex == currentRowCntr){
                    currentRow.add(1);
                } else {
                    currentRow.add(prevRow.get(currentRowIndex - 1)+prevRow.get(currentRowIndex));
                }
            }
            triangle.add(currentRow);
            prevRow.clear();
            prevRow.addAll(currentRow);
        }
        return triangle;
    }

    public static void main(String args[]){
        PascalTriangle p = new PascalTriangle();
        ArrayList<ArrayList<Integer>> triangle = p.generate(5);
        for(ArrayList<Integer> rows: triangle){
            System.out.println(rows);
        }

    }
}
