package queue;

import linkedlist.Link;
import linkedlist.LinkedList;
import linkedlist.LinkedListIterator;

public class PriorityQSortedLL {

    private final LinkedList linkedList;

    public PriorityQSortedLL() {
        this.linkedList = new LinkedList();
    }

    public void insert(int iData, double dData) {
        linkedList.insertInOrder(iData, dData);
    }

    public Link remove() {
        return linkedList.deleteFirst();
    }

    public Link peekMin() {
        return linkedList.getFirst();
    }

    public Link peekMax() {
        LinkedListIterator iterator = this.linkedList.getIterator();
        while (!iterator.atEnd()) {
            iterator.nextLink();
        }
        return iterator.getCurrent();
    }

    public void print() {
        this.linkedList.print();
    }

    public boolean isEmpty() {
        return this.linkedList.isEmpty();
    }

    private static class PriorityQSortedLLDemo {
        public static void main(String[] args) {
            PriorityQSortedLL pq = new PriorityQSortedLL();
            pq.insert(3, 1.1);
            pq.insert(2, 1.1);

            pq.print();

            pq.insert(1, 1.1);
            pq.insert(4, 1.1);

            pq.print();

            System.out.println(pq.peekMax());
            System.out.println(pq.peekMin());

            System.out.println(pq.remove());

            pq.print();
        }
    }
}
