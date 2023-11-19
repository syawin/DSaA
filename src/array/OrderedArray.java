package array;

public class OrderedArray extends HiArray{
    public OrderedArray(int max) {
        super(max);
    }

    @Override
    public void insert(long value) {
        int i;
        for (i = 0; i < nElems; i++) {
            if (a[i] > value) break;
        }
        for (int k = nElems; k > i; k--) {
            a[k] = a[k-1];
        }
        a[i] = value;
        nElems++;
    }

    /**
     * Binary search impl of find method
     *
     * @param key key to find
     * @return index of found element, -1 if not found
     */
    @Override
    public int find(long key) {
        int lowBound = 0;
        int hiBound = nElems - 1;
        int index;

        while (true) {
            index = (lowBound + hiBound) / 2;
            if (a[index] == key) return index;
            else if (lowBound > hiBound) return -1;
            else {
                if (a[index] < key) lowBound = index + 1;
                else hiBound = index - 1;
            }
        }
    }

    @Override
    public boolean delete(long key) {
        int index = find(key);
        if (index < 0) return false;
        else {
            for (int i = index; i < nElems; i++) a[i] = a[i + 1];
            nElems--;
            return true;
        }
    }
}
