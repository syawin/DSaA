package tree.btree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DataItemTest {
    
    DataItem d;
    
    @Before
    public void setUp() throws Exception
    {
        d = new DataItem(5);
    }
    
    @Test
    public void testEquals()
    {
        DataItem d1 = new DataItem(5);
        assertEquals(d, d1);
    }
    
    @Test
    public void testNotEqualsOtherDataItem()
    {
        DataItem d1 = new DataItem(6);
        assertNotEquals(d, d1);
    }
    
}
