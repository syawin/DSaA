package tree;

public final class DataItem<T> {
    
    private final T data;
    
    public DataItem(T data) { this.data = data; }
    
    // getter
    public T getData()
    {
        return data;
    }
    // getter end
}
