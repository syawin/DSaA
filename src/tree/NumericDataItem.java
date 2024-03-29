package tree;

public class NumericDataItem<T extends Number> extends DataItem<T> {
    
    public NumericDataItem(T data)
    {
        super(data);
    }
    
    @Override
    public String toString()
    {
        return "/%d/".formatted(getData());
    }
    
}
