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

    private static Node rotateRight(Node node){
        Node leftSon = node.leftNode;
        Node father = node;

        father.leftNode = leftSon.rightNode;
        leftSon.rightNode = father;

        leftSon.rightHeight = father.rightHeight + 1;
        leftSon.leftHeight = father.leftHeight - 1;
        leftSon.height = Math.max(leftSon.leftHeight, leftSon.rightHeight);
        leftSon.balanceFactor = leftSon.leftHeight - leftSon.rightHeight;

        //father.rightHeight CONTINUA O MESMO NO PAI
        if (father.leftNode != null) {
            father.leftHeight = father.leftNode.height + 1;
        } else {
            father.leftHeight = 0;
        }

        father.height = Math.max(father.leftHeight, father.rightHeight);
        father.balanceFactor = father.leftHeight - father.rightHeight;

        return leftSon;
    }

    private static Node rotateLeft(Node node){
        Node rightSon = node.rightNode;
        Node father = node;

        father.rightNode = rightSon.leftNode;
        rightSon.leftNode = father;

        rightSon.leftHeight = father.leftHeight + 1;
        rightSon.rightHeight = father.rightHeight - 1;
        rightSon.height = Math.max(rightSon.leftHeight, rightSon.rightHeight);
        rightSon.balanceFactor = rightSon.leftHeight - rightSon.rightHeight;

        //father.leftHeight CONTINUA O MESMO NO PAI
        if (father.rightNode != null) {
            father.rightHeight = father.rightNode.height + 1;
        } else {
            father.rightHeight = 0;
        }

        father.height = Math.max(father.leftHeight, father.rightHeight);
        father.balanceFactor = father.leftHeight - father.rightHeight;

        return rightSon;
    }

    private static Node balance(Node node){
        if (node.balanceFactor > 1) {
            System.out.println("PENDENDO A ESQUERDA: Fator de balanceamento maior que 1: " + node.value);
            if (node.leftNode != null) {
                if (node.leftNode.balanceFactor < 0) {
                    node = rotateLeft(node.leftNode);
                    System.out.println("Rotacao dupla: Esquerda -> direita: " + node.value);
                }
            }

            node = rotateRight(node);
        } else if (node.balanceFactor < -1) {
            System.out.println("PENDENDO A DIREITA: Fator de balanceamento menor que -1: " + node.value);
            if (node.leftNode != null) {
                if (node.leftNode.balanceFactor > 0) {
                    System.out.println("Rotacao dupla: Direita -> esquerda: " + node.value);
                    node = rotateRight(node.rightNode);
                }
            }

            node = rotateLeft(node);
        }

        return node;
    }

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

        return balance(node);
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
