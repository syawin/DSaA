package sort;

import java.util.HashMap;
import java.util.LinkedList;

public class RadixSort extends SortableArray {

    private final HashMap<Integer, LinkedList<Long>> map = new HashMap<>();

    public RadixSort(int maxSize)
    {
        super(maxSize);
        for (int i = 0; i < 10; i++) {
            map.put(i, new LinkedList<>());
        }
    }

    public static void main(String[] args)
    {
        System.out.println(RadixSort.hash(801, 1));
        System.out.println(RadixSort.hash(801, 2));
        System.out.println(RadixSort.hash(801, 3));
        System.out.println(RadixSort.hash(801, 41));
        System.out.println(RadixSort.hash(4001, 4));
    }

    /**
     * Hash function that returns the nth digit in a value in decimal
     *
     * @param val    value to be hashed
     * @param nDigit nth place digit to be returned
     *
     * @return hash value between 0 and 9
     */
    private static int hash(final long val, final int nDigit)
    {
        int count = nDigit;
        long temp = val;
        while (count-- > 1) {
            temp /= 10;
        }
        return (int) (temp % 10);
    }

    @Override
    public void sort()
    {
        int maxDigits = this.getMaxDigits();
        for (int i = 1; i <= maxDigits; i++) {
            for (Long val : arr) {
                map.get(hash(val, i))
                   .add(val);
            }
            int counter = 0;
            for (LinkedList<Long> vals : map.values()) {
                for (Long val : vals) {
                    this.arr[counter++] = val;
                }
                vals.clear();
            }
        }
    }

    private int getMaxDigits()
    {
        long max = -1;
        int numDigits = 0;
        for (Long l : arr) {
            if (l > max) max = l;
        }
        while (max > 0) {
            max /= 10;
            numDigits++;
        }
        return numDigits;
    }

    private static final class SortDemo {

        public static void main(String[] args)
        {
            int maxSize = 20;
            RadixSort sort = new RadixSort(maxSize);
            for (int i = 0; i < maxSize; i++) {
                long n = (int) (Math.random() * 99999);
                sort.insert(n);
            }
            System.out.println(sort.getMaxDigits());
            System.out.println(sort);
            sort.sort();
            System.out.println(sort);
        }

    }

}
