package practice.leetcode;


import java.util.*;

import static java.lang.String.valueOf;

/**
 * Created by romilbm on 12/19/13.
 */
public class StringOp {

    public static boolean isIsometric(String s1, String s2){
        if(s1.length() != s2.length()){
            return false;
        }
        HashMap<String, String> m = new HashMap<String, String>();

        for(int i=0; i < s1.length(); i++){
            if(m.containsKey(valueOf(s1.charAt(i)))){
                if(!m.get(valueOf(s1.charAt(i))).equals(valueOf(s2.charAt(i)))){
                    return false;
                }

            } else {
                m.put(valueOf(s1.charAt(i)), valueOf(s2.charAt(i)));
            }
        }
        return true;
    }

    public static boolean isValid(String s){
        Deque<Character> parenthesisTracker = new ArrayDeque<Character>();
        Map<Character, Character> matchingBraces = new HashMap<Character, Character>();
        matchingBraces.put('(',')');
        matchingBraces.put('{','}');
        matchingBraces.put('[',']');
        Set<Character> openingBraces = matchingBraces.keySet();

        for(char c : s.toCharArray()){
            if(parenthesisTracker.size() == 0 && !openingBraces.contains(c)){
                return false;
            }
            if(openingBraces.contains(c)){
                parenthesisTracker.add(c);
            } else {
                Character popped = parenthesisTracker.pollLast();
                if(!openingBraces.contains(popped)){
                    return false;
                }
                Character closingBrace = matchingBraces.get(popped);
                if(closingBrace != c){
                    return false;
                }
            }
        }

        if(!parenthesisTracker.isEmpty()){
            return false;
        }
        return true;
    }

    public static String amazonOccurrenceCalcString(String s){
        Map<Character,Integer> m = new LinkedHashMap<Character, Integer>();

        for(int i=0;i<s.length();i++){
            Character c = s.charAt(i);
            if(m.containsKey(c)){
                int count = m.get(c);
                count++;
                m.put(c,count);
            } else {
                m.put(c,1);
            }
        }

        String op = "";

        for(Character c:m.keySet()){
            op += c.toString() + m.get(c).toString();
            //vanessa@e-walkwater.com
        }
        return op;
    }



}
