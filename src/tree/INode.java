package tree;

public class INode {
    
    int    key;
    INode  lChild;
    INode  rChild;
    double val;
    
    public INode(int key, double val)
    {
        this.key = key;
        this.val = val;
    }
    
    public INode(int key, double val, INode lChild, INode rChild)
    {
        this.key    = key;
        this.val    = val;
        this.lChild = lChild;
        this.rChild = rChild;
    }
    
// getter
    public int getKey()
    {
        return key;
    }
    
    public double getVal()
    {
        return val;
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
    
    public void setVal(double val)
    {
        this.val = val;
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
               + val
               + ", lChild="
               + lChild
               + ", rChild="
               + rChild
               + '}';
    }
    
}
