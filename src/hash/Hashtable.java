package hash;

import common.DataItem;

public interface Hashtable {
    
    /**
     * Deletes a DataItem with the specified key from the hashtable.
     * If the key is found, the slot is marked with a special non-item marker.
     *
     * @param key
     *         the key of the item to delete
     *
     * @return the deleted DataItem if found, otherwise null
     */
    DataItem delete(long key);
    
    void displayTable();
    
    /**
     * Finds a DataItem with the specified key in the hashtable.
     * Assumes the table is not full.
     *
     * @param key
     *         the key to search for
     *
     * @return the found DataItem, or null if not found
     */
    DataItem find(long key);
    
    /**
     * Inserts a new DataItem into the hashtable.
     * Assumes the table is not full.
     *
     * @param item
     *         the DataItem to insert
     */
    void insert(DataItem item);
    
}
