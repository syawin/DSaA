package tree.btree;

public class BTree {
    
    private final int       ORDER;
    private       MultiNode root;
    
    public BTree(int order)
    {
        ORDER = order;
        root  = new MultiNode(ORDER);
    }
    
    // getter
    public long getMin()
    {
        if (root == null) {
            return -1;
        }
        MultiNode current = root;
        while (!current.isLeaf()) {
            current = current.getChild(0);
        }
        return current.getData(0)
                      .key();
    }
    
    public MultiNode getRoot()
    {
        return root;
    }
    // getter end
    
    // setter
    public void setRoot(MultiNode root)
    {
        this.root = root;
    }
    // setter end
    
    public void displayTree(int spaces)
    {
        // todo impl method to display BTree recursively
    }
    
    public void displayTree()
    {
        recDisplayTree(root, 0, 0);
    }
    
    @SuppressWarnings("ReassignedVariable")
    public int find(long key)
    {
        MultiNode current = root;
        int       childNumber;
        
        while (true) {
            if ((childNumber = current.findItem(key)) != -1) { return childNumber; }
            else if (current.isLeaf()) { return -1; }
            else { current = getNextChild(current, key); }
        }
    }
    
    private MultiNode getNextChild(MultiNode curr, long key)
    {
        int i;
        // todo assumes the node is not empty, not full, and not a leaf
        int count = curr.getItemCount();
        for (i = 0; i < count; i++) {
            if (key < curr.getData(i)
                          .key())
            { return curr.getChild(i); }
        }
        return curr.getChild(i);
    }
    
    public String inOrder()
    {
        StringBuilder sb = new StringBuilder("[");
        if (root != null) {
            inOrderRec(root.getChild(0), sb);
            if (root.getData(0) != null) sb.append(root.getData(0));
            inOrderRec(root.getChild(1), sb);
            if (root.getData(1) != null) sb.append(root.getData(1));
            inOrderRec(root.getChild(2), sb);
            if (root.getData(2) != null) sb.append(root.getData(2));
            inOrderRec(root.getChild(3), sb);
        }
        sb.append("]");
        return sb.toString();
    }
    
    public void insert(long key)
    {
        MultiNode curr = root;
        DataItem  temp = new DataItem(key);
        
        while (true) {
            if (curr.isFull()) {
                split(curr);
                curr = curr.getParent();
                curr = getNextChild(curr, key);
            }
            else if (curr.isLeaf()) { break; }
            else { curr = getNextChild(curr, key); }
        }
        curr.insertItem(temp);
    }
    
    private void split(MultiNode curr)
    {
        // assumes order is 4
        DataItem  itemB, itemC;
        MultiNode parent, child2, child3;
        int       itemIndex;
        
        itemC  = curr.removeItem();
        itemB  = curr.removeItem();
        child2 = curr.destroyTheChild(2);
        child3 = curr.destroyTheChild(3);
        MultiNode newRight = new MultiNode(ORDER);
        if (curr == root) {
            root   = new MultiNode(ORDER);
            parent = root;
            root.connectChild(0, curr);
        }
        else { parent = curr.getParent(); }
        itemIndex = parent.insertItem(itemB);
        int n = parent.getItemCount();
        
        for (int i = n - 1; i > itemIndex; i--) {
            MultiNode temp = parent.destroyTheChild(i);
            parent.connectChild(i + 1, temp);
        }
        parent.connectChild(itemIndex + 1, newRight);
        
        newRight.insertItem(itemC);
        newRight.connectChild(0, child2);
        newRight.connectChild(1, child3);
    }
    
    public String traverseInOrder()
    {
        StringBuilder sb      = new StringBuilder();
        MultiNode     current = root;
        // iterate thru leftmost child until leaf is reached
        while (!current.isLeaf()) {
            // get next child
            current = current.getChild(0);
        }
        sb.append(current.dataToString());
        return sb.toString();
    }
    
    private void inOrderRec(MultiNode localRoot, StringBuilder sb)
    {
        if (localRoot != null) {
            inOrderRec(localRoot.getChild(0), sb);
            if (localRoot.getData(0) != null) sb.append(localRoot.getData(0));
            inOrderRec(localRoot.getChild(1), sb);
            if (localRoot.getData(1) != null) sb.append(localRoot.getData(1));
            inOrderRec(localRoot.getChild(2), sb);
            if (localRoot.getData(2) != null) sb.append(localRoot.getData(2));
            inOrderRec(localRoot.getChild(3), sb);
        }
    }
    
    private void recDisplayTree(MultiNode curr, int level, int childIndex)
    {
        System.out.println(STR."""
                              level=\{level} child=\{childIndex}""");
        System.out.println(curr);
        int itemCount = curr.getItemCount();
        for (int i = 0; i < itemCount + 1; i++) {
            MultiNode next = curr.getChild(i);
            if (next != null) { recDisplayTree(next, level + 1, i); }
            else { return; }
        }
    }
    
    private static class BTreeDemo {
        
        public static void main(String[] args)
        {
            BTree bTree = new BTree(4);
            System.out.println(bTree.inOrder());
            for (int i : new int[] { 57, 83, 26, 45, 9, 72, 38, 14, 66, 4 }) { bTree.insert(i); }
            bTree.displayTree();
            System.out.println(bTree.getMin());
        }
        
    }
    
}
