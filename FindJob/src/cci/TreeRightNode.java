package cci;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by romil on 4/16/15.
 */
public class TreeRightNode {
    public static class Node {
        public String name;
        public Node right;
        public Node left;
        public Node next;

        public Node(String name) {
            this.name = name;
        }
    }

    private class Tree {
        Set<Node> tree = new HashSet<>();
        Node root;

        public Tree(Node root) {
            this.root = root;
            tree.add(root);
        }

        public Pair<Node, Node> getChildren(Node node) {
            return new Pair<>(node.left, node.right);
        }

        public void putNode(Node n) {
            tree.add(n);
        }

        public String toString() {
            StringBuffer output = null;
            for(Node n : tree){
                output.append(n.name);
                output.append(n.left.name);
                output.append(n.right.name);
            }

            return output.toString();
        }
    }

    public static void main(String[] args) {
        Node a = new Node("a");
        Node b = new Node("b");
    }
}
