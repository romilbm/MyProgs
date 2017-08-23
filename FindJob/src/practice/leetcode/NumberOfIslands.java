package practice.leetcode;

import java.util.*;

public class NumberOfIslands {
    public static class Point {
        int x;
        int y;
        boolean isOOR = false;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int maxx, int maxy) {
            if (x<0 || x>= maxx || y<0 || y>=maxy) {
                isOOR = true;
            }
            this.x = x;
            this.y = y;
        }

        @Override public boolean equals(Object aThat) {
            if ( this == aThat ) return true;
            if ( !(aThat instanceof Point) ) return false;
            Point that = (Point)aThat;

            return (this.x == that.x && this.y == that.y);
        }

        @Override public int hashCode() {
            return (int) (3*Math.pow(x,2) + 4*y);
        }
    }
    Set<Point> disc;
    public int numIslands(char[][] grid) {
        disc = new HashSet<>();
        int c = 0;
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[i].length; j++) {
                Point p = new Point(i,j);
                if (grid[i][j] == '1' && !disc.contains(p)) {
                    HashSet<Point> explored = new HashSet<>();
                    explored.add(p);
                    getLandNeighbors(p,explored, grid);
                    disc.addAll(explored);
                    c++;
                }
            }
        }
        return c;
    }

    private void getLandNeighbors(Point r, Set<Point> explored, char[][] grid) {
        int maxx = grid.length;
        int maxy = grid[0].length;

        ArrayDeque<Point> q = new ArrayDeque<Point>();
        q.offer(r);

        while(q.peek() != null) {
            Point p = q.poll();
            Point[] n = new Point[4];
            n[0] = new Point(p.x-1, p.y, maxx, maxy);
            n[1] = new Point(p.x+1, p.y, maxx, maxy);
            n[2] = new Point(p.x, p.y-1, maxx, maxy);
            n[3] = new Point(p.x, p.y+1, maxx, maxy);

            for (Point t:n) {
                if (!t.isOOR && grid[t.x][t.y] == '1' && !explored.contains(t)) {
                    explored.add(t);
                    q.offer(t);
                }
            }
        }
    }

    public static void main(String[] args) {
//        NumberOfIslands noi = new NumberOfIslands();
//        char[][] grid = {"11110".toCharArray(),"11010".toCharArray(),"11000".toCharArray(),"00000".toCharArray()};
//        System.out.println(noi.numIslands(grid));

//        int a = Integer.parseInt("3456237490");
//        System.out.println("a".substring(1));
        String[] ans = "1+2*3".split("\\*");
        System.out.println("ans size:" + ans.length);
        for (String a : ans)
            System.out.println(a);
//        System.out.println(a);


    }
}
