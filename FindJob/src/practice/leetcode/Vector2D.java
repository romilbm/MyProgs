package practice.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Vector2D implements Iterator<Integer> {

    List<Integer> i;
    int iptr;
    int j;
    List<List<Integer>> v;

    private Iterator<List<Integer>> it;
    private Iterator<Integer> cur;
    public Vector2D(List<List<Integer>> vec2d) {
        it = vec2d.iterator();
    }

    @Override
    public Integer next() {
        return cur.next();
    }

    @Override
    public boolean hasNext() {
        while ((cur == null || !cur.hasNext()) && it.hasNext()) {
            cur = it.next().iterator();
        }
        return cur != null && cur.hasNext();
    }

//    public Vector2D(List<List<Integer>> vec2d) {
//        this.v = vec2d;
//        i = null;
//        iptr = 0;
//        for ( ;iptr < v.size(); iptr++) {
//            if (v.get(iptr).isEmpty()) {
//                continue;
//            } else {
//                i = v.get(iptr);
//                break;
//            }
//        }
//        j=0;
//    }
//
//    @Override
//    public Integer next() {
//        // if (!hasNext()) throw new NoSuchElementException();
//        int ret = i.get(j);
//        j++;
//        return ret;
//    }
//
//    @Override
//    public boolean hasNext() {
//        if (i == null) return false;
//        if (j < i.size()) {
//            return true;
//        } else {
//            iptr++;
//            j = 0;
//            i = null;
//            for (; iptr < v.size(); iptr++) {
//                if (v.get(iptr).isEmpty()) {
//                    continue;
//                } else {
//                    i = v.get(iptr);
//                    break;
//                }
//            }
//        }
//        if (i == null) return false;
//        return true;
//    }

    public static void main(String[] args) {
        List<List<Integer>> vec2d = new ArrayList<>();
        vec2d.add(new ArrayList<>(Arrays.asList(1,2)));
        vec2d.add(new ArrayList<>());
        vec2d.add(new ArrayList<>(Arrays.asList(3)));
        vec2d.add(new ArrayList<>(Arrays.asList(4,5,6)));
        Vector2D i = new Vector2D(vec2d);
        while (i.hasNext()) System.out.println((i.next()));
    }
}