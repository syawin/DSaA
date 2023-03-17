package queue;

public class PriorityQ {
    private final int maxSize;
    private long[] qArray;
    private int nItems;

    public PriorityQ(int maxSize) {
        this.maxSize = maxSize;
        qArray = new long[maxSize];
        nItems = 0;
    }

    //Complex, expensive insert
    //O(n)
    public void insert(long item) {
        int i;

        if (nItems == 0)
            qArray[nItems++] = item;
        else {
            for (i = nItems - 1; i >= 0; i--) {
                if (item > qArray[i])
                    qArray[i + 1] = qArray[i];
                else
                    break;
            }
            qArray[i + 1] = item;
            nItems++;
        }
    }

    //remove minimum item
    //inexpensive, simple removal
    //O(1)
    public long remove() {
        return qArray[--nItems];
    }

    public long peekMin() {
        return qArray[nItems - 1];
    }

    public long peekMax() {
        return qArray[0];
    }

    public boolean isEmpty() {
        return nItems == 0;
    }

    public boolean isFull() {
        return nItems == maxSize;
    }
}

class PriorityQFastInsert {
    private final int maxSize;
    private long[] qArray;
    private int nItems;

    public PriorityQFastInsert(int maxSize) {
        this.maxSize = maxSize;
        qArray = new long[maxSize];
        nItems = 0;
    }

    /**
     * Fast insert
     * O(1)
     * @param l item to be inserted
     */
    public void insert(long l) {
        if (this.isFull()) return;
        qArray[nItems++] = l;
    }

    /**
     * Slow remove
     * O(n) for find least + shift operation (another O(n))
     * @return next smallest item in sequence
     */
    public long remove() {
        if (this.isEmpty()) return -1;
        long temp = qArray[--nItems];
        long smallestIndex = nItems;
        for (int index = nItems; index > -1; index--)
            if (temp > qArray[index]) {
                temp = qArray[index];
                smallestIndex = index;
            }
        this.shift(smallestIndex);
        return temp;
    }

    private void shift(final long shiftTo) {
        int index = (int) shiftTo;
        while (index < this.maxSize - 1) {
            qArray[index++] = qArray[index];
        }
    }

    public boolean isEmpty() {
        return nItems == 0;
    }

    public boolean isFull() {
        return nItems == maxSize;
    }
}

class PriorityQApp {
    public static void main(String[] args) {
        PriorityQFastInsert priorityQ = new PriorityQFastInsert(5);
        priorityQ.insert(30);
        priorityQ.insert(50);
        priorityQ.insert(10);
        priorityQ.insert(60);
        priorityQ.insert(5);

        while (!priorityQ.isEmpty()) {
            long item = priorityQ.remove();
            System.out.print(item + " ");
        }
        //testing that fast insert PQ can be reused (essentially testing impl of shift operation)
        priorityQ.insert(4);
        while (!priorityQ.isEmpty()) {
            long item = priorityQ.remove();
            System.out.print(item + " ");
        }
        System.out.println();
    }
}
