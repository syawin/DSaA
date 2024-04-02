package tree.btree;

public class MultiNode {
    
    private final int         ORDER;
    private       MultiNode[] childArr;
    private       DataItem[]  dataArr;
    private       int         dataCount = 0;
    private       MultiNode   parent    = null;
    
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
    
    public DataItem[] getDataArr()
    {
        return dataArr;
    }
    
    public int getDataCount()
    {
        return dataCount;
    }
    
    public int getORDER()
    {
        return ORDER;
    }
    
    public MultiNode getParent()
    {
        return parent;
    }
    // getter end
    
    // setter
    public void setChildArr(MultiNode[] childArr)
    {
        this.childArr = childArr;
    }
    
    public void setDataArr(DataItem[] dataArr)
    {
        this.dataArr = dataArr;
    }
    
    public void setDataCount(int dataCount)
    {
        this.dataCount = dataCount;
    }
    
    public void setParent(MultiNode parent)
    {
        this.parent = parent;
    }
    // setter end
}
