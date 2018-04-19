package practice.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class BinaryTreeSeDes {
    public static class Node {
        Integer val;
        Node left;
        Node right;
        public Node(Node l, Node r, Integer v) {
            val = v;
            left = l;
            right = r;
        }

        public Node(Integer v) {
            val = v;
        }
    }

    public String serailize(Node root) {
        if (root == null) return "";

        StringBuilder ret = new StringBuilder();

        Queue<Node> q = new ArrayDeque<>();
        q.offer(root);

        while(q.peek() != null) {
            Node curr = q.poll();
            Integer val = curr.val;
            if (val == null) {
                ret.append("X ");
                continue;
            }

            ret.append(val + " ");
            Node l = curr.left;
            Node r = curr.right;

            if (l != null) {
                q.offer(l);
            } else {
                q.offer(new Node(null));
            }

            if (r != null) {
                q.offer(r);
            } else {
                q.offer(new Node(null));
            }
        }

        return ret.toString().trim();
    }

    public Node deserialize(String tree) {
        if (tree == null || tree.isEmpty()) return null;

        String[] nodes = tree.split(" ");

        Node root = new Node(Integer.parseInt(nodes[0]));

        Queue<Node> q = new ArrayDeque<>();
        q.offer(root);

        int pos = 1;

        while(pos < nodes.length) {

            Node curr = q.poll();
            Node left = nodes[pos].equals("X") ? new Node(null) : new Node(Integer.parseInt
                    (nodes[pos]));
            Node right = nodes[pos + 1].equals("X") ? new Node(null) : new Node(Integer.parseInt
                    (nodes[pos + 1]));

            curr.left = left;
            curr.right = right;

            if (left.val != null) q.offer(left);
            if (right.val != null) q.offer(right);

            pos += 2;
        }

        return root;
    }



    public static void main(String[] args) {
        Node n3 = new Node(3);
        Node n5 = new Node(5);
        Node n8 = new Node(8);
        Node n4 = new Node(4);
        Node n1 = new Node(1);

        n3.left = n5;
        n3.right = n1;

        n1.left = n4;
        n4.left = n8;

        BinaryTreeSeDes bsd = new BinaryTreeSeDes();
        String ans = bsd.serailize(n3);
        System.out.println(ans);

        Node n = bsd.deserialize(ans);
        String ans1 = bsd.serailize(n);
        System.out.println(ans1);

    }
}
