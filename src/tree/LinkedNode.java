package tree;

public class LinkedNode extends Node {

    private LinkedNode next;

    LinkedNode(Builder builder)
    {
        super(builder);
        next = builder.next;
    }

    // getter
    public LinkedNode getNext()
    {
        return next;
    }
    // getter end

    // setter
    public void setNext(LinkedNode next)
    {
        this.next = next;
    }
    // setter end

    public boolean hasNext()
    {
        return next != null;
    }

    public static class Builder extends Node.Builder {

        private LinkedNode next = null;

        public Builder(int key, Object val)
        {
            super(key, val);
        }

        public LinkedNode build()
        {
            return new LinkedNode(this);
        }

        public Builder next(LinkedNode next)
        {
            this.next = next;
            return this;
        }

    }

}
