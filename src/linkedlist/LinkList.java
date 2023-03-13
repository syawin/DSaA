package linkedlist;

public class LinkList {
    private Link first;

    public LinkList() {
        first = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insertFirst(int iData, double dData) {
        Link newLink = new Link(iData,dData);
        newLink.setNext(first);
        this.first = newLink;
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

    //assumes non-empty list
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

    static class LinkedListDemo {
        public static void main(String[] args) {
            LinkList linkList = new LinkList();

            linkList.insertFirst(22, 2.99);
            linkList.insertFirst(33, 3.81);
            linkList.insertFirst(44, 4.90);
            linkList.insertFirst(55, 1.01);

            linkList.print();

            Link found = linkList.find(44);
            if (found != null) System.out.println("Found link: " + found);
            else System.out.println("Link not found.");

            Link deleted = linkList.find(55);
            if (deleted != null) System.out.println("Deleted link: " + deleted);
            else System.out.println("Can't deleted; link not found.");

            while (!linkList.isEmpty()) {
                Link link = linkList.deleteFirst();
                System.out.println("Deleted link: " + link);
            }
            linkList.print();

            found = linkList.find(44);
            if (found != null) System.out.println("Found link: " + found);
            else System.out.println("Link not found.");
        }
    }
}
