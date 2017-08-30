package practice.leetcode.ttt;

class HumanMove implements MoveMethod {

    public int move(int[][] board, Player.Symbol symbolToPut) throws InvalidMoveException {
        promptHuman();
        String posn = PlayTTT.getUserInput();
        int move_int;
        try {
            move_int = Integer.parseInt(posn);
        } catch (NumberFormatException e) {
            throw new InvalidMoveException("Invalid Input");
        }

        if ((move_int > (TicTacToe.N) * (TicTacToe.N)) ||
             move_int < 1) {
            throw new InvalidMoveException("Invalid Input");
        }

        int x_coord = (move_int - 1) / 3;
        int y_coord = (move_int - 1) % 3;

        if (board[x_coord][y_coord] != 0) throw new InvalidMoveException("I I");
        board[x_coord][y_coord] = symbolToPut.equals(Player.Symbol.X) ? 1 : -1;
        return move_int;
    }

    private void promptHuman() {
        System.out.println("Which position?");
    }
}
