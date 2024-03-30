package tree.btree;

import java.util.ArrayList;
import java.util.List;

public class MultiNode<T> {
    
    private final int                ORDER;
    // item count is ORDER - 1
    private       List<DataItem<T>>  itemArray;
    // child count is ORDER
    private       List<MultiNode<T>> childArray;
    private       MultiNode<T>       parent;
    private       long               count;
    
    public MultiNode(int order)
    {
        ORDER      = order;
        itemArray  = new ArrayList<>(ORDER - 1);
        childArray = new ArrayList<>(ORDER);
        count      = 0;
    }
    
    
    // getter
    public List<MultiNode<T>> getChildArray()
    {
        return childArray;
    }
    
    public long getCount()
    {
        return count;
    }
    
    public List<DataItem<T>> getItemArray()
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
    public void setChildArray(List<MultiNode<T>> childArray)
    {
        this.childArray = childArray;
    }
    
    public void setCount(long count)
    {
        this.count = count;
    }
    
    public void setItemArray(List<DataItem<T>> itemArray)
    {
        this.itemArray = itemArray;
    }
    
    public void setParent(MultiNode<T> parent)
    {
        this.parent = parent;
    }
    // setter end
    
    public void connectChild(int childIndex, MultiNode<T> child)
    {
        if (childIndex < 0 || childIndex >= ORDER) {
            throw new IllegalArgumentException("Index %d is invalid for ORDER %d".formatted(childIndex, ORDER));
        }
        childArray.set(childIndex, child);
        if (child != null) {
            child.setParent(this);
        }
    }
    
    @Override
    public String toString()
    {
        return (itemArray == null || itemArray.isEmpty()
                ? "[]"
                : itemArray.toString()
                           .replace(",", "")
                           .replace(" ", ""));
    }
    
}
