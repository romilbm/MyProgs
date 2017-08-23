package practice.leetcode;

import org.junit.Assert;

import java.util.*;

public class Stripe {


    //host_type -> list of allocated servers
    Map<String, List<Integer>> serverLookup;

    public void Stripe() {
        serverLookup = new HashMap<>();
    }

    public String allocate(String hosttype) {
        if (serverLookup.containsKey(hosttype)) {
            List<Integer> allocServer = serverLookup.get(hosttype);
            int servernum = getServerNumber(allocServer);
            allocServer.add(servernum);
            return hosttype + servernum;
        } else {
            List<Integer> allocServer = new ArrayList<>();
            allocServer.add(1);
            serverLookup.put(hosttype, allocServer);
            return hosttype + "1";
        }
    }

    public void deallocate(String hostname) {
        String[] parts = hostname.split("\\P{Alpha}+");
        String hostType = parts[0];

        serverLookup.remove(hostType);
    }

    public int getServerNumber(List<Integer> servers) {
        HashSet<Integer> serversPresent = new HashSet<>();
        // check the length of servers

        for (int server : servers) {
            serversPresent.add(server);
        }

        int i = 1;
        for (;i < Integer.MAX_VALUE; i++) {
            if (!serversPresent.contains(i)) return i;
        }

        return i;
    }

    public static void main(String[] args) {
        Stripe stripe = new Stripe();

//        //happy case
//        int ans = stripe.getServerNumber(new int []{5, 3, 1});
//        Assert.assertEquals(ans, 2);
//
//        // empty list
//        ans = stripe.getServerNumber(new int []{});
//        Assert.assertEquals(ans, 1);
//
//        //test with ip containing values upto INT_MAX-1;
//
//
//        //numbers in descending order
//        ans = stripe.getServerNumber(new int []{6, 5, 4});
//        Assert.assertEquals(ans, 1);
//
//
//        //non first being missing
//        ans = stripe.getServerNumber(new int []{5, 4, 1, 3});
//        Assert.assertEquals(ans, 2);
//
//        //nums in ascending order with largest missing
//        ans = stripe.getServerNumber(new int []{1, 2, 3, 4, 5});
//        Assert.assertEquals(ans, 6);

        System.out.println("Testing done");

    }
}
