package exercise;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Implements a standard playing card.
 * It has a rank and a suit.
 * All cards in a suit have standard values according to their rank.
 * except the Ace which can have the lowest or highest value.
 * It can be compared to other Cards based on its value.
 * Created by romil on 1/17/14.
 */
public class Card implements Comparable<Card>{

    /**
     * The index to determine the rank of the card from the list of possible ranks.
     */
    private int rank;

    /**
     * The index to determine the suit of the card from the list of possible suits.
     */
    private int suit;

    /**
     * The value of the card.
     */
    private int value;

    /**
     * A list of all possible suits.
     */
    private ArrayList<String> suits = new ArrayList<String>(Arrays.asList("Hearts", " Spades", "Diamonds", "Clubs"));

    /**
     * A list of all possible ranks.
     */
    private ArrayList<String> ranks = new ArrayList<String>(Arrays.asList("Ace", "2", "3", "4", "5", "6", "7", "8",
                                                                                "9", "10", "Jack", "Queen", "King"));

    /**
     * A Card constructor.
     * Suits can be 0 for Hearts, 1 for Spades, 2 for Diamonds, 3 for Clubs.
     * Value can be 2 - 10 for the corresponding cards. 11, 12, 13 for Jack, Queen and King respectively.
     * Ace can have 1 or 14.
     *
     * @param suit index value for the suit from 0 to 3.
     * @param value index value of the card from 1 to 14.
     */
    public Card(int suit, int value){
        this.suit = suit;
        this.value = value;
        if(value==14){
            rank = 0;
        } else {
            rank = value-1;
        }
    }

    /**
     * Gets the value of the card.
     * @return the value of the card.
     */
    public int getValue(){
        return value;
    }

    /**
     * Gets the suit of this card.
     * @return one of the four possible suits - Hearts, Spades, Diamonds, Clubs
     */
    public String getSuit(){
        return suits.get(suit);
    }

    /**
     * Two cards are equal if they have the same suit and value.
     * @param obj the object to be compared
     * @return true of they are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }

        if(!(obj instanceof Card)){
            return false;
        }

        Card c = (Card) obj;

        return (value == c.getValue())
                && (getSuit().equals(c.getSuit()));
    }

    @Override
    public int hashCode(){
        int code = 13;
        int ret = 0;
        ret += 32 * code + suit;
        ret += 32 * code + rank;
        ret += 32 * code + value;
        return ret;
    }

    @Override
    public String toString(){
        return ranks.get(rank) + " of " + suits.get(suit);
    }

    /**
     * A simple value based comparison.
     * @param c is the card to compare to
     * @return -1 if the value of this card is less than that of c, 1 if the value is greater than that of c
     *          and 0 if they are equal.
     */
    @Override
    public int compareTo(Card c) {
        if(c == null){
            return 1;
        }
        if(this.value < c.getValue()){
            return -1;
        } else if(this.value == c.getValue()){
            return 0;
        } else {
            return 1;
        }
    }
}
