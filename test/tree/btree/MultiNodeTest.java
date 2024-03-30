package tree.btree;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MultiNodeTest {
    
    @Test
    public void testToString()
    {
        MultiNode<Number> multiNode = new MultiNode<>(4);
        assertEquals("toString on empty Multinode should be '[]'", "[]", multiNode.toString());
        List<DataItem<Number>> itemArray = List.of(new NumericDataItem<>(1L), new NumericDataItem<>(2L));
        multiNode.setItemArray(itemArray);
        assertEquals("toString on non-empty Multinode should be '[/1//2/]'", "[/1//2/]", multiNode.toString());
    }
    
}
