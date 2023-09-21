package sort;

public class FindMedian extends SortableArray {

    public FindMedian(int maxSize)
    {
        super(maxSize);
    }

    /**
     * Expected output is a list with its median value in the center index & the rest of the array partitioned around
     * it.
     */
    @Override
    public void sort()
    {
        recPartition(0, getSize() - 1, arr[getSize() - 1]);
    }

    // todo recPartition uses left, right, & pivot as args
    public void recPartition(int left, int right, long pivot)
    {
        if (right - left <= 0) {
            return;
        }
        else {
            int partition = partitionArray(left, right, pivot);
            if (partition == getSize() / 2) {
                return;
            }
            else if (partition > getSize() / 2) {
                recPartition(left, partition - 1, arr[partition - 1]);
            }
            else {
                recPartition(partition + 1, right, arr[right]);
            }
        }
    }

    private static final class MedianDemo {

        public static void main(String[] args)
        {
            int maxSize = 4;
            FindMedian median = new FindMedian(maxSize);
            for (int i = 0; i < maxSize; i++) {
                long n = (int) (Math.random() * 99);
                median.insert(n);
            }
            System.out.println(median);
            median.sort();
            System.out.println(median);
        }

    }

}
