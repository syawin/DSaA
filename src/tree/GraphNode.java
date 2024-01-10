package tree;

public class GraphNode {

    private final int       key;
    private final Object    val;
    private       GraphNode rChild;
    private       GraphNode lChild;

    private GraphNode(Builder builder)
    {
        key    = builder.key;
        val    = builder.val;
        lChild = builder.lChild;
        rChild = builder.rChild;
    }

    public GraphNode getrChild()
    {
        return rChild;
    }

    public void setrChild(GraphNode right)
    {
        this.rChild = right;
    }

    public GraphNode getlChild()
    {
        return lChild;
    }

    public void setlChild(GraphNode left)
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
        private       GraphNode lChild = null;
        private       GraphNode rChild = null;

        public Builder(int key, Object val)
        {
            this.key = key;
            this.val = val;
        }

        public Builder leftChild(GraphNode lChild)
        {
            this.lChild = lChild;
            return this;
        }

        public Builder rightChild(GraphNode rChild)
        {
            this.rChild = rChild;
            return this;
        }

        public GraphNode build()
        {
            return new GraphNode(this);
        }

    }

}
