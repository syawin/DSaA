package hash;

import linkedlist.Link;
import linkedlist.LinkedList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChainingHashtable extends GenericHashtable<Long, Link> {
    
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
    
    @Override
    public Link delete(Long key)
    {
        int hashVal = hashFunc(key);
        return hashArray[hashVal].delete(key);
    }
    
    @Override
    public void displayTable()
    {
        for (int i = 0; i < arraySize; i++) {
            System.out.print(i + ". ");
            hashArray[i].print();
        }
    }
    
    @Override
    public Link find(Long key)
    {
        int hashVal = hashFunc(key);
        return hashArray[hashVal].find(key);
    }
    
    public int hashFunc(long key)
    {
        return ( int ) (key % arraySize);
    }
    
    @Override
    public void insert(Long key)
    {
        int        hashVal = hashFunc(key);
        LinkedList list    = hashArray[hashVal];
        list.insertInOrder(key, 0.0);
    }
    
    private static class ChainingHashtableDemo {
        
        private static BufferedReader reader = null;
        
        public static void main(String[] args) throws IOException
        {
            long key;
            int  size, n, keysPerCell = 100;
            System.out.print("Enter the size of the table: ");
            size = readInt();
            System.out.print("Enter the initial number of items: ");
            n = readInt();
            
            ChainingHashtable table = new ChainingHashtable(size);
            for (int i = 0; i < n; i++) {
                key = ( long ) (Math.random() * keysPerCell * size);
                table.insert(key);
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
        
        private static void delete(ChainingHashtable table) throws IOException
        {
            long key;
            System.out.print("Enter key value to delete: ");
            key = readInt();
            table.delete(key);
        }
        
        private static void find(ChainingHashtable table) throws IOException
        {
            long key;
            Link item;
            System.out.print("Enter key value to find: ");
            key  = readInt();
            item = table.find(key);
            if (item != null) {
                System.out.println("Found " + key);
            }
            else { System.out.println("Key not found"); }
        }
        
        private static void insert(ChainingHashtable table) throws IOException
        {
            long key;
            System.out.print("Enter key value to insert: ");
            key = readInt();
            table.insert(key);
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
