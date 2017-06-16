package practice.leetcode;

import java.lang.*;
import java.util.*;

public class MinMeetingRooms {

    public static class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
    }

    public int minMeetingRooms(Interval[] intervals) {
        ArrayList<LinkedList<Interval>> confs = new ArrayList<>();
        for (Interval i : intervals) {
            boolean isIns = false;
            for (int ctr = 0; ctr < confs.size(); ctr++) {
                ListIterator<Interval> llitr = confs.get(ctr).listIterator(0);

                //add before current head if possible
                Interval head = llitr.next();
                if (head.start >= i.end ) {
                    llitr.previous();
                    llitr.add(i);
                    isIns = true;
                    break;
                }

                Interval previous = head;
                Interval next = null;
                while (llitr.hasNext()) {
                    next = llitr.next();
                    //add somewhere in middle
                    if (next.start >= i.end && previous.end <= i.start) {
                        llitr.previous();
                        llitr.add(i);
                        isIns = true;
                        break;
                    }
                    previous = next;
                }

                if (next == null) next = previous;

                //add at the end
                if (next.end <= i.start) {
                    llitr.add(i);
                    isIns = true;
                    break;
                }

            }
            if (isIns != true) {
                LinkedList<Interval> ll = new LinkedList<>();
                ll.add(i);
                confs.add(ll);
            }
        }
        return confs.size();
    }

    public static void main(String[] args) {
        MinMeetingRooms mmr = new MinMeetingRooms();
        int [][] its = {{13,15}, {1,13}, {6,9}};
//        int [][] its = {{1,5}, {8,9}};
        Interval[] is = getIntervalsArray(its);
        System.out.println(mmr.minMeetingRooms(is));
    }

    private static Interval[] getIntervalsArray(int[][] its) {
        Interval [] ret =  new Interval[its.length];

        for (int ctr = 0; ctr < its.length; ctr++) {
            ret[ctr] = new Interval(its[ctr][0], its[ctr][1]);
        }
        return ret;
    }
}
