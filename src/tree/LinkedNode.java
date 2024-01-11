package tree;

public class LinkedNode extends Node {

    private LinkedNode next;

    LinkedNode(Builder builder)
    {
        super(builder);
        next = builder.next;
    }

    public boolean hasNext()
    {
        return next != null;
    }

    public LinkedNode getNext()
    {
        return next;
    }

    public void setNext(LinkedNode next)
    {
        this.next = next;
    }

    @Override
    public String toString()
    {
        return "LinkedNode{" +
                       "next=" + next +
                       ", key=" + getKey() +
                       ", val=" + getVal() +
                       '}';
    }

    public static class Builder extends Node.Builder {

        private LinkedNode next = null;

        public Builder(int key, Object val)
        {
            super(key, val);
        }

        public Builder next(LinkedNode next)
        {
            this.next = next;
            return this;
        }

        public LinkedNode build()
        {
            return new LinkedNode(this);
        }

    }

}
