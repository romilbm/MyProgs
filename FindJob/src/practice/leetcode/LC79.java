package practice.leetcode;

import java.util.HashSet;

public class LC79 {
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

    public boolean exist(char[][] board, String word) {
        HashSet<Tile> visited = new HashSet<>();

        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                Tile c = new Tile(i,j);
                if (findWord(board, word, 0, visited, c)) return true;
                visited.clear();
            }
        }
        return false;
    }

    private boolean findWord(char[][] board, String w, int index, HashSet<Tile> visited, Tile c) {
        if (index == w.length()) return true;
        if (visited.contains(c)) return false;
        if (c.x < 0 || c.x >= board.length || c.y < 0 || c.y >= board[0].length) return false;
        if (w.charAt(index) != board[c.x][c.y]) return false;
        
        visited.add(c);

        if (findWord(board, w, index+1, visited, new Tile(c.x-1,c.y)) ||
                findWord(board, w, index+1, visited, new Tile(c.x+1,c.y)) ||
                findWord(board, w, index+1, visited, new Tile(c.x,c.y-1)) ||
                findWord(board, w, index+1, visited, new Tile(c.x,c.y+1))) return true;

        visited.remove(c);
        return false;
    }

    public static void main(String[] args) {
        LC79 lc79 = new LC79();
//        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        char[][] board = {{'a'}, {'a'}};
        String w = "aaa";
        System.out.println(lc79.exist(board,w));
    }
}
