package tree;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RBTreeTest {
    
    @Test
    public void insertWhenRootIsBlackWithTwoRedChildren()
    {
        RBTree rbTree = new RBTree();
        rbTree.insert(50, 'A');
        RBTreeNode root;
        root = (( RBTreeNode ) rbTree.getRoot());
        assertTrue("root node is not black", root.isBlack());
        
        rbTree.insert(25, 'B');
        rbTree.insert(75, 'C');
        root = ( RBTreeNode ) rbTree.getRoot();
        assertTrue("root node is not black", root.isBlack());
        assertTrue("Left Child is not red", (( RBTreeNode ) root.getlChild()).isRed());
        assertTrue("Right Child is not red", (( RBTreeNode ) root.getrChild()).isRed());
        
        rbTree.insert(37, 'D');
        root = ( RBTreeNode ) rbTree.getRoot();
        assertTrue("Left child is not black after color flip", (( RBTreeNode ) root.getlChild()).isBlack());
        assertTrue("Right child is not black after color flip", (( RBTreeNode ) root.getrChild()).isBlack());
        assertTrue(
                "Newly inserted node is not red",
                (( RBTreeNode ) root.getlChild()
                                    .getrChild()).isRed()
                  );
    }
    
    @Test
    public void insertWhenRootIsNull()
    {
        RBTree rbTree = new RBTree();
        assert rbTree.getRoot() == null;
        rbTree.insert(1, 'A');
        assert rbTree.getRoot()
                     .getKey() == 1;
        assertTrue("root node is not black", (( RBTreeNode ) rbTree.getRoot()).isBlack());
    }
    
}
