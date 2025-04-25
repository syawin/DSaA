package tree.btree;

import common.DataItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static common.CommonConstants.comma;
import static common.CommonConstants.formatStr;

/**
 * 2-3 Tree class that has the same properties as a regular BTree but has a different method of
 * splitting.
 * <p>
 *
 * @see tree.btree.BTree
 */
@SuppressWarnings("DuplicatedCode")
public class Tree23 {
    
    // suggest make Tree23 an extension of Btree
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
    
    public List<common.DataItem> inOrder()
    {
        List<common.DataItem> itemsInOrder = new ArrayList<>();
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
        common.DataItem newItem = new common.DataItem(key);
        
        // 1. Empty‑tree case ─ create a root and stop.
        if (root == null) {
            root = new MultiNode(ORDER);
            root.insertItem(newItem);
            return;
        }
        
        // 2. Descend to the correct leaf.
        MultiNode leaf = findLeafNode(key);
        
        // 3. Insert (and split if necessary).
        insertIntoNode(leaf, newItem, null);
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
        return current.getData(0).key();
    }
    
    /**
     * Returns the leaf that should contain {@code key}.
     */
    private MultiNode findLeafNode(long key)
    {
        MultiNode curr = root;
        while (!curr.isLeaf()) {
            if (key < curr.getData(0).key()) {
                curr = curr.getChild(0);
            }
            else if (curr.getItemCount() == 1 || key < curr.getData(1).key()) {
                curr = curr.getChild(1);
            }
            else {
                curr = curr.getChild(2);
            }
        }
        return curr;
    }
    
    private MultiNode getNextChild(MultiNode curr, long key)
    {
        int i;
        // assumes the node is not empty, not full, and not a leaf
        int count = curr.getItemCount();
        for (i = 0; i < count; i++) {
            if (key < curr.getData(i).key()) { return curr.getChild(i); }
        }
        return curr.getChild(i);
    }
    
    private void inOrderRec(MultiNode localRoot, List<common.DataItem> items)
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
    
    /**
     * Insert {@code item} into {@code node}.
     * If {@code extraChild} is non‑null it is attached immediately to the right
     * of the position where {@code item} ends up.
     * Splits and recurses upward as required.
     */
    private void insertIntoNode(MultiNode node, common.DataItem item, MultiNode extraChild)
    {
        // Simple case – node has room.
        if (!node.isFull()) {
            int pos = node.insertItem(item);
            
            if (extraChild != null) {
                // shift children right to make room
                for (int i = node.getItemCount(); i > pos + 1; i--) {
                    node.connectChild(i, node.getChild(i - 1));
                }
                node.connectChild(pos + 1, extraChild);
            }
            return;
        }
        
        // Node is full – split first.
        splitNode(node, item, extraChild);
    }
    
    private void recDisplayTree(MultiNode curr, int level, int childIndex)
    {
        System.out.println("level=" + level + " child=" + childIndex);
        System.out.println(curr);
        int itemCount = curr.getItemCount();
        for (int i = 0; i < itemCount + 1; i++) {
            MultiNode next = curr.getChild(i);
            if (next != null) { recDisplayTree(next, level + 1, i); }
            else { return; }
        }
    }
    
    /**
     * Split a full {@code node} around {@code newItem}.
     * {@code extraChild} is the child that belongs immediately to the right of
     * {@code newItem} (null when the split originated in a leaf).
     */
    private void splitNode(MultiNode node, common.DataItem newItem, MultiNode extraChild)
    {
        /* ---- 1. gather and sort the three keys ---- */
        common.DataItem[] keys = {
                node.getData(0),
                node.getData(1),
                newItem
        };
        Arrays.sort(keys, Comparator.comparingLong(common.DataItem::key));
        
        common.DataItem leftKey     = keys[0];
        common.DataItem promotedKey = keys[1];
        DataItem        rightKey    = keys[2];
        
        /* ---- 2. build the right sibling ---- */
        MultiNode sibling = new MultiNode(ORDER);
        sibling.insertItem(rightKey);
        
        /* ---- 3. re‑initialise the current node ---- */
        Arrays.fill(node.getDataArr(), null);
        node.getDataArr()[0] = leftKey;
        node.setItemCount(1);
        
        /* ---- 4. re‑attach children (if internal) ---- */
        if (!node.isLeaf()) {
            if (extraChild != null) {
                if (extraChild.getData(0).key() < rightKey.key()) {
                    // new child belongs between current and sibling
                    sibling.connectChild(0, node.getChild(1));
                    sibling.connectChild(1, node.getChild(2));
                    
                    node.connectChild(1, extraChild);
                    node.connectChild(2, null);
                }
                else {
                    // extraChild is greater than rightKey, so it must become the right‑most child
                    sibling.connectChild(0, node.getChild(2));
                    sibling.connectChild(1, extraChild);
                    
                    node.connectChild(2, null);
                }
            }
            else { // split originated internally
                sibling.connectChild(0, node.getChild(2));
                node.connectChild(2, null);
            }
        }
        
        /* ---- 5. propagate the promoted key ---- */
        MultiNode parent = node.getParent();
        if (parent == null) { // root split
            root = new MultiNode(ORDER);
            root.insertItem(promotedKey);
            root.connectChild(0, node);
            root.connectChild(1, sibling);
        }
        else {
            insertIntoNode(parent, promotedKey, sibling);
        }
    }
    
    @SuppressWarnings("unused")
    private static class Tree23Demo {
        
        public static void main(String[] args)
        {
            Tree23 tree23    = new Tree23();
            String formatted = formatStr(tree23.inOrder().toString(), comma);
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
