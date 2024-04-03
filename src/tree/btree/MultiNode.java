package tree.btree;

public class MultiNode {
    
    private final int         ORDER;
    private       MultiNode[] childArr;
    private int       count  = 0;
    private       DataItem[]  dataArr;
    private MultiNode parent = null;
    
    public MultiNode(int order)
    {
        ORDER    = order;
        childArr = new MultiNode[ORDER];
        dataArr  = new DataItem[ORDER - 1];
    }
    
    // getter
    public MultiNode[] getChildArr()
    {
        return childArr;
    }
    
    public int getCount()
    {
        return count;
    }
    
    public DataItem[] getDataArr()
    {
        return dataArr;
    }
    
    public int getORDER()
    {
        return ORDER;
    }
    
    public MultiNode getParent()
    {
        return parent;
    }
    
    public boolean isFull()
    {
        return count == ORDER - 1;
    }
    
    public boolean isLeaf()
    {
        return childArr[0] == null;
    }
    // getter end
    
    // setter
    public void setChildArr(MultiNode[] childArr)
    {
        this.childArr = childArr;
    }
    
    public void setCount(int count)
    {
        this.count = count;
    }
    
    public void setDataArr(DataItem[] dataArr)
    {
        this.dataArr = dataArr;
    }
    
    public void setParent(MultiNode parent)
    {
        this.parent = parent;
    }
    // setter end
    
    public void connectChild(int childIndex, MultiNode child)
    {
        childArr[childIndex] = child;
        if (child != null) {
            child.parent = this;
        }
    }
    
    // What do I do Lord? Corrupt them all.
    public MultiNode destroyTheChild(int childIndex)
    {
        MultiNode temp = childArr[childIndex];
        childArr[childIndex] = null;
        return temp;
    }
    
}
