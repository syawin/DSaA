package queue;

/**
 * Programming Project 4.2
 * Implement a dequeue
 */
public class Dequeue {

    private final long[] qArray;

    private int nItems, left, right;

    private final int maxSize;
    public Dequeue(int maxSize) {
        this.maxSize = maxSize;
        this.nItems = 0;
        this.left = this.right = -1;
        this.qArray = new long[maxSize];
    }

    public boolean isEmpty() {
        return nItems == 0;
    }

    public boolean isFull() {
        return nItems == maxSize;
    }

    public void insertLeft(long l){
        if (this.isFull()) return;
        if (left == -1) left = maxSize;
        qArray[--left] = l;
        nItems++;
    }

    public void insertRight(long l) {
        if (this.isFull()) return;
        if (right == maxSize) right = -1;
        qArray[++right] = l;
        nItems++;
    }

    public long removeLeft() {
        if (this.isEmpty()) return 0;
        long temp = qArray[left++];
        if (left == maxSize) left = 0;

        nItems--;
        return temp;
    }

    public long removeRight() {
        if (this.isEmpty()) return 0;
        long temp = qArray[right--];
        if (right == -1) right = maxSize - 1;

        nItems--;
        return temp;
    }

    public int size() {
        return nItems;
    }
}

class DequeueApp {
    public static void main(String[] args) {
            Dequeue dequeue = new Dequeue(5);
            dequeue.insertLeft(1);
            dequeue.insertRight(2);
            dequeue.insertLeft(-1);
            dequeue.insertRight(3);
            dequeue.insertRight(5);
            dequeue.insertRight(6);

            System.out.println(dequeue.removeLeft());
            System.out.println(dequeue.removeLeft());
            System.out.println(dequeue.removeLeft());
            System.out.println(dequeue.removeLeft());
            System.out.println(dequeue.removeLeft());
            System.out.println(dequeue.removeLeft());
    }
}
