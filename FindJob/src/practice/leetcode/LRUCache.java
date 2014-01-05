package practice.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by romil on 1/3/14.
 */
public class LRUCache {
    class valueSet {
        int value;
        int next;
        int previous;

        valueSet(int value) {
            this.value = value;
            next = -1;
            previous = -1;
        }
    }

    private int capacity;
    private Map<Integer, valueSet> cache;
    private int currentLength;
    private int head;
    private int tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<Integer, valueSet>();
        this.currentLength = 0;
        this.head = -1;
        this.tail = -1;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            moveToHead(key);
            return cache.get(key).value;
        }
        return -1;
    }

    public void set(int key, int value) {
        if (cache.containsKey(key)) {
            cache.get(key).value = value;
            moveToHead(key);
        } else {
            valueSet newValue = new valueSet(value);
            if (currentLength < capacity) {
                cache.put(key, newValue);
                currentLength++;
                addToHead(key);
            } else {
                int newTail = cache.get(tail).previous;
                cache.remove(tail);
                tail = newTail;
                cache.put(key, newValue);
                addToHead(key);
            }
        }
    }

    private void moveToHead(int key) {
        if (head != key) {
            valueSet newHead = cache.get(key);
            cache.get(newHead.previous).next = newHead.next;

            if (tail != key) {
                cache.get(newHead.next).previous = newHead.previous;
            }
            else tail = newHead.previous;
            newHead.next = head;
            newHead.previous = -1;
            cache.get(head).previous = key;
            head = key;
        }
    }

    private void addToHead(int key){
        valueSet newValue = cache.get(key);
        newValue.next = head;
        if(currentLength > 1){
            cache.get(head).previous = key;
        }
        head = key;
        if(currentLength == 1)
            tail = key;
    }

    public static void main(String args[]){

    }


}