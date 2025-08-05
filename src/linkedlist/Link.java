package linkedlist;

public class Link {
    
    double data;
    long   key;
    Link   next;
    
    public Link(long key, double data)
    {
        this.key  = key;
        this.data = data;
    }
    
    // getter
    public long getKey()
    {
        return key;
    }
    
    public Link getNext()
    {
        return next;
    }
    // getter end
    
    // setter
    public void setNext(Link next)
    {
        this.next = next;
    }
    // setter end
    
    public boolean hasNext()
    {
        return next != null;
    }
    
    public void print()
    {
        System.out.printf("{%d, %f} ", key, data);
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(this.key);
        sb.append(", ");
        sb.append(this.data);
        sb.append("} ");
        return sb.toString();
    }
    
}
