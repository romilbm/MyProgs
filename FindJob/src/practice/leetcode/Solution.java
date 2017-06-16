package practice.leetcode;

import java.lang.*;
import java.util.*;

public class Solution {
    public static class Graph {
        Set<Node> nodes;

        public Graph() {
            nodes = new HashSet<Node>();
        }

        public void addEdge(Node n1, Node n2) {
            nodes.add(n1);
            nodes.add(n2);
            n1.addNode(n2);
            n2.addNode(n1);
        }

        public void traverseGraph(Graph g, Node start) {
            Set<Node> visited = new HashSet<Node>();

            //validation check

            Deque<Node> q = new ArrayDeque<>();

            q.addLast(start);

            while (!q.isEmpty()) {
                Node curr = q.removeFirst();
                System.out.println(curr.getVal());
                visited.add(curr);
                Set<Node> conns = curr.getConnections();
                for (Node n: conns) {
                    if (!visited.contains(n)) {
                        q.addLast(n);
                    }
                }
            }
        }
    }

    public static class Node {
        int val;
        Set<Node> nodes;
        public Node(int val) {
            this.val = val;
            nodes = new HashSet<>();
        }

        public int getVal() {
            return val;
        }

        public void addNode(Node n) {
            nodes.add(n);
        }

        public Set<Node> getConnections() {
            return nodes;
        }
    }

    public static void main(String args[]) {
        Graph g = new Graph();
        Node n1 = new Node(1);
        Node n2 = new Node(2);

        g.addEdge(n1, n2);
        g.traverseGraph(g, n1);
    }
}