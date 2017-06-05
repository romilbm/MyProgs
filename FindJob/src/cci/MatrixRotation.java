package cci;

import javafx.util.Pair;

/**
 * Created by romil on 5/14/17.
 */
public class MatrixRotation {

    void rotateMatrixRight(int [][]c) {
        int x = 0;
        int y = 0;
        int temp = c[x][y];
        int num = c.length * c[0].length;

        for(int ctr = 0; ctr < num; ctr ++) {
            Pair<Integer, Integer> newPos = getSwapPosnFor(x,y, c.length);
            if(newPos.getKey() == 0 && newPos.getValue() == 0)
            {
                c[x][y] = temp;
                continue;
            }
            c[x][y] = c[newPos.getKey()][newPos.getValue()];
            x = newPos.getKey();
            y = newPos.getValue();
        }

    }

    private Pair<Integer, Integer> getSwapPosnFor(int x, int y, int maxx) {
        return null;
    }
}
