package practice.leetcode.ttt;

class Player {
    public enum Symbol {
        X,
        ZERO;
    }

    private String name;

    private MoveMethod move_strategy;

    private Symbol symbol;
    public Player(String pname, MoveMethod move_s, Symbol s) {
        name = pname;
        move_strategy = move_s;
        symbol = s;
    }

    public MoveMethod getMove_strategy() {
        return move_strategy;
    }

    public Symbol getSymbol() {
        return symbol;
    }


    public String getName() {
        return name;
    }

    public int move(int[][] board) throws InvalidMoveException {
        return move_strategy.move(board, symbol);
    }

    public String toString() {
        return name + " " + (symbol.equals(Symbol.X) ? "X" : "0");
    }
}