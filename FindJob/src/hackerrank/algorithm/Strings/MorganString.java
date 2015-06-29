package hackerrank.algorithm.Strings;

import java.util.*;

public class MorganString {

    public static int CAPACITY = Integer.MAX_VALUE - 10;
    public static Map<String, Integer> cache;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());

        for(int i=0; i<n; i++){
            cache = new HashMap<>();
            String j = in.nextLine();
            String d = in.nextLine();
            List<String> answers = combineLex(j, d);
            for(String answer : answers) {
                    System.out.print(answer);
            }
            System.out.println();
        }
    }

    private static List<String> combineLex(final String j, final String d) {
        int jPtr = 0;
        int dPtr = 0;
        StringBuffer morgan = new StringBuffer();

        List<String> answers = new ArrayList<>();

        while(jPtr < j.length() || dPtr < d.length()) {
            Character jChar = null;
            Character dChar = null;

            if(jPtr < j.length()) {
                jChar = j.charAt(jPtr);
            }
            if(dPtr < d.length()){
                dChar = d.charAt(dPtr);
            }

            if(jChar == null && dChar != null) {
                morgan.append(dChar);
                dPtr++;
            } else if(dChar == null && jChar != null) {
                morgan.append(jChar);
                jPtr++;
            } else {
                if(dChar < jChar){
                    morgan.append(dChar);
                    dPtr++;
                } else if (dChar > jChar){
                    morgan.append(jChar);
                    jPtr++;
                } else {
                    //find next unequal
                    int tmpJPtr = jPtr+1;
                    int tmpDPtr = dPtr+1;
                    if(cache.containsKey(jPtr + " " + dPtr)) {
                       int choice = cache.get(jPtr + " " + dPtr);
                       if(choice == 0) {
                           morgan.append(jChar);
                           jPtr++;
                       } else {
                           morgan.append(dChar);
                           dPtr++;
                       }
                    } else {
                        int choice = 0;
                        Character jSmallest = jChar;
                        Character dSmallest = dChar;
                        boolean isCharAppend = false;
                        Set<String> tempKeys = new HashSet<>();
                        while(tmpJPtr < j.length() && tmpDPtr < d.length()){
                            Character jTempChar = j.charAt(tmpJPtr);
                            Character dTempChar = d.charAt(tmpDPtr);
                            if(jTempChar < dTempChar){
                                morgan.append(jChar);
                                jPtr++;
                                choice = 0;
                                isCharAppend = true;
                                break;
                            } else if(dTempChar < jTempChar){
                                morgan.append(dChar);
                                dPtr++;
                                choice = 1;
                                isCharAppend = true;
                                break;
                            } else {
                                if(jTempChar < jSmallest) {
                                    jSmallest = jTempChar;
                                }

                                if(dTempChar < dSmallest) {
                                    dSmallest = dTempChar;
                                }
                            }
                            tempKeys.add(tmpJPtr + " " + tmpDPtr);
                            tmpJPtr++;
                            tmpDPtr++;
                        }

                        if(!isCharAppend) {
                            if(tmpJPtr == j.length() && tmpDPtr < d.length()) {
                                if(j.charAt(tmpJPtr-1) >= dSmallest) {
                                    morgan.append(dChar);
                                    dPtr++;
                                    choice = 1;
                                } else {
                                    morgan.append(jChar);
                                    jPtr++;
                                    choice = 0;
                                }
                            } else if(tmpDPtr == d.length() && tmpJPtr < j.length()) {
                                if(d.charAt(tmpDPtr-1) >= jSmallest) {
                                    morgan.append(jChar);
                                    jPtr++;
                                    choice = 0;
                                } else {
                                    morgan.append(dChar);
                                    dPtr++;
                                    choice = 1;
                                }
                            } else {
                                morgan.append(jChar);
                                jPtr++;
                                choice = 0;
                            }
                        }

                        for(String tempKey: tempKeys) {
                            cache.put(tempKey, choice);
                        }
                    }

                }
            }

            if(morgan.length() == CAPACITY){
                answers.add(morgan.toString());
                morgan = new StringBuffer();
            }
        }
        answers.add(morgan.toString());
        return answers;
    }
}
