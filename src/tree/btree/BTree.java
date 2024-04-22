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
        while (current != null) {
            if ((childNumber = current.findItem(key)) != -1) { return childNumber; }
            else if (current.isLeaf()) { return -1; }
            else { current = getNextChild(current, key); }
        }
        return -1;
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
        return null;
    }
    
    public void insert(long key)
    {
        MultiNode curr = root;
        DataItem  temp = new DataItem(key);
        
        while (curr != null) {
            if (curr.isFull()) {
                split(curr);
                curr = curr.getParent();
                curr = getNextChild(curr, key);
            }
            else if (curr.isLeaf()) { break; }
            else { curr = getNextChild(curr, key); }
        }
        if (curr != null) {
            curr.insertItem(temp);
        }
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
        
        for (int i = n - 1; i < itemIndex; i--) {
            MultiNode temp = parent.destroyTheChild(i);
            parent.connectChild(i + 1, temp);
        }
        parent.connectChild(itemIndex + 1, newRight);
        
        newRight.insertItem(itemC);
        newRight.connectChild(0, child2);
        newRight.connectChild(1, child3);
    }
    
    private void recDisplayTree(MultiNode curr, int level, int childIndex)
    {
        System.out.println(STR."""
                              level=\{level} child=\{childIndex}""");
        System.out.println(curr);
        int itemCount = curr.getItemCount();
        for (int i = 0; i < itemCount; i++) {
            MultiNode next = curr.getChild(i);
            if (next != null) { recDisplayTree(next, level + 1, i); }
            else { return; }
        }
    }
    
    private static class BTreeDemo {
        
        public static void main(String[] args)
        {
            BTree bTree = new BTree(4);
            bTree.insert(57);
            bTree.insert(83);
            bTree.insert(26);
            bTree.insert(45);
            bTree.insert(9);
            bTree.insert(72);
            bTree.insert(38);
            bTree.insert(14);
            bTree.insert(66);
            bTree.insert(4);
            bTree.displayTree();
            System.out.println(bTree.getMin());
        }
        
    }
    
}
