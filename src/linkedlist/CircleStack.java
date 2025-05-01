package linkedlist;

public class CircleStack {
    
    // stack is FILO
    private final CircleLinkedList linkedList;
    
    public CircleStack()
    {
        linkedList = new CircleLinkedList();
    }
    
    public Link pop()
    {
        return linkedList.delete();
    }
    
    public void push(int iData, double dData)
    {
        linkedList.insert(iData, dData);
    }
    
    private void print()
    {
        linkedList.print();
    }
    
    private static class CircleStackDemo {
        
        public static void main(String[] args)
        {
            CircleStack stack = new CircleStack();
            stack.push(22, 2.99);
            stack.print();
            stack.push(33, 3.81);
            stack.print();
            stack.push(44, 4.90);
            stack.print();
            stack.push(55, 1.01);
            stack.print();
            Link pop = stack.pop();
            assert pop.key == 55;
            System.out.println(pop);
            stack.print();
        }
        
    }
    
}
