package sort;

public class SortableArray {

    protected long[] arr;
    protected int    size;

    public SortableArray(int maxSize)
    {
        this.arr  = new long[maxSize];
        this.size = 0;
    }

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

    public int getSize()
    {
        return size;
    }

    public int getMaxSize()
    {
        return arr.length;
    }

}
