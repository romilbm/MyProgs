package practice.leetcode;

import java.util.*;

public class Codec {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static class TreeNodeHolder {
        TreeNode tn;

        TreeNodeHolder(TreeNode tn) {
            this.tn = tn;
        }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        ArrayDeque<TreeNodeHolder> q = new ArrayDeque<>();
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        q.offer(new TreeNodeHolder(root));

        while (q.peek() != null) {
            TreeNodeHolder holder = q.poll();
            if (holder.tn == null) {
                sb.append("X ");
            } else {
                sb.append(holder.tn.val);
                sb.append(" ");
                q.offer(new TreeNodeHolder(holder.tn.left));
                q.offer(new TreeNodeHolder(holder.tn.right));
            }
        }

        return sb.toString().trim();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.isEmpty()) return null;
        String[] parts = data.split(" ");
        ArrayDeque<TreeNode> q = new ArrayDeque<>();
        TreeNode root = new TreeNode(Integer.parseInt(parts[0]));
        q.offer(root);

        for (int i=1; i<parts.length-1; i+=2) {
            TreeNode n = q.poll();
            String lc = parts[i];
            String rc = parts[i+1];

            if (!lc.equals("X")) {
                n.left = new TreeNode(Integer.parseInt(lc));
                q.offer(n.left);
            }

            if (!rc.equals("X")) {
                n.right = new TreeNode(Integer.parseInt(rc));
                q.offer(n.right);
            }
        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        n1.left = n2;
        n2.left = n4;
        n2.right = n5;
        n5.left = n6;
        n5.right = n7;



        Codec c = new Codec();
//        System.out.println(c.serialize(n1));
        TreeNode r = c.deserialize(c.serialize(new TreeNode(1)));

        System.out.println("a".split(" "));
    }
}