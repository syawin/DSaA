package tree.btree;

import java.util.ArrayList;
import java.util.List;

import static common.CommonConstants.comma;
import static common.CommonConstants.formatStr;

// todo modify class to handle a 2-3 tree
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
        }
        return itemsInOrder;
    }
    
    public void insert(long key)
    {
        MultiNode parent, curr = root;
        DataItem  temp         = new DataItem(key);
        
        // first impl not recursive
        // split only happens when curr is full but not parent
        
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
        // assume order is 3
        DataItem  itemB = curr.removeItem();
        MultiNode par   = curr.getParent();
        if (par == null) {
            //     root case
            par  = new MultiNode(ORDER);
            root = par;
            root.connectChild(0, curr);
        }
        MultiNode newNode = new MultiNode(ORDER);
        newNode.insertItem(insert.compareTo(itemB) > 0 ? insert : itemB);
        par.insertItem(insert.compareTo(itemB) < 0 ? insert : itemB);
        MultiNode temp = par.destroyTheChild(1);
        if (temp != null) {
            par.connectChild(1, newNode.compareTo(temp) < 0 ? newNode : temp);
            par.connectChild(2, newNode.compareTo(temp) > 0 ? newNode : temp);
        }
        else {
            par.connectChild(1, newNode);
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
            tree23.insert(11);
            tree23.insert(33);
            tree23.insert(22);
            tree23.insert(44);
            tree23.insert(14);
            tree23.insert(35);
            tree23.insert(36);
            tree23.displayTree();
        }
        
    }
    
}
