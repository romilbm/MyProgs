package exercise.test;

import exercise.Card;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by romil on 1/17/14.
 */
public class testCard {
    Card c1;

    @Before
    public void setup(){
        c1 = new Card(1,1);
    }

    @Test
    public void testEquals(){
        Card c = new Card(1,1);
        assertEquals(true, c1.equals(c));
    }

    @Test
    public void testNotEqualsDifferentValues(){
        Card c = new Card(1,2);
        assertEquals(false, c1.equals(c));
    }

    @Test
    public void testNotEqualsDifferentSuits(){
        Card c = new Card(2,1);
        assertEquals(false, c1.equals(c));
    }

    @Test
    public void testCompareEqual(){
        Card c = new Card(1,1);
        assertEquals(0,c1.compareTo(c));
    }

    @Test
    public void testCompareEqualDiffSuit(){
        Card c = new Card(2,1);
        assertEquals(0,c1.compareTo(c));
    }

    @Test
    public void testCompareLessThan(){
        Card c = new Card(2,2);
        assertEquals(1,c.compareTo(c1));
    }

    @Test
    public void testCompareGreaterThan(){
        Card c = new Card(2,2);
        assertEquals(-1,c1.compareTo(c));
    }
}
