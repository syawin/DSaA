package tree.btree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class Tree23Test {
    
    static long[] inputs = new long[] { 60, 80, 40, 50, 70, 90, 45 };
    MultiNode expected;
    Tree23    tree;
    
    @Before
    public void setUp()
    {
        tree = new Tree23();
    }
    
    @Ignore
    @Test
    public void insertAndSplitRoot()
    {
        tree.insert(inputs[0]);
        tree.insert(inputs[1]);
        tree.insert(inputs[2]);
        tree.displayTree();
        expected = new MultiNode(3);
        expected.setDataArr(new DataItem[] { new DataItem(inputs[0]), null });
        expected.setItemCount(1);
        Assert.assertEquals(expected, tree.getRoot());
        
        expected = new MultiNode(3);
        expected.setDataArr(new DataItem[] { new DataItem(inputs[2]), null });
        expected.setItemCount(1);
        Assert.assertEquals(
                expected,
                tree.getRoot()
                    .getChild(0)
                           );
        
        expected = new MultiNode(3);
        expected.setDataArr(new DataItem[] { new DataItem(inputs[1]), null });
        expected.setItemCount(1);
        Assert.assertEquals(
                expected,
                tree.getRoot()
                    .getChild(1)
                           );
    }
    
    @Test
    public void insertChildNodeNoSplits()
    {
        tree.insert(inputs[0]);
        tree.insert(inputs[1]);
        tree.insert(inputs[2]);
        tree.insert(inputs[3]);
        tree.insert(inputs[4]);
        tree.displayTree();
        
        expected = new MultiNode(3);
        expected.setDataArr(new DataItem[] { new DataItem(inputs[0]), null });
        expected.setItemCount(1);
        Assert.assertEquals(expected, tree.getRoot());
        
        expected = new MultiNode(3);
        expected.setDataArr(new DataItem[] { new DataItem(inputs[2]), new DataItem(inputs[3]) });
        expected.setItemCount(2);
        Assert.assertEquals(
                expected,
                tree.getRoot()
                    .getChild(0)
                           );
        
        expected = new MultiNode(3);
        expected.setDataArr(new DataItem[] { new DataItem(inputs[4]), new DataItem(inputs[1]) });
        expected.setItemCount(2);
        Assert.assertEquals(
                expected,
                tree.getRoot()
                    .getChild(1)
                           );
    }
    
    @Test
    public void testInsertIntoEmptyTree()
    {
        tree.insert(inputs[0]);
        tree.displayTree();
        expected = new MultiNode(3);
        expected.setDataArr(new DataItem[] { new DataItem(inputs[0]), null });
        expected.setItemCount(1);
        Assert.assertEquals(expected, tree.getRoot());
    }
    
    @Test
    public void testInsertIntoExistingRoot()
    {
        tree.insert(inputs[0]);
        tree.insert(inputs[1]);
        tree.displayTree();
        expected = new MultiNode(3);
        expected.setDataArr(new DataItem[] { new DataItem(inputs[0]), new DataItem(inputs[1]) });
        expected.setItemCount(2);
        Assert.assertEquals(expected, tree.getRoot());
    }
    
    @Test
    public void testSplitChildNoSplitParent()
    {
        tree.insert(inputs[0]);
        tree.insert(inputs[1]);
        tree.insert(inputs[2]);
        tree.insert(inputs[3]);
        tree.insert(inputs[4]);
        tree.insert(inputs[5]);
        tree.displayTree();
        
        expected = new MultiNode(3);
        expected.setDataArr(new DataItem[] { new DataItem(inputs[0]), new DataItem(inputs[1]) });
        expected.setItemCount(2);
        Assert.assertEquals(expected, tree.getRoot());
        
        expected = new MultiNode(3);
        expected.setDataArr(new DataItem[] { new DataItem(inputs[2]), new DataItem(inputs[3]) });
        expected.setItemCount(2);
        Assert.assertEquals(
                expected,
                tree.getRoot()
                    .getChild(0)
                           );
        
        expected = new MultiNode(3);
        expected.setDataArr(new DataItem[] { new DataItem(inputs[4]), null });
        expected.setItemCount(1);
        Assert.assertEquals(
                expected,
                tree.getRoot()
                    .getChild(1)
                           );
        
        expected = new MultiNode(3);
        expected.setDataArr(new DataItem[] { new DataItem(inputs[5]), null });
        expected.setItemCount(1);
        Assert.assertEquals(
                expected,
                tree.getRoot()
                    .getChild(2)
                           );
    }
    
}
