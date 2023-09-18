package sort;

public abstract class SortableArray {

    protected long[] arr;
    protected int    size;

    public SortableArray(int maxSize)
    {
        this.arr  = new long[maxSize];
        this.size = 0;
    }

    public abstract void sort();

    public void insert(long val)
    {
        arr[size++] = val;
    }

    @Override
    public String toString()
    {
        StringBuffer stringBuffer = new StringBuffer("A=");
        for (long l : arr) {
            stringBuffer.append(" [")
                        .append(l)
                        .append("]");
        }
        return stringBuffer.toString();
    }

    public void swap(int idex1, int idex2)
    {
        long temp = arr[idex1];
        arr[idex1] = arr[idex2];
        arr[idex2] = temp;
    }

    public int getSize()
    {
        return size;
    }

    public int getMaxSize()
    {
        return arr.length;
    }

}
