package tree.btree;

public record DataItem(long key) {
    
    // getter end
    
    @Override
    public String toString()
    {
        return "/%d/".formatted(key);
    }
    
}
