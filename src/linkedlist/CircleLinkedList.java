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

    public void step() {
        curr = curr.getNext();
    }

    public Link delete() {
        Link result;
        if (isEmpty()) return null;
        if (this.curr == this.curr.getNext()) {
            result = this.curr;
            this.curr = null;
        } else {
            result = this.curr.getNext();
            this.curr.setNext(result.getNext());
        }
        return result;
    }

    public Link find(int key) {
        if (isEmpty()) return null;
        Link start = this.curr;
        do {
            if (this.curr.iData == key) return this.curr;
            this.step();
        } while (this.curr != start);
        return null;
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

            //test not found
            System.out.println(circleLinkedList.find(88));

            circleLinkedList.insert(55, 1.01);

            circleLinkedList.print();

            System.out.println(circleLinkedList.delete());

            circleLinkedList.print();

        }
    }
}
