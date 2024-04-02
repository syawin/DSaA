package tree.btree;

public record DataItem(long key) {
    
    @Override
    public String toString()
    {
        return "/%d/".formatted(key);
    }
    
}
