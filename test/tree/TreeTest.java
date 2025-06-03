package tree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TreeTest {

    @Test
    public void testFind()
    {
        // Create a tree with some nodes
        Tree tree = new Tree();
        tree.insert(4, 'a');
        tree.insert(3, "abc");
        tree.insert(6, 1);
        tree.insert(5, 2.0);

        // Test finding existing keys
        TreeNode node1 = tree.find(4);
        assertNotNull(node1);
        assertEquals('a', node1.getValue());

        TreeNode node2 = tree.find(3);
        assertNotNull(node2);
        assertEquals("abc", node2.getValue());

        TreeNode node3 = tree.find(6);
        assertNotNull(node3);
        assertEquals(1, node3.getValue());

        TreeNode node4 = tree.find(5);
        assertNotNull(node4);
        assertEquals(2.0, node4.getValue());

        // Test finding non-existing key
        TreeNode node5 = tree.find(2);
        assertNull(node5);
    }

}
