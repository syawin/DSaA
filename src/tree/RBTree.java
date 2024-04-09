package tree;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

import static common.CommonConstants.LINE_BREAK;

public class RBTree extends Tree {
    
    private static final Predicate<RBTreeNode>               isFlip        = n -> n.isBlack() && n.hasTwoRedChildren();
    private static final BiPredicate<RBTreeNode, RBTreeNode> isRRViolation = (x, p) -> x.isRed() && p.isRed();
    
    public RBTree()
    {
    }
    
    public RBTree(TreeNode root)
    {
        super(root);
    }
    
    // todo implement delete method
    @Override
    public boolean delete(int key)
    {
        return false;
    }
    
    @Override
    public void insert(int key, Object val)
    {
        RBTreeNode insert      = new RBTreeNode.Builder(key, val).build();
        Boolean    isLeftChild = null, isLeftGrandChild = null;
        System.out.println("Ins:\t" + insert);
        if (this.getRoot() == null) {
            insert.setRed(false);
            this.setRoot(insert);
        }
        else {
            RBTreeNode curr = ( RBTreeNode ) this.getRoot();
            RBTreeNode paren = null, gp = null;
            if (isFlip.test(curr)) {
                curr.flipColors();
                curr.setRed(false);
            }
            while (true) {
                gp = paren;
                paren = curr;
                // begin seek subroutine
                if (key < curr.getKey()) {
                    curr = ( RBTreeNode ) curr.getlChild();
                    // paren is P, paren.L is X1 & paren.R is X2. this will only happen when P has 2 children
                    if (isFlip.test(paren)) paren.flipColors();
                    if (gp != null && isRRViolation.test(paren, gp)) {
                        logRedRedViolation(paren, gp);
                        rotateOnWayDown(paren, gp, isLeftGrandChild, isLeftChild);
                    }
                    isLeftGrandChild = isLeftChild;
                    isLeftChild      = true;
                    if (curr == null) {
                        paren.setlChild(insert);
                        insert.setParent(paren);
                        curr = ( RBTreeNode ) paren.getlChild();
                        break;
                    }
                }
                else {
                    curr = ( RBTreeNode ) curr.getrChild();
                    if (isFlip.test(paren)) paren.flipColors();
                    if (gp != null && isRRViolation.test(paren, gp)) {
                        logRedRedViolation(paren, gp);
                        rotateOnWayDown(paren, gp, isLeftGrandChild, isLeftChild);
                    }
                    isLeftGrandChild = isLeftChild;
                    isLeftChild      = false;
                    if (curr == null) {
                        paren.setrChild(insert);
                        insert.setParent(paren);
                        curr = ( RBTreeNode ) paren.getrChild();
                        break;
                    }
                }
                // end seek
            }
            
            // after node insert, curr can be considered  X, p is Parent, g is GP
            System.out.println("X:\t " + curr);
            System.out.println("P:\t " + paren);
            System.out.println("G:\t " + gp);
            // at this point g, p, and x should all be set to the vals we need to properly do the final rotation
            // test for 3 cases:
            //     if P is black, nothing to do
            if (gp != null && isRRViolation.test(curr, paren)) {
                logRedRedViolation(curr, paren);
                rotateAfterInsert(paren, isLeftGrandChild, isLeftChild, gp, insert);
            }
        }
        this.displayTree();
        System.out.println(LINE_BREAK);
    }
    
    private void logRedRedViolation(RBTreeNode curr, RBTreeNode paren)
    {
        this.displayTree();
        System.out.println("R-R violation between " + curr.getVal() + " and " + paren.getVal());
    }
    
    private void rotateOnWayDown(RBTreeNode paren, RBTreeNode gp, Boolean isLeftGrandChild, Boolean isLeftChild)
    {
        if (isLeftGrandChild != null) {
            // begin case outer gc
            RBTreeNode ggp       = ( RBTreeNode ) gp.getParent();
            TreeNode   top, temp = ggp.getParent();
            if ((isLeftChild && isLeftGrandChild) || (!isLeftChild && !isLeftGrandChild)) {
                gp.toggleNodeColor();
                ggp.toggleNodeColor();
                if (isLeftChild) {
                    // do right rotation to raise P
                    top = ggp.rotateRight();
                }
                else {
                    top = ggp.rotateLeft();
                }
                // GGP is top of rotation
                // after rotation the node returned by the method must either be reattached to
                // its parent or the root node. can determine if root if ggp.paren == null
                // if ggp paren is null, update root
            }
            // end case outer gc
            // begin case inner gc
            else {
                paren.toggleNodeColor();
                ggp.toggleNodeColor();
                // rotate GP in the direction that raises P
                // then rotate ggp in the direction that raises P
                if (isLeftChild) {
                    // then isLeftGC must be false
                    // rotate GP right
                    // rotate GGP left
                    ggp.setrChild(gp.rotateRight());
                    ggp.getrChild()
                       .setParent(ggp);
                    top = ggp.rotateLeft();
                }
                else {
                    // then isLeftGC must be true
                    // rotate GP left
                    // rotate GGP right
                    ggp.setlChild(gp.rotateLeft());
                    ggp.getlChild()
                       .setParent(ggp);
                    top = ggp.rotateRight();
                }
            }
            // end case inner gc
            top.setParent(temp);
            if (temp == null) {
                this.setRoot(top);
            }
            else if (top.getKey() < temp.getKey()) {
                temp.setlChild(top);
            }
            else {
                temp.setrChild(top);
            }
        }
    }
    
