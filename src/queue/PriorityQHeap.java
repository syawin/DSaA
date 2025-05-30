package queue;

import heap.Heap;
import tree.Node;

class PriorityQHeap {
    
    private final int  maxSize;
    private       Heap priorityHeap;
    
    public PriorityQHeap(int maxSize)
    {
        this.maxSize = maxSize;
        priorityHeap = new Heap(maxSize);
    }
    
    // getter
    public int getMaxSize()
    {
        return maxSize;
    }
    
    public Heap getPriorityHeap()
    {
        return priorityHeap;
    }
    
    public int getnItems()
    {
        return priorityHeap.getCurrentSize();
    }
    // getter end
    
    // setter
    public void setPriorityHeap(Heap priorityHeap)
    {
        this.priorityHeap = priorityHeap;
    }
    
    // setter end
    
    public void insert(int key)
    {
        priorityHeap.insert(key);
    }
    
    public boolean isEmpty()
    {
        return priorityHeap.isEmpty();
    }
    
    public Node peek() {
        return priorityHeap.getHeap()[0];
    }
    
    public Node remove()
    {
        return priorityHeap.remove();
    }
}
