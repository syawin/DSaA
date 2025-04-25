package tree.btree;

import common.DataItem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DataItemTest {
    
    common.DataItem d;
    
    @Before
    public void setUp() throws Exception
    {
        d = new common.DataItem(5);
    }
    
    @Test
    public void testEquals()
    {
        common.DataItem d1 = new common.DataItem(5);
        assertEquals(d, d1);
    }
    
    @Test
    public void testNotEqualsOtherDataItem()
    {
        common.DataItem d1 = new DataItem(6);
        assertNotEquals(d, d1);
    }
    
}
