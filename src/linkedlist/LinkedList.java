package linkedlist;

public class LinkedList {
    private Link first;

    public LinkedList() {
        first = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insertFirst(int iData, double dData) {
        Link newLink = new Link(iData, dData);
        newLink.setNext(first);
        this.first = newLink;
    }

    public void insertInOrder(int iData, double dData) {
        Link newLink = new Link(iData, dData);
        Link previous = null;
        Link current = this.first;

        while (current!=null && iData > current.iData) {
            previous = current;
            current = current.getNext();
        }
        //list is empty, insert at beginning
        if (previous == null) first = newLink;
        //list is not empty, inserting at middle or end
        else previous.setNext(newLink);
        //point new link next ref to the current link
        newLink.setNext(current);
    }

    public Link find(int key) {
        if (isEmpty()) return null;
        Link current = this.first;
        while (current.iData != key) {
            //continue to next link
            if (current.hasNext()) current = current.getNext();
                //key not found
            else return null;
        }
        return current;
    }

    public Link delete(int key) {
        if (isEmpty()) return null;
        Link current, previous;
        current = previous = this.first;
        while (current.iData != key) {
            //not found
            if (!current.hasNext()) return null;
            else {
                //next link
                previous = current;
                current = current.getNext();
            }
        }
        //link to delete is first link
        if (current == first) first = first.getNext();
            //remove current by setting prev.next to curr.next
        else previous.setNext(current.getNext());
        return current;
    }

    public Link deleteFirst() {
        if (isEmpty()) return null;
        Link temp = this.first;
        this.first = this.first.getNext();
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

    private static class LinkedListDemo {
        public static void main(String[] args) {
            LinkedList linkedList = new LinkedList();

            linkedList.insertFirst(22, 2.99);
            linkedList.insertFirst(33, 3.81);
            linkedList.insertFirst(44, 4.90);
            linkedList.insertFirst(55, 1.01);

            linkedList.print();

            Link found = linkedList.find(44);
            if (found != null) System.out.println("Found link: " + found);
            else System.out.println("Link not found.");

            Link deleted = linkedList.find(55);
            if (deleted != null) System.out.println("Deleted link: " + deleted);
            else System.out.println("Can't deleted; link not found.");

            while (!linkedList.isEmpty()) {
                Link link = linkedList.deleteFirst();
                System.out.println("Deleted link: " + link);
            }
            linkedList.print();

            found = linkedList.find(44);
            if (found != null) System.out.println("Found link: " + found);
            else System.out.println("Link not found.");
        }
    }

    private static class SortedListDemo {
        public static void main(String[] args) {
            LinkedList linkedList = new LinkedList();

            linkedList.insertInOrder(33, 3.81);
            linkedList.insertInOrder(22, 2.99);

            linkedList.print();

            linkedList.insertInOrder(55, 1.01);
            linkedList.insertInOrder(44, 4.90);

            linkedList.print();

            linkedList.deleteFirst();

            linkedList.print();
        }
    }
}
