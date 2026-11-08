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

    public static void preOrder(Node node) {
        if (node == null) return;

        System.out.println(node.value);
        preOrder(node.leftNode);
        preOrder(node.rightNode);
    }

    public static void inOrder(Node node) {
        if (node == null) return;

        inOrder(node.leftNode);
        System.out.println(node.value);
        inOrder(node.rightNode);
    }

    public static void postOrder(Node node) {
        if (node == null) return;

        postOrder(node.leftNode);
        postOrder(node.rightNode);
        System.out.println(node.value);
    }

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
            System.out.println("PENDENDO A ESQUERDA: Fator de balanceamento maior que 1 no nó: " + node.value);
            if (node.leftNode != null) {
                if (node.leftNode.balanceFactor < 0) {
                    System.out.println("Rotacao dupla: Esquerda -> direita: " + node.value);
                    node.leftNode = rotateLeft(node.leftNode);
                }
            }

            node = rotateRight(node);
        } else if (node.balanceFactor < -1) {
            System.out.println("PENDENDO A DIREITA: Fator de balanceamento menor que -1 no nó: " + node.value);
            if (node.rightNode  != null) {
                if (node.rightNode.balanceFactor > 0) {
                    System.out.println("Rotacao dupla: Direita -> esquerda: " + node.value);
                    node.rightNode = rotateRight(node.rightNode);
                }
            }

            node = rotateLeft(node);
        }

        return node;
    }

    public Node search(int value){
        System.out.print(this.value);

        if (value > this.value){
            if (rightNode == null) {
                System.out.println(" -> Elemento " + value + " não encontrado na árvore.");
                return null;
            }

            System.out.print(" -> ");
            return this.rightNode.search(value);

        } else if (value < this.value) {

            if (leftNode == null) {
                System.out.println(" -> Elemento " + value + " não encontrado na árvore.");
                return null;
            }

            System.out.print(" -> ");
            return this.leftNode.search(value);
        }

        System.out.println(" -> Elemento " + value + " encontrado na árvore.");

        return this;
    }

    public static Node insert(Node node, int value){
        if (node == null) {
            return new Node(value);
        }

        if (value == node.value) {
            return node;
        }

        if (value > node.value){
            if (node.rightNode == null) {
                node.rightNode = new Node(value);
            }
            else {
                node.rightNode = insert(node.rightNode, value);
            }
            node.rightHeight = node.rightNode.height + 1;
        } else {
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

    private static Node findMin(Node node) {
        while (node.leftNode != null) {
            node = node.leftNode;
        }

        return node;
    }

    public static Node delete(Node node, int value) {
        if (node == null) {
            return null;
        }

        if (value > node.value) {
            node.rightNode = delete(node.rightNode, value);
        }
        else if (value < node.value) {
            node.leftNode = delete(node.leftNode, value);
        }
        else {
            // Caso 1: folha
            if (node.leftNode == null && node.rightNode == null) {
                return null;
            }

            // Caso 2: apenas um filho
            if (node.leftNode == null) {
                return node.rightNode;
            }

            if (node.rightNode == null) {
                return node.leftNode;
            }

            // Caso 3: dois filhos
            Node successor = findMin(node.rightNode);

            node.value = successor.value;

            node.rightNode = delete(node.rightNode, successor.value); // deleta a folha se houver
        }


        node.leftHeight =  node.leftNode != null ? node.leftNode.height + 1 : 0;
        node.rightHeight = node.rightNode != null ? node.rightNode.height + 1 : 0;

        node.height = Math.max(node.leftHeight, node.rightHeight);
        node.balanceFactor = node.leftHeight - node.rightHeight;

        return balance(node);
    }

    public static void display(Node node, int level) {
        if (node == null) {
            return;
        }

        display(node.rightNode, level + 1);

        System.out.println("    ".repeat(level) + node.value);

        display(node.leftNode, level + 1);
    }

    @Override
    public String toString() {
        return "/(" + this.value + ")\\";
    }
}
