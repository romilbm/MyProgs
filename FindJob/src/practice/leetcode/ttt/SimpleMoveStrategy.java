package practice.leetcode.ttt;

class SimpleMoveStrategy implements MoveMethod {

    public int move(int[][] board, Player.Symbol symbolToPut) {
        for (int i = 0; i < TicTacToe.N; i++) {
            for (int j = 0; j < TicTacToe.N; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = symbolToPut.equals(Player.Symbol.X) ? 1 : -1;
                    return (i * 3 + j + 1);
                }
            }
        }
        return 0;
    }
}
