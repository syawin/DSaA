package linkedlist;

public class Link {
    
    double dData;
    long   key;
    Link   next;
    
    public Link(long key, double dData)
    {
        this.key   = key;
        this.dData = dData;
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
        System.out.printf("{%d, %f} ", key, dData);
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(this.key);
        sb.append(", ");
        sb.append(this.dData);
        sb.append("} ");
        return sb.toString();
    }
    
}
