package tree;

import stack.StackGeneric;

public class Tree {

    private INode root;

    public INode find(int key)
    {
        INode current = root;
        while (current != null && current.key != key) {
            if (key < current.key) {
                current = current.lChild;
            }
            else {
                current = current.rChild;
            }
        }
        return current;
    }

    public void insert(int key, double val)
    {
        INode insert = new INode(key, val);
        if (root == null) {
            root = insert;
        }
        else {
            INode current = root;
            INode parent;
            while (true) {
                parent = current;
                // less than - left
                if (key < current.key) {
                    current = current.lChild;
                    if (current == null) {
                        parent.lChild = insert;
                        return;
                    }
                }
                //  greater than or equal - right
                else {
                    current = current.rChild;
                    if (current == null) {
                        parent.rChild = insert;
                        return;
                    }
                }
            }
        }
    }

    public boolean delete(int key)
    {
        INode parent, curr;
        parent = curr = root;
        boolean isLeftChild = true;
        while (curr.key != key) {
            parent = curr;
            if (key < curr.key) {
                isLeftChild = true;
                curr        = curr.lChild;
            }
            else {
                isLeftChild = false;
                curr        = curr.rChild;
            }
            if (curr == null) return false;
        }
        // begin case: 0 child
        if (curr.lChild == null && curr.rChild == null) {
            if (curr == root) {
                root = null;
            }
            else if (isLeftChild) {
                parent.lChild = null;
            }
            else {
                parent.rChild = null;
            }
        }
        // end case: 0 child
        // begin case: 1 child
        // child left
        else if (curr.rChild == null) {
            // special case: node to be deleted root
            if (curr == root) {
                root = curr.lChild;
            }
            // child of left
            else if (isLeftChild) {
                parent.lChild = curr.lChild;
            }
            // child of right
            else {
                parent.rChild = curr.rChild;
            }
        }
        // child right
        else if (curr.lChild == null) {
            // special case: node to be deleted root
            if (curr == root) {
                root = curr.rChild;
            }
            // child of left
            else if (isLeftChild) {
                parent.lChild = curr.rChild;
            }
            // child of right
            else {
                parent.rChild = curr.rChild;
            }
        }
        // end case: 1 child
        // begin case: 2 child
        else {
            INode successor = getSuccessor(curr);
            if (curr == root) {
                root = successor;
            }
            else if (isLeftChild) {
                parent.lChild = successor;
            }
            else {
                parent.rChild = successor;
            }
            successor.lChild = curr.lChild;
        }
        // end case: 2 child
        return true;
    }

    public INode min()
    {
        INode min = null;
        INode current = root;
        while (current != null) {
            min     = current;
            current = current.lChild;
        }
        return min;
    }

    public void displayTree()
    {
        displayTree(32);
    }

    public void displayTree(int spaces)
    {
        StackGeneric<INode> globalStack = new StackGeneric<>();
        globalStack.push(root);
        int nBlanks = spaces;
        boolean isRowEmpty = false;
        StringBuilder lineBreak = new StringBuilder();
        lineBreak.append("•".repeat(nBlanks * 2));
        System.out.println(lineBreak);

        while (!isRowEmpty) {
            StackGeneric<INode> localStack = new StackGeneric<>();
            isRowEmpty = true;
            for (int i = 0; i < nBlanks; i++) {
                System.out.print(' ');
            }

            while (!globalStack.isEmpty()) {
                INode temp = globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.key);
                    localStack.push(temp.lChild);
                    localStack.push(temp.rChild);

                    if (temp.lChild != null || temp.rChild != null) {
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

    void inOrder(INode localRoot)
    {
        if (localRoot != null) {
            inOrder(localRoot.lChild);
            System.out.println(localRoot.key + " ");
            inOrder(localRoot.rChild);
        }
    }

    void preOrder(INode localRoot)
    {
        if (localRoot != null) {
            System.out.println(localRoot.key + " ");
            preOrder(localRoot.lChild);
            preOrder(localRoot.rChild);
        }
    }

    void postOrder(INode localRoot)
    {
        if (localRoot != null) {
            postOrder(localRoot.lChild);
            postOrder(localRoot.rChild);
            System.out.println(localRoot.key + " ");
        }
    }

    private INode getSuccessor(INode delNode)
    {
        INode paren, successor, curr;
        paren = successor = delNode;
        curr  = delNode.rChild;
        while (curr != null) {
            paren     = successor;
            successor = curr;
            curr      = curr.lChild;
        }
        if (successor != delNode.rChild) {
            paren.lChild     = successor.rChild;
            successor.rChild = delNode.rChild;
        }
        return successor;
    }

    private static class TreeDemo {

        public static void main(String[] args)
        {
            Tree tree = new Tree();
            tree.insert(4, 2.0);
            tree.insert(3, 2.0);
            tree.insert(6, 2.0);
            tree.insert(5, 2.0);
            tree.displayTree(16);
            tree.displayTree();
        }

    }

}
