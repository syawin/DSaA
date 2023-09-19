package sort;

public class Partition extends SortableArray {

    public Partition(int maxSize)
    {
        super(maxSize);
    }

    @Override
    public void sort()
    {
        long pivot = this.arr[this.size - 1];
        partitionArray(0, this.size - 1, pivot);
    }


    private static final class PartitionDemo {

        public static void main(String[] args)
        {
            Partition part = new Partition(16);
            for (int i = 0; i < part.getMaxSize(); i++) {
                part.insert(((int) (Math.random() * 199)));
            }
            System.out.println(part);
            long pivot = part.arr[part.size - 1];
            int partitionIndex = part.partitionArray(0, part.size - 1, pivot);
            System.out.println("Partition is at index " + partitionIndex);
            System.out.println(part);
        }

    }

}
