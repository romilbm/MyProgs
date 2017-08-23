package practice.leetcode;

import unival.Tree;

import java.util.*;
public class ValidateBST {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;

        return checkBST(root, new ArrayList<Integer>(), new ArrayList<Integer>());
    }

    private boolean checkBST(TreeNode n, List<Integer> less, List<Integer> more) {
        if (n == null) return true;

        for (int i: less) {
            if (n.val >= i) return false;
        }

        for (int i: more) {
            if (n.val <= i) return false;
        }

        List<Integer> newless = new ArrayList<>();
        List<Integer> newmore = new ArrayList<>();
        newless.addAll(less);
        newless.add(n.val);
        newmore.addAll(more);
        newmore.add(n.val);
        return checkBST(n.left, newless, more) && checkBST(n.right, less, newmore);
    }

    public static void main(String[] args) {
        ValidateBST vbst = new ValidateBST();

        TreeNode n2 = new TreeNode(2);
        TreeNode n1 = new TreeNode(1);
        TreeNode n3 = new TreeNode(3);
        n2.left = n1;
        n2.right = n3;

        System.out.println(vbst.isValidBST(n2));
    }
}
