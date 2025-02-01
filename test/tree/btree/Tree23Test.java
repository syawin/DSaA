package tree.btree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Tree23Test {
    
    static long[] inputs = new long[] {
            60,
            80,
            40,
            50,
            70,
            90,
            45
    };
    MultiNode expected;
    Tree23    tree;
    
    @Before
    public void setUp()
    {
        tree = new Tree23();
    }
    
    @Test
    public void insertAndSplitRoot()
    {
        tree.insert(inputs[0]);
        tree.insert(inputs[1]);
        tree.insert(inputs[2]);
        tree.displayTree();
        expected = new MultiNode(3);
        expected.setDataArr(new DataItem[] {
                new DataItem(inputs[0]),
                null
        });
        expected.setItemCount(1);
        Assert.assertEquals(expected, tree.getRoot());
        
        expected = new MultiNode(3);
        expected.setDataArr(new DataItem[] {
                new DataItem(inputs[2]),
                null
        });
        expected.setItemCount(1);
        Assert.assertEquals(expected, tree.getRoot().getChild(0));
        
        expected = new MultiNode(3);
        expected.setDataArr(new DataItem[] {
                new DataItem(inputs[1]),
                null
        });
        expected.setItemCount(1);
        Assert.assertEquals(expected, tree.getRoot().getChild(1));
    }
    
    @Test
    public void insertAscendingOrder()
    {
        // Compute the sorted array from the given inputs.
        long[] sorted = Arrays.stream(inputs).sorted().toArray();
        
        // Create a new tree instance.
        tree = new Tree23();
        
        // Insert the keys in ascending order.
        for (long key : sorted) {
            tree.insert(key);
        }
        
        // Optionally display the tree structure (useful for debugging).
        tree.displayTree();
        
        // Retrieve the in-order traversal from the tree.
        List<DataItem> inOrderList = tree.inOrder();
        
        // Assert that the number of items returned matches the number of keys inserted.
        Assert.assertEquals("The inOrder list size should match the number of inserted keys.",
                            sorted.length,
                            inOrderList.size());
        
        // Check that each element in the inOrder list has the expected key.
        for (int i = 0; i < sorted.length; i++) {
            Assert.assertEquals("The key at index " + i + " should be " + sorted[i],
                                sorted[i],
                                inOrderList.get(i).key());
        }
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
        expected.setDataArr(new DataItem[] {
                new DataItem(inputs[0]),
                null
        });
        expected.setItemCount(1);
        Assert.assertEquals(expected, tree.getRoot());
        
        expected = new MultiNode(3);
        expected.setDataArr(new DataItem[] {
                new DataItem(inputs[2]),
                new DataItem(inputs[3])
        });
        expected.setItemCount(2);
        Assert.assertEquals(expected, tree.getRoot().getChild(0));
        
        expected = new MultiNode(3);
        expected.setDataArr(new DataItem[] {
                new DataItem(inputs[4]),
                new DataItem(inputs[1])
        });
        expected.setItemCount(2);
        Assert.assertEquals(expected, tree.getRoot().getChild(1));
    }
    
    @Test
    public void testInsertIntoEmptyTree()
    {
        tree.insert(inputs[0]);
        tree.displayTree();
        expected = new MultiNode(3);
        expected.setDataArr(new DataItem[] {
                new DataItem(inputs[0]),
                null
        });
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
        expected.setDataArr(new DataItem[] {
                new DataItem(inputs[0]),
                new DataItem(inputs[1])
        });
        expected.setItemCount(2);
        Assert.assertEquals(expected, tree.getRoot());
    }
    
    @Test
    public void testSplitChildOneNoSplitParent()
    {
        tree.insert(inputs[0]);
        tree.insert(inputs[1]);
        tree.insert(inputs[2]);
        tree.insert(inputs[3]);
        tree.insert(inputs[4]);
        tree.displayTree();
        tree.insert(inputs[5]);
        tree.displayTree();
        
        expected = new MultiNode(3);
        expected.setDataArr(new DataItem[] {
                new DataItem(inputs[0]),
                new DataItem(inputs[1])
        });
        expected.setItemCount(2);
        Assert.assertEquals(expected, tree.getRoot());
        
        expected = new MultiNode(3);
        expected.setDataArr(new DataItem[] {
                new DataItem(inputs[2]),
                new DataItem(inputs[3])
        });
        expected.setItemCount(2);
        Assert.assertEquals(expected, tree.getRoot().getChild(0));
        
        expected = new MultiNode(3);
        expected.setDataArr(new DataItem[] {
                new DataItem(inputs[4]),
                null
        });
        expected.setItemCount(1);
        Assert.assertEquals(expected, tree.getRoot().getChild(1));
        
        expected = new MultiNode(3);
        expected.setDataArr(new DataItem[] {
                new DataItem(inputs[5]),
                null
        });
        expected.setItemCount(1);
        Assert.assertEquals(expected, tree.getRoot().getChild(2));
    }
    
    // todo impl tests with different inputs to avoid myopic happy path data
    //      * Input in ascending order
    //      * Input in descending order
    @Test
    public void testSplitChildZeroNoSplitParent()
    {
        tree.insert(inputs[0]);
        tree.insert(inputs[1]);
        tree.insert(inputs[2]);
        tree.insert(inputs[3]);
        tree.insert(inputs[4]);
        tree.insert(inputs[6]);
        tree.displayTree();
        
        expected = new MultiNode(3);
        expected.setDataArr(new DataItem[] {
                new DataItem(inputs[6]),
                new DataItem(inputs[0])
        });
        expected.setItemCount(2);
        Assert.assertEquals(expected, tree.getRoot());
        
        expected = new MultiNode(3);
        expected.setDataArr(new DataItem[] {
                new DataItem(inputs[2]),
                null
        });
        expected.setItemCount(1);
        Assert.assertEquals(expected, tree.getRoot().getChild(0));
        
        expected = new MultiNode(3);
        expected.setDataArr(new DataItem[] {
                new DataItem(inputs[3]),
                null
        });
        expected.setItemCount(1);
        Assert.assertEquals(expected, tree.getRoot().getChild(1));
        
        expected = new MultiNode(3);
        expected.setDataArr(new DataItem[] {
                new DataItem(inputs[4]),
                new DataItem(inputs[1])
        });
        expected.setItemCount(2);
        Assert.assertEquals(expected, tree.getRoot().getChild(2));
    }
    
}
