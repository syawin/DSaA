package tree.btree;

import java.util.ArrayList;
import java.util.List;

import static common.CommonConstants.comma;
import static common.CommonConstants.formatStr;

@SuppressWarnings("DuplicatedCode")
public class Tree23 {
    
    private final static int       ORDER = 3;
    private              MultiNode root  = new MultiNode(ORDER);
    
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
        // assumes the node is not empty, not full, and not a leaf
        int count = curr.getItemCount();
        for (i = 0; i < count; i++) {
            if (key < curr.getData(i)
                          .key())
            { return curr.getChild(i); }
        }
        return curr.getChild(i);
    }
    
    public List<DataItem> inOrder()
    {
        List<DataItem> itemsInOrder = new ArrayList<>();
        // suggest refactor this code into a loop
        if (root != null) {
            inOrderRec(root.getChild(0), itemsInOrder);
            if (root.getData(0) != null) itemsInOrder.add(root.getData(0));
            inOrderRec(root.getChild(1), itemsInOrder);
            if (root.getData(1) != null) itemsInOrder.add(root.getData(1));
            inOrderRec(root.getChild(2), itemsInOrder);
        }
        return itemsInOrder;
    }
    
    public void insert(long key)
    {
        MultiNode par, curr = root;
        DataItem  temp      = new DataItem(key);
        
        while (true) {
            if (curr.isLeaf()) { break; }
            else { curr = getNextChild(curr, key); }
        }
        if (curr.isFull()) {
            split(curr, temp);
        }
        else {
            curr.insertItem(temp);
        }
    }
    
    private void split(MultiNode curr, DataItem insert)
    {
        // base case
        if (curr.getParent() == null) {
            DataItem  itemB   = curr.removeItem();
            MultiNode insNode = new MultiNode(ORDER);
            insNode.insertItem(insert);
            MultiNode newSibling = new MultiNode(ORDER);
            newSibling.insertItem(itemB);
            if (insNode.compareTo(curr) < 0) {
                root = curr;
                root.connectChild(0, insNode);
                root.connectChild(1, newSibling);
            }
            else if (insNode.compareTo(newSibling) > 0) {
                root = newSibling;
                root.connectChild(0, curr);
                root.connectChild(1, insNode);
            }
            else {
                root = insNode;
                root.connectChild(0, curr);
                root.connectChild(1, newSibling);
            }
        }
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
    
    private void inOrderRec(MultiNode localRoot, List<DataItem> items)
    {
        // suggest refactor this code into a loop
        if (localRoot != null) {
            inOrderRec(localRoot.getChild(0), items);
            if (localRoot.getData(0) != null) items.add(localRoot.getData(0));
            inOrderRec(localRoot.getChild(1), items);
            if (localRoot.getData(1) != null) items.add(localRoot.getData(1));
            inOrderRec(localRoot.getChild(2), items);
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
    
    @SuppressWarnings("unused")
    private static class Tree23Demo {
        
        public static void main(String[] args)
        {
            Tree23 tree23 = new Tree23();
            String formatted = formatStr(tree23.inOrder()
                                               .toString(), comma);
            System.out.println(formatted);
            tree23.insert(60);
            tree23.insert(80);
            tree23.insert(40);
            tree23.insert(50);
            tree23.insert(70);
            tree23.insert(90);
            tree23.insert(45);
            tree23.displayTree();
        }
        
    }
    
}
