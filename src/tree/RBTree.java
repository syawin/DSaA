package tree;

public class RBTree extends Tree {

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
        if (this.getRoot() == null) {
            this.setRoot(new RBTreeNode.Builder(key, val).red(false)
                                                         .build());
        }
        // todo color flips on the way down
        // todo rotations on the way down
        // todo rotations after the node is inserted
    }

    private static class RBTreeDemo {

        public static void main(String[] args)
        {

        }

    }

}
