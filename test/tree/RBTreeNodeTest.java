package tree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;

public class RBTreeNodeTest {
    
    RBTreeNode node, left, right;

    @Before
    public void setUp()
    {
        left  = new RBTreeNode.Builder(1, 'B').red(true)
                                                        .build();
        right = new RBTreeNode.Builder(2, 'C').red(true)
                                                         .build();
        node = (RBTreeNode) new RBTreeNode.Builder(3, 'A').red(false)
                                                          .leftChild(left)
                                                          .rightChild(right)
                                                          .build();
    }

    @Test
    public void flipColors()
    {
        node.flipColors();
        assert !node.hasTwoRedChildren();
        assert !node.isBlack();
    }
    
    @Test
    public void hasRedChildren()
    {
        assert node.hasTwoRedChildren();
    }
    
    @Test
    public void rotateLeft()
    {
        RBTreeNode top = node.rotateLeft();
        assertSame("Right child has not rotated to top pos", top, right);
    }
    
    @Test
    public void rotateLeftNoRightChild()
    {
        node.setrChild(null);
        RBTreeNode result = node.rotateLeft();
        assertSame("top node has changed", result, node);
    }
    
    @Test
    public void rotateRight()
    {
        RBTreeNode top = node.rotateRight();
        assertSame("Left child has not rotated to top pos", top, left);
    }
    
    @Test
    public void rotateRightNoLeftChild()
    {
        node.setlChild(null);
        RBTreeNode result = node.rotateRight();
        assertSame("top node has changed", result, node);
    }

    @Test
    public void isBlack()
    {
        assert node.isBlack();
    }

}
