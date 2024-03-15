package tree;

public class TreeNode extends Node {
    
    private TreeNode lChild;
    private TreeNode rChild;
    private TreeNode parent;
    
    TreeNode(Builder builder)
    {
        super(builder);
        lChild = builder.lChild;
        rChild = builder.rChild;
        parent = builder.parent;
    }
    
    // getter
    public TreeNode getParent()
    {
        return parent;
    }
    
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
    public void setParent(TreeNode parent)
    {
        this.parent = parent;
    }
    
    public void setlChild(TreeNode left)
    {
        this.lChild = left;
    }
    
    public void setrChild(TreeNode right)
    {
        this.rChild = right;
    }
    // setter end
    
    public TreeNode rotateLeft()
    {
        TreeNode top = this;
        TreeNode r = top.getrChild();
        if (r != null) {
            TreeNode temp = r.lChild;
            r.lChild   = top;
            top.rChild = temp;
            if (temp != null) {
                temp.parent = top;
            }
            r.parent   = top.parent;
            top.parent = r;
            top        = r;
        }
        return top;
    }
    
    /**
     * This method is meant to be invoked on the node to be rotated. Parent ref should be updated to point to the new
     * top & rotated node must have its parent ref updated.
     *
     * @return Ref to the node that moves into the place of 'this'.
     */
    public TreeNode rotateRight()
    {
        TreeNode top = this;
        TreeNode l = top.getlChild();
        if (l != null) {
            TreeNode temp = l.rChild;
            l.rChild   = top;
            top.lChild = temp;
            if (temp != null) {
                temp.parent = top;
            }
            l.parent   = top.parent;
            top.parent = l;
            top        = l;
        }
        return top;
    }
    
    public static class Builder extends Node.Builder {
        
        private TreeNode lChild = null;
        private TreeNode rChild = null;
        private TreeNode parent = null;
        
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
        
        public Builder parent(TreeNode parent)
        {
            this.parent = parent;
            return this;
        }
        
        public Builder rightChild(TreeNode rChild)
        {
            this.rChild = rChild;
            return this;
        }
        
    }
    
}
