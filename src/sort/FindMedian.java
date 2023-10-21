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
        int dex = getSize() / 2;
        recPartition(0, getSize() - 1, arr[getSize() - 1], dex);
    }

    public void recPartition(int left, int right, long pivot, int selectDex)
    {
        // noinspection DuplicatedCode
        if (right - left <= 0) {
            return;
        }
        else {
            int partition = partitionArray(left, right, pivot);
            if (partition == selectDex) {
                return;
            }
            else if (partition > selectDex) {
                recPartition(left, partition - 1, arr[partition - 1], selectDex);
            }
            else {
                recPartition(partition + 1, right, arr[right], selectDex);
            }
        }
    }

    private static final class MedianDemo {

        public static void main(String[] args)
        {
            int maxSize = 15;
            FindMedian median = new FindMedian(maxSize);
            FindMedian clone = new FindMedian(maxSize);
            for (int i = 0; i < maxSize; i++) {
                long n = (int) (Math.random() * 99);
                median.insert(n);
                clone.insert(n);
            }
            System.out.println(median);
            median.sort();
            assert median.hasAllElems(clone);
            System.out.println(median);
        }

    }

}
