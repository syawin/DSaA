package common;

import org.jetbrains.annotations.NotNull;

public record DataItem( long key ) implements Comparable<DataItem> {
    
    @Override
    public int compareTo(@NotNull DataItem o)
    {
        return Long.compare(this.key, o.key);
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof DataItem other) {
            return this.compareTo(other) == 0;
        }
        return false;
    }
    
    @Override
    public String toString()
    {
        return "/%d/".formatted(key);
    }
    
}
