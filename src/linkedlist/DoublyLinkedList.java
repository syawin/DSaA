package linkedlist;

public class DoublyLinkedList {
    private DoubleLink left, right;

    public DoublyLinkedList() {
        left = right = null;
    }

    public boolean isEmpty() {
        return left == null;
    }

    public void insertLeft(int iData) {
        DoubleLink newLink = new DoubleLink(iData);

        if (isEmpty()) right = newLink;
        else left.setPrev(newLink);
        newLink.setNext(left);
        left = newLink;
    }

    public void insertRight(int iData) {
        DoubleLink newLink = new DoubleLink(iData);

        if (isEmpty()) left = newLink;
        else {
            right.setNext(newLink);
            newLink.setPrev(right);
        }
        right = newLink;
    }

    public DoubleLink deleteLeft() {
        if (isEmpty()) return null;

        DoubleLink temp = left;
        if (left.getNext() == null) right = null;
        else left.getNext().getPrev().setPrev(null);

        left = left.getNext();
        return temp;
    }

    public DoubleLink deleteRight() {
        if (isEmpty()) return null;

        DoubleLink temp = right;
        if (left.getNext() == null) left = null;
        else right.getPrev().setNext(null);

        right = right.getPrev();
        return temp;
    }

    public boolean insertAfter(int key, int iData) {
        //return false if list is empty to avoid NPE
        if (isEmpty()) return false;
        DoubleLink current = left;
        while (current.getiData() != key) {
            //advance search node
            current = current.getNext();
            //at end of list, node not found
            if (current == null) return false;
        }
        DoubleLink newLink = new DoubleLink(iData);
        //if current is last node
        if (current == right) {
            newLink.setNext(null);
            right = newLink;
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
        DoubleLink current = left;
        while (current.getiData() != key) {
            //advance current node
            current = current.getNext();
            //not found
            if (current == null) return null;
        }
        DoubleLink temp = current;

        //set next node value
        if (current == left) left = current.getNext();
        else current.getPrev().setNext(current.getNext());

        //set prev node value
        if (current == right) right = current.getPrev();
        else current.getNext().setPrev(current.getPrev());
        return current;
    }

    public void printForward() {
        System.out.print("List (left  --> right): ");
        DoubleLink current = this.left;
        while (current != null) {
            current.print();
            current = current.getNext();
        }
        System.out.println();
    }

    public void printBackward() {
        System.out.print("List (right --> left): ");
        DoubleLink current = this.right;
        while (current != null) {
            current.print();
            current = current.getPrev();
        }
        System.out.println();
    }

    private static class DoublyLinkedApp {
        public static void main(String[] args) {
            DoublyLinkedList linkedList = new DoublyLinkedList();

            linkedList.insertLeft(22);
            linkedList.insertLeft(44);
            linkedList.insertLeft(33);
            linkedList.insertRight(13);
            linkedList.insertRight(10);
            linkedList.insertRight(88);

            linkedList.printForward();
            linkedList.printBackward();

            linkedList.deleteLeft();
            linkedList.deleteRight();
            linkedList.deleteKey(13);

            linkedList.printForward();

            linkedList.insertAfter(22,17);
            linkedList.insertAfter(17,99);

            linkedList.printForward();
        }
    }
}
