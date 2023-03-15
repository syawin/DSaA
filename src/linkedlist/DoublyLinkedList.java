package linkedlist;

public class DoublyLinkedList {
    private DoubleLink first, last;

    public DoublyLinkedList() {
        first = last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insertFirst(int iData) {
        DoubleLink newLink = new DoubleLink(iData);

        if (isEmpty()) last = newLink;
        else first.setPrev(newLink);
        newLink.setNext(first);
        first = newLink;
    }

    public void insertLast(int iData) {
        DoubleLink newLink = new DoubleLink(iData);

        if (isEmpty()) first = newLink;
        else {
            last.setNext(newLink);
            newLink.setPrev(last);
        }
        last = newLink;
    }

    public DoubleLink deleteFirst() {
        if (isEmpty()) return null;

        DoubleLink temp = first;
        if (first.getNext() == null) last = null;
        else first.getNext().getPrev().setPrev(null);

        first = first.getNext();
        return temp;
    }

    public DoubleLink deleteLast() {
        if (isEmpty()) return null;

        DoubleLink temp = last;
        if (first.getNext() == null) first = null;
        else last.getPrev().setNext(null);

        last = last.getPrev();
        return temp;
    }

    public boolean insertAfter(int key, int iData) {
        //return false if list is empty to avoid NPE
        if (isEmpty()) return false;
        DoubleLink current = first;
        while (current.getiData() != key) {
            //advance search node
            current = current.getNext();
            //at end of list, node not found
            if (current == null) return false;
        }
        DoubleLink newLink = new DoubleLink(iData);
        //if current is last node
        if (current == last) {
            newLink.setNext(null);
            last = newLink;
        //current node is not last node
        } else {
            //set new node next to be current next node
            newLink.setNext(current.getNext());
            current.getNext().setPrev(newLink);
        }
        newLink.setPrev(current);
        current.setNext(newLink);
        return true;
    }

    public DoubleLink deleteKey(int key) {
        //reutrn null if list is empty to prevent NPE
        if (isEmpty()) return null;
        DoubleLink current = first;
        while (current.getiData() != key) {
            //advance current node
            current = current.getNext();
            //not found
            if (current == null) return null;
        }
        DoubleLink temp = current;

        //set next node value
        if (current == first) first = current.getNext();
        else current.getPrev().setNext(current.getNext());

        //set prev node value
        if (current == last) last = current.getPrev();
        else current.getNext().setPrev(current.getPrev());
        return current;
    }

    public void printForward() {
        System.out.print("List (first --> last): ");
        DoubleLink current = this.first;
        while (current != null) {
            current.print();
            current = current.getNext();
        }
        System.out.println();
    }

    public void printBackward() {
        System.out.print("List (last --> first): ");
        DoubleLink current = this.last;
        while (current != null) {
            current.print();
            current = current.getPrev();
        }
        System.out.println();
    }

    private static class DoublyLinkedApp {
        public static void main(String[] args) {
            DoublyLinkedList linkedList = new DoublyLinkedList();

            linkedList.insertFirst(22);
            linkedList.insertFirst(44);
            linkedList.insertFirst(33);
            linkedList.insertLast(13);
            linkedList.insertLast(10);
            linkedList.insertLast(88);

            linkedList.printForward();
            linkedList.printBackward();

            linkedList.deleteFirst();
            linkedList.deleteLast();
            linkedList.deleteKey(13);

            linkedList.printForward();

            linkedList.insertAfter(22,17);
            linkedList.insertAfter(17,99);

            linkedList.printForward();
        }
    }
}
