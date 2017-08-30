package practice.leetcode.ttt;

class TicTacToe {
    protected static final int N = 3;
    private static final int HSPACE = 20;
    protected int[][] board;

    public int playFirst;

    private Player[] players;

    private Player[] playOrder;
    public static String getPosDescription(int pos) {
        String str = "";
        if (pos == 5) {
            str = "center";
            return str;
        }

        if ((pos - 1) / 3 == 0) {
            str += "upper ";
        } else if ((pos - 1) / 3 == 1) {
            str += "middle ";
        } else
            str += "lower ";

        if ((pos - 1) % 3 == 0)
            str += "left";
        else if ((pos - 1) % 3 == 1)
            str += "middle";
        else
            str += "right";

        return str;
    }

    public void setPlayFirst(int playFirst) {
        this.playFirst = playFirst;
    }


    public TicTacToe(Player[] p) {
        players = p;
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = 0;
            }
        }
    }

    public WinConfig isWinningConfig() {
        WinConfig w = WinConfig.WIN;
        // rows
        for (int i = 0; i < N; i++) {
            if ((board[i][0] != 0) && (board[i][0] == board[i][1]) &&
                    (board[i][0] == board[i][2]))

            {
                return w;
            }
        }
        // columns
        for (int i = 0; i < N; i++) {
            if ((board[0][i] != 0) && (board[0][i] == board[1][i]) &&
                    (board[0][i] == board[2][i]))

            {
                return w;
            }
        }
        // diags
        if ((board[0][0] != 0) && (board[0][0] == board[1][1]) &&
                (board[0][0] == board[2][2])) {
            return w;
        }

        if ((board[2][0] != 0) && (board[2][0] == board[1][1]) &&
                (board[2][0] == board[0][2]))

        {
            return w;
        }

        // draw
        w = WinConfig.DRAW;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0)
                    w = WinConfig.NONE;
            }
        return w;

    }

    private String getRowString(int row) {
        String s = "";
        for (int i = 0; i < N; i++) {
            switch (board[row][i]) {
                case 0:
                    s += " ";
                    break;
                case -1:
                    s += "O";
                    break;
                case 1:
                    s += "X";
            }

            if (i != N - 1) {
                s += " | ";
            }
        }

        s += String.format("%" + HSPACE + "s", "");

        for (int i = 0; i < N; i++) {
            s += row * 3 + i + 1;

            if (i == N - 1) {
                s += "\n";
            } else {
                s += " | ";
            }
        }
        return s;
    }

    public String toString() {
        String s = "";
        // iterate through the rows
        for (int i = 0; i < N; i++) {
            s += getRowString(i);
        }
        return s;
    }

    public TTTResult playGame() {
        if (playFirst == 0) {
            playOrder = players;
        } else {
            playOrder = new Player[] {players[1], players[0]};
        }

        WinConfig w = WinConfig.NONE;
        boolean isValidMove;
        Player lastPlayer = null;
        while (w == WinConfig.NONE) {
            for (Player p: playOrder) {
                int move=0;
                do {
                    try {
                        move = p.move(board);
                        isValidMove = true;
                    } catch (InvalidMoveException e) {
                        System.out.println("Invalid Move. Try again.");
                        isValidMove = false;
                    }
                } while (!isValidMove);
                System.out.println(p.getName() + " put a " + p.getSymbol() + " in the " + TicTacToe.getPosDescription(move) + ".");
                w = isWinningConfig();
                lastPlayer = p;
                if (!p.getMove_strategy().getClass().equals(HumanMove.class)) {
                    System.out.println(this);
                }
                if (w != WinConfig.NONE) break;
            }
        }

        Player winner = lastPlayer;
        if (w != WinConfig.WIN) winner = null;
        return new TTTResult(playOrder, w, winner);
    }

}
