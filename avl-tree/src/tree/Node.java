package tree;

public class Node {
    public Node rightNode;
    public Node leftNode;

    public int value;

    public int height = 0;
    public int rightHeight = 0;
    public int leftHeight = 0;

    public int balanceFactor = 0;

    public Node(int value) {
        this.value = value;
    }

    // search element ok
    // insert element ok
    // delete element
    // optimize node

    public Node search(int value){
        if (value > this.value){
            if (rightNode == null) return null;
            return this.rightNode.search(value);
        } else if (value < this.value) {
            if (leftNode == null) return null;
            return this.leftNode.search(value);
        }

        return this;
    }

    public static Node insert(Node node, int value){
        if (value > node.value){
            if (node.rightNode == null) {
                node.rightNode = new Node(value);
            }
            else {
                node.rightNode = insert(node.rightNode, value);
            }
            node.rightHeight = node.rightNode.height + 1;
        } else if (value < node.value){
            if (node.leftNode == null) {
                node.leftNode = new Node(value);
            }
            else {
                node.leftNode = insert(node.leftNode, value);
            }
            node.leftHeight = node.leftNode.height + 1;
        }

        node.height = Math.max(node.leftHeight, node.rightHeight);
        node.balanceFactor = node.leftHeight - node.rightHeight;

        return node;
    }

    private Node rotateRight(Node node){
       return null;
    }

    private Node rotateLeft(Node node){
        return null;
    }

    private Node balance(Node node){
        if (node.balanceFactor > 1) {
            if (node.leftNode.balanceFactor < 1) {

            }
            rotateRight(node);
        }

        return node;
    }

//    public static boolean deleteElement(Node node, int value){
//        if (node.rightNode.value == value){
//            node.rightNode = null;
//            return true;
//        } else if (node.leftNode.value == value) {
//            node.leftNode = null;
//            return true;
//        }
//
//        if (value > node.value){
//            return deleteElement(node.rightNode, value);
//        } else if (value < node.value) {
//            return deleteElement(node.leftNode, value);
//        }
//
//        return false;
//    }

    @Override
    public String toString() {
        return "/(" + this.value + ")\\";
    }
}
