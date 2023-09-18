package sort;

public class QuicksortInsert extends SortableArray {

    public QuicksortInsert(int maxSize)
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
        int size = right - left + 1;
        if (size <= 10) {
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
        int leftPtr = left;
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

    // Sort lists of size 10 or less using insertion sort since it is fast enough for short lists
    private void manualSort(int left, int right)
    {
        for (int i = left + 1; i <= right; i++) {
            long temp = arr[i];
            int h = i;
            while (h > left && temp < arr[h - 1]) {
                arr[h] = arr[h - 1];
                h--;
            }
            arr[h] = temp;
        }
    }

    private static final class QuickSortDemo {

        public static void main(String[] args)
        {
            int maxSize = 100;
            QuicksortInsert qs = new QuicksortInsert(maxSize);
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
