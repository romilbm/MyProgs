package exercise;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Arrays;

/**
 * The Deck class
 * Implements a standard deck of 52 cards.
 * It has methods that shuffle cards, riffle them
 * and also compare them based on their value, trump suit or the on going suit in a hand.
 * Created by romil on 1/17/14.
 */
public class Deck implements Comparator<Card>{

    /**
     * The standard deck.
     */
    private ArrayList<Card> standardDeck;

    /**
     * The trump suit for the game or hand.
     */
    private String trumpSuit = null;

    /**
     * Preference if the Ace is to be treated as lowest or highest.
     */
    private final boolean isAceLowest;

    /**
     * The suit for the game or hand.
     */
    private String suitInPlay = null;

    /**
     * A Deck constructor
     * It initializes the standard deck with 52 cards.
     * By default, the ace has the highest value.
     */
    public Deck(){
        isAceLowest = false;
        standardDeck = new ArrayList<Card>(52);
        initializeDeck();
    }

    /**
     * A Deck constructor with preference on how the ace should be treated.
     * @param isAceLowest true to treat ace lowest. false for highest.
     */
    public Deck(boolean isAceLowest){
        standardDeck = new ArrayList<Card>(52);
        this.isAceLowest = isAceLowest;
        initializeDeck();
    }

    /**
     * A Deck constructor with an option to set the trumpSuit which is null by default.
     * @param trumpSuit can be one of hearts, clubs, diamonds, spades with upper or lower case.
     * @throws java.lang.IllegalArgumentException if the value of trump suit is not valid.
     */
    public Deck(String trumpSuit) throws IllegalArgumentException{
        if(!isSuitValid(trumpSuit)){
            throw new IllegalArgumentException(trumpSuit + " is not a valid suit in a standard deck.");
        }
        this.trumpSuit = trumpSuit;
        standardDeck = new ArrayList<Card>(52);
        isAceLowest = false;
        initializeDeck();
    }

    /**
     * A Deck constructor with an option to set the trumpSuit which is null by default and
     * with preference on how the ace should be treated.
     * @param trumpSuit can be one of hearts, clubs, diamonds, spades with upper or lower case.
     * @param isAceLowest true to treat ace lowest. false for highest.
     * @throws java.lang.IllegalArgumentException if the value of trump suit is not valid.
     */
    public Deck(String trumpSuit, boolean isAceLowest) throws IllegalArgumentException{
        if(!isSuitValid(trumpSuit)){
            throw new IllegalArgumentException(trumpSuit + " is not a valid suit in a standard deck.");
        }
        standardDeck = new ArrayList<Card>(52);
        this.trumpSuit = trumpSuit;
        this.isAceLowest = isAceLowest;
        initializeDeck();
    }

    /**
     * Set the suit for the on going hand.
     * @param suit can be one of hearts, clubs, diamonds, spades with upper or lower case.
     * @throws IllegalArgumentException if the value of trump suit is not valid.
     */
    public void setSuitInPlay(String suit) throws IllegalArgumentException{
        if(!isSuitValid(suit)){
            throw new IllegalArgumentException(suit + "is not a valid suit in a standard deck");
        }
        suitInPlay = suit;
    }

    /**
     * Resets the value of the on-going suit to null.
     */
    public void resetSuitInPlay(){
        suitInPlay = null;
    }

    /**
     * The current value of the suit in play.
     * @return one of hearts, clubs, diamonds or spades.
     */
    public String getSuitInPlay(){
        return suitInPlay;
    }

    /**
     * Set the trump suit for the on going hand or game.
     * @param suit can be one of hearts, clubs, diamonds, spades with upper or lower case.
     * @throws IllegalArgumentException if the value of trump suit is not valid.
     */
    public void setTrumpSuit(String suit) throws IllegalArgumentException{
        if(!isSuitValid(suit)){
            throw new IllegalArgumentException(suit + "is not a valid suit in a standard deck");
        }
        trumpSuit = suit;
    }

    /**
     * Resets the trump suit to null.
     */
    public void resetTrumpSuit(){
        trumpSuit = null;
    }

