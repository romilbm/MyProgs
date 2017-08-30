package practice.leetcode.ttt;

interface MoveMethod {
    int move(int[][] board, Player.Symbol symbolToPut) throws InvalidMoveException;
}