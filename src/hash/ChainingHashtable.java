package hash;

import linkedlist.Link;
import linkedlist.LinkedList;

public class ChainingHashtable {
    
    private final int          arraySize;
    private final LinkedList[] hashArray;
    
    public ChainingHashtable(int size)
    {
        this.arraySize = size;
        this.hashArray = new LinkedList[size];
        for (int i = 0; i < arraySize; i++) {
            hashArray[i] = new LinkedList();
        }
    }
    
    public Link delete(long key)
    {
        int hashVal = hashFunc(key);
        return hashArray[hashVal].delete(key);
    }
    
    public void displayTable()
    {
        for (int i = 0; i < arraySize; i++) {
            System.out.print(i + ". ");
            hashArray[i].print();
        }
    }
    
    public Link find(long key)
    {
        int hashVal = hashFunc(key);
        return hashArray[hashVal].find(key);
    }
    
    public int hashFunc(long key)
    {
        return ( int ) (key % arraySize);
    }
    
    public void insert(long key)
    {
        int        hashVal = hashFunc(key);
        LinkedList list    = hashArray[hashVal];
        list.insertInOrder(key, 0.0);
    }
    
}
