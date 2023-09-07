package sort;

public class Partition extends SortableArray {

    public Partition(int maxSize)
    {
        super(maxSize);
    }

    // partition algorithm has O(n) efficiency
    public int partitionArray(int left, int right, long pivot)
    {
        int leftPtr = left - 1;
        int rightPtr = right + 1;
        while (true) {
            // avg N+1 or N+2 comparisons
            while (leftPtr < right && arr[++leftPtr] < pivot)
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
        return leftPtr;
    }

    // avg N/2 swaps
    public void swap(int idex1, int idex2)
    {
        long temp = arr[idex1];
        arr[idex1] = arr[idex2];
        arr[idex2] = temp;
    }

    private static final class PartitionDemo {

        public static void main(String[] args)
        {
            Partition part = new Partition(16);
            for (int i = 0; i < part.getMaxSize(); i++) {
                part.insert(((int) (Math.random() * 199)));
            }
            System.out.println(part);
            int partitionIndex = part.partitionArray(0, part.getMaxSize() - 1, 99);
            System.out.println("Partition is at index " + partitionIndex);
            System.out.println(part);
        }

    }

}
