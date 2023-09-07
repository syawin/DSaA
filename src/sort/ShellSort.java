package sort;

import java.util.concurrent.TimeUnit;

public class ShellSort extends SortableArray {

    public ShellSort(int maxSize)
    {
        super(maxSize);
    }

    public void shellSort()
    {
        int inner, outer;
        long temp;

        int h = 1;
        while (h <= size / 3) {
            h = h * 3 + 1;
        }
        while (h > 0) {
            // iterate thru the list 1-by-1 starting at H
            for (outer = h; outer < size; outer++) {
                // store the value at Outer in temp
                temp = arr[outer];
                // start the Inner counter at the pos of Outer
                inner = outer;

                // this inner loop runs backwards
                // runs backwards so long as there is a next backwards h-step & that the backwards h-step element is
                // greater than the object to compare in temp.
                while (inner > h - 1 && arr[inner - h] >= temp) {
                    // if backwards h-step element is the bigger one, move it up into the place temp occupied
                    arr[inner] = arr[inner - h];
                    // decrement by h-step
                    inner -= h;
                }
                // place the stored temp value at the spot Inner ended at as we've finished sorting this round
                arr[inner] = temp;
            }
            h = (h - 1) / 3;
        }
    }

    private static final class ShellSortDemo {

        public static void main(String[] args)
        {
            int size = 1001000;
            ShellSort sort = new ShellSort(size);
            for (int i = 0; i < size; i++) {
                sort.insert(((int) (Math.random() * 99)));
            }
            System.out.println(sort);
            long start = System.nanoTime();
            sort.shellSort();
            long stop = System.nanoTime();
            long timeInMilli = TimeUnit.MILLISECONDS.convert(stop - start, TimeUnit.NANOSECONDS);
            System.out.println(sort);
            System.out.printf("Sort took %dms", timeInMilli);
        }

    }

}
