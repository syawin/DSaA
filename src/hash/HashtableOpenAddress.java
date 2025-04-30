package hash;

import common.DataItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class HashtableOpenAddress implements Hashtable {
    
    private static final int        STEP_CONSTANT = 5;
    private final        DataItem   NON_ITEM      = new DataItem(-1);
    private final        int        arraySize;
    private final        DataItem[] hashArray;
    
    public HashtableOpenAddress(int size)
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
    public DataItem delete(long key)
    {
        int hashVal  = hashFuncPrime(key);
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
    public DataItem find(long key)
    {
        int hashVal  = hashFuncPrime(key);
        int stepSize = hashFuncSec(key);
        
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].key() == key) return hashArray[hashVal];
            hashVal += stepSize;
            hashVal %= arraySize;
        }
        return null;
    }
    
    @Override
    public void insert(DataItem item)
    {
        int hashVal  = hashFuncPrime(item.key());
        int stepSize = hashFuncSec(item.key());
        
        while (hashArray[hashVal] != null && hashArray[hashVal].key() != -1) {
            hashVal += stepSize;
            hashVal %= arraySize;
        }
        hashArray[hashVal] = item;
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
    
    private int hashFuncPrime(long key)
    {
        return ( int ) (key % arraySize);
    }
    
    private int hashFuncSec(long key)
    {
        return ( int ) (STEP_CONSTANT - key % STEP_CONSTANT);
    }
    
    private static class HashtableDemo {
        
        private static BufferedReader reader = null;
        
        public static void main(String[] args) throws IOException
        {
            DataItem  item;
            long      key;
            final int size, n, keysPerCell = 2;
            System.out.print("Enter the size of the table: ");
            size = readInt();
            System.out.print("Enter the initial number of items: ");
            n = readInt();
            
            Hashtable table = new HashtableOpenAddress(size); // create table
            
            for (int i = 0; i < n; i++) {
                key  = ( long ) (Math.random() * keysPerCell * size);
                item = new DataItem(key);
                table.insert(item);
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
        
        private static void delete(Hashtable table) throws IOException
        {
            long key;
            System.out.print("Enter key value to delete: ");
            key = readInt();
            table.delete(key);
        }
        
        private static void find(Hashtable table) throws IOException
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
        
        private static void insert(Hashtable table) throws IOException
        {
            long     key;
            DataItem item;
            System.out.print("Enter key value to insert: ");
            key  = readInt();
            item = new DataItem(key);
            table.insert(item);
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
