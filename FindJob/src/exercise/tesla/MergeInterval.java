package exercise.tesla;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MergeInterval {
    public class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
  }

    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> ret = new ArrayList<>();
        if (intervals.size() == 0) return ret;

        if (intervals.size() == 1) {
            ret.add(intervals.get(0));
            return ret;
        }

        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2) {
                if (i1.start < i2.start) return -1;
                if (i1.start > i2.start) return 1;
                return 0;
            }
        });

        int aptr = 1;
        int rptr = 0;
        ret.add(intervals.get(0));
        while (aptr < intervals.size()) {
            Interval t = intervals.get(aptr);
            Interval rt = ret.get(rptr);
            if (t.start >= rt.start && t.start <= rt.end) {
                rt.end = Math.max(t.end, rt.end);
                aptr++;
            } else {
                ret.add(t);
                rptr++;
                aptr++;
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        MergeInterval mi = new MergeInterval();
        List<Interval> ans = mi.merge(mi.getInts(new int[][] {{2,3},{2,2},{3,3},{1,3},{5,7},{2,2},{4,6}}));

        for (Interval i: ans) {
            System.out.print("[" + i.start + "," + i.end + "], ");
        }
    }

    private List<Interval> getInts(int[][] ints) {
        List<Interval> ret = new ArrayList<>();

        for (int[] i: ints) {
            ret.add(new Interval(i[0], i[1]));
        }

        return ret;
    }
}