    /**
     * The current value of the trump suit.
     * @return one of hearts, clubs, diamonds or spades.
     */
    public String getTrumpSuit(){
        return trumpSuit;
    }

    /**
     * The first card from the deck.
     * @return the first Card in the deck.
     */
    public Card deal(){
        return drawNCards(1).get(0);
    }

    /**
     * Pick n cards from the top of the deck.
     * @return the first Card in the deck.
     */
    public List<Card> drawNCards(int n){
        List<Card> tempDeck = standardDeck.subList(0,n-1);
        standardDeck.removeAll(tempDeck);
        return tempDeck;
    }

    /**
     * Riffles the deck.
     */
    public void riffle(){
        int from;
        if(standardDeck.size() % 2 != 0){
            from = (standardDeck.size()+1)/2;
        } else {
            from = standardDeck.size()/2;
        }

        List<Card> tempDeck = standardDeck.subList(from,standardDeck.size()-1);
        standardDeck.removeAll(tempDeck);

        Iterator<Card> cardIterator = tempDeck.iterator();
        int i=1;

        while(cardIterator.hasNext()){
            standardDeck.set(i,cardIterator.next());
            i+=2;
        }
    }

    /**
     * Quickly shuffles the deck.
     */
    public void shuffle(){
        shuffle(2);
    }

    /**
     * Shuffle the deck with a degree of randomness upto 4.
     * The shuffle method quickly shuffles with a degree of 2.
     * This method will shuffle with atleast a degree of 1.
     * If the desired degree is greater than 4, it will riffle first and then shuffle for greater randomness.
     * @param degreeOfRandomness the desired degree of randomness
     */
    public void shuffle(int degreeOfRandomness){
        if (degreeOfRandomness > 4){
            degreeOfRandomness = 3;
            riffle();
        }

        if(degreeOfRandomness < 1){
            degreeOfRandomness = 1;
        }

        long iterations = (int) Math.pow(10,degreeOfRandomness);
        Random generator = new Random();

        for(int i =0; i<iterations; i++){
            int index = generator.nextInt(standardDeck.size()-1);
            Card c = standardDeck.remove(index);
            standardDeck.add(c);
        }
    }

    private void initializeDeck() {
        int startValue = 1;

        if(!isAceLowest){
            startValue = 2;
        }

        for(int j=0; j<4; j++){
            for(int i=startValue; i<startValue+13; i++){
                standardDeck.add(new Card(j,i));
            }
        }

    }

    private boolean isSuitValid(String suit){
        List<String> suitList = Arrays.asList("diamonds", "hearts", "spades", "clubs");
        if(!suitList.contains(suit.toLowerCase())){
            return false;
        }
        return true;
    }

    @Override
    public String toString(){
        String s = "";
        for(Card card:standardDeck){
            s += card.toString() + "\n";
        }
        return s;
    }

    /**
     * Compares the cards.
     * If both are from the same suit, it compares them normally.
     * If not, then marks the card from the trump suit as the higher one.
     * If neither is from trump suit, it will determine the one which belongs to the suit in play as the higher one.
     * If neither belong to trump suit or suit in play and are from different suits, they are compared normally.
     * @param c1 first Card
     * @param c2 second Card
     * @return -1 if first card is smaller, 1 if it is higher, 0 if neither can be determined higher than the other
     *              or if both are same cards.
     */
    @Override
    public int compare(Card c1, Card c2) {
        if(c1.getSuit().equals(c2.getSuit())){
            return c1.compareTo(c2);
        }

        if(trumpSuit != null){
            if(c1.getSuit().toLowerCase().equals(trumpSuit.toLowerCase())){
                return 1;
            }

            if(c2.getSuit().toLowerCase().equals(trumpSuit.toLowerCase())){
                return -1;
            }
        }

        if(suitInPlay != null){
            if(c1.getSuit().toLowerCase().equals(suitInPlay.toLowerCase())){
                return 1;
            }

            if(c2.getSuit().toLowerCase().equals(suitInPlay.toLowerCase())){
                return -1;
            }
        }

        return c1.compareTo(c2);
    }

}
