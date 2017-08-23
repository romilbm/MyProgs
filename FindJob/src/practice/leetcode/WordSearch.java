package practice.leetcode;

import java.util.*;
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[i].length; j++) {
                List<Integer> start = new ArrayList<>(Arrays.asList(i,j));
                Set<List<Integer>> visited = new HashSet<>();
                if(find(board, start, word, visited)) return true;
            }
        }

        return false;
    }

    public boolean find(char[][] board, List<Integer> start, String word, Set<List<Integer>> visited) {
        if (word.isEmpty()) return true;
        int x = start.get(0);
        int y = start.get(1);
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) return false;
        if (word.charAt(0) != board[x][y]) return false;

        Set<List<Integer>> adj = getNeighbors(x,y);

        for (List<Integer> p: adj) {
            if (visited.contains(p)) {
                continue;
            }
            Set<List<Integer>> v = new HashSet<>();
            v.addAll(visited);
            v.add(start);

            if (find(board, p, word.substring(1), v)) {
                return true;
            }
        }
        return false;
    }

    private Set<List<Integer>> getNeighbors(int i, int j) {
        Set<List<Integer>> ret = new HashSet<>();
        ret.add(Arrays.asList(i-1,j));
        ret.add(Arrays.asList(i+1,j));
        ret.add(Arrays.asList(i,j-1));
        ret.add(Arrays.asList(i,j+1));

        return ret;
    }

    public static void main(String[] args) {
        WordSearch ws = new WordSearch();
        char[][] d2 = getip(new String[]{"aaaa","aaaa","aaaa","aaaa","aaab"});
        System.out.println(ws.exist(d2, "aaaaaaaaaaaaaaaaaaaa"));
        
    }

    private static char[][] getip(String[] ss) {
        char[][] ret = new char[ss.length][];

        for (int i=0; i<ss.length; i++) {
            ret[i] = ss[i].toCharArray();
        }

        return ret;
    }
}
