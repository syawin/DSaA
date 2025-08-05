package linkedlist;

import java.util.Iterator;

public class IteratorTest {
    
    public static void main(String[] args)
    {
        // Create a new LinkedList
        LinkedList list = new LinkedList();
        
        // Add some elements
        list.insertFirst(10, 1.1);
        list.insertFirst(20, 2.2);
        list.insertFirst(30, 3.3);
        list.insertFirst(40, 4.4);
        
        System.out.println("Original list:");
        list.print();
        
        // Test using the iterator directly
        System.out.println("\nUsing iterator directly:");
        Iterator<Link> iterator = list.getIterator();
        while (iterator.hasNext()) {
            Link link = iterator.next();
            System.out.printf("{%d, %f} ", link.key, link.data);
        }
        System.out.println();
        
        // Test using for-each loop (which uses the Iterable interface)
        System.out.println("\nUsing for-each loop:");
        for (Link link : list) {
            System.out.printf("{%d, %f} ", link.key, link.data);
        }
        System.out.println();
        
        // Test the remove method
        System.out.println("\nRemoving elements using iterator:");
        iterator = list.getIterator();
        while (iterator.hasNext()) {
            Link link = iterator.next();
            if (link.key == 30 || link.key == 10) {
                System.out.println("Removing: " + link);
                iterator.remove();
            }
        }
        
        System.out.println("\nList after removal:");
        list.print();
    }
    
}
