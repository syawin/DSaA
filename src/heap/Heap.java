package heap;

import tree.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Heap {
    
    private final Node[] heap;
    private final int    maxSize;
    private       int    currentSize;
    
    public Heap(int maxSize)
    {
        this.maxSize = maxSize;
        heap         = new Node[maxSize];
        currentSize  = 0;
    }
    
    // getter
    public int getCurrentSize()
    {
        return currentSize;
    }

    public Node[] getHeap()
    {
        return heap;
    }
    
    public int getMaxSize()
    {
        return maxSize;
    }
    // getter end
    
    public boolean changeKey(int index, int newKey)
    {
        if (index < 0 || index >= currentSize) return false;
        Node oldVal = heap[index];
        heap[index] = new Node.Builder(newKey, oldVal.getValue()).build();
        if (oldVal.getKey() < newKey) { trickleUp(index); }
        else { trickleDown(index); }
        return true;
    }
    
    public void displayHeap()
    {
        System.out.print("heapArray: ");
        for (int i = 0; i < currentSize; i++) {
            if (heap[i] != null) {
                System.out.print(heap[i].getKey() + " ");
            }
            else {
                System.out.print("NULL");
            }
        }
        System.out.println();
        
        int    nBlanks = 32, itemsPerRow = 1, column = 0, j = 0;
        String dots    = "•".repeat(nBlanks - 1);
        System.out.println(dots + dots);
        
        while (currentSize > 0) {
            if (column == 0) {
                for (int i = 0; i < nBlanks; i++) {
                    System.out.print(' ');
                }
            }
            System.out.print(heap[j].getKey());
            if (++j == currentSize) {
                break;
            }
            if (++column == itemsPerRow) {
                nBlanks /= 2;
                itemsPerRow *= 2;
                column = 0;
                System.out.println();
            }
            else {
                for (int i = 0; i < nBlanks * 2 - 2; i++) {
                    System.out.print(' ');
                }
            }
        }
        System.out.println("\n" + dots + dots);
    }
    
    public boolean insert(int key)
    {
        if (currentSize == maxSize) return false;
        Node newNode = new Node.Builder(key, null).build();
        heap[currentSize] = newNode;
        trickleUp(currentSize++);
        return true;
    }
    
    public boolean isEmpty()
    {
        return currentSize == 0;
    }
    
    /**
     * Validates the max-heap property for the heap array.
     *
     * @return true if the heap property holds for every node; false otherwise.
     */
    public boolean isValidHeap()
    {
        for (int i = 0; i < currentSize / 2; i++) { // Only internal nodes need checking
            int left  = 2 * i + 1;
            int right = 2 * i + 2;
            
            if (left < currentSize && heap[i].getKey() < heap[left].getKey()) {
                return false;
            }
            if (right < currentSize && heap[i].getKey() < heap[right].getKey()) {
                return false;
            }
        }
        return true;
    }
    
    public Node remove()
    {
        Node root = heap[0];
        heap[0] = heap[--currentSize];
        trickleDown(0);
        return root;
    }
    
    public void restoreHeap() {
        for (int i = (currentSize/2 - 1); i >= 0; i--) {
            trickleDown(i);
        }
    }
    
    /**
     * Add a new key to the end of the array without any concern for ordering.
     *
     * @param key
     *         the key to insert
     *
     * @return true if the insert succeeded
     */
    public boolean toss(int key)
    {
        if (currentSize == maxSize) return false;
        Node newNode = new Node.Builder(key, null).build();
        heap[currentSize] = newNode;
        incrementSize();
        return true;
    }
    
    public void trickleDown(int index)
    {
        int  bigChild;
        Node top = heap[index];
        while (index < (currentSize / 2)) { // while node has at least one child
            int leftChild  = 2 * index + 1;
            int rightChild = leftChild + 1;
            if (rightChild < currentSize && heap[leftChild].getKey() < heap[rightChild].getKey()) {
                bigChild = rightChild;
            }
            else {
                bigChild = leftChild;
            }
            // check if 'top' is larger than 'bigChild'
            if (top.getKey() >= heap[bigChild].getKey()) {
                break;
            }
            
            heap[index] = heap[bigChild];
            index       = bigChild;
        }
        heap[index] = top;
    }
    
    public void trickleUp(int index)
    {
        int  par    = (index - 1) / 2;
        Node bottom = heap[index];
        while (index > 0 && heap[par].getKey() < bottom.getKey()) {
            heap[index] = heap[par];
            index       = par;
            par         = (par - 1) / 2;
        }
        heap[index] = bottom;
    }
    
    void displayArray()
    {
        for (int i = 0; i < maxSize; i++) {
            System.out.print(heap[i].getKey() + " ");
        }
        System.out.println();
    }
    
    void incrementSize()
    {
        incrementSize(1);
    }
    
    void incrementSize(int increment)
    {
        currentSize += increment;
    }
    
    void insertAt(int index, Node node)
    {
        heap[index] = node;
    }
    
    // DEMO
    private static class HeapDemo {
        
        private static BufferedReader reader = null;
        
        public static void main(String[] args) throws IOException
        {
            int     val1, val2;
            Heap    heap = new Heap(31);
            boolean success;
            int[] data = {
                    64,
                    26,
                    15,
                    76,
                    74,
                    10,
                    87,
                    77,
                    28,
                    73,
                    52,
                    37,
                    71
            };
            for (int datum : data) {
                heap.insert(datum);
            }
            while (true) {
                System.out.println("Enter the first letter of the command:");
                System.out.print("\tShow,\n\tInsert,\n\tDelete,\n\tChange :");
                char opt = readChar();
                switch (opt) {
                    case 's':
                        heap.displayHeap();
                        break;
                    case 'i':
                        insert(heap);
                        break;
                    case 'd':
                        delete(heap);
                        break;
                    case 'c':
                        change(heap);
                        break;
                    case 'q':
                        return;
                    default:
                        System.out.println("Invalid input");
                }
            }
        }
        
        private static void change(Heap heap) throws IOException
        {
            int key1, key2;
            System.out.print("Enter index of value to replace: ");
            key1 = readInt();
            System.out.print("Enter key value to replace it: ");
            key2 = readInt();
            boolean changed = heap.changeKey(key1, key2);
            if (changed) {
                System.out.println("Change succeeded");
            }
            else { System.out.println("Change Failed"); }
        }
        
        private static void delete(Heap heap) throws IOException
        {
            heap.remove();
        }
        
        private static void insert(Heap heap) throws IOException
        {
            int key;
            System.out.print("Enter key value to insert: ");
            key = readInt();
            heap.insert(key);
        }
        
        private static char readChar() throws IOException
        {
            return readString().charAt(0);
        }
        
        private static int readInt() throws IOException
        {
            return Integer.parseInt(readString());
        }
        
        private static String readString() throws IOException
        {
            if (reader == null) {
                reader = new BufferedReader(new InputStreamReader(System.in));
            }
            return reader.readLine();
        }
        
    }
    // DEMO end
}
