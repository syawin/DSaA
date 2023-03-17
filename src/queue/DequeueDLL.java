package queue;

import linkedlist.DoubleLink;
import linkedlist.DoublyLinkedList;

public class DequeueDLL {
    private final DoublyLinkedList dll;

    public DequeueDLL() {
        this.dll = new DoublyLinkedList();
    }

    public boolean isEmpty() {
        return this.dll.isEmpty();
    }

    public void insertLeft(int iData) {
        this.dll.insertLeft(iData);
    }

    public void insertRight(int iData) {
        this.dll.insertRight(iData);
    }

    public DoubleLink removeLeft() {
        return this.dll.deleteLeft();
    }

    public DoubleLink removeRight() {
        return this.dll.deleteRight();
    }

    public void print() {
        this.dll.printLtoR();
    }

    private static class DequeueDLLDemo {
        public static void main(String[] args) {
            DequeueDLL dequeue = new DequeueDLL();

            dequeue.insertLeft(1);
            dequeue.insertRight(2);
            dequeue.insertLeft(-1);
            dequeue.insertRight(3);
            dequeue.insertRight(5);
            dequeue.insertRight(6);

            dequeue.print();

            System.out.println(dequeue.removeLeft());
            System.out.println(dequeue.removeLeft());
            System.out.println(dequeue.removeLeft());
            System.out.println(dequeue.removeLeft());
            System.out.println(dequeue.removeLeft());
            System.out.println(dequeue.removeLeft());

            dequeue.print();
        }
    }
}
