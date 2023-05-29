package arrays;

public class BinarySearchOrderedArray extends OrderedArray {
    public BinarySearchOrderedArray(int max) {
        super(max);
    }

    /**
     * Binary search impl of find method
     *
     * @param key key to find
     * @return index of found element, -1 if not found
     */
    @Override
    public int find(long key) {
        return recFind(key, 0, nElems - 1);
    }

    private int recFind(long key, int lo, int hi) {
        int index = (lo + hi) / 2;
        if (a[index] == key) return index;
        else if (lo > hi) return -1;
        else {
            if (a[index] < key) return recFind(key, index + 1, hi);
            else return recFind(key, lo, index - 1);
        }
    }

    private static final class BinarySearchDemo {
        public static void main(String[] args) {
            int max = 50;
            BinarySearchOrderedArray array = new BinarySearchOrderedArray(max);
            array.insert(72);
            array.insert(90);
            array.insert(45);
            array.insert(126);
            array.insert(54);
            array.insert(99);
            array.insert(144);
            array.insert(27);
            array.insert(135);
            array.insert(81);
            array.insert(18);
            array.insert(108);
            array.insert(9);
            array.insert(117);
            array.insert(63);
            array.insert(36);
            System.out.println(array);

            int searchKey = 27;
            assert (array.find(searchKey) >= 0) : System.out.printf("Key %d not found\n",searchKey);
            System.out.printf("Found key %d\n", searchKey);
        }
    }
}
