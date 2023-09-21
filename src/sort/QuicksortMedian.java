package sort;

public class QuicksortMedian extends SortableArray {

    public QuicksortMedian(int maxSize)
    {
        super(maxSize);
    }

    @Override
    public void sort()
    {
        recQuickSort(0, this.size - 1);
    }

    private void recQuickSort(int left, int right)
    {
        if (right - left + 1 <= 3) {
            manualSort(left, right);
        }
        else {
            long median = medianOf3(left, right);
            int partition = partitionIt(left, right, median);
            recQuickSort(left, partition - 1);
            recQuickSort(partition + 1, right);
        }
    }

    private int partitionIt(int left, int right, long pivot)
    {
        int leftPtr = left - 1;
        int rightPtr = right;
        while (true) {
            while (arr[++leftPtr] < pivot)
                ;
            while (arr[--rightPtr] > pivot)
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

    private long medianOf3(int left, int right)
    {
        int center = (left + right) / 2;
        if (arr[left] > arr[center]) {
            swap(left, center);
        }
        if (arr[left] > arr[right]) {
            swap(left, right);
        }
        if (arr[center] > arr[right]) {
            swap(center, right);
        }
        // put the median value at the rightmost end
        swap(center, right);
        return arr[right];
    }

    // sorts list of size <= 3 since median of 3 methodology can't handle them
    private void manualSort(int left, int right)
    {
        int size = right - left + 1;
        if (size <= 1) return;
        if (size == 2) {
            if (this.arr[left] > this.arr[right]) {
                swap(left, right);
            }
        }
        else {
            if (arr[left] > arr[right - 1]) {
                swap(left, right - 1);
            }
            if (arr[left] > arr[right]) {
                swap(left, right);
            }
            if (arr[right - 1] > arr[right]) {
                swap(right - 1, right);
            }
        }
    }

    private static final class QuickSortDemo {

        public static void main(String[] args)
        {
            int maxSize = 100;
            QuicksortMedian qs = new QuicksortMedian(maxSize);
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
