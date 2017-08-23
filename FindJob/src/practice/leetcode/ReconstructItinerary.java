package practice.leetcode;

import java.util.*;
public class ReconstructItinerary {
    public List<String> findItinerary(String[][] tickets) {
        Map<String, List<String>> map = new HashMap<>();

        for (int i=0; i<tickets.length; i++) {
            String from = tickets[i][0];
            String to = tickets[i][1];
            List<String> tos = map.get(from);
            if (tos == null) {
                tos = new ArrayList<>();
                tos.add(to);
                map.put(from, tos);
            } else {
                tos.add(to);
            }
        }

        String curr = "JFK";
        List<String> ret = new ArrayList<>();
        while (curr != null) {
            ret.add(curr);
            List<String> tos = map.get(curr);
            if (tos == null || tos.isEmpty()) break;
            Collections.sort(tos);
            curr = tos.get(0);
            tos.remove(0);
        }

        return ret;
    }

    public static void main(String[] args) {
        ReconstructItinerary ri = new ReconstructItinerary();

        String[][] ip = new String[][] {{"JFK","ATL"},{"ATL","JFK"}};

        System.out.println(ri.findItinerary(ip));


    }
}
