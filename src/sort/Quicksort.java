package sort;

public class Quicksort extends SortableArray {

    public Quicksort(int maxSize)
    {
        super(maxSize);
    }

    @Override
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
            int partition = partitionArray(left, right, pivot);
            recQuickSort(left, partition - 1);
            recQuickSort(partition + 1, right);
        }
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
