package tree.btree;

import java.util.Arrays;

public class MultiNode<T> {
    
    private DataItem<T>[]  itemArray;
    private MultiNode<T>[] childArray;
    private MultiNode<T> parent;
    private final int  ORDER;
    private       long count;
    
    public MultiNode(int order) { ORDER = order; }
    
    
    // getter
    public MultiNode<T>[] getChildArray()
    {
        return childArray;
    }
    
    public long getCount()
    {
        return count;
    }
    
    public DataItem<T>[] getItemArray()
    {
        return itemArray;
    }
    
    public long getOrder()
    {
        return ORDER;
    }
    
    public MultiNode<T> getParent()
    {
        return parent;
    }
    // getter end
    
    // setter
    public void setChildArray(MultiNode<T>[] childArray)
    {
        this.childArray = childArray;
    }
    
    public void setCount(long count)
    {
        this.count = count;
    }
    
    public void setItemArray(DataItem<T>[] itemArray)
    {
        this.itemArray = itemArray;
    }
    
    public void setParent(MultiNode<T> parent)
    {
        this.parent = parent;
    }
    // setter end
    
    // todo display method; prints data items and all children recursively
    public void display()
    {
    }
    
    @Override
    public String toString()
    {
        return (itemArray == null
                ? "[]"
                : Arrays.asList(itemArray)
                        .toString()
                        .replace(",", "")
                        .replace(" ", ""));
    }
    
}
