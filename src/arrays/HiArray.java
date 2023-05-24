package arrays;

import java.util.Arrays;

public class HiArray {
    private long[] a;
    private int nElems;

    public HiArray(int max) {
        a = new long[max];
        nElems = 0;
    }

    /**
     * Searches array for provided key by performing a simple iteration over all array elements.
     * @param searchKey key to find
     * @return true if key found, false if not found
     */
    public boolean iterFind(long searchKey) {
        int i;
        for (i = 0; i < nElems; i++) if (a[i] == searchKey) break;
        return i != nElems;
    }

    public void insert(long value) {
        a[nElems++] = value;
    }

    public boolean delete(long value) {
        int i;
        for (i = 0; i < nElems; i++) {
            if (value == a[i]) break;
        }
        if (i==nElems) {
            return false;
        } else {
            for (int j = i; j < nElems; j++) {
                a[j] = a[j+1];
            }
            nElems--;
            return true;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(a);
    }
}
