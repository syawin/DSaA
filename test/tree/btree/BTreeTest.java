package tree.btree;

import org.junit.Assert;
import org.junit.Test;

public class BTreeTest {
    
    @Test
    public void getMin()
    {
        BTree bTree = new BTree(4);
        bTree.insert(57);
        bTree.insert(83);
        bTree.insert(26);
        bTree.insert(45);
        bTree.insert(9);
        bTree.insert(72);
        bTree.insert(4);
        Assert.assertEquals(4, bTree.getMin());
    }
    
    @Test
    public void printInOrder()
    {
        BTree bTree = new BTree(4);
        bTree.insert(57);
        bTree.insert(83);
        bTree.insert(26);
        bTree.insert(45);
        bTree.insert(9);
        bTree.insert(72);
        bTree.insert(4);
        String expected = "[/4//9//26/45//57//72//83/]";
        Assert.assertEquals(expected, bTree.traverseInOrder());
    }
    
}
