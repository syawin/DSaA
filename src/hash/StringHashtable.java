package hash;

public class StringHashtable extends GenericHashtable<String, String> {
    
    private final int      ARRAY_SIZE;
    private final int      PRIME_CONSTANT = 7;
    private final String[] hashArray;
    
    public StringHashtable(int size)
    {
        ARRAY_SIZE = getPrime(size);
        hashArray  = new String[ARRAY_SIZE];
    }
    
    @Override
    public String delete(String key)
    {
        return "";
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
        return "";
    }
    
    @Override
    public void insert(String key)
    {
        int hash = 0;
        // iterate through the string as a char array
        for (int i = 0; i < key.length(); i++) {
            hash = (hash * PRIME_CONSTANT + key.charAt(i)) % ARRAY_SIZE;
        }
        while (hashArray[hash] != null) { hash++; }
        hashArray[hash] = key;
    }
    
}
