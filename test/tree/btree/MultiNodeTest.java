package tree.btree;

import org.junit.Before;
import org.junit.Test;

public class MultiNodeTest {
    
    MultiNode node;
    
    @Before
    public void setUp() throws Exception
    {
        node = new MultiNode(4);
    }
    
    @Test
    public void testToStringEmpty()
    {
        System.out.print(node);
    }
    
    @Test
    public void testToStringFull()
    {
        DataItem dataItem  = new DataItem(1);
        DataItem dataItem2 = new DataItem(2);
        DataItem dataItem3 = new DataItem(3);
        node.insertItem(dataItem);
        node.insertItem(dataItem2);
        node.insertItem(dataItem3);
        System.out.print(node);
    }
    
    @Test
    public void testToStringPartialData()
    {
        DataItem dataItem  = new DataItem(1);
        DataItem dataItem2 = new DataItem(2);
        node.insertItem(dataItem);
        node.insertItem(dataItem2);
        System.out.print(node);
    }
    
}
