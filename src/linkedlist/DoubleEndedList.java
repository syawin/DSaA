package linkedlist;

public class DoubleEndedList {
    private Link first, last;

    public DoubleEndedList() {
        this.first = null;
        this.last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insertFirst(int iData, double dData) {
        Link link = new Link(iData, dData);
        if (isEmpty()) this.last = link;
        link.setNext(first);
        this.first = link;
    }

    public void insertLast(int iData, double dData) {
        Link link = new Link(iData, dData);
        if (isEmpty()) this.first = link;
        //else is here to prevent NPE
        else last.setNext(link);
        this.last = link;
    }

    public Link deleteFirst() {
        if (isEmpty()) return null;
        Link temp = this.first;
        if (!first.hasNext()) {
            last = null;
        }
        first = first.getNext();
        return temp;
    }

    public void print() {
        System.out.println("List (first --> last)");
        Link current = this.first;
        while (current != null) {
            current.print();
            current = current.getNext();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DoubleEndedList list = new DoubleEndedList();
        list.insertFirst(1, 22.2);
        list.insertFirst(3, 1.23);
        list.insertFirst(55, 66.0);

        list.insertLast(10, 1.01);
        list.insertLast(22, 66.6);
        list.insertLast(69, 3.33);

        list.print();

        list.deleteFirst();
        list.deleteFirst();

        list.print();
    }
}
