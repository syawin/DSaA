package linkedlist;

public class LinkStack {
    private final LinkedList linkedList;

    public LinkStack() {
        this.linkedList = new LinkedList();
    }

    public void push(int iData, double dData) {
        this.linkedList.insertFirst(iData, dData);
    }

    public Link pop() {
        return this.linkedList.deleteFirst();
    }

    public boolean isEmpty() {
        return this.linkedList.isEmpty();
    }

    public void print() {
        this.linkedList.print();
    }

    private static class LinkStackApp {
        public static void main(String[] args) {
            LinkStack stack = new LinkStack();

            stack.push(1,1.0);
            stack.push(2,1.1);

            stack.print();

            stack.push(3,3.33);
            stack.push(4,60.01);

            stack.print();

            stack.pop();
            stack.pop();

            stack.print();
        }
    }
}
