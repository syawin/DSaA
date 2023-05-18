package linkedlist;

public class CircleLinkedList {

    private Link curr, first, prev;
    private int size;

    public CircleLinkedList() {
        curr = first = prev = null;
        size = 0;
    }

    //creates a LinkedList pre-populated with data nodes in ascending order (from 'curr' to 'first')
    //useful for testing
    public CircleLinkedList(int size) {
        this.size = 0;
        curr = first = prev = null;
        for (int i = size; i > 0; i--) {
            insert(i,0.0);
        }
    }

    public void insert(int iData, double dData) {
        Link newLink = new Link(iData, dData);
        if (isEmpty()) {
            curr = first = prev = newLink;
            newLink.setNext(prev);
        }
        else {
            newLink.setNext(curr);
            prev.setNext(newLink);
            curr = newLink;
        }
        size++;
    }

    public void step() {
        if (!isEmpty()) {
            prev = curr;
            curr = curr.getNext();
        }
    }

    public Link delete() {
        Link result;
        if (isEmpty()) return null;
        if (size == 1) {
            result = this.curr;
            this.curr = this.first = this.prev = null;
        } else {
            result = this.curr;
            this.curr = this.curr.getNext();
            this.prev.setNext(this.curr);
            //this statement is clipping the list b/c it assumes curr always points to the last elem added
        }
        size--;
        return result;
    }

    public Link find(int key) {
        if (isEmpty()) return null;
        Link start = this.curr;
        do {
            if (start.iData == key) return start;
            start = start.next;
        } while (start != this.curr);
        return null;
    }

    public boolean isEmpty() {
        return null == curr;
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("Empty list.");
            return;
        }
        System.out.println("List (curr --> curr - 1)");
        Link current = this.curr;
        do {
            current.print();
            current = current.getNext();
        } while (current != curr);
        System.out.println();
    }

    public int getSize() {
        return size;
    }

    private static class LinkedListDemo {
        public static void main(String[] args) {
            CircleLinkedList circleLinkedList = new CircleLinkedList();

            //test empty list checks
            System.out.println(circleLinkedList.find(88));
            System.out.println(circleLinkedList.delete());

            circleLinkedList.insert(22, 2.99);

            circleLinkedList.print();

            circleLinkedList.insert(33, 3.81);

            circleLinkedList.print();

            circleLinkedList.insert(44, 4.90);

            circleLinkedList.print();

            System.out.println(circleLinkedList.find(22));

            //test key not found
            System.out.println(circleLinkedList.find(88));

            circleLinkedList.insert(55, 1.01);

            circleLinkedList.print();

            System.out.println(circleLinkedList.delete());
            System.out.println(circleLinkedList.delete());
            System.out.println(circleLinkedList.delete());
            System.out.println(circleLinkedList.delete());
        }
    }
}
