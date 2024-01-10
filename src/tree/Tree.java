package tree;

import stack.StackGeneric;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("DuplicatedCode")
public class Tree {

    private Node root;

    public Tree()
    {
    }

    public Tree(Node root)
    {
        this.root = root;
    }

    public Node find(int key)
    {
        Node current = root;
        while (current != null && current.getKey() != key) {
            if (key < current.getKey()) {
                current = current.getlChild();
            }
            else {
                current = current.getrChild();
            }
        }
        return current;
    }

    public static Tree createStringTree(String message)
    {
        Tree master = new Tree();
        List<Tree> forest = new ArrayList<>();
        for (char c : message.toCharArray()) {
            Node node = new Node.Builder(-1, c).build();
            Tree tree = new Tree(node);
            forest.add(tree);
        }
        for (Tree tree : forest) {
            master = mergeTrees(master, tree);
        }
        return master;
    }

    public static Tree mergeTrees(Tree aTree, Tree bTree)
    {
        Node newRoot;
        if (aTree.root == null) {
            newRoot = bTree.root;
        }
        else {
            newRoot = new Node.Builder(-1, '+')
                              .leftChild(aTree.root)
                              .rightChild(bTree.root)
                              .build();
        }
        return new Tree(newRoot);
    }

    public void insert(int key, Object val)
    {
        Node insert = new Node.Builder(key, val).build();
        if (root == null) {
            root = insert;
        }
        else {
            Node current = root;
            Node parent;
            while (true) {
                parent = current;
                // less than - left
                if (key < current.getKey()) {
                    current = current.getlChild();
                    if (current == null) {
                        parent.setlChild(insert);
                        return;
                    }
                }
                //  greater than or equal - right
                else {
                    current = current.getrChild();
                    if (current == null) {
                        parent.setrChild(insert);
                        return;
                    }
                }
            }
        }
    }

    public boolean delete(int key)
    {
        Node parent, curr;
        parent = curr = root;
        boolean isLeftChild = true;
        while (curr.getKey() != key) {
            parent = curr;
            if (key < curr.getKey()) {
                isLeftChild = true;
                curr        = curr.getlChild();
            }
            else {
                isLeftChild = false;
                curr        = curr.getrChild();
            }
            if (curr == null) return false;
        }
        // begin case: 0 child
        if (curr.getlChild() == null && curr.getrChild() == null) {
            if (curr == root) {
                root = null;
            }
            else if (isLeftChild) {
                parent.setlChild(null);
            }
            else {
                parent.setrChild(null);
            }
        }
        // end case: 0 child
        // begin case: 1 child
        // child left
        else if (curr.getrChild() == null) {
            // special case: node to be deleted root
            if (curr == root) {
                root = curr.getlChild();
            }
            // child of left
            else if (isLeftChild) {
                parent.setlChild(curr.getlChild());
            }
            // child of right
            else {
                parent.setrChild(curr.getrChild());
            }
        }
        // child right
        else if (curr.getlChild() == null) {
            // special case: node to be deleted root
            if (curr == root) {
                root = curr.getrChild();
            }
            // child of left
            else if (isLeftChild) {
                parent.setlChild(curr.getrChild());
            }
            // child of right
            else {
                parent.setrChild(curr.getrChild());
            }
        }
        // end case: 1 child
        // begin case: 2 child
        else {
            Node successor = getSuccessor(curr);
            if (curr == root) {
                root = successor;
            }
            else if (isLeftChild) {
                parent.setlChild(successor);
            }
            else {
                parent.setrChild(successor);
            }
            successor.setlChild(curr.getlChild());
        }
        // end case: 2 child
        return true;
    }

    public Node min()
    {
        Node min = null;
        Node current = root;
        while (current != null) {
            min     = current;
            current = current.getlChild();
        }
        return min;
    }

    public void displayTree()
    {
        displayTree(32);
    }

    public void displayTree(int spaces)
    {
        StackGeneric<Node> globalStack = new StackGeneric<>();
        globalStack.push(root);
        int nBlanks = spaces;
        boolean isRowEmpty = false;
        StringBuilder lineBreak = new StringBuilder();
        lineBreak.append("•".repeat(nBlanks * 2));
        System.out.println(lineBreak);

        while (!isRowEmpty) {
            StackGeneric<Node> localStack = new StackGeneric<>();
            isRowEmpty = true;
            for (int i = 0; i < nBlanks; i++) {
                System.out.print(' ');
            }

            while (!globalStack.isEmpty()) {
                Node temp = globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.getVal());
                    localStack.push(temp.getlChild());
                    localStack.push(temp.getrChild());

                    if (temp.getlChild() != null || temp.getrChild() != null) {
                        isRowEmpty = false;
                    }
                }
                else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int i = 0; i < (nBlanks * 2) - 2; i++) {
                    System.out.print(' ');
                }
            }
            System.out.println();
            nBlanks /= 2;
            while (!localStack.isEmpty()) {
                globalStack.push(localStack.pop());
            }
        }
        System.out.println(lineBreak);
    }

    public void traverse(int traverseType)
    {
        switch (traverseType) {
            case 1:
                System.out.print("\nPreorder traversal: ");
                preOrder(root);
                break;
            case 2:
                System.out.print("\nInorder traversal");
                inOrder(root);
                break;
            case 3:
                System.out.print("\nPostorder traversal");
                postOrder(root);
                break;
        }
    }

    void inOrder(Node localRoot)
    {
        if (localRoot != null) {
            inOrder(localRoot.getlChild());
            System.out.println(localRoot.getKey() + " ");
            inOrder(localRoot.getrChild());
        }
    }

    void preOrder(Node localRoot)
    {
        if (localRoot != null) {
            System.out.println(localRoot.getKey() + " ");
            preOrder(localRoot.getlChild());
            preOrder(localRoot.getrChild());
        }
    }

    void postOrder(Node localRoot)
    {
        if (localRoot != null) {
            postOrder(localRoot.getlChild());
            postOrder(localRoot.getrChild());
            System.out.println(localRoot.getKey() + " ");
        }
    }

    private Node getSuccessor(Node delNode)
    {
        Node paren, successor, curr;
        paren = successor = delNode;
        curr  = delNode.getrChild();
        while (curr != null) {
            paren     = successor;
            successor = curr;
            curr      = curr.getlChild();
        }
        if (successor != delNode.getrChild()) {
            paren.setlChild(successor.getrChild());
            successor.setrChild(delNode.getrChild());
        }
        return successor;
    }

    private static class TreeDemo {

        public static void main(String[] args)
        {
            Tree tree = new Tree();
            tree.insert(4, 'a');
            tree.insert(3, "abc");
            tree.insert(6, 1);
            tree.insert(5, 2.0);
            tree.displayTree(16);
            tree.displayTree();
        }

    }

    private static class AlphaTreeDemo {

        public static void main(String[] args)
        {
            Tree alphaTree = createStringTree("ABCDE");
            alphaTree.displayTree();
        }

    }

}
