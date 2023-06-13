package recursion;

import java.util.Arrays;

public class MergeSort {
    private long[] array;
    private int nElems;

    public MergeSort(int maxSize) {
        this.array = new long[maxSize];
        nElems = 0;
    }

    public void insert(long value) {
        this.array[nElems] = value;
        nElems++;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.array);
    }

    public void mergeSort() {
        long[] temp = new long[nElems];
        recMergeSort(temp, 0, nElems - 1);
    }

    private void recMergeSort(long[] workspace, int lo, int hi) {
        if (lo == hi) return;
        else {
            int mid = (lo + hi) / 2;
            recMergeSort(workspace, lo, mid);
            recMergeSort(workspace, mid + 1, hi);
            merge(workspace, lo, mid + 1, hi);
        }
    }

    private void merge(long[] workspace, int loPtr, int hiPtr, int upperBound) {
        int i = 0;
        int lowerBound = loPtr;
        int mid = hiPtr - 1;
        int n = upperBound - lowerBound + 1;

        while (loPtr <= mid && hiPtr <= upperBound) {
            if (this.array[loPtr] < this.array[hiPtr])
                workspace[i++] = this.array[loPtr++];
            else
                workspace[i++] = this.array[hiPtr++];
        }

        while (loPtr <= mid)
            workspace[i++] = this.array[loPtr++];

        while (hiPtr <= upperBound)
            workspace[i++] = this.array[hiPtr++];

        for (i = 0; i < n; i++) {
            this.array[lowerBound + i] = workspace[i];
        }
    }

    public static void main(String[] args) {
        MergeSort sort = new MergeSort(100);
        sort.insert(64);
        sort.insert(21);
        sort.insert(33);
        sort.insert(70);
        sort.insert(12);
        sort.insert(85);
        sort.insert(44);
        sort.insert(3);
        sort.insert(99);
        sort.insert(0);
        sort.insert(108);
        sort.insert(36);
        System.out.println(sort);
        sort.mergeSort();
        System.out.println(sort);
    }
}
