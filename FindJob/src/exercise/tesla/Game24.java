package exercise.tesla;


public class Game24 {
    public String get24Combo(int[] cards, float target) {
        if (cards.length == 1 && cards[0] != target) return null;
        if (cards.length == 1 && cards[0] == target) return "" + cards[0];
        for (int cctr=0; cctr<cards.length; cctr++) {
            for (int i=0; i<4; i++) {
                float newTarget = 0;
                String op = "";
                switch (i) {
                    case 0:
                        newTarget = target / cards[cctr];
                        op = "*";
                        break;
                    case 1:
                        newTarget = target * cards[cctr];
                        op = "/";
                        break;
                    case 2:
                        newTarget = target - cards[cctr];
                        op = "+";
                        break;
                    case 3:
                        newTarget = target + cards[cctr];
                        op = "-";
                        break;
                }

                int[] newCards = new int[cards.length-1];
                int ctr = 0;
                for (int ncctr=0; ncctr<cards.length; ncctr++) {
                    if (ncctr != cctr) {
                        newCards[ctr] = cards[ncctr];
                        ctr++;
                    }
                }

                String combo = get24Combo(newCards, newTarget);
                if (combo != null) {
                    combo += op + cards[cctr];
                    return combo;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Game24 game = new Game24();

        System.out.println(game.get24Combo(new int[]{9,6,6,2}, 24));
    }
}
