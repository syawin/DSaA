package hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class StringHashtable extends GenericHashtable<String, String> {
    
    private final int      ARRAY_SIZE;
    private final String   NON = "!";
    private final int      PRIME_CONSTANT;
    private final String[] hashArray;
    
    public StringHashtable(int size)
    {
        PRIME_CONSTANT = getPrime(size);
        ARRAY_SIZE     = getPrime(PRIME_CONSTANT);
        hashArray      = new String[ARRAY_SIZE];
    }
    
    @Override
    public String delete(String key)
    {
        int index = getHash(key);
        int probe = 0;
        while (hashArray[index] != null && probe <= ARRAY_SIZE) {
            if (hashArray[index].equals(key)) {
                String temp = hashArray[index];
                hashArray[index] = NON;
                return temp;
            }
            index++;
            probe++;
            index %= ARRAY_SIZE;
        }
        return null;
    }
    
    @Override
    public void displayTable()
    {
        System.out.print("Table: ");
        for (int i = 0; i < ARRAY_SIZE; i++) {
            if (hashArray[i] != null) { System.out.print(hashArray[i] + " "); }
            else { System.out.print("** "); }
        }
        System.out.println();
    }
    
    @Override
    public String find(String key)
    {
        int index = getHash(key);
        int probe = 0;
        while (hashArray[index] != null && probe <= ARRAY_SIZE) {
            if (hashArray[index].equals(key)) return hashArray[index];
            index++;
            probe++;
            index %= ARRAY_SIZE;
        }
        return null;
    }
    
    @Override
    public void insert(String key)
    {
        int hash = getHash(key);
        while (hashArray[hash] != null || !Objects.equals(hashArray[hash], NON) || !Objects.equals(
                hashArray[hash],
                key)) {
            hash++;
            hash %= ARRAY_SIZE;
        }
        hashArray[hash] = key;
    }
    
    private int getHash(String key)
    {
        int hash = 0;
        // iterate through the string as a char array
        for (int i = 0; i < key.length(); i++) {
            hash = (hash * PRIME_CONSTANT + key.charAt(i)) % ARRAY_SIZE;
        }
        return hash;
    }
    
    @SuppressWarnings({
            "unused",
            "DuplicatedCode"
    })
    private static class StringHashtableDemo {
        
        private static BufferedReader reader = null;
        
        public static void main(String[] args) throws IOException
        {
            final int size, n;
            System.out.print("Enter the size of the table: ");
            size = readInt();
            System.out.print("Enter the initial number of items: ");
            n = readInt();
            
            StringHashtable table = new StringHashtable(size); // create table
            
            for (int i = 0; i < n; i++) {
                int    length = ( int ) (Math.random() * size) + 1;
                String keyStr = randomString(length);
                table.insert(keyStr);
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
        
        private static void delete(StringHashtable table) throws IOException
        {
            String key;
            System.out.print("Enter key value to delete: ");
            key = readString();
            table.delete(key);
        }
        
        private static void find(StringHashtable table) throws IOException
        {
            String key;
            String item;
            System.out.print("Enter key value to find: ");
            key  = readString();
            item = table.find(key);
            if (item != null && !item.isEmpty()) {
                System.out.println("Found " + key);
            }
            else { System.out.println("Key not found"); }
        }
        
        private static void insert(StringHashtable table) throws IOException
        {
            String key;
            System.out.print("Enter key value to insert: ");
            key = readString();
            table.insert(key);
        }
        
        private static String randomString(int length)
        {
            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                char c = ( char ) ('a' + ( int ) (Math.random() * 26));
                sb.append(c);
            }
            return sb.toString();
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
