package tree;

import org.junit.Before;
import org.junit.Test;

public class RBTreeNodeTest {

    RBTreeNode node;

    @Before
    public void setUp() throws Exception
    {
        RBTreeNode left = new RBTreeNode.Builder(1, 'B').red(true)
                                                        .build();
        RBTreeNode right = new RBTreeNode.Builder(2, 'C').red(true)
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
    public void isBlack()
    {
        assert node.isBlack();
    }

}
