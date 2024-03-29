package tree;

public abstract class DataItem<T> {
    
    private final T data;
    
    public DataItem(T data) { this.data = data; }
    
    // getter
    public final T getData()
    {
        return data;
    }
    // getter end
    
    
    @Override
    public String toString()
    {
        return "/%s/".formatted(data);
    }
    
}
