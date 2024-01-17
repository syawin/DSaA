package tree;

import stack.StackGeneric;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("DuplicatedCode")
public class Tree {

    private TreeNode root;

    public Tree()
    {
    }

    public Tree(TreeNode root)
    {
        this.root = root;
    }

    public static Tree createBalancedStringTree(String message)
    {
        NodeLL forest = new NodeLL();

        for (char c : message.toCharArray()) {
            TreeNode root = new TreeNode.Builder(-1, c).build();
            Tree tree = new Tree(root);
            Node wrapper = new Node.Builder(-1, tree).build();
            forest.insertFirst(wrapper);
        }
        while (forest.length() > 1) {
            Node a = forest.removeFirst();
            Node b = forest.removeFirst();
            Node c = new Node.Builder(-1, mergeTrees((Tree) a.getVal(), (Tree) b.getVal())).build();
            forest.insertLast(c);
        }
        return (Tree) forest.getFirst()
                            .getVal();
    }

    private static Tree mergeTrees(Tree aTree, Tree bTree)
    {
        TreeNode newRoot;
        if (aTree.root == null) {
            newRoot = bTree.root;
        }
        else {
            newRoot = new TreeNode.Builder(-1, '+')
                              .leftChild(aTree.root)
                              .rightChild(bTree.root)
                              .build();
        }
        return new Tree(newRoot);
    }

    public static Tree createStringTree(String message)
    {
        Tree master = new Tree();
        List<Tree> forest = new ArrayList<>();
        for (char c : message.toCharArray()) {
            TreeNode node = new TreeNode.Builder(-1, c).build();
            Tree tree = new Tree(node);
            forest.add(tree);
        }
        for (Tree tree : forest) {
            master = mergeTrees(master, tree);
        }
        return master;
    }

    public boolean delete(int key)
    {
        TreeNode parent, curr;
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
            TreeNode successor = getSuccessor(curr);
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

    public TreeNode find(int key)
    {
        TreeNode current = root;
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

    public void displayTree()
    {
        displayTree(32);
    }

    public void displayTree(int spaces)
    {
        StackGeneric<TreeNode> globalStack = new StackGeneric<>();
        globalStack.push(root);
        int nBlanks = spaces;
        boolean isRowEmpty = false;
        StringBuilder lineBreak = new StringBuilder();
        lineBreak.append("•".repeat(nBlanks * 2));
        System.out.println(lineBreak);

        while (!isRowEmpty) {
            StackGeneric<TreeNode> localStack = new StackGeneric<>();
            isRowEmpty = true;
            for (int i = 0; i < nBlanks; i++) {
                System.out.print(' ');
            }

            while (!globalStack.isEmpty()) {
                TreeNode temp = globalStack.pop();
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

    public void insert(int key, Object val)
    {
        TreeNode insert = new TreeNode.Builder(key, val).build();
        if (root == null) {
            root = insert;
        }
        else {
            TreeNode current = root;
            TreeNode parent;
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

    public TreeNode min()
    {
        TreeNode min = null;
        TreeNode current = root;
        while (current != null) {
            min     = current;
            current = current.getlChild();
        }
        return min;
    }

    void preOrder(TreeNode localRoot)
    {
        if (localRoot != null) {
            System.out.println(localRoot.getKey() + " ");
            preOrder(localRoot.getlChild());
            preOrder(localRoot.getrChild());
        }
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

    void inOrder(TreeNode localRoot)
    {
        if (localRoot != null) {
            inOrder(localRoot.getlChild());
            System.out.println(localRoot.getKey() + " ");
            inOrder(localRoot.getrChild());
        }
    }

    void postOrder(TreeNode localRoot)
    {
        if (localRoot != null) {
            postOrder(localRoot.getlChild());
            postOrder(localRoot.getrChild());
            System.out.println(localRoot.getKey() + " ");
        }
    }

    private TreeNode getSuccessor(TreeNode delNode)
    {
        TreeNode paren, successor, curr;
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
            Tree test = createBalancedStringTree("ABCDEFGHIJKL");
            test.displayTree();
        }

    }

}
