package tree.btree;

import java.util.Arrays;

public class MultiNode<T> {
    
    private DataItem<T>[]  itemArray;
    private MultiNode<T>[] childArray;
    
    // getter
    public MultiNode<T>[] getChildArray()
    {
        return childArray;
    }
    
    public DataItem<T>[] getItemArray()
    {
        return itemArray;
    }
    // getter end
    
    // setter
    public void setChildArray(MultiNode<T>[] childArray)
    {
        this.childArray = childArray;
    }
    
    public void setItemArray(DataItem<T>[] itemArray)
    {
        this.itemArray = itemArray;
    }
    // setter end
    
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
