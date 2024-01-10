package tree;

public class TreeNode extends Node {

    private       TreeNode rChild;
    private       TreeNode lChild;

    private TreeNode(Builder builder)
    {
        super(builder);
        lChild = builder.lChild;
        rChild = builder.rChild;
    }

    public TreeNode getrChild()
    {
        return rChild;
    }

    public void setrChild(TreeNode right)
    {
        this.rChild = right;
    }

    public TreeNode getlChild()
    {
        return lChild;
    }

    public void setlChild(TreeNode left)
    {
        this.lChild = left;
    }

    @Override
    public String toString()
    {
        return "Node{" +
                       "key=" + getKey() +
                       ", val=" + getVal() +
                       ", rChild=" + rChild +
                       ", lChild=" + lChild +
                       '}';
    }

    public static class Builder extends Node.Builder {

        private       TreeNode lChild = null;
        private       TreeNode rChild = null;

        public Builder(int key, Object val)
        {
            super(key, val);
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

        public TreeNode build()
        {
            return new TreeNode(this);
        }

    }

}
