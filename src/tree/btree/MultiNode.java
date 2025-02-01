package tree.btree;

import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Min;

public class MultiNode implements Comparable<MultiNode> {
    
    @Min(3)
    private final int         ORDER;
    private       MultiNode[] childArr;
    private       DataItem[]  dataArr;
    private       int         itemCount = 0;
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
    
    public int getItemCount()
    {
        return itemCount;
    }
    
    public int getORDER()
    {
        return ORDER;
    }
    
    public MultiNode getParent()
    {
        return parent;
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
    
    public void setDataArr(DataItem[] dataArr)
    {
        this.dataArr = dataArr;
    }
    
    public void setItemCount(int itemCount)
    {
        this.itemCount = itemCount;
    }
    
    public void setParent(MultiNode parent)
    {
        this.parent = parent;
    }
    // setter end
    
    @Override
    public int compareTo(@NotNull MultiNode o)
    {
        return this.getData(0).compareTo(o.getData(0));
    }
    
    public DataItem getData(int dataIndex)
    {
        return dataArr[dataIndex];
    }
    
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
    
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof MultiNode other) {
            if (this.ORDER != other.ORDER) return false;
            if (this.itemCount != other.itemCount) return false;
            for (int i = 0; i < itemCount; i++) {
                if (this.dataArr[i].compareTo(other.dataArr[i]) != 0) return false;
            }
            return true;
        }
        return false;
    }
    
    public int findItem(long key)
    {
        for (int i = 0; i < ORDER; i++) {
            if (dataArr[i] == null) {
                break;
            }
            else if (dataArr[i].key() == key) {
                return i;
            }
        }
        return -1;
    }
    
    public MultiNode getChild(int childIndex)
    {
        return childArr[childIndex];
    }
    
    public int insertItem(@NotNull DataItem insert) throws IllegalStateException
    {
        if (isFull()) {
            throw new IllegalStateException("Cannot insert into a full node");
        }
        // todo move the logic incrementing itemCount to later in the method. Note that just
        //  doing that causes everything to break.
        itemCount++;
        long newKey = insert.key();
        for (int i = itemCount - 2; i >= 0; i--) {
            if (dataArr[i] == null) {
                continue;
            }
            else {
                long currKey = dataArr[i].key();
                if (newKey < currKey) { dataArr[i + 1] = dataArr[i]; }
                else {
                    dataArr[i + 1] = insert;
                    return i + 1;
                }
            }
        }
        dataArr[0] = insert;
        return 0;
    }
    
    public boolean isFull()
    {
        return itemCount == ORDER - 1;
    }
    
    public DataItem removeItem()
    {
        // assumes node is not empty
        DataItem temp = dataArr[itemCount - 1];
        dataArr[itemCount - 1] = null;
        itemCount--;
        return temp;
    }
    
    @Override
    public String toString()
    {
        return (isEmpty() ? "[]" : "[" + dataToString() + "]") + "\n";
    }
    
    public boolean isEmpty()
    {
        return itemCount == 0;
    }
    
    public String dataToString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (DataItem dataItem : dataArr) {
            if (dataItem != null) stringBuilder.append(dataItem);
        }
        return stringBuilder.toString();
    }
    
    private static class MultiNodeDemo {
        
        public static void main(String[] args)
        {
            MultiNode root = new MultiNode(3);
            System.out.print(root.dataToString());
        }
        
    }
    
}
