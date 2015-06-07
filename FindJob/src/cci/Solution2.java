package cci;

import java.util.*;

public class Solution2 {
    public static void main(String args[] ) throws Exception {
        Scanner in = new Scanner(System.in);
        String l = in.nextLine();
        String [] rc = l.split(",");
        int rows = Integer.parseInt(rc[0]);
        int col = Integer.parseInt(rc[1]);
        int matrix[][] = new int[rows][col];

        for(int i = 0; i < rows; i++){
            String rowString = in.nextLine();
            String vals[] = rowString.split(",");
            for(int j = 0; j < col; j++) {
                matrix[i][j] = Integer.parseInt(vals[j]);
            }
        }

        System.out.println(printSpiral(matrix, rows, col));
    }

    public static String printSpiral(int matrix[][], int rows, int cols) {
        int mat[] = new int[rows*cols];
        int matptr = 0;
        Set<List<Integer>> visited = new HashSet<>();

        int dir = 1;
        ArrayList<Integer> current = new ArrayList<>();
        current.add(0);
        current.add(0);

        while(!visited.contains(current) && isValid(current.get(0), current.get(1), rows, cols)) {
            mat[matptr++] = matrix[current.get(0)][current.get(1)];
            visited.add(current);
            ArrayList<Integer> nextxy = nextmove(dir, current.get(0), current.get(1));
            if(!isValid(nextxy.get(0), nextxy.get(1), rows, cols)
                    || visited.contains(nextxy)
                    ) {
                dir = changeDir(dir);
                current = nextmove(dir, current.get(0), current.get(1));
            } else {
                current = nextxy;
            }
        }

        StringBuffer b = new StringBuffer();
        matptr = 0;
        for(int i:mat) {
            b.append(i);
            matptr++;
            if(mat.length != matptr) {
                b.append(",");
            }
        }
        return b.toString();
    }

    private static boolean isValid(Integer x, Integer y, int rows, int cols) {
        if (   x >= rows
            || y >= cols
            || x < 0
            || y < 0)
        return false;
        return true;
    }

    private static int changeDir(int dir) {
        return (dir == 4) ? 1 : (dir+1);
    }

    private static ArrayList<Integer> nextmove(int dir, int x, int y) {
        ArrayList<Integer> l = new ArrayList<>();
        switch(dir) {
            case 1:
                l.add(x);
                l.add(++y);
            break;
            
            case 2:
                l.add(++x);
                l.add(y);
            break;
            
            case 3:
                l.add(x);
                l.add(--y);
            break;

            case 4:
                l.add(--x);
                l.add(y);
            break;
        }
        return l;
    }
}