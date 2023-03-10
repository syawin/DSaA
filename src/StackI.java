//Reversing a String with Stacks
//Stack push/pop = O(1)
@SuppressWarnings("BooleanMethodIsAlwaysInverted")
class StackI {
    private final int maxSize;
    private int[] stackArray;
    private int top;

    public StackI(int maxSize) {
        this.maxSize = maxSize;
        this.stackArray = new int[this.maxSize];
        this.top = -1;
    }

    public void push(int c) {
        stackArray[++top] = c;
    }

    public int pop() {
        return stackArray[top--];
    }

    public int peek() {
        return stackArray[top];
    }

    public String peekN(int n) {
        return String.valueOf(stackArray[n]);
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }

    public void displayStack(String s) {
        System.out.print(s);
        System.out.print("Stack (bottom->top):\t");
        for (int i = 0; i < size(); i++) {
            System.out.print(peekN(i) + " ");
        }
        System.out.println();
    }
}

