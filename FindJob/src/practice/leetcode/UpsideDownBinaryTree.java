package practice.leetcode;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by romil on 6/6/17.
 */
public class UpsideDownBinaryTree {
      public static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }

    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null) return null;

        Pair<TreeNode, TreeNode> dq = new Pair<>(root.left, root.right);
        root.left = null;
        root.right = null;
        TreeNode curr = root;
        TreeNode next;
        while (dq.getKey() != null) {
            next = dq.getKey();
            if (next == null) {
                break;
            }
            Pair ndq = new Pair<>(next.left, next.right);
            next.right= curr;
            next.left = dq.getValue();
            dq = ndq;
            curr = next;
        }

        return curr;
    }

    public TreeNode upsideDownBinaryTree2(TreeNode n) {
        if (n.left == null) return n;
        TreeNode parent = n.left;
        TreeNode root = upsideDownBinaryTree(parent);
        parent.right = n;
        parent.left = n.right;
        n.right = null;
        n.left = null;
        return root;

//        if(root.left == null) {
//            return root;
//        }
//
//        TreeNode newRoot = upsideDownBinaryTree2(root.left);
//        root.left.left = root.right;   // node 2 left children
//        root.left.right = root;         // node 2 right children
//        root.left = null;
//        root.right = null;
//        return newRoot;
    }

    public static void main(String[] args) {
        UpsideDownBinaryTree u = new UpsideDownBinaryTree();
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);

        n1.left = n2;
//        n1.right = n3;
        n2.left = n4;
//        n2.right = n5;
        n4.left = n6;

//        TreeNode n =u.upsideDownBinaryTree(n1);
        TreeNode nA =u.upsideDownBinaryTree2(n1);
        System.out.println(nA);
    }
}
