package tree.btree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static common.CommonConstants.comma;
import static common.CommonConstants.formatStr;

public class BTreeTest {
    
    private static final long[] input       = { 57, 83, 26, 45, 9, 72, 4 };
    private static final long[] inputSorted = Arrays.stream(input)
                                                    .sorted()
                                                    .toArray();
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
    public void sort()
    {
        Assert.assertArrayEquals(inputSorted, BTree.sort(input));
    }
    
    @Test
    public void testInOrder()
    {
        String expected = "[/4//9//26//45//57//72//83/]";
        bTree.displayTree();
        String formatted = formatStr(bTree.inOrder()
                                          .toString(), comma);
        Assert.assertEquals(expected, formatted);
    }
    
}
