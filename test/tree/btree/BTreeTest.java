package tree.btree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class BTreeTest {
    
    private static final long[] input = { 57, 83, 26, 45, 9, 72, 4 };
    private              BTree  bTree;
    
    @Before
    public void setUp() throws Exception
    {
        bTree = new BTree(4);
        for (long i : input) {
            bTree.insert(i);
        }
    }
    
    @Test
    public void getMin()
    {
        long min = Arrays.stream(input)
                         .min()
                         .orElse(-1);
        Assert.assertEquals(min, bTree.getMin());
    }
    
    @Test
    public void printInOrder()
    {
        String expected = "[/4//9//26//45//57//72//83/]";
        bTree.displayTree();
        Assert.assertEquals(expected, bTree.inOrder());
    }
    
}
