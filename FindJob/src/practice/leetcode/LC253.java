package practice.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class LC253 {
      public static class Interval {
          int start;
          int end;
          Interval() { start = 0; end = 0; }
          Interval(int s, int e) { start = s; end = e; }
      }

    public int minMeetingRooms(Interval[] intervals) {
        ArrayList<LinkedList<Interval>> confs = new ArrayList<>();
        if (intervals.length <= 1) return intervals.length;

        LinkedList<Interval> init = new LinkedList<>();
        init.add(intervals[0]);
        confs.add(init);

        for (int outer = 1; outer < intervals.length; outer++) {
            Interval meetingToInsert = intervals[outer];
            boolean isIns = false;
            for (int ctr = 0; ctr < confs.size(); ctr++) {
                LinkedList<Interval> schedule = confs.get(ctr);
                ListIterator<Interval> scheduleItr = schedule.listIterator(0);

                Interval prevMeeting = null;
                Interval currMeeting;
                Interval nextMeeting = null;

                //add anywhere after a meeting
                while (scheduleItr.hasNext()) {
                    currMeeting = scheduleItr.next();
                    scheduleItr.previous();

                    //insert before
                    if (scheduleItr.hasPrevious()) {
                        prevMeeting = scheduleItr.previous();
                        scheduleItr.next();
                    }
                    if ((prevMeeting != null &&
                        prevMeeting.end <= meetingToInsert.start &&
                        meetingToInsert.end <= currMeeting.start) ||
                            (prevMeeting == null && meetingToInsert.end <= currMeeting.start) ) {
                        if (scheduleItr.hasPrevious()) scheduleItr.previous();
                        scheduleItr.add(meetingToInsert);
                        isIns = true;
                        break;
                    }

                    if (scheduleItr.hasNext()) {
                        nextMeeting = scheduleItr.next();
                        scheduleItr.previous();
                    }
                    if ((nextMeeting == null && meetingToInsert.start >= currMeeting.end) ||
                        (nextMeeting != null && currMeeting.end <= meetingToInsert.start &&
                        meetingToInsert.end <= nextMeeting.start)) {
                        if (scheduleItr.hasNext()) scheduleItr.next();
                        scheduleItr.add(meetingToInsert);
                        isIns = true;
                        break;
                    }
                }
                if (isIns) break;
            }

            if (!isIns) {
                LinkedList<Interval> ll = new LinkedList<>();
                ll.add(meetingToInsert);
                confs.add(ll);
            }
        }
        return confs.size();
    }
    public static void main(String[] args) {
        LC253 lc253 = new LC253();
        Interval[] i = {new Interval(7,10), new Interval(2,4)};
//        Interval[] i = {new Interval(5,8), new Interval(6,8)};
//        Interval[] i = {new Interval(0,30), new Interval(5,10), new Interval(15,20)};
        System.out.println(lc253.minMeetingRooms(i));
    }
}


