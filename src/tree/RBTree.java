package tree;

import java.util.function.Predicate;

public class RBTree extends Tree {
    
    private static final Predicate<RBTreeNode> isFlip = n -> n.isBlack() && n.hasTwoRedChildren();
    
    public RBTree()
    {
    }
    
    public RBTree(TreeNode root)
    {
        super(root);
    }
    
    @Override
    public void insert(int key, Object val)
    {
        RBTreeNode insert = new RBTreeNode.Builder(key, val).build();
        if (this.getRoot() == null) {
            insert.setRed(false);
            this.setRoot(insert);
        }
        else {
            RBTreeNode curr = ( RBTreeNode ) this.getRoot();
            RBTreeNode paren;
            if (isFlip.test(curr)) {
                curr.flipColors();
                curr.setRed(false);
            }
            while (true) {
                paren = curr;
                if (isFlip.test(curr)) curr.flipColors();
                if (key < curr.getKey()) {
                    curr = ( RBTreeNode ) curr.getlChild();
                    if (curr == null) {
                        paren.setlChild(insert);
                        return;
                    }
                }
                else {
                    curr = ( RBTreeNode ) curr.getrChild();
                    if (curr == null) {
                        paren.setrChild(insert);
                        return;
                    }
                }
            }
        }
        // todo rotations on the way down
        // todo rotations after the node is inserted
    }
    
    // todo public boolean validateRBCorrect()
    
    private static class RBTreeDemo {
        
        public static void main(String[] args)
        {
            RBTree rbTree = new RBTree();
            rbTree.insert(1, 'A');
        }
        
    }
    
}
