package practice.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by romil on 7/16/17.
 */
public class LCA {

      public static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> path1 = getPath(root, p);
        List<TreeNode> path2 = getPath(root, q);

        if (path1.size() == 0 || path2.size() == 0) return null;
        TreeNode lca = null;
        for (int i=0; i<path1.size() && i<path2.size(); i++) {
            if (path1.get(i).val != path2.get(i).val) {
                return lca;
            } else {
                lca = path1.get(i);
            }
        }

        return lca;
    }

    public List<TreeNode> getPath(TreeNode n, TreeNode target) {
        if (n == null) return null;
        System.out.println("Came for " + n.val + " " + target.val);
        if (n.val == target.val) {
            System.out.println(n.val + " matched");
            return new ArrayList<>(Arrays.asList(n));
        }
        System.out.println("Will go left from " + n.val + " " + target.val);
        List<TreeNode> leftret = getPath(n.left, target);
        if (leftret != null) {
            leftret.add(0, n);
            System.out.println("Final left at " + n.val + " :" + leftret);
            return leftret;
        }
        System.out.println("Will go right from " + n.val + " " + target.val);
        List<TreeNode> rightret = getPath(n.right, target);
        if (rightret != null) {
            rightret.add(0, n);
            System.out.println("Final right at " + n.val + " :" + rightret);
        }
        System.out.println("Returning null from" + n.val + " " + target.val);
        return null;
    }

    public static void main(String[] args) {
        TreeNode n2 = new TreeNode(2);
        TreeNode n1 = new TreeNode(1);
        n2.right = n1;

        LCA lca = new LCA();

        TreeNode n = lca.lowestCommonAncestor(n2, n2, n1);
        System.out.println(n);
    }
}
