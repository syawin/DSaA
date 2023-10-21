package sort;

import java.util.Arrays;

public abstract class SortableArray {

    protected Long[] arr;
    protected int    size;

    public SortableArray(int maxSize)
    {
        this.arr = new Long[maxSize];
        this.size = 0;
    }

    public abstract void sort();

    public void insert(long val)
    {
        arr[size++] = val;
    }

    // partition algorithm has O(n) efficiency
    public int partitionArray(int left, int right, long pivot)
    {
        int leftPtr = left - 1;
        int rightPtr = right;
        while (true) {
            while (arr[++leftPtr] < pivot)
                ;
            while (rightPtr > left && arr[--rightPtr] > pivot)
                ;
            if (leftPtr >= rightPtr) {
                break;
            }
            else {
                swap(leftPtr, rightPtr);
            }
        }
        swap(leftPtr, right);
        return leftPtr;
    }

    @Override
    public String toString()
    {
        StringBuffer stringBuffer = new StringBuffer("A=");
        for (long l : arr) {
            stringBuffer.append(" [")
                        .append(l)
                        .append("]");
        }
        return stringBuffer.toString();
    }

    public void swap(int idex1, int idex2)
    {
        long temp = arr[idex1];
        arr[idex1] = arr[idex2];
        arr[idex2] = temp;
    }

    public int getSize()
    {
        return size;
    }

    public int getMaxSize()
    {
        return arr.length;
    }

    @SuppressWarnings("SlowListContainsAll")
    public boolean hasAllElems(SortableArray toCompare)
    {
        return Arrays.asList(this.arr)
                     .containsAll(Arrays.asList(toCompare.arr));
    }
}
