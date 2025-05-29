package heap;

import tree.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class HeapSort {
    
    private static BufferedReader reader = null;
    
    public static void main(String[] args)
    {
        int size, i;
        System.out.print("Enter number of items: ");
        size = getInt();
        Heap heap = new Heap(size);
        HashSet<Integer> uniqueNumbers = new HashSet<>();
        
        for (i = 0; i < size; i++) {
            int random;
            do {
                random = ( int ) (Math.random() * 100);
            }
            while (!uniqueNumbers.add(random));
            heap.insertAt(i, new Node.Builder(random, null).build());
            heap.incrementSize();
        }
        
        System.out.print("Random: ");
        heap.displayArray();
        
        for (i = (size / 2) - 1; i >= 0; i--) {
            heap.trickleDown(i);
        }
        
        System.out.print("Heap: ");
        heap.displayArray();
        heap.displayHeap();
        
        for (i = size - 1; i >= 0; i--) {
            Node bigNode = heap.remove();
            heap.insertAt(i, bigNode);
        }
        System.out.println("Sorted: ");
        heap.displayArray();
    }
    
    private static int getInt()
    {
        return Integer.parseInt(getLine());
    }
    
    private static String getLine()
    {
        try {
            return getReader().readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    private static BufferedReader getReader()
    {
        if (reader == null) {
            reader = new BufferedReader(new InputStreamReader(System.in));
        }
        return reader;
    }
    
}
