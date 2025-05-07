package hash;

/**
 * A minimal contract for a set‑style hash table.  <K> is the key type that
 * will be hashed; <V> is the internal node/entry type returned by lookup
 * operations (e.g.DataItem, Link, etc.).
 */
public abstract class GenericHashtable<K, V> {
    
    protected static int getPrime(int min) // returns 1st prime > min
    {
        if (min < 2) {
            return 2;
        }
        int candidate = min + 1;
        if (candidate % 2 == 0) {
            candidate++;
        }
        while (!GenericHashtable.isPrime(candidate)) {
            candidate += 2;
        }
        return candidate;
    }
    
    private static boolean isPrime(int n) // is n prime?
    {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Remove and return the entry associated with {@code key}, or {@code null} if absent.
     */
    public abstract V delete(K key);
    
    /**
     * Print a human‑readable representation of the table.
     */
    public abstract void displayTable();
    
    /**
     * Return the entry associated with {@code key}, or {@code null} if absent.
     */
    public abstract V find(K key);
    
    /**
     * Insert a key (and any associated value handled internally).
     */
    public abstract void insert(K key);
    
}
