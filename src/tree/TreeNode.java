package tree;

public class TreeNode extends Node {

    private TreeNode lChild;
    private TreeNode rChild;

    TreeNode(Builder builder)
    {
        super(builder);
        lChild = builder.lChild;
        rChild = builder.rChild;
    }

    // getter
    public TreeNode getlChild()
    {
        return lChild;
    }

    public TreeNode getrChild()
    {
        return rChild;
    }

    public boolean isLeaf()
    {
        return lChild == null && rChild == null;
    }
    // getter end

    // setter
    public void setlChild(TreeNode left)
    {
        this.lChild = left;
    }

    public void setrChild(TreeNode right)
    {
        this.rChild = right;
    }
    // setter end

    public static class Builder extends Node.Builder {

        private TreeNode lChild = null;
        private TreeNode rChild = null;

        public Builder(int key, Object val)
        {
            super(key, val);
        }

        public TreeNode build()
        {
            return new TreeNode(this);
        }

        public Builder leftChild(TreeNode lChild)
        {
            this.lChild = lChild;
            return this;
        }

        public Builder rightChild(TreeNode rChild)
        {
            this.rChild = rChild;
            return this;
        }

    }

}
