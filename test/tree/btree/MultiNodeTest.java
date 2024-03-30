package tree.btree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MultiNodeTest {
    
    @Test
    public void testToString()
    {
        MultiNode<Long> multiNode = new MultiNode<>(4);
        assertEquals("toString on empty Multinode should be '[]'", "[]", multiNode.toString());
        DataItem<Long>[] itemArray = new DataItem[] {new NumericDataItem<>(1L), new NumericDataItem<>(2L)};
        multiNode.setItemArray(itemArray);
        assertEquals("toString on non-empty Multinode should be '[/1//2/]'", "[/1//2/]", multiNode.toString());
    }
    
}
