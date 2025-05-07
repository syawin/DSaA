package hash;

import common.DataItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class OpenAddressHashtable implements GenericHashtable<Long, DataItem> {
    
    private static final int        STEP_CONSTANT = 5;
    private final        DataItem   NON_ITEM      = new DataItem(-1);
    private final        int        arraySize;
    private final        DataItem[] hashArray;
    
    public OpenAddressHashtable(int size)
    {
        arraySize = size;
        hashArray = new DataItem[arraySize];
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
    
    @Override
    public DataItem delete(Long key)
    {
        int hashVal  = hashFunc(key);
        int stepSize = hashFuncSec(key);
        
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].key() == key) {
                DataItem temp = hashArray[hashVal];
                hashArray[hashVal] = NON_ITEM;
                return temp;
            }
            hashVal += stepSize;
            hashVal %= arraySize;
        }
        return null;
    }
    
    @Override
    public void displayTable()
    {
        System.out.print("Table: ");
        for (int i = 0; i < arraySize; i++) {
            if (hashArray[i] != null) { System.out.print(hashArray[i].toString() + " "); }
            else { System.out.print("** "); }
        }
        System.out.println();
    }
    
    @Override
    public DataItem find(Long key)
    {
        int hashVal  = hashFunc(key);
        int stepSize = hashFuncSec(key);
        
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].key() == key) return hashArray[hashVal];
            hashVal += stepSize;
            hashVal %= arraySize;
        }
        return null;
    }
    
    public int hashFunc(long key)
    {
        return ( int ) (key % arraySize);
    }
    
    /**
     * Default insertion strategy uses double hashing.
     */
    @Override
    public void insert(Long key)
    {
        insertDoubleHash(key);
    }
    
    public void insertDoubleHash(long key)
    {
        DataItem dataItem = new DataItem(key);
        int      hashVal  = hashFunc(dataItem.key());
        int      stepSize = hashFuncSec(dataItem.key());
        
        while (hashArray[hashVal] != null && hashArray[hashVal].key() != -1) {
            hashVal += stepSize;
            hashVal %= arraySize;
        }
        hashArray[hashVal] = dataItem;
    }
    
    public void insertQuadProbe(long key)
    {
        DataItem dataItem = new DataItem(key);
        int      hashVal  = hashFunc(dataItem.key());
        int      step     = 1;
        
        while (hashArray[hashVal] != null && hashArray[hashVal].key() != -1) {
            hashVal = (hashVal + step * step) % arraySize;
            step++;
        }
        hashArray[hashVal] = dataItem;
    }
    
    private int getPrime(int min) // returns 1st prime > min
    {
        if (min < 2) {
            return 2;
        }
        int candidate = min + 1;
        if (candidate % 2 == 0) {
            candidate++;
        }
        while (!isPrime(candidate)) {
            candidate += 2;
        }
        return candidate;
    }
    
    private int hashFuncSec(long key)
    {
        return ( int ) (STEP_CONSTANT - key % STEP_CONSTANT);
    }
    
    @SuppressWarnings("unused")
    private static class HashtableDemo {
        
        private static BufferedReader reader = null;
        
        public static void main(String[] args) throws IOException
        {
            long      key;
            final int size, n, keysPerCell = 2;
            System.out.print("Enter the size of the table: ");
            size = readInt();
            System.out.print("Enter the initial number of items: ");
            n = readInt();
            
            OpenAddressHashtable table = new OpenAddressHashtable(size); // create table
            
            for (int i = 0; i < n; i++) {
                key = ( long ) (Math.random() * keysPerCell * size);
                table.insertQuadProbe(key);
            }
            
            while (true) {
                System.out.println("Enter the first letter of the command:");
                System.out.print("\tShow,\n\tInsert,\n\tDelete,\n\tFind :");
                char opt = readChar();
                switch (opt) {
                    case 's':
                        table.displayTable();
                        break;
                    case 'i':
                        insert(table);
                        break;
                    case 'd':
                        delete(table);
                        break;
                    case 'f':
                        find(table);
                        break;
                    case 'q':
                        return;
                    default:
                        System.out.println("Invalid input");
                }
            }
        }
        
        private static void delete(OpenAddressHashtable table) throws IOException
        {
            long key;
            System.out.print("Enter key value to delete: ");
            key = readInt();
            table.delete(key);
        }
        
        private static void find(OpenAddressHashtable table) throws IOException
        {
            long     key;
            DataItem item;
            System.out.print("Enter key value to find: ");
            key  = readInt();
            item = table.find(key);
            if (item != null) {
                System.out.println("Found " + key);
            }
            else { System.out.println("Key not found"); }
        }
        
        private static void insert(OpenAddressHashtable table) throws IOException
        {
            long key;
            System.out.print("Enter key value to insert: ");
            key = readInt();
            table.insertDoubleHash(key);
        }
        
        private static char readChar() throws IOException
        {
            return readString().charAt(0);
        }
        
        private static int readInt() throws IOException
        {
            return Integer.parseInt(readString());
        }
        
        private static String readString() throws IOException
        {
            if (reader == null) { reader = new BufferedReader(new InputStreamReader(System.in)); }
            return reader.readLine();
        }
        
    }
    
}
