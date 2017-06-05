package practice.leetcode;

import javafx.util.Pair;

import java.util.*;

/**
 * Created by romil on 5/11/17.
 */
public class NQueen {
    Deque<Pair<Integer, Integer>> queensPlacedAt = new ArrayDeque<>();
    int solutionsFound = 0;
    List<List<String>> output = new ArrayList<>();
    Set<Pair<Integer, Integer>> firstQPosExplored = new HashSet<>();

    int yblacklist = 0;

    public List<List<String>> solveNQueens(int n) {
        int ystart = 0;
        for (int x = 0; x < n; x++) {
            boolean isNewQueenPlaced = false;

            for (int y = ystart; y < n; y++) {
                Pair<Integer, Integer> position = new Pair<>(x, y);
                if (canPlaceQueen(position)) {
                    queensPlacedAt.add(new Pair<Integer, Integer>(x, y));
                    isNewQueenPlaced = true;
                    break;
                }
            }
            //backtrack.. remove a queen just placed.
            if (!isNewQueenPlaced) {
                if (queensPlacedAt.isEmpty()) {
                    break;
                }
                Pair<Integer, Integer> position = queensPlacedAt.removeLast();
                if (queensPlacedAt.isEmpty()) {
                    resetForBacktracking();
                    x = -1;
                    ystart = 0;
                    continue;
                }
                x = position.getKey() - 1;
                ystart = position.getValue() + 1;
            } else {
                ystart = 0;
            }

            if (queensPlacedAt.size() == n) {
                printOutput(n);
                solutionsFound++;
                Pair<Integer, Integer> position = queensPlacedAt.removeLast();
                x = position.getKey() - 1;
                ystart = position.getValue() + 1;
                continue;
            }
        }

        return output;
    }

    private void printOutput(int n) {
        List<String> boardConf = printBoardConfig(n);
        output.add(boardConf);
    }

    private List<String> printBoardConfig(int n) {
        List<String> boardConf = new ArrayList<>();
        for (Pair<Integer, Integer> qp : queensPlacedAt) {
            StringBuilder row = getBlankRow(n);
            row.setCharAt(qp.getValue(), 'Q');
            boardConf.add(row.toString());
        }
        return boardConf;
    }

    private StringBuilder getBlankRow(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append('.');
        }
        return sb;
    }

    private void resetForBacktracking() {
        firstQPosExplored.add(new Pair<Integer, Integer>(0, yblacklist));
        yblacklist++;
    }

    private boolean canPlaceQueen(Pair<Integer, Integer> position) {

        if(firstQPosExplored.contains(position)) return false;

        for (Pair<Integer, Integer> queenPos : queensPlacedAt) {
            if (position.getKey() == queenPos.getKey()) return false;
            if (position.getValue() == queenPos.getValue()) return false;
            float num = queenPos.getValue() - position.getValue();
            float den = queenPos.getKey() - position.getKey();
            float slope = num/den;
            if(slope == 1 || slope == -1) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        NQueen nq = new NQueen();
        List<List<String>> op = nq.solveNQueens(4);
        System.out.println(op);
    }
}
