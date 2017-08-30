package practice.leetcode.ttt;

public class TTTResult {
    private Player[] participants;
    private WinConfig w;
    private Player winner;

    public TTTResult(Player[] participants, WinConfig w, Player winner) {
        this.participants = participants;
        this.w = w;
        this.winner = winner;
    }

    public Player[] getParticipants() {
        return participants;
    }

    public WinConfig getW() {
        return w;
    }

    public Player getWinner() {
        return winner;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Players = " + participants[0] + " and " + participants[1]  + "\n");
        sb.append("Result = " + w + "\n");
        if (!w.equals(WinConfig.DRAW)) {
            sb.append("Winner = " + winner);
        }

        return sb.toString();
    }
}
