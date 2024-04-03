package tree.btree;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class MultiNode {
    
    private final int ORDER;
    private MultiNode[] childArr;
    private int count = 0;
    private DataItem[] dataArr;
    private MultiNode parent = null;
    
    public MultiNode(int order)
    {
        ORDER = order;
        childArr = new MultiNode[ORDER];
        dataArr = new DataItem[ORDER - 1];
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
    
    public int insertItem(@NotNull DataItem insert)
    {
        // assumes node is not full
        count++;
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
    
    public DataItem removeLargest()
    {
        // assumes node is not empty
        DataItem temp = dataArr[count - 1];
        dataArr[count - 1] = null;
        count--;
        return temp;
    }
    
    @Override
    public String toString()
    {
        final StringBuffer sb = new StringBuffer("MultiNode{");
        sb.append("dataArr=")
          .append(dataArr == null
                  ? "null"
                  : Arrays.asList(dataArr)
                          .toString());
        sb.append('}');
        return sb.toString();
    }
    
}
