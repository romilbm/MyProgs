package cci;

import java.util.ArrayList;
import java.util.List;

public class SpaceReplacement {

    private final static Character SPACE = ' ';
    private final static Character[] SPACE_REPLACEMENT = {'%', '2', '0'};

    public static Character[] doSpaceReplacement(Character[] stringOfCharacters, int length) {
        List<Integer> positionOfSpaces = new ArrayList<>();
        int numberOfSpaces = 0;
        for (int pointer = 0; pointer < length; pointer++) {
            if (stringOfCharacters[pointer].equals(SPACE)) {
                positionOfSpaces.add(pointer);
                numberOfSpaces ++;
            }
        }

        for (int characterPointer = length-1, currentNumOfSpaces = numberOfSpaces; characterPointer >= 0; characterPointer--) {
            int positionOfCurrentSpace = currentNumOfSpaces != 0 ? positionOfSpaces.get(currentNumOfSpaces - 1) : -1;
            if (characterPointer != positionOfCurrentSpace) {
                Character currentCharacter = stringOfCharacters[characterPointer];
                int newPosition = characterPointer + (currentNumOfSpaces * SPACE_REPLACEMENT.length) - currentNumOfSpaces;
                stringOfCharacters[newPosition] = currentCharacter;

            } else {
                copySpaceReplacement(stringOfCharacters, characterPointer + ((currentNumOfSpaces - 1) * SPACE_REPLACEMENT.length) - (currentNumOfSpaces - 1));
                currentNumOfSpaces--;
            }
        }

        return stringOfCharacters;
    }

    private static void copySpaceReplacement(Character[] stringOfCharacters, int characterPointer) {
        for (Character c : SPACE_REPLACEMENT) {
            stringOfCharacters[characterPointer] = c;
            characterPointer++;
        }
    }

    private static Character[] convertStringToCharacterArray(String s, int padding) {
        Character[] out = new Character[s.length() + padding];

        for (int i = 0; i < s.length(); i++) {
            out[i] = s.charAt(i);
        }

        return out;
    }

    private static String convertCharacterArrayToString(Character [] ip) {
        StringBuffer sb = new StringBuffer();
        for (Character c : ip) {
            sb.append(c);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Character[] ip = convertStringToCharacterArray(" Mr John Smith ", 10);
        Character[] op = doSpaceReplacement(ip, 15);
        System.out.println(convertCharacterArrayToString(op));
    }
}
