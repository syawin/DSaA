package hash;

import java.util.Objects;

public class DigitFoldingHashtable extends GenericHashtable<Long, Long> {
    
    private final int    ARRAY_SIZE;
    private final int    MAGNITUDE;
    private final Long[] hashArray;
    
    public DigitFoldingHashtable(int size)
    {
        ARRAY_SIZE = getPrime(size);
        hashArray  = new Long[ARRAY_SIZE];
        MAGNITUDE  = calcMagnitude(ARRAY_SIZE);
    }
    
    private static int calcMagnitude(int arg)
    {
        int count = 1;
        while (arg > 0) {
            count *= 10;
            arg /= 10;
        }
        return count;
    }
    
    @Override
    public Long delete(Long key)
    {
        int index = hashFunc(key);
        int probe = 0;
        while (hashArray[index] != null && probe <= ARRAY_SIZE) {
            if (hashArray[index].equals(key)) {
                long temp = hashArray[index];
                hashArray[index] = -1L;
                return temp;
            }
            probe++;
            index = (index + 1) % ARRAY_SIZE;
        }
        return null;
    }
    
    @Override
    public void displayTable()
    {
        System.out.print("Table: ");
        for (int i = 0; i < ARRAY_SIZE; i++) {
            if (hashArray[i] != null) { System.out.print(hashArray[i].toString() + " "); }
            else { System.out.print("** "); }
        }
        System.out.println();
    }
    
    @Override
    public Long find(Long key)
    {
        int index = hashFunc(key);
        int probe = 0;
        while (hashArray[index] != null && probe <= ARRAY_SIZE) {
            if (hashArray[index].equals(key)) return hashArray[index];
            probe++;
            index++;
            index %= ARRAY_SIZE;
        }
        return null;
    }
    
    @Override
    public void insert(Long key)
    {
        int index = hashFunc(key);
        while (hashArray[index] != null || hashArray[index] != -1) {
            if (Objects.equals(hashArray[index], key)) return; // forbid duplicates
            index++;
            index %= ARRAY_SIZE;
        }
        hashArray[index] = key;
    }
    
    private int hashFunc(long key)
    {
        long temp  = key;
        long index = 0;
        while (temp != 0) {
            index += temp % MAGNITUDE;
            temp /= MAGNITUDE;
        }
        return ( int ) (index % ARRAY_SIZE);
    }
    
}
