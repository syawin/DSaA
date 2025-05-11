package hash;

import tree.INode;
import tree.ITree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class BinaryTreeHashtable extends GenericHashtable<Integer, Integer> {
    
    private final int     arraySize;
    private final ITree[] hashArray;
    
    public BinaryTreeHashtable(int size)
    {
        this.arraySize = size;
        this.hashArray = new ITree[size];
        for (int i = 0; i < arraySize; i++) {
            hashArray[i] = new ITree();
        }
    }
    
    @Override
    public Integer delete(Integer key)
    {
        return Optional.ofNullable(hashArray[hashFunc(key)].delete(key)).map(INode::getKey).orElse(
                null);
    }
    
    @Override
    public void displayTable()
    {
        for (int i = 0; i < arraySize; i++) {
            System.out.print(i + ". ");
            hashArray[i].displayTree();
        }
    }
    
    @Override
    public Integer find(Integer key)
    {
        return Optional.ofNullable(hashArray[hashFunc(key)].find(key)).map(INode::getKey).orElse(
                null);
    }
    
    public int hashFunc(int key)
    {
        return key % arraySize;
    }
    
    @Override
    public void insert(Integer key)
    {
        hashArray[hashFunc(key)].insert(key, 0.0);
    }
    
    private static class BinaryTreeHashtableDemo {
        
        private static BufferedReader reader = null;
        
        public static void main(String[] args) throws IOException
        {
            int key;
            int size, n, keysPerCell = 100;
            System.out.print("Enter the size of the table: ");
            size = readInt();
            System.out.print("Enter the initial number of items: ");
            n = readInt();
            
            BinaryTreeHashtable table = new BinaryTreeHashtable(size);
            for (int i = 0; i < n; i++) {
                key = ( int ) (Math.random() * keysPerCell * size);
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
        
        private static void delete(BinaryTreeHashtable table) throws IOException
        {
            int key;
            System.out.print("Enter key value to delete: ");
            key = readInt();
            table.delete(key);
        }
        
        private static void find(BinaryTreeHashtable table) throws IOException
        {
            int     key;
            Integer item;
            System.out.print("Enter key value to find: ");
            key  = readInt();
            item = table.find(key);
            if (item != null) {
                System.out.println("Found " + key);
            }
            else { System.out.println("Key not found"); }
        }
        
        private static void insert(BinaryTreeHashtable table) throws IOException
        {
            int key;
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
            if (reader == null) {
                reader = new BufferedReader(new InputStreamReader(System.in));
            }
            return reader.readLine();
        }
        
    }
    
}
