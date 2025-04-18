package tree.btree;

import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Min;

public class MultiNode {
    
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
    
    public boolean isFull()
    {
        return itemCount == ORDER - 1;
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
    
    public DataItem getData(int dataIndex)
    {
        return dataArr[dataIndex];
    }
    
    public int insertItem(@NotNull DataItem insert)
    {
        // todo make full-safe to avoid IndexOutofBounds
        itemCount++;
        long newKey = insert.key();
        for (int i = ORDER - 2; i >= 0; i--) {
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
        String toString = STR."""
        \{isEmpty() ? "[]" : STR."[\{dataToString()}]"}
        """;
        return toString;
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
