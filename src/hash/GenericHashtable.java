package hash;

/**
 * A minimal contract for a set‑style hash table.  <K> is the key type that
 * will be hashed; <V> is the internal node/entry type returned by lookup
 * operations (e.g.DataItem, Link, etc.).
 */
public interface GenericHashtable<K, V> {
    
    /**
     * Remove and return the entry associated with {@code key}, or {@code null} if absent.
     */
    V delete(K key);
    
    /**
     * Print a human‑readable representation of the table.
     */
    void displayTable();
    
    /**
     * Return the entry associated with {@code key}, or {@code null} if absent.
     */
    V find(K key);
    
    /**
     * Insert a key (and any associated value handled internally).
     */
    void insert(K key);
    
}
