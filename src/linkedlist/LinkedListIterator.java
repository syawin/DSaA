package linkedlist;

public class LinkedListIterator {
    private Link curr, prev;
    private LinkedList linkedList;

    public LinkedListIterator(LinkedList linkedList) {
        this.linkedList = linkedList;
        reset();
    }

    private void reset() {
        curr = linkedList.getFirst();
        prev = null;
    }

    public boolean atEnd() {
        return curr.getNext() == null;
    }

    public void nextLink() {
        prev = curr;
        curr = curr.getNext();
    }

    public Link getCurrent() {
        return curr;
    }

    public void insertAfter(int iData, double dData) {
        Link link = new Link(iData, dData);
        if (linkedList.isEmpty()) {
            linkedList.setFirst(link);
            curr = link;
        } else {
            link.setNext(curr.getNext());
            curr.setNext(link);
            //advance pointer to newly added node
            nextLink();
        }
    }

    public void insertBefore(int iData, double dData) {
        Link link = new Link(iData, dData);
        //empty list or at beginning
        if (prev == null) {
            link.setNext(linkedList.getFirst());
            linkedList.setFirst(link);
            reset();
        } else {
            //set prev next to new link
            link.setNext(prev.getNext());
            //set new node next to current
            prev.setNext(link);
            //set current to new node
            this.curr = link;
        }
    }

    public Link deleteCurrent() {
        Link result = this.getCurrent();
        // at beginning
        if (prev == null) {
           this.linkedList.setFirst(curr.getNext());
           reset();
        } else {
            prev.setNext(curr.getNext());
            if (atEnd()) reset();
            else this.curr = curr.getNext();
        }
        return result;
    }
}
