package linkedlist;

public class LinkQueue {
    private DoubleEndedList list;

    public LinkQueue() {
        this.list = new DoubleEndedList();
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public void insert(int iData, double dData) {
        this.list.insertLast(iData, dData);
    }

    public Link remove() {
        return this.list.deleteFirst();
    }

    public void print() {
        this.list.print();
    }

    private static class LinkQueueApp {
        public static void main(String[] args) {
            LinkQueue queue = new LinkQueue();
            queue.insert(1,1.0);
            queue.insert(2,80.9);

            queue.print();

            queue.insert(3,1.01);
            queue.insert(4,0.03);

            queue.print();

            queue.remove();
            queue.remove();

            queue.print();
        }
    }
}
