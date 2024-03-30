package tree.btree;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MultiNodeTest {
    
    private static final int             ORDER = 4;
    private              MultiNode<Long> multiNode;
    
    @Before
    public void setUp()
    {
        multiNode = new MultiNode<>(ORDER);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void connectChildIndexLessThanZero()
    {
        multiNode.connectChild(-1, null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConnectChildGreaterThanOrEqualToOrder()
    {
        multiNode.connectChild(ORDER, null);
    }
    
    @Test
    public void testToString()
    {
        assertEquals("toString on empty Multinode should be '[]'", "[]", multiNode.toString());
        List<DataItem<Long>> itemArray = List.of(new NumericDataItem<>(1L), new NumericDataItem<>(2L));
        multiNode.setItemArray(itemArray);
        assertEquals("toString on non-empty Multinode should be '[/1//2/]'", "[/1//2/]", multiNode.toString());
    }
    
}
