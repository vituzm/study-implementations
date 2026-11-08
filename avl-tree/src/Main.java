import tree.Node;

void main() {
    Scanner scanner = new Scanner(System.in);
    Node root = null;

    IO.println("=== Árvore AVL - Menu Interativo ===");
    IO.println("Comandos aceitos conforme especificação:");
    IO.println("  i <valor>  - Insere um elemento (ex: i 45)");
    IO.println("  r <valor>  - Remove um elemento (ex: r 78)");
    IO.println("  b <valor>  - Busca um elemento (ex: b 9)");
    IO.println("  pre        - Percurso Pré-Ordem");
    IO.println("  em (ou in) - Percurso Em-Ordem");
    IO.println("  pos        - Percurso Pós-Ordem");
    IO.println("  printhor   - Exibe a árvore (Horizontal)");
    IO.println("  sair       - Encerra o programa\n");

    while (true) {
        IO.print("Comando: ");
        String input = scanner.nextLine().trim();
        if (input.equalsIgnoreCase("sair")) {
            break;
        }

        String[] parts = input.split("\\s+");
        String command = parts[0].toLowerCase();

        try {
            switch (command) {
                case "i":
                    if (parts.length < 2) {
                        IO.println("Uso: i <elemento>");
                        break;
                    }
                    int valInsert = Integer.parseInt(parts[1]);
                    root = Node.insert(root, valInsert);
                    IO.println("Árvore após inserção de " + valInsert + " na horizontal:");
                    Node.display(root, 0);
                    break;

                case "r":
                    if (parts.length < 2) {
                        IO.println("Uso: r <elemento>");
                        break;
                    }
                    int valDelete = Integer.parseInt(parts[1]);
                    root = Node.delete(root, valDelete);
                    IO.println("Árvore após remoção de " + valDelete + ":");
                    Node.display(root, 0);
                    break;

                case "b":
                    if (parts.length < 2) {
                        IO.println("Uso: b <elemento>");
                        break;
                    }
                    int valSearch = Integer.parseInt(parts[1]);
                    IO.print("Caminho: ");
                    if (root != null) {
                        root.search(valSearch);
                    } else {
                        IO.println("Árvore vazia -> Elemento " + valSearch + " não encontrado na árvore.");
                    }
                    break;

                case "pre":
                    IO.println("--- Pré-Ordem ---");
                    Node.preOrder(root);
                    break;

                case "em":
                case "in":
                    IO.println("--- Em-Ordem ---");
                    Node.inOrder(root);
                    break;

                case "pos":
                    IO.println("--- Pós-Ordem ---");
                    Node.postOrder(root);
                    break;

                case "printhor":
                    IO.println("--- Visualização Vertical ---");
                    if (root == null) IO.println("Árvore vazia.");
                    else Node.display(root, 0);
                    break;

                default:
                    IO.println("Comando inválido.");
            }
        } catch (NumberFormatException e) {
            IO.println("Erro: Informe um número inteiro válido.");
        }
        IO.println();
    }

    scanner.close();
}