    private void rotateAfterInsert(RBTreeNode paren,
                                   Boolean isLeftGrandChild,
                                   Boolean isLeftChild,
                                   RBTreeNode gp,
                                   RBTreeNode insert)
    {
        TreeNode top, temp = gp.getParent();
        if (isLeftGrandChild != null) {
            if ((isLeftChild && isLeftGrandChild) || (!isLeftChild && !isLeftGrandChild)) {
                // case P is red and X is an outside grandchild
                gp.toggleNodeColor();
                paren.toggleNodeColor();
                if (isLeftChild) {
                    top = gp.rotateRight();
                }
                else {
                    top = gp.rotateLeft();
                }
            }
            else {
                // case P is red and X is an inside grandchild
                gp.toggleNodeColor();
                insert.toggleNodeColor();
                if (isLeftChild) {
                    // if X is a left child then that means P is a right child
                    // perform a right rotation on P then a left on G
                    gp.setrChild(paren.rotateRight());
                    top = gp.rotateLeft();
                }
                else {
                    // if X is a right child then that means P is a left child
                    gp.setlChild(paren.rotateLeft());
                    top = gp.rotateRight();
                }
            }
            top.setParent(temp);
            if (temp == null) {
                this.setRoot(top);
            }
            else if (top.getKey() < temp.getKey()) {
                temp.setlChild(top);
            }
            else {
                temp.setrChild(top);
            }
        }
    }
    
    // generated 2024-03-20 GPT 4 turbo
    public boolean validateRBCorrect()
    {
        // Property 2: The root is always black. If root is null, the tree is considered valid.
        if (this.getRoot() != null && !isBlack(( RBTreeNode ) this.getRoot())) {
            return false;
        }
        
        // Use a helper method to validate properties 3, 4, and 5
        return validateNode(( RBTreeNode ) this.getRoot(), 0) != -1;
    }
    
    // generated 2024-03-20 GPT 4 turbo
    private boolean isBlack(RBTreeNode node)
    {
        return node == null || node.isBlack();
    }
    
    // generated 2024-03-20 GPT 4 turbo
    private int validateNode(RBTreeNode node, int blackCount)
    {
        // Base case: reach a leaf (NIL node)
        if (node == null) {
            return blackCount + 1; // Count the black NIL node
        }
        
        // Property 1: node must be either red or black, checked implicitly by node structure
        
        // Property 3: Red nodes have only black children
        if (!isBlack(node) && (!isBlack(( RBTreeNode ) node.getlChild())
                               || !isBlack(( RBTreeNode ) node.getrChild())))
        {
            return -1; // Invalid RB tree
        }
        
        // Increment black count if the current node is black
        if (isBlack(node)) {
            blackCount++;
        }
        
        // Check if the left and right children have the same black node count
        int leftBlackCount = validateNode(( RBTreeNode ) node.getlChild(), blackCount);
        int rightBlackCount = validateNode(( RBTreeNode ) node.getrChild(), blackCount);
        if (leftBlackCount == -1 || rightBlackCount == -1 || leftBlackCount != rightBlackCount) {
            return -1; // Invalid RB tree
        }
        
        return leftBlackCount; // Or rightBlackCount, they're equal if valid
    }
    
    private static class RBTreeDemo {
        
        public static void main(String[] args)
        {
            RBTree rbTree = new RBTree();
            rbTree.insert(50, 'A');
            rbTree.insert(25, 'B');
            rbTree.insert(75, 'C');
            rbTree.insert(30, 'D');
            rbTree.insert(19, 'D');
            rbTree.displayTree();
            rbTree.setRoot(rbTree.getRoot()
                                 .rotateRight());
            rbTree.displayTree();
            rbTree.setRoot(rbTree.getRoot()
                                 .rotateLeft());
            rbTree.displayTree();
            rbTree.setRoot(rbTree.getRoot()
                                 .rotateLeft());
            rbTree.displayTree();
            rbTree.setRoot(rbTree.getRoot()
                                 .rotateLeft());
        }
        
    }
    
}
