package cci;

import java.util.*;

public class snakesNLadders {
    Set<Integer> allSnakesLaddersSources;
    Map<Integer, Integer> snakeTailsToMouths;
    Map<Integer, Integer> ladderTopsToBottoms;
    int maxDieRoll;

    Map<Integer, Integer> minMoves = new HashMap<>();

    public snakesNLadders(Set<Integer> allSnakesLaddersSources,
                          Map<Integer, Integer> snakeTailsToMouths,
                          Map<Integer,Integer> ladderTopsToBottoms,
                          int maxDieRoll) {
        this.allSnakesLaddersSources = allSnakesLaddersSources;
        this.snakeTailsToMouths = snakeTailsToMouths;
        this.ladderTopsToBottoms = ladderTopsToBottoms;
        this.maxDieRoll = maxDieRoll;
    }

    public int getMinMoves(int square) {

        Set<Integer> possibleSources = new HashSet<>();

        if(minMoves.containsKey(square)) {
            return minMoves.get(square);
        }

        if(square <= maxDieRoll) {
            this.minMoves.put(square, 1);
            return 1;
        }

        if(ladderTopsToBottoms.containsKey(square)) {
            int ladderBottom = ladderTopsToBottoms.get(square);
            ladderTopsToBottoms.remove(square);
            int movesToLadderBottom = getMinMoves(ladderBottom);
            minMoves.put(ladderBottom, movesToLadderBottom);
            possibleSources.add(movesToLadderBottom);
        }

        if(snakeTailsToMouths.containsKey(square)) {
            int snakeMouth = snakeTailsToMouths.get(square);
            snakeTailsToMouths.remove(square);
            int movesToSnakeMouth = getMinMoves(snakeMouth);
            minMoves.put(snakeMouth, movesToSnakeMouth);
            possibleSources.add(movesToSnakeMouth);
        }

        for(int ctr = 1; ctr <= maxDieRoll; ctr++) {
            if(!allSnakesLaddersSources.contains(square - ctr)) {
                int neighborSquare = square - ctr;
                int movesToNeighborSquare = getMinMoves(neighborSquare);
                minMoves.put(neighborSquare, movesToNeighborSquare);
                possibleSources.add(1 + movesToNeighborSquare);
            }
        }

        int minMoves  = Collections.min(possibleSources);

        this.minMoves.put(square, minMoves);

        return minMoves;
    }

    public static void main(String[] args) {
        Map<Integer, Integer> snakeIntegerIntegerMap = new HashMap<>();
        snakeIntegerIntegerMap.put(7, 13);

        Map<Integer, Integer> ladderIntegerIntegerMap = new HashMap<>();
        ladderIntegerIntegerMap.put(12, 1);
        ladderIntegerIntegerMap.put(16, 8);

        Set<Integer> allIntegers = new HashSet<>();
        allIntegers.add(1);
        allIntegers.add(8);
        allIntegers.add(13);

        snakesNLadders snakesNLadders = new snakesNLadders(allIntegers, snakeIntegerIntegerMap,
                ladderIntegerIntegerMap, 2);


        System.out.println(snakesNLadders.getMinMoves(16));
    }
}
