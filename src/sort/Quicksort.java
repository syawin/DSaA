package sort;

public class Quicksort extends SortableArray {

    public Quicksort(int maxSize)
    {
        super(maxSize);
    }

    public void sort()
    {
        recQuickSort(0, this.size - 1);
    }

    // About O(log2N) space complexity
    // Time complexity O(N*log2N): O(N) partition * O(log2N) recursive layers
    // Big O degenerates to O(N^2) when input is inversely sorted b/c pivot is meant to be close to median value of
    // the list. By picking the pivot to be the rightmost value of the array we will keep partitioning & sorting the
    // list into sublists of size (n-1) & 1. Thus we are not getting any of the benefits of splitting the list.
    public void recQuickSort(int left, int right)
    {
        if (right - left <= 0) {
            return;
        }
        else {
            long pivot = this.arr[right];
            int partition = partitionIt(left, right, pivot);
            recQuickSort(left, partition - 1);
            recQuickSort(partition + 1, right);
        }
    }

    // Time complexity O(N)
    public int partitionIt(int left, int right, long pivot)
    {
        int leftPtr = left - 1;
        int rightPtr = right;
        while (true) {
            while (arr[++leftPtr] < pivot)
                ;
            while (rightPtr > 0 && arr[--rightPtr] > pivot)
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

    public void swap(int idex1, int idex2)
    {
        long temp = arr[idex1];
        arr[idex1] = arr[idex2];
        arr[idex2] = temp;
    }

    private static final class QuickSortDemo {

        public static void main(String[] args)
        {
            int maxSize = 16;
            Quicksort qs = new Quicksort(maxSize);
            for (int i = 0; i < maxSize; i++) {
                long n = (int) (Math.random() * 99);
                qs.insert(n);
            }
            System.out.println(qs);
            qs.sort();
            System.out.println(qs);
        }

    }

}
