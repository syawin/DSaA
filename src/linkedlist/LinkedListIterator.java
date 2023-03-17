package linkedlist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    private static class LinkedListIterApp {
        public static void main(String[] args) throws IOException {
            LinkedList list = new LinkedList();
            LinkedListIterator iterator = list.getIterator();
            int value;
            boolean quit = false;

            iterator.insertAfter(1, 2.2);
            iterator.insertAfter(3, 4.4);
            iterator.insertAfter(5, 6.66);
            iterator.insertAfter(7, 7.8);

            while (true) {
                System.out.print("Enter first letter of Show, Reset,\nNext, Get, Before, After, Delete: ");
                System.out.flush();
                int choice = getChar();
                switch (choice) {
                    case 's':
                        if (!list.isEmpty()) list.print();
                        else System.out.println("List is empty.");
                        break;
                    case 'r':
                        iterator.reset();
                        break;
                    case 'n':
                        if (!list.isEmpty() && !iterator.atEnd()) iterator.nextLink();
                        else System.out.println("Can't go to next link.");
                        break;
                    case 'g':
                        if (!list.isEmpty()) {
                            value = iterator.getCurrent().iData;
                            System.out.printf("Returned %d.\n", value);
                        } else System.out.println("List is empty.");
                        break;
                    case 'b':
                        System.out.print("Enter value to insert: ");
                        System.out.flush();
                        value = getInt();
                        //insert blank value for new nodes b/c I'm too lazy to impl two inputs
                        iterator.insertBefore(value, 0.0);
                        break;
                    case 'a':
                        System.out.print("Enter value to insert: ");
                        System.out.flush();
                        value = getInt();
                        //insert blank value for new nodes b/c I'm too lazy to impl two inputs
                        iterator.insertAfter(value, 0.0);
                        break;
                    case 'd':
                        if (!list.isEmpty()) {
                            value = iterator.deleteCurrent().iData;
                            System.out.printf("Deleted %d.\n", value);
                        } else System.out.println("Can't delete.");
                        break;
                    case 'q':
                        quit = true;
                        break;
                    default:
                        System.out.println("Invalid entry.");
                        break;
                } if (quit) break;
            }
        }

        static String getString() throws IOException {
            InputStreamReader streamReader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            return bufferedReader.readLine();
        }

        static char getChar() throws IOException {
            String s = getString();
            return s.charAt(0);
        }

        static int getInt() throws IOException {
            String s = getString();
            return Integer.parseInt(s);
        }
    }
}
