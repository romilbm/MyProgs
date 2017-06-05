package practice.leetcode;

import javafx.util.Pair;

import java.util.*;

import static java.lang.Math.abs;

/**
 * Created by romil on 5/8/17.
 */
public class BestMeetingPoint {
    Map<Pair<Integer, Integer>, Integer> lookup = new HashMap<>();
    ArrayDeque<Pair<Integer, Integer>> toExplore = new ArrayDeque<>();
    ArrayDeque<Pair<Integer, Integer>> tempQLess= new ArrayDeque<>();
    ArrayDeque<Pair<Integer, Integer>> tempQEqual= new ArrayDeque<>();
    Set<Pair<Integer, Integer>> explored = new HashSet<>();

    public int minTotalDistance(int[][] grid) {
        ArrayList<int[]> posn = new ArrayList<>();
        int maxx = 0;
        int maxy = 0;
        int minx = grid.length;
        int miny = grid[0].length;

        for (int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    //identify the positions
                    int[] tmp = {i, j};
                    posn.add(tmp);
                    minx = i < minx ? i : minx;
                    maxx = i > maxx ? i : maxx;
                    miny = j < miny ? j : miny;
                    maxy = j > maxy ? j : maxy;
                }
            }
        }

        int arbx = (minx + maxx) / 2;
        int arby = (miny + maxy) / 2;

        int minDist = calculateTotalDist(posn, arbx, arby);
        Pair<Integer, Integer> arbKey = new Pair<>(arbx, arby);
        toExplore.push(arbKey);

        while (toExplore.peek() != null) {
            Pair<Integer, Integer> currentKey = toExplore.removeLast();
            explored.add(currentKey);
            int curx = currentKey.getKey();
            int cury = currentKey.getValue();
            int origDist = calculateTotalDist(posn, curx, cury);
            int dist;
            Pair<Integer, Integer> keytoAdd;

            if(curx-1 >= minx && curx-1 >= 0) {
                dist = calculateTotalDist(posn, curx - 1, cury);
                keytoAdd = new Pair<>(curx-1, cury);
                if(dist == origDist) tempQEqual.add(keytoAdd);
                if(dist < origDist) tempQLess.add(keytoAdd);
            }

            if(curx+1 <= maxx && curx+1 >= 0) {
                dist = calculateTotalDist(posn, curx + 1, cury);
                keytoAdd = new Pair<>(curx+1, cury);
                if(dist == origDist) tempQEqual.add(keytoAdd);
                if(dist < origDist) tempQLess.add(keytoAdd);
            }

            if(cury-1 >= miny && cury-1 >= 0) {
                dist = calculateTotalDist(posn, curx, cury - 1);
                keytoAdd = new Pair<>(curx, cury-1);
                if(dist == origDist) tempQEqual.add(keytoAdd);
                if(dist < origDist) tempQLess.add(keytoAdd);
            }

            if(cury+1 <= maxy && cury+1 >= 0) {
                dist = calculateTotalDist(posn, curx, cury + 1);
                keytoAdd = new Pair<>(curx, cury+1);
                if(dist == origDist) tempQEqual.add(keytoAdd);
                if(dist < origDist) tempQLess.add(keytoAdd);
            }

            if(tempQEqual.size() == 0 && tempQLess.size() == 0)
                return origDist;

            if(tempQLess.size() >= 1) {
                toExplore.addAll(tempQLess);
                for(Pair<Integer, Integer> k : tempQLess) {
                    if(!explored.contains(k)) {
                        toExplore.add(k);
                    }
                }
                tempQLess.clear();
                tempQEqual.clear();
                continue;
            }

            if(tempQEqual.size() >= 1) {
                minDist = origDist;
                for(Pair<Integer, Integer> k : tempQEqual) {
                    if(!explored.contains(k)) {
                        toExplore.add(k);
                    }
                }
                tempQEqual.clear();
                continue;
            }

        }
        return minDist;
    }

    private int calculateTotalDist(ArrayList<int[]> posn, int curx, int cury) {
        int dist = 0;
        Pair<Integer, Integer> key = new Pair<>(curx, cury);
        if(lookup.containsKey(key)) {
            dist = lookup.get(key);
        } else {
            for (int i = 0; i < posn.size(); i++) {
                dist += abs((curx - posn.get(i)[0])) + abs((cury - posn.get(i)[1]));
            }
            lookup.put(key, dist);
        }
        return dist;
    }

    public static void main(String[] args) {
        BestMeetingPoint bmp = new BestMeetingPoint();
        int ip[][] = {{1,1}};
        int d = bmp.minTotalDistance(ip);
        System.out.println(d);
    }
}
