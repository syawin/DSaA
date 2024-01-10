package tree;

public class TreeNode {

    private final int      key;
    private final Object   val;
    private       TreeNode rChild;
    private       TreeNode lChild;

    private TreeNode(Builder builder)
    {
        key    = builder.key;
        val    = builder.val;
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

    public int getKey()
    {
        return key;
    }

    public Object getVal()
    {
        return val;
    }

    @Override
    public String toString()
    {
        return "Node{" +
                       "key=" + key +
                       ", val=" + val +
                       ", rChild=" + rChild +
                       ", lChild=" + lChild +
                       '}';
    }

    public static class Builder {

        private final int    key;
        private final Object val;
        private       TreeNode lChild = null;
        private       TreeNode rChild = null;

        public Builder(int key, Object val)
        {
            this.key = key;
            this.val = val;
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
