package practice.leetcode;

import java.util.*;

/**
 * Created by romil on 5/18/17.
 */
public class InsertInterval {

    public static class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
    }

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if(newInterval == null) return intervals;

        List<Interval> finalList = new ArrayList<Interval>();

        if(intervals.isEmpty()) {
            finalList.add(newInterval);
            return finalList;
        }

        int first = 0;
        int last = 0;

        //add at the beginning
        if (newInterval.end < intervals.get(0).start) {
            intervals.add(0,newInterval);
            return intervals;
        }

        //add at the end
        if (newInterval.start > intervals.get(intervals.size()-1).end) {
            intervals.add(newInterval);
            return intervals;
        }

        //expand start
        if (newInterval.end == intervals.get(0).start) {
            Interval i = intervals.get(0);
            i.start = newInterval.start;
            return intervals;
        }

        //expand end
        if (newInterval.start == intervals.get(intervals.size()-1).end) {
            Interval i = intervals.get(intervals.size()-1);
            i.end = newInterval.end;
            return intervals;
        }

        int ctr = 0;
        boolean startInserted = false;
        boolean endInserted = false;
        for (Interval i:intervals) {
            if(!startInserted && newInterval.start > i.end) {
                ctr++;
                continue;
            }

            if (!startInserted && newInterval.start <= i.end) {
                startInserted = true;
                first = ctr;
            }

            boolean isEndLessThanNextStart = ((ctr+1) <= (intervals.size() - 1)) ? newInterval.end < intervals.get(ctr+1).start : true;

            if (startInserted && !endInserted && newInterval.end >= i.start && isEndLessThanNextStart) {
                endInserted = true;
                last = ctr;
            }

            if (startInserted && endInserted) break;
            ctr++;
        }

        if (first == last) {
            Set<Integer> s = new HashSet<>();
            s.add(newInterval.start);
            s.add(newInterval.end);
            Interval i = intervals.get(first);
            s.add(i.end);
            s.add(i.start);
            i.start = Collections.min(s);
            i.end = Collections.max(s);
            intervals.set(first,i);
            return intervals;
        }

        int min = (newInterval.start <= intervals.get(first).start) ? newInterval.start : intervals
                .get(first).start;

        int max = (newInterval.end >= intervals.get(last).end) ? newInterval.end : intervals
                .get(last).end;

        ctr = last;
        while (last >= first) {
            intervals.remove(ctr--);
            last--;
        }

        Interval n = new Interval(min, max);
        intervals.add(first, n);

        return intervals;
    }

    public static void main(String[] args) {
        int ip[][] = {{1,5}};
        Interval ti = new Interval(0,1);

        InsertInterval ii = new InsertInterval();
        List<Interval> lip = new ArrayList<>();

        for (int i = 0; i < ip.length; i++) {
            Interval it = new Interval(ip[i][0], ip[i][1]);
            lip.add(it);
        }

        List<Interval> op = ii.insert(lip,ti);

        StringBuilder sb = new StringBuilder();
        for (Interval iop : op) {
            sb.append('[');
            sb.append(iop.start);
            sb.append(',');
            sb.append(iop.end);
            sb.append(']');
            sb.append(',');
        }

        int len = sb.length();
        String s = sb.substring(0, len-1);

        System.out.println(s);
    }

}
