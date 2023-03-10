// Queue insert & remove = O(1)
public class Queue {
    private final int maxSize;
    private long[] qArray;
    private int front, rear, nItems;

    public Queue(int maxSize) {
        this.maxSize = maxSize;
        qArray = new long[maxSize];
        front = nItems = 0;
        rear = -1;
    }

    public void insert(long l) {
        if (this.isFull()) return;
        if (rear == maxSize - 1)
            rear = -1;
        qArray[++rear] = l;
        nItems++;
    }

    public long remove() {
        if (this.isFull()) return 0;
        long temp = qArray[front++];
        if (front == maxSize)
            front = 0;
        nItems--;
        return temp;
    }

    public long peekFront() {
        return qArray[front];
    }

    public boolean isEmpty() {
        return nItems == 0;
    }

    public boolean isFull() {
        return nItems == maxSize;
    }

    public int size() {
        return nItems;
    }

    public long decrementFront() {
        if (this.isEmpty()) return 0;
        return --this.qArray[front];
    }

    /**
     * Programming project 4.1
     * Display the queue from front to back, skipping over empty cells
     */
    public void displayQueue() {
        int f = this.front;
        int r = this.rear;
        System.out.println("Front");
        while (this.qArray[f] != 0) {
            System.out.println(qArray[f++]);
            if (f == this.maxSize) f = 0;
            if (f == r) {
                System.out.println(qArray[f]);
                break;
            }
        }
        System.out.println("Rear");
    }
}

class QueueApp {
    public static void main(String[] args) {
        Queue queue = new Queue(5);
        queue.displayQueue();
        queue.insert(10);
        queue.insert(20);
        queue.insert(30);
        queue.insert(40);

        queue.displayQueue();

        queue.remove();
        queue.remove();
        queue.remove();

        queue.displayQueue();

        queue.insert(80);
        queue.insert(70);
        queue.insert(60);
        queue.insert(50);

        queue.displayQueue();

        while (!queue.isEmpty()) {
            System.out.print(queue.remove() + " ");
        }

        System.out.println();
    }

}

@SuppressWarnings("unused")
//Queue impl w/o item count. Complicated!
class QueueX {
    private int maxSize;
    private long[] qArray;
    private int front;
    private int rear;

    public QueueX(int maxSize) {
        this.maxSize = maxSize + 1;
        this.qArray = new long[maxSize];
        front = 0;
        rear = -1;
    }

    public void insert(long l) {
        if (rear == maxSize - 1)
            rear = -1;
        qArray[++rear] = l;
    }

    public long remove() {
        long temp = qArray[front++];
        if (front == maxSize)
            front = 0;
        return temp;
    }

    public long peek() {
        return qArray[front];
    }

    public boolean isEmpty() {
        return (rear + 1 == front) || (front + maxSize - 1 == rear);
    }

    public boolean isFull() {
        return (rear + 2 == front) || (front + maxSize - 2 == rear);
    }

    public int size() {
        if (rear >= front)
            return rear - front + 1;
        else
            return (maxSize - front) + (rear + 1);
    }
}
