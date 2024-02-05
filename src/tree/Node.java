package tree;

// todo parameterize class Node
public class Node {

    private final int    key;
    private final Object val;

    Node(Builder builder)
    {
        key = builder.key;
        val = builder.val;
    }

    // getter
    public int getKey()
    {
        return key;
    }

    public Object getVal()
    {
        return val;
    }
    // getter end

    @Override
    public String toString()
    {
        return "Node{" + "key=" + key + ", val=" + val + '}';
    }

    public static class Builder {

        private final int    key;
        private final Object val;

        public Builder(int key, Object val)
        {
            this.key = key;
            this.val = val;
        }

        public Node build()
        {
            return new Node(this);
        }

    }

}
