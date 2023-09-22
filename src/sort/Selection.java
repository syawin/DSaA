package sort;

public class Selection extends SortableArray {

    public Selection(int maxSize)
    {
        super(maxSize);
    }

    @Override
    public void sort()
    {
        // note that this code is exactly the same as the code used in sort.FindMedian, just edited to use an
        // arbitrary index rather than the center index (aka median)
        int dex = getSize() - 1;
        recPartition(0, getSize() - 1, arr[getSize() - 1], dex);
    }

    public void recPartition(int left, int right, long pivot, int selectDex)
    {
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

    private static final class SelectionDemo {

        public static void main(String[] args)
        {
            int maxSize = 15;
            Selection selection = new Selection(maxSize);
            for (int i = 0; i < maxSize; i++) {
                long n = (int) (Math.random() * 99);
                selection.insert(n);
            }
            System.out.println(selection);
            selection.sort();
            System.out.println(selection);
        }

    }

}
