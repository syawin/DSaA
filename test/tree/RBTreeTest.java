package tree;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RBTreeTest {
    
    @Test
    public void insertRRViolationWithRedUncle()
    {
        RBTree rbTree = new RBTree();
        rbTree.insert(4, 'A');
        rbTree.insert(2, 'B');
        rbTree.insert(6, 'C');
        rbTree.insert(1, 'D');
        rbTree.insert(3, 'E');
        rbTree.insert(5, 'F');
        rbTree.insert(7, 'G');
        rbTree.displayTree();
        assertTrue("Tree is not RB correct", rbTree.validateRBCorrect());
        rbTree.insert(8, 'G');
        rbTree.displayTree();
        assertTrue("Tree is not RB correct", rbTree.validateRBCorrect());
    }
    
    @Test
    public void insertRRViolationWithoutUncle()
    {
        RBTree rbTree = new RBTree();
        rbTree.insert(10, 'A');
        rbTree.insert(5, 'B');
        rbTree.insert(20, 'C');
        rbTree.insert(15, 'D');
        rbTree.insert(25, 'E');
        rbTree.insert(30, 'F');
        rbTree.displayTree();
        assertTrue("Tree is not RB correct", rbTree.validateRBCorrect());
    }
    
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
        assertTrue("Tree is not RB correct", rbTree.validateRBCorrect());
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
        assertTrue("Tree is not RB correct", rbTree.validateRBCorrect());
    }
    
    @Test
    public void insertWithMultipleViolations()
    {
        RBTree rbTree = new RBTree();
        rbTree.insert(50, 'A');
        rbTree.insert(25, 'B');
        rbTree.insert(75, 'C');
        rbTree.insert(15, 'D');
        rbTree.insert(30, 'E');
        rbTree.insert(60, 'F');
        rbTree.insert(85, 'G');
        rbTree.insert(55, 'H');
        rbTree.displayTree();
        assertTrue("Tree is not RB correct", rbTree.validateRBCorrect());
        rbTree.insert(65, 'I');
        rbTree.displayTree();
        assertTrue("Tree is not RB correct", rbTree.validateRBCorrect());
    }
    
    @Test
    public void insertWithoutViolations()
    {
        RBTree rbTree = new RBTree();
        rbTree.insert(1, 'A');
        rbTree.insert(2, 'B');
        rbTree.insert(3, 'C');
        assertTrue("Tree is not RB correct", rbTree.validateRBCorrect());
    }
    
    @Test
    public void testColorFlipsNotRoot()
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
        rbTree.insert(22, 'E');
        rbTree.displayTree();
        assertTrue("Tree is not RB correct", rbTree.validateRBCorrect());
        rbTree.insert(21, 'F');
        rbTree.displayTree();
        assertTrue("Tree is not RB correct", rbTree.validateRBCorrect());
    }
    
    @Test
    public void testRotateAfterInsertSmall()
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
        
        rbTree.insert(22, 'D');
        rbTree.displayTree();
        rbTree.insert(20, 'E');
        rbTree.displayTree();
        
        rbTree.insert(76, 'F');
        rbTree.displayTree();
        rbTree.insert(77, 'G');
        rbTree.displayTree();
        assertTrue("Tree is not RB correct", rbTree.validateRBCorrect());
        rbTree.insert(70, 'D');
        rbTree.displayTree();
        assertTrue("Tree is not RB correct", rbTree.validateRBCorrect());
    }
    
    @Test
    public void testRotateLeftInnerGrandChild()
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
        
        rbTree.insert(12, 'E');
        rbTree.displayTree();
        assertTrue("Tree is not RB correct", rbTree.validateRBCorrect());
        rbTree.insert(18, 'D');
        rbTree.displayTree();
        assertTrue("Tree is not RB correct", rbTree.validateRBCorrect());
    }
    
    @Test
    public void testRotateOnWayDownLeftInnerGrandChild()
    {
        RBTree rbTree = new RBTree();
        rbTree.insert(50, 'A');
        rbTree.insert(25, 'C');
        rbTree.insert(75, 'B');
        rbTree.insert(85, 'E');
        rbTree.insert(65, 'D');
        rbTree.insert(60, 'F');
        rbTree.insert(55, 'G');
        rbTree.displayTree();
        assertTrue("Tree is not RB correct", rbTree.validateRBCorrect());
        rbTree.insert(59, 'H');
        rbTree.displayTree();
        assertTrue("Tree is not RB correct", rbTree.validateRBCorrect());
    }
    
    @Test
    public void testRotateOnWayDownLeftOuterGrandChild()
    {
        RBTree rbTree = new RBTree();
        rbTree.insert(50, 'A');
        rbTree.insert(75, 'B');
        rbTree.insert(25, 'C');
        rbTree.insert(37, 'D');
        rbTree.insert(12, 'E');
        rbTree.insert(6, 'F');
        rbTree.insert(18, 'G');
        rbTree.displayTree();
        assertTrue("Tree is not RB correct", rbTree.validateRBCorrect());
        rbTree.insert(3, 'H');
        rbTree.displayTree();
        assertTrue("Tree is not RB correct", rbTree.validateRBCorrect());
    }
    
    @Test
    public void testRotateOnWayDownRightInnerGrandChild()
    {
        RBTree rbTree = new RBTree();
        rbTree.insert(50, 'A');
        rbTree.insert(25, 'C');
        rbTree.insert(75, 'B');
        rbTree.insert(12, 'E');
        rbTree.insert(37, 'D');
        rbTree.insert(31, 'F');
        rbTree.insert(43, 'G');
        rbTree.displayTree();
        assertTrue("Tree is not RB correct", rbTree.validateRBCorrect());
        rbTree.insert(30, 'H');
        rbTree.displayTree();
        assertTrue("Tree is not RB correct", rbTree.validateRBCorrect());
    }
    
    @Test
    public void testRotateOnWayDownRightOuterGrandChild()
    {
        RBTree rbTree = new RBTree();
        rbTree.insert(50, 'A');
        rbTree.insert(75, 'B');
        rbTree.insert(25, 'C');
        rbTree.insert(60, 'D');
        rbTree.insert(90, 'E');
        rbTree.insert(80, 'F');
        rbTree.insert(95, 'G');
        rbTree.displayTree();
        assertTrue("Tree is not RB correct", rbTree.validateRBCorrect());
        rbTree.insert(99, 'H');
        rbTree.displayTree();
        assertTrue("Tree is not RB correct", rbTree.validateRBCorrect());
    }
    
    @Test
    public void testRotateRightInnerGrandChild()
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
        
        rbTree.insert(80, 'E');
        rbTree.displayTree();
        assertTrue("Tree is not RB correct", rbTree.validateRBCorrect());
        rbTree.insert(76, 'D');
        rbTree.displayTree();
        assertTrue("Tree is not RB correct", rbTree.validateRBCorrect());
    }
}
