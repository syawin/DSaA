package sort;

public class InsSort extends SortableArray {

    public InsSort(int maxSize)
    {
        super(maxSize);
    }

    @Override
    public void sort()
    {
        for (int i = 1; i < arr.length; i++) {
            long temp = arr[i];
            int j = i;
            while (j > 0 && temp < arr[j - 1]) {
                j--;
            }
            for (int k = i; k > j; k--) {
                arr[k] = arr[k - 1];
            }
            arr[j] = temp;
        }
    }

    private static final class InsSortDemo {

        public static void main(String[] args)
        {
            int maxSize = 16;
            InsSort is = new InsSort(maxSize);
            for (int i = 0; i < maxSize; i++) {
                long n = (int) (Math.random() * 99);
                is.insert(n);
            }
            System.out.println(is);
            is.sort();
            System.out.println(is);
        }

    }

}
