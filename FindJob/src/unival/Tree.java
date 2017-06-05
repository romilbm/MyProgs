package unival;

import java.util.ArrayDeque;
import java.util.Queue;

public class Tree {
    private Node root;

    public Tree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }
    public boolean isUnival() {
        Queue<Node> parsingQueue = new ArrayDeque<>();

        if (root == null) return true;
        parsingQueue.offer(root);
        int value = root.getValue();
        do {
            Node current = parsingQueue.poll();
            if (current.getValue() != value) {
                return false;
            }

            Node leftChild = current.getLeftNode();
            if (leftChild != null) {
                parsingQueue.add(leftChild);
            }

            Node rightChild = current.getRightNode();
            if (rightChild != null) {
                parsingQueue.add(rightChild);
            }

        } while (parsingQueue.peek() != null);

        return true;
    }
    
    public Result largestUnival(Node root) {
        if(root == null){
            return new Result(0, null);
        }

        Result leftSideResult = null;
        if(root.getLeftNode() != null) {
            leftSideResult = largestUnival(root.getLeftNode());
        }

        Result rightSideResult = null;
        if(root.getRightNode() != null) {
            rightSideResult = largestUnival(root.getRightNode());
        }

        if(leftSideResult != null && rightSideResult == null) {
            if(leftSideResult.getLargest() == root.getLeftNode() && root.getValue() == root
                    .getLeftNode().getValue()) {
                return new Result(leftSideResult.getCount()+1, root);
            } else {
                return leftSideResult;
            }
        } else if (leftSideResult == null && rightSideResult != null) {
            if(rightSideResult.getLargest() == root.getRightNode() && root.getValue() == root
                    .getRightNode().getValue()) {
                return new Result(rightSideResult.getCount()+1, root);
            } else {
                return rightSideResult;
            }
        } else if (leftSideResult == null && rightSideResult == null) {
            return new Result(1, root);
        } else {
            if(
                    leftSideResult.getLargest() == root.getLeftNode()
                 && rightSideResult.getLargest() == root.getRightNode()
                 && root.getValue() == root.getLeftNode().getValue()
                 && root.getValue() == root.getRightNode().getValue()
                    ) {
                return new Result(leftSideResult.getCount() + rightSideResult.getCount() + 1, root);
            } else {
                if(leftSideResult.getCount() > rightSideResult.getCount()) {
                    return leftSideResult;
                } else {
                    return rightSideResult;
                }
            }
        }
    }

    public int countUnivals(Node root) {
        if(root == null){
            return 0;
        }

        int leftCount = countUnivals(root.getLeftNode());
        int rightCount = countUnivals(root.getRightNode());

        if(isOneBigUnival(leftCount, rightCount, root)){
            return 1;
        } else {
            return leftCount + rightCount;
        }
    }

    private boolean isOneBigUnival(int leftCount, int rightCount, Node root) {
        int rootValue = root.getValue();

        if(root.getLeftNode() == null && root.getRightNode() != null){
            if(rootValue == root.getRightNode().getValue() && rightCount == 1){
                return true;
            }
        } else if(root.getLeftNode() != null && root.getRightNode() == null){
            if(rootValue == root.getLeftNode().getValue() && leftCount == 1) {
                return true;
            }
        } else if(root.getLeftNode() == null && root.getRightNode() == null) {
            return true;
        } else {
            if((leftCount == 1 && rightCount == 1) && rootValue == root.getLeftNode().getValue() &&
                    rootValue == root.getRightNode().getValue()){
                return true;
            }
        }

        return false;
    }

    private class Result {
        private final int count;
        private final Node largest;

        private Result(int count, Node largest) {
            this.count = count;
            this.largest = largest;
        }

        public int getCount() {
            return count;
        }

        public Node getLargest() {
            return largest;
        }
    }

    public static void main(String[] args) {
        Node a = new Node(2, "a");
        
        Node b = new Node(2, "b");
        Node c = new Node(2, "c");
        
        Node d = new Node(2, "d");
        Node e = new Node(2, "e");
        Node f = new Node(2, "f");
        Node g = new Node(2, "g");
        
        Node h = new Node(2, "h");
        Node i = new Node(2, "i");
        Node j = new Node(2, "j");
        Node k = new Node(2, "k");
        Node l = new Node(2, "l");
        Node m = new Node(2, "m");
        Node n = new Node(2, "n");
        Node o = new Node(2, "o");

        a.setLeftNode(b);
        a.setRightNode(c);

        b.setLeftNode(d);
        b.setRightNode(e);

        c.setLeftNode(f);
        c.setRightNode(g);

        d.setLeftNode(h);
        d.setRightNode(i);

        e.setLeftNode(j);
        e.setRightNode(k);

        f.setLeftNode(l);
        f.setRightNode(m);

        g.setLeftNode(n);
        g.setRightNode(o);

        Tree t = new Tree(a);

        System.out.println(t.isUnival());
        System.out.println(t.countUnivals(t.getRoot()));
        System.out.println(t.largestUnival(t.getRoot()).getLargest().getName());

    }
}