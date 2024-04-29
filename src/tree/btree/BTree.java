package tree.btree;

import java.util.ArrayList;
import java.util.List;

import static common.CommonConstants.comma;
import static common.CommonConstants.formatStr;

public class BTree {
    
    private final int       ORDER;
    private       MultiNode root;
    
    public BTree(int order)
    {
        ORDER = order;
        root  = new MultiNode(ORDER);
    }
    
    // getter
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

    public static long[] sort(long[] in)
    {
        BTree sorter = new BTree(4);
        for (long l : in) {
            sorter.insert(l);
        }
        return sorter.inOrder()
                     .stream()
                     .mapToLong(DataItem::key)
                     .toArray();
    }
    
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
    
    // todo this can be generalized to take into account ORDER
    public List<DataItem> inOrder()
    {
        List<DataItem> itemsInOrder = new ArrayList<>();
        // todo refactor this code into a loop
        if (root != null) {
            inOrderRec(root.getChild(0), itemsInOrder);
            if (root.getData(0) != null) itemsInOrder.add(root.getData(0));
            inOrderRec(root.getChild(1), itemsInOrder);
            if (root.getData(1) != null) itemsInOrder.add(root.getData(1));
            inOrderRec(root.getChild(2), itemsInOrder);
            if (root.getData(2) != null) itemsInOrder.add(root.getData(2));
            inOrderRec(root.getChild(3), itemsInOrder);
        }
        return itemsInOrder;
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
    
    public long minimum()
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
    
    // todo this can be generalized to take into account ORDER
    private void inOrderRec(MultiNode localRoot, List<DataItem> items)
    {
        // todo refactor this code into a loop
        if (localRoot != null) {
            inOrderRec(localRoot.getChild(0), items);
            if (localRoot.getData(0) != null) items.add(localRoot.getData(0));
            inOrderRec(localRoot.getChild(1), items);
            if (localRoot.getData(1) != null) items.add(localRoot.getData(1));
            inOrderRec(localRoot.getChild(2), items);
            if (localRoot.getData(2) != null) items.add(localRoot.getData(2));
            inOrderRec(localRoot.getChild(3), items);
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
    
    @SuppressWarnings("unused")
    private static class BTreeDemo {
        
        public static void main(String[] args)
        {
            BTree bTree = new BTree(4);
            String formatted = formatStr(bTree.inOrder()
                                              .toString(), comma);
            System.out.println(formatted);
            for (int i : new int[] { 57, 83, 26, 45, 9, 72, 38, 14, 66, 4 }) { bTree.insert(i); }
            bTree.displayTree();
            System.out.println(bTree.minimum());
            formatted = formatStr(bTree.inOrder()
                                       .toString(), comma);
            System.out.println(formatted);
        }
        
    }
    
}
