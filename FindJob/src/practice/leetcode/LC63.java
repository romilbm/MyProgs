package practice.leetcode;

import java.util.HashMap;

public class LC63 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int ex = obstacleGrid.length-1;
        int ey = obstacleGrid[0].length-1;
        return getPath(new Tile(0,0), obstacleGrid);
    }


    class Tile {
        int x;
        int y;

        public Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            Tile t = (Tile)o;
            return (t.x == x && t.y == y);
        }

        @Override
        public int hashCode() {
            return (int) Math.sqrt((x*x) + (y*y));
        }
    }

    HashMap<Tile, Integer> cache = new HashMap<>();
    int ex;
    int ey;

    private int getPath(Tile t, int[][] obstacleGrid) {
        if (t.x == ex && t.y == ey) return 1;
        if (t.x > ex || t.y > ey) return 0;
        if (cache.containsKey(t)) return cache.get(t);
        if (obstacleGrid[t.x][t.y] == 1) return 0;

        int total = getPath(new Tile(t.x+1, t.y), obstacleGrid) + getPath(new Tile(t.x, t.y+1), obstacleGrid);

        cache.put(t, total);
        return total;
    }

    public static void main(String[] args) {
        LC63 lc63 = new LC63();
        int[][] o = {{0,0,0},{0,1,0},{0,0,0}};
        System.out.println(lc63.uniquePathsWithObstacles(o));
    }

}
