package tree.btree;

import common.DataItem;
import org.junit.Assert;
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
    public void testEquals()
    {
        MultiNode node2 = new MultiNode(4);
        node.insertItem(new common.DataItem(5));
        node2.insertItem(new common.DataItem(5));
        Assert.assertEquals(node, node2);
    }
    
    @Test
    public void testEqualsWhenEmpty()
    {
        MultiNode node2 = new MultiNode(4);
        Assert.assertEquals(node, node2);
    }
    
    @Test
    public void testNotEqualsDiffData()
    {
        MultiNode node2 = new MultiNode(4);
        node.insertItem(new common.DataItem(5));
        node2.insertItem(new common.DataItem(4));
        Assert.assertNotEquals(node, node2);
    }
    
    @Test
    public void testNotEqualsDiffItemCount()
    {
        MultiNode node2 = new MultiNode(4);
        node.insertItem(new common.DataItem(5));
        Assert.assertNotEquals(node, node2);
    }
    
    @Test
    public void testNotEqualsDifferentOrder()
    {
        MultiNode node2 = new MultiNode(3);
        Assert.assertNotEquals(node, node2);
    }
    
    @Test
    public void testToStringEmpty()
    {
        System.out.print(node);
    }
    
    @Test
    public void testToStringFull()
    {
        common.DataItem dataItem  = new common.DataItem(1);
        common.DataItem dataItem2 = new common.DataItem(2);
        common.DataItem dataItem3 = new common.DataItem(3);
        node.insertItem(dataItem);
        node.insertItem(dataItem2);
        node.insertItem(dataItem3);
        System.out.print(node);
    }
    
    @Test
    public void testToStringPartialData()
    {
        common.DataItem dataItem  = new common.DataItem(1);
        common.DataItem dataItem2 = new DataItem(2);
        node.insertItem(dataItem);
        node.insertItem(dataItem2);
        System.out.print(node);
    }
    
}
