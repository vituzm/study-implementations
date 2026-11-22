# AVL Tree — Java Implementation

This project implements an AVL tree with an interactive command-line interface. The tree maintains binary-search-tree ordering while using rotations to keep itself balanced after insertions and deletions.

## Source Files

- `src/Main.java` — Interactive console menu and command handling.
- `src/tree/Node.java` — AVL node, insertion, deletion, search, traversal, balancing, and tree display logic.

## AI Assistance Note

The code in `src/tree/Node.java` was completely written without AI assistance. AI was used only to help understand binary-tree concepts and related theory. The implementation itself was created manually by the repository author.

## Features

- Insert integer values
- Delete integer values
- Search for values and display the visited path
- Traverse the tree in pre-order, in-order, or post-order
- Display the tree horizontally
- Automatically perform single and double rotations
- Reject duplicate values

## Run the Program

Open `avl-tree` as a module in IntelliJ IDEA and run `Main.java`. A JDK that supports Java compact source files and the `IO` API is required.

The program can also be launched from the project directory with a compatible JDK:

```powershell
javac src/Main.java src/tree/Node.java
java -cp src Main
```

## Commands

| Command | Description | Example |
|---|---|---|
| `i <value>` | Insert a value | `i 45` |
| `r <value>` | Remove a value | `r 78` |
| `b <value>` | Search for a value | `b 9` |
| `pre` | Pre-order traversal | `pre` |
| `em` or `in` | In-order traversal | `em` |
| `pos` | Post-order traversal | `pos` |
| `printhor` | Display the tree horizontally | `printhor` |
| `sair` | Exit the program | `sair` |

## Core Operations

### Insert

`insert` follows the binary-search-tree insertion rule: values smaller than the current node go left, and values greater than it go right. After insertion, node heights and balance factors are updated, and the tree is rebalanced when necessary.

### Delete

`delete` supports all standard cases:

1. Removing a leaf
2. Removing a node with one child
3. Removing a node with two children, using the in-order successor

The affected nodes are rebalanced after the deletion.

### Balance Factor

Each node stores a balance factor based on the difference between its left and right subtree heights:

```text
balance factor = left height - right height
```

If the factor is greater than `1`, the left side is rebalanced. If it is less than `-1`, the right side is rebalanced.

### Rotations

- **Right rotation** — used for a left-heavy imbalance
- **Left rotation** — used for a right-heavy imbalance
- **Left-right rotation** — used when a left child leans right
- **Right-left rotation** — used when a right child leans left

## Traversals

- **Pre-order:** node, left subtree, right subtree
- **In-order:** left subtree, node, right subtree
- **Post-order:** left subtree, right subtree, node

In an AVL or binary search tree, an in-order traversal produces values in ascending order.
