package tree;

public class RBTreeNode extends TreeNode {

    private boolean red;

    public RBTreeNode(Builder builder)
    {
        super(builder);
        this.red = builder.red;
    }

    // getter
    public boolean isBlack() { return !red; }
    // getter end

    // setter
    public void setRed(boolean red)
    {
        this.red = red;
    }
    // setter end

    public boolean hasTwoRedChildren()
    {
        return this.getlChild() != null
               && this.getrChild() != null
               && ((RBTreeNode) this.getlChild()).isRed()
               && ((RBTreeNode) this.getrChild()).isRed();
    }
    
    public void toggleNodeColor()
    {
        this.red = !this.red;
    }
    
    public void flipColors()
    {
        this.setRed(true);
        ((RBTreeNode) this.getlChild()).setRed(false);
        ((RBTreeNode) this.getrChild()).setRed(false);
    }
    
    public boolean isRed()
    {
        return red;
    }

    public static class Builder extends TreeNode.Builder {

        private boolean red = true;

        public Builder(int key, Object val)
        {
            super(key, val);
        }

        // getter
        public boolean isRed()
        {
            return red;
        }
        // getter end

        public RBTreeNode build()
        {
            return new RBTreeNode(this);
        }

        public Builder red(boolean red)
        {
            this.red = red;
            return this;
        }

    }

}
