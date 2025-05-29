package heap;

import tree.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HeapSort {
    
    private static BufferedReader reader = null;
    
    // getter
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
    // getter end
    
    public static void main(String[] args)
    {
        int size, i;
        System.out.print("Enter number of items: ");
        size = getInt();
        Heap heap = new Heap(size);
        
        for (i = 0; i < size; i++) {
            int random = ( int ) (Math.random() * 100);
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
    }
    
}
