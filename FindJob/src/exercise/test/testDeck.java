package exercise.test;

import exercise.Card;
import exercise.Deck;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by romil on 1/17/14.
 */
public class testDeck {

    Deck d;

    @Before
    public void setup(){
        d = new Deck();
    }

    @Test
    public void compareCardsSameSuit(){
        Card c1 = new Card(1,1);
        Card c2 = new Card(1,2);

        assertEquals(-1, d.compare(c1, c2));
    }

    @Test
    public void compareCardsDifferentSuit(){
        Card c1 = new Card(1,1);
        Card c2 = new Card(1,2);

        assertEquals(-1, d.compare(c1, c2));
    }

    @Test
    public void compareCardsTrumpSet(){
        Card c1 = new Card(1,1);
        Card c2 = new Card(2,2);

        d.setTrumpSuit("Spades");
        assertEquals(1, d.compare(c1, c2));
    }

    @Test
    public void compareCardsSetSuitInPlay(){
        Card c1 = new Card(1,1);
        Card c2 = new Card(2,2);

        d.setSuitInPlay("Spades");
        assertEquals(1, d.compare(c1, c2));
    }

    @Test
    public void compareCardsSetSuitInPlayAndTrump(){
        Card c1 = new Card(1,1);
        Card c2 = new Card(2,2);

        d.setSuitInPlay("Diamonds");
        d.setTrumpSuit("Spades");

        assertEquals(1, d.compare(c1, c2));
    }

}
