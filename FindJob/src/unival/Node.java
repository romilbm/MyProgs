package unival;

/**
 * Created by romil on 3/5/16.
 */
public class Node {
    private final int value;
    private final String name;
    private Node leftNode;
    private Node rightNode;

    public Node(int value, String name) {
        this.value = value;
        this.name = name;
        this.leftNode = null;
        this.rightNode = null;
    }

    public int getValue() {
        return value;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public String getName() {
        return name;
    }
}
