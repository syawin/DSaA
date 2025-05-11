package hash;

import java.util.Objects;

public class DigitFoldingHashtable extends GenericHashtable<Long, Long> {
    
    private int    arraySize;
    private Long[] hashArray;
    private int    itemCount;
    private int    magnitude;
    
    public DigitFoldingHashtable(int size)
    {
        arraySize = getPrime(size);
        hashArray = new Long[arraySize];
        magnitude = calcMagnitude(arraySize);
        itemCount = 0;
    }
    
    // getter
    public int getItemCount()
    {
        return itemCount;
    }
    // getter end
    
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
        while (hashArray[index] != null && probe <= arraySize) {
            if (hashArray[index].equals(key)) {
                long temp = hashArray[index];
                hashArray[index] = -1L;
                itemCount--;
                return temp;
            }
            probe++;
            index = (index + 1) % arraySize;
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
    public Long find(Long key)
    {
        int index = hashFunc(key);
        int probe = 0;
        while (hashArray[index] != null && probe <= arraySize) {
            if (hashArray[index].equals(key)) return hashArray[index];
            probe++;
            index++;
            index %= arraySize;
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
            index %= arraySize;
        }
        hashArray[index] = key;
        itemCount++;
        if (loadFactor() >= 0.5F) rehash();
    }
    
    private int hashFunc(long key)
    {
        long temp  = key;
        long index = 0;
        while (temp != 0) {
            index += temp % magnitude;
            temp /= magnitude;
        }
        return ( int ) (index % arraySize);
    }
    
    private float loadFactor()
    {
        return ( float ) itemCount / arraySize;
    }
    
    private void rehash()
    {
        Long[] temp    = hashArray;
        int    newSize = getPrime(arraySize * 2);
        hashArray = new Long[newSize];
        arraySize = newSize;
        magnitude = calcMagnitude(arraySize);
        for (Long aLong : temp) {
            if (aLong != null && aLong != -1) {
                insert(aLong);
            }
        }
    }
    
}
