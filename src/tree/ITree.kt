package tree;

import stack.StackGeneric;

@SuppressWarnings("unused")
public class ITree {
    
    private INode root;
    
    public INode delete(int key)
    {
        INode parent, curr;
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
            if (curr == null) return null;
        }
        INode deleted = curr;
        // case 0 children
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
        // case 1 child: only left
        else if (curr.getrChild() == null) {
            if (curr == root) {
                root = curr.getlChild();
            }
            else if (isLeftChild) {
                parent.setlChild(curr.getlChild());
            }
            else {
                parent.setrChild(curr.getlChild());
            }
        }
        // case 1 child: only right
        else if (curr.getlChild() == null) {
            if (curr == root) {
                root = curr.getrChild();
            }
            else if (isLeftChild) {
                parent.setlChild(curr.getrChild());
            }
            else {
                parent.setrChild(curr.getrChild());
            }
        }
        // case 2 children
        else {
            INode successor = getSuccessor(curr);
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
        return deleted;
    }
    
    public void displayTree()
    {
        displayTree(32);
    }
    
    public void displayTree(int spaces)
    {
        StackGeneric<INode> globalStack = new StackGeneric<>();
        globalStack.push(root);
        int           nBlanks    = spaces;
        boolean       isRowEmpty = false;
        StringBuilder lineBreak  = new StringBuilder();
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
                    System.out.print(temp.getKey());
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
    
    public INode find(int key)
    {
        INode current = root;
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
                if (key < current.getKey()) {
                    current = current.getlChild();
                    if (current == null) {
                        parent.setlChild(insert);
                        return;
                    }
                }
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
    
    public INode max()
    {
        INode max     = null;
        INode current = root;
        while (current != null) {
            max     = current;
            current = current.getrChild();
        }
        return max;
    }
    
    public INode min()
    {
        INode min     = null;
        INode current = root;
        while (current != null) {
            min     = current;
            current = current.getlChild();
        }
        return min;
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
            inOrder(localRoot.getlChild());
            System.out.println(localRoot.getKey() + " ");
            inOrder(localRoot.getrChild());
        }
    }
    
    void postOrder(INode localRoot)
    {
        if (localRoot != null) {
            postOrder(localRoot.getlChild());
            postOrder(localRoot.getrChild());
            System.out.println(localRoot.getKey() + " ");
        }
    }
    
    void preOrder(INode localRoot)
    {
        if (localRoot != null) {
            System.out.println(localRoot.getKey() + " ");
            preOrder(localRoot.getlChild());
            preOrder(localRoot.getrChild());
        }
    }
    
    private INode getSuccessor(INode delNode)
    {
        INode parent = delNode, successor = delNode, curr = delNode.getrChild();
        while (curr != null) {
            parent    = successor;
            successor = curr;
            curr      = curr.getlChild();
        }
        if (successor != delNode.getrChild()) {
            parent.setlChild(successor.getrChild());
            successor.setrChild(delNode.getrChild());
        }
        return successor;
    }
    
    // DEMO
    private static class TreeDemo {
        
        public static void main(String[] args)
        {
            ITree tree = new ITree();
            tree.insert(4, 2.0);
            tree.insert(3, 2.0);
            tree.insert(6, 2.0);
            tree.insert(5, 2.0);
            tree.displayTree(16);
            tree.displayTree();
        }
        
    }
    // DEMO end
}
