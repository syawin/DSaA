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
    }

    public static int hash(final long val, final int nDigit)
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
        for (int i = 1; i <= 3; i++) {
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

    private static final class RadixDemo {

        public static void main(String[] args)
        {
            int maxSize = 16;
            RadixSort qs = new RadixSort(maxSize);
            for (int i = 0; i < maxSize; i++) {
                long n = (int) (Math.random() * 999);
                qs.insert(n);
            }
            System.out.println(qs);
            qs.sort();
            System.out.println(qs);
        }

    }

}
