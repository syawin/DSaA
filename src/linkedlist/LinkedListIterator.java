package linkedlist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListIterator implements Iterator<Link> {
    
    private final LinkedList linkedList;
    private boolean canRemove;
    private Link    curr, prev;
    
    public LinkedListIterator(LinkedList linkedList)
    {
        this.linkedList = linkedList;
        reset();
        canRemove = false;
    }
    
    // getter
    public Link getCurrent()
    {
        return curr;
    }
    // getter end
    
    public boolean atEnd()
    {
        return curr == null || curr.getNext() == null;
    }
    
    public Link deleteCurrent()
    {
        if (curr == null) {
            throw new IllegalStateException("Cannot delete: iterator is at end of list");
        }
        
        Link result = curr;
        // at beginning
        if (prev == null) {
            this.linkedList.setFirst(curr.getNext());
            reset();
        }
        else {
            prev.setNext(curr.getNext());
            if (atEnd()) { reset(); }
            else { this.curr = curr.getNext(); }
        }
        return result;
    }
    
    @Override
    public boolean hasNext()
    {
        return curr != null;
    }
    
    public void insertAfter(long iData, double dData)
    {
        Link link = new Link(iData, dData);
        if (linkedList.isEmpty()) {
            linkedList.setFirst(link);
            curr = link;
        }
        else {
            link.setNext(curr.getNext());
            curr.setNext(link);
            // advance pointer to newly added node
            nextLink();
        }
    }
    
    public void insertBefore(long iData, double dData)
    {
        Link link = new Link(iData, dData);
        // empty list or at beginning
        if (prev == null) {
            link.setNext(linkedList.getFirst());
            linkedList.setFirst(link);
            reset();
        }
        else {
            // set prev next to new link
            link.setNext(prev.getNext());
            // set new node next to current
            prev.setNext(link);
            // set current to new node
            this.curr = link;
        }
    }
    
    @Override
    public Link next()
    {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Link current = curr;
        prev      = curr;
        curr      = curr.getNext();
        canRemove = true;
        return current;
    }
    
    public void nextLink()
    {
        prev = curr;
        curr = curr.getNext();
    }
    
    @Override
    public void remove()
    {
        if (!canRemove) {
            throw new IllegalStateException(
                    "Cannot remove element: next() has not been called or element already removed");
        }
        
        /*
         * Pointer situation right now:
         *   prev  -> element returned by the most recent next()
         *   curr  -> element that will be returned by the next call to next() (may be null)
         *
         * We must unlink `prev` from the underlying list.
         */
        
        // Case 1 – deleting the first node
        if (linkedList.getFirst() == prev) {
            linkedList.setFirst(curr);
            prev = null;                // iterator is now positioned *before* the new first node
        }
        else {
            // Locate the node that precedes `prev`
            Link beforePrev = linkedList.getFirst();
            while (beforePrev != null && beforePrev.getNext() != prev) {
                beforePrev = beforePrev.getNext();
            }
            if (beforePrev == null) {
                throw new IllegalStateException("Iterator is in an inconsistent state");
            }
            // Bypass `prev`
            beforePrev.setNext(curr);
            // Re-position `prev` one node back so the iterator can continue seamlessly
            prev = beforePrev;
        }
        
        canRemove = false;              // forbid consecutive remove() calls without next()
    }
    
    public void reset()
    {
        curr      = linkedList.getFirst();
        prev      = null;
        canRemove = false;
    }
    
    private static class LinkedListIterApp {
        
        // getter
        // getter end
        
        public static void main(String[] args) throws IOException
        {
            LinkedList         list     = new LinkedList();
            LinkedListIterator iterator = list.getIterator();
            long               value;
            boolean            quit     = false;
            
            iterator.insertAfter(1, 2.2);
            iterator.insertAfter(3, 4.4);
            iterator.insertAfter(5, 6.66);
            iterator.insertAfter(7, 7.8);
            
            do {
                System.out.print(
                        "Enter first letter of Show, Reset,\nNext, Get, Before, After, Delete: ");
                System.out.flush();
                int choice = getChar();
                switch (choice) {
                    case 's':
                        if (!list.isEmpty()) { list.print(); }
                        else { System.out.println("List is empty."); }
                        break;
                    case 'r':
                        iterator.reset();
                        break;
                    case 'n':
                        if (!list.isEmpty() && !iterator.atEnd()) { iterator.nextLink(); }
                        else { System.out.println("Can't go to next link."); }
                        break;
                    case 'g':
                        if (!list.isEmpty()) {
                            value = iterator.getCurrent().key;
                            System.out.printf("Returned %d.\n", value);
                        }
                        else { System.out.println("List is empty."); }
                        break;
                    case 'b':
                        System.out.print("Enter value to insert: ");
                        System.out.flush();
                        value = getInt();
                        // insert blank value for new nodes b/c I'm too lazy to impl two inputs
                        iterator.insertBefore(value, 0.0);
                        break;
                    case 'a':
                        System.out.print("Enter value to insert: ");
                        System.out.flush();
                        value = getInt();
                        // insert blank value for new nodes b/c I'm too lazy to impl two inputs
                        iterator.insertAfter(value, 0.0);
                        break;
                    case 'd':
                        if (!list.isEmpty()) {
                            value = iterator.deleteCurrent().key;
                            System.out.printf("Deleted %d.\n", value);
                        }
                        else { System.out.println("Can't delete."); }
                        break;
                    case 'q':
                        quit = true;
                        break;
                    default:
                        System.out.println("Invalid entry.");
                        break;
                }
            }
            while (!quit);
        }
        
        static char getChar() throws IOException
        {
            String s = getString();
            return s.charAt(0);
        }
        
        static int getInt() throws IOException
        {
            String s = getString();
            return Integer.parseInt(s);
        }
        
        static String getString() throws IOException
        {
            InputStreamReader streamReader   = new InputStreamReader(System.in);
            BufferedReader    bufferedReader = new BufferedReader(streamReader);
            return bufferedReader.readLine();
        }
        
    }
    
}
