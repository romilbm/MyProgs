package cci;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MyDatabase {

    private Map<String, String> database;
    private Map<String, Integer> countMap;
    //Stack of List of Operations paired with their result.
    private ArrayDeque<List<Pair<String, String>>> transactionStack = new ArrayDeque<>();
    private List<Pair<String, String>> currentTransaction;
    private boolean onGoingTransaction;

    public MyDatabase() {
        database = new Hashtable<>();
        countMap = new Hashtable<>();
        transactionStack = new ArrayDeque<>();
        currentTransaction = new ArrayList<>();
        onGoingTransaction = false;
    }

    public void set(String var, String value) {
        String oldValue = null;
        if(database.containsKey(var)) {
            oldValue = database.get(var);
            int oldValueCount = countMap.get(oldValue);
            oldValueCount--;

            if(oldValueCount == 0){
                countMap.remove(oldValue);
            } else if (oldValueCount > 0){
                countMap.put(oldValue, oldValueCount);
            }

        }

        database.put(var, value);

        int currentCount = 1;
        if(countMap.containsKey(value)){

            currentCount = countMap.get(value);
            currentCount++;
        }

        countMap.put(value, currentCount);
    }

    public String get(String var){
        return database.get(var);
    }

    public String delete(String var) {
        String value = database.get(var);

        if(value != null){
            database.remove(var);
            int currentCount = countMap.get(value);
            currentCount--;

            if(currentCount == 0){
                countMap.remove(value);
            } else if (currentCount > 0){
                countMap.put(value, currentCount);
            }
        }

        return value;
    }

    public Integer count(String value){
        if(countMap.containsKey(value)){
            return countMap.get(value);
        }

        return 0;
    }

    public void begin() {
        if(!currentTransaction.isEmpty()) {
            transactionStack.add(currentTransaction);
        }
        currentTransaction = new ArrayList<>();
        onGoingTransaction = true;
    }

    public void rollback() {
        if(onGoingTransaction == false) {
            System.out.println("NO TRANSACTION");
            return;
        }

        while (transactionStack.peekLast() != null){
            List<Pair<String, String>> transactiontoBeRolled = transactionStack.getLast();
//            String commandPart = transactiontoBeRolled.
        }


    }

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        MyDatabase db = new MyDatabase();

        try {
            while(!(input=br.readLine()).equals("END")){
                db.processInput(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void processInput(String input) {
        String[] inputParts = input.split(" ");

        String command = inputParts[0];

        switch (command){
            case "SET":
                set(inputParts[1], inputParts[2]);

                if(onGoingTransaction) {
                    Pair<String, String> setCommand = new ImmutablePair<>(input, "");
                    currentTransaction.add(setCommand);
                }
                break;

            case "GET":
                System.out.println(get(inputParts[1]));
                break;

            case "DELETE":
                String val = delete(inputParts[1]);

                if(onGoingTransaction) {
                    Pair<String, String> setCommand = new ImmutablePair<>(input, val);
                    currentTransaction.add(setCommand);
                }

                break;

            case "COUNT":
                System.out.println(count(inputParts[1]));
                break;

            case "BEGIN":
                begin();
                break;

            case "COMMIT":
                transactionStack.clear();
                currentTransaction.clear();
                onGoingTransaction = false;
                break;

            case "ROLLBACK":
                rollback();
                break;
        }
    }

}
