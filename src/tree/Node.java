package tree;

public class Node {

    private final int    key;
    private final Object val;
    private       Node   rChild;
    private       Node   lChild;

    private Node(Builder builder)
    {
        key    = builder.key;
        val    = builder.val;
        lChild = builder.lChild;
        rChild = builder.rChild;
    }

    public Node getRight()
    {
        return rChild;
    }

    public void setRight(Node right)
    {
        this.rChild = right;
    }

    public Node getLeft()
    {
        return lChild;
    }

    public void setLeft(Node left)
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
        private       Node   lChild = null;
        private       Node   rChild = null;

        public Builder(int key, Object val)
        {
            this.key = key;
            this.val = val;
        }

        public Builder leftChild(Node lChild)
        {
            this.lChild = lChild;
            return this;
        }

        public Builder rightChild(Node rChild)
        {
            this.rChild = rChild;
            return this;
        }

        public Node build()
        {
            return new Node(this);
        }

    }

}
