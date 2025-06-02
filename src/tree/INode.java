package tree;

public class INode {
    
    int    key;
    INode  lChild;
    INode  rChild;
    double value;
    
    public INode(int key, double value)
    {
        this.key   = key;
        this.value = value;
    }
    
    public INode(int key, double value, INode lChild, INode rChild)
    {
        this.key    = key;
        this.value  = value;
        this.lChild = lChild;
        this.rChild = rChild;
    }
    
// getter
    public int getKey()
    {
        return key;
    }
    
    public double getValue()
    {
        return value;
    }
    
    public INode getlChild()
    {
        return lChild;
    }
    
    public INode getrChild()
    {
        return rChild;
    }
// getter end

// setter
    public void setKey(int key)
    {
        this.key = key;
    }
    
    public void setValue(double value)
    {
        this.value = value;
    }
    
    public void setlChild(INode lChild)
    {
        this.lChild = lChild;
    }
    
    public void setrChild(INode rChild)
    {
        this.rChild = rChild;
    }
// setter end
    
    @Override
    public String toString()
    {
        return "Node{"
               + "key="
               + key
               + ", val="
               + value
               + ", lChild="
               + lChild
               + ", rChild="
               + rChild
               + '}';
    }
    
}
