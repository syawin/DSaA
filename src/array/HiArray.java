package array;

import java.util.Arrays;

public class HiArray {
    long[] a;
    int nElems;

    public HiArray(int max) {
        a = new long[max];
        nElems = 0;
    }

    /**
     * Searches array for provided key by performing a simple iteration over all array elements.
     *
     * @param key key to find
     * @return index of found element, -1 if not found
     */
    public int find(long key) {
        int i;
        for (i = 0; i < nElems; i++) if (a[i] == key) break;
        return i != nElems ? i : -1;
    }

    public void insert(long value) {
        a[nElems++] = value;
    }

    public boolean delete(long key) {
        int i;
        for (i = 0; i < nElems; i++) {
            if (key == a[i]) break;
        }
        if (i == nElems) {
            return false;
        } else {
            for (int j = i; j < nElems; j++) {
                a[j] = a[j + 1];
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
