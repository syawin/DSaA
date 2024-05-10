package tree.btree;

import org.jetbrains.annotations.NotNull;

public record DataItem(long key) implements Comparable<DataItem> {
    
    @Override
    public int compareTo(@NotNull DataItem o)
    {
        return Long.compare(this.key, o.key);
    }
    
    @Override
    public String toString()
    {
        return "/%d/".formatted(key);
    }
    
}
