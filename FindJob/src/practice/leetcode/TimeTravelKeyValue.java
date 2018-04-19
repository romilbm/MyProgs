package practice.leetcode;

import java.util.*;

public class TimeTravelKeyValue {

    public class TimeValue {
        long ts;
        Integer val;
        public TimeValue(long ts, Integer val) {
            this.ts = ts;
            this.val = val;
        }
    }

    Map<String, List<TimeValue>> data;

    public TimeTravelKeyValue() {
        data = new HashMap<>();
    }

    public void set(String key, Integer val) {


        if (data.containsKey(key)) {
            List<TimeValue> valueList = data.get(key);
            valueList.add(new TimeValue(System.currentTimeMillis(), val));
        } else {
            List<TimeValue> valueList = new ArrayList<>();
            valueList.add(new TimeValue(System.currentTimeMillis(), val));
            data.put(key, valueList);
        }
    }

    //if the key doesn't exist or if the value wasn't set before the ts, null will be returned.
    public Integer get(String key, long ts) {
        if (data.containsKey(key)) {
            List<TimeValue> valueList = data.get(key);
            TimeValue prev = null;

            for (TimeValue curr : valueList) {
                if (curr.ts ==  ts) {
                    return curr.val;
                } else if (curr.ts > ts) {
                    break;
                }
                prev = curr;
            }
            if (prev == null) return null;
            return prev.val;
        } else {
            return null;
        }

    }

    public static void main(String[] args) {
        TimeTravelKeyValue ttkv = new TimeTravelKeyValue();

        //test for

    }
}
