import tree.Node;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    Node arvore = new Node(1);

    arvore = Node.insert(arvore, 2);
    arvore = Node.insert(arvore, 3);
    arvore = Node.insert(arvore, 6);
    arvore = Node.insert(arvore, 6);
    arvore = Node.insert(arvore, 0);
    arvore = Node.insert(arvore, -2);
    arvore = Node.insert(arvore, -4);
    arvore = Node.insert(arvore, -3);

    System.out.println("Valores de cada nó");
    System.out.println(arvore.value);
    System.out.println(arvore.rightNode.value);
    System.out.println(arvore.rightNode.rightNode.value);
    System.out.println(arvore.leftNode.value);
    System.out.println(arvore.leftNode.rightNode.value);
    System.out.println(arvore.leftNode.leftNode.value);


    System.out.println("\n");
    System.out.println("Valores de altura height");
    System.out.println(arvore.height);
    System.out.println(arvore.rightNode.height);
    System.out.println(arvore.rightNode.rightNode.height);
    System.out.println(arvore.leftNode.height);
    System.out.println(arvore.leftNode.rightNode.height);
    System.out.println(arvore.leftNode.leftNode.height);


    System.out.println("\n");
    System.out.println("Valores de balanceFactor");
    System.out.println(arvore.balanceFactor);
    System.out.println(arvore.rightNode.balanceFactor);
    System.out.println(arvore.rightNode.rightNode.balanceFactor);
    System.out.println(arvore.leftNode.balanceFactor);
    System.out.println(arvore.leftNode.rightNode.balanceFactor);
    System.out.println(arvore.leftNode.leftNode.balanceFactor);


}
