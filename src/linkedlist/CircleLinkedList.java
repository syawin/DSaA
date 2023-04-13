package linkedlist;

public class CircleLinkedList {

    private Link curr;

    public CircleLinkedList() {
        curr = null;
    }

    public void insert(int iData, double dData) {
        Link newLink = new Link(iData, dData);
        if (isEmpty()) {
            curr = newLink;
            newLink.setNext(curr);
        }
        else {
            newLink.setNext(curr.getNext());
            curr.setNext(newLink);
            curr = newLink;
        }
    }

    public boolean isEmpty() {
        return null == curr;
    }

    public void print() {
        System.out.println("List (curr --> curr - 1)");
        Link current = this.curr;
        do {
            current.print();
            current = current.getNext();
        } while (current != curr);
        System.out.println();
    }

    private static class LinkedListDemo {
        public static void main(String[] args) {
            CircleLinkedList circleLinkedList = new CircleLinkedList();

            circleLinkedList.insert(22, 2.99);

            circleLinkedList.print();

            circleLinkedList.insert(33, 3.81);

            circleLinkedList.print();

            circleLinkedList.insert(44, 4.90);

            circleLinkedList.print();

            circleLinkedList.insert(55, 1.01);

            circleLinkedList.print();

        }
    }
}
