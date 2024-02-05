package tree;

public class INode {

    int    key;
    double val;
    INode  lChild;
    INode  rChild;

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

    @Override
    public String toString()
    {
        return "Node{" + "key=" + key + ", val=" + val + ", lChild=" + lChild + ", rChild=" + rChild + '}';
    }

}
