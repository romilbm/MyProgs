package practice.leetcode;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by romil on 5/10/17.
 */
public class SmallestRectangleBlackPixels {
    Set<Pair<Integer, Integer>> explored = new HashSet<>();
    int minx;
    int miny;
    int maxx;
    int maxy;
    char[][] orig_image;
    int xd = 0;
    int yd = 0;
    int td = 0;

    public int minArea(char[][] image, int x, int y) {

        minx = image.length;
        miny = image[0].length;
        maxx = -1;
        maxy = -1;
        orig_image = image;

//        updateXExtremes(x, y);
//        explored.clear();
//        updateYExtremes(x, y);
        updateExtremes(x,y);

        return (maxx - minx + 1) * (maxy - miny + 1);
    }

    private void updateExtremes(int x, int y) {
        Pair<Integer, Integer> key = new Pair<>(x, y);

        if(explored.contains(key))
            return;

        explored.add(key);

        td++;
        traverseNeighbors(x, y, 'u');
        traverseNeighbors(x, y, 'd');
        traverseNeighbors(x, y, 'l');
        traverseNeighbors(x, y, 'r');
    }

    private void updateXExtremes(int x, int y) {
        Pair<Integer, Integer> key = new Pair<>(x, y);

        if(explored.contains(key))
            return;

        explored.add(key);

        xd++;
        traverseNeighbors(x, y, 'u');
        traverseNeighbors(x, y, 'd');

    }

    private void updateYExtremes(int x, int y) {
        Pair<Integer, Integer> key = new Pair<>(x, y);

        if(explored.contains(key))
            return;

        explored.add(key);

        yd++;
        traverseNeighbors(x, y, 'l');
        traverseNeighbors(x, y, 'r');
    }

    private void traverseNeighbors(int x, int y, char direction) {
        Set<Pair<Integer, Integer>> neighbor = new HashSet<>();
        boolean isNeighborBlack = false;

        switch (direction) {
            case 'u':
                if(x-1 >= 0) {
                    if(y-1 >= 0){
                        if(orig_image[x-1][y-1] == '1') {
                            neighbor.add(new Pair<Integer, Integer>(x-1, y-1));
                            isNeighborBlack = true;
                        }
                    }
                    if(y+1 < orig_image[0].length){
                        if(orig_image[x-1][y+1] == '1') {
                            neighbor.add(new Pair<Integer, Integer>(x-1, y+1));
                            isNeighborBlack = true;
                        }
                    }
                    if(orig_image[x-1][y] == '1') {
                        neighbor.add(new Pair<Integer, Integer>(x-1, y));
                        isNeighborBlack = true;
                    }
                }
                if(!isNeighborBlack && x < minx){
                    minx = x;
                    return;
                }
                break;
            case 'd':
                if(x+1 < orig_image.length) {
                    if(y-1 >= 0){
                        if(orig_image[x+1][y-1] == '1') {
                            neighbor.add(new Pair<Integer, Integer>(x+1, y-1));
                            isNeighborBlack = true;
                        }
                    }
                    if(y+1 < orig_image[0].length){
                        if(orig_image[x+1][y+1] == '1') {
                            neighbor.add(new Pair<Integer, Integer>(x+1, y+1));
                            isNeighborBlack = true;
                        }
                    }
                    if(orig_image[x+1][y] == '1') {
                        neighbor.add(new Pair<Integer, Integer>(x+1, y));
                        isNeighborBlack = true;
                    }
                }
                if(!isNeighborBlack && x > maxx){
                    maxx = x;
                    return;
                }
                break;
            case 'r':
                if(y+1 < orig_image[0].length) {
                    if(x-1 >= 0){
                        if(orig_image[x-1][y+1] == '1') {
                            neighbor.add(new Pair<Integer, Integer>(x-1, y+1));
                            isNeighborBlack = true;
                        }
                    }
                    if(x+1 < orig_image.length){
                        if(orig_image[x+1][y+1] == '1') {
                            neighbor.add(new Pair<Integer, Integer>(x+1, y+1));
                            isNeighborBlack = true;
                        }
                    }
                    if(orig_image[x][y+1] == '1') {
                        neighbor.add(new Pair<Integer, Integer>(x, y+1));
                        isNeighborBlack = true;
                    }
                }
                if(!isNeighborBlack && y > maxy){
                    maxy = y;
                    return;
                }
                break;
            case 'l':
                if(y-1 >= 0) {
                    if(x-1 >= 0){
                        if(orig_image[x-1][y-1] == '1') {
                            neighbor.add(new Pair<Integer, Integer>(x-1, y-1));
                            isNeighborBlack = true;
                        }
                    }
                    if(x+1 < orig_image.length){
                        if(orig_image[x+1][y-1] == '1') {
                            neighbor.add(new Pair<Integer, Integer>(x+1, y-1));
                            isNeighborBlack = true;
                        }
                    }
                    if(orig_image[x][y-1] == '1') {
                        neighbor.add(new Pair<Integer, Integer>(x, y-1));
                        isNeighborBlack = true;
                    }
                }
                if(!isNeighborBlack && y < miny){
                    miny = y;
                    return;
                }
                break;
        }

        for(Pair<Integer, Integer> pt : neighbor) {
            if(explored.contains(pt)) continue;
//            switch (direction){
//                case 'l':
//                case 'r':
//                    updateYExtremes(pt.getKey(), pt.getValue());
//                    break;
//                case 'u':
//                case 'd':
//                    updateXExtremes(pt.getKey(), pt.getValue());
//                    break;
//            }
            updateExtremes(pt.getKey(), pt.getValue());
        }

    }

    public static void main(String[] args) {
        SmallestRectangleBlackPixels smallestRectangleBlackPixels = new SmallestRectangleBlackPixels();
        char[][] ip = {
            {'0','0','1','0'},
            {'0','1','1','0'},
            {'0','1','0','0'}
        };

        int x = smallestRectangleBlackPixels.minArea(ip, 0, 2);

        System.out.println(x);
        System.out.println(smallestRectangleBlackPixels.xd);
        System.out.println(smallestRectangleBlackPixels.yd);
        System.out.println(smallestRectangleBlackPixels.td);
    }
}
