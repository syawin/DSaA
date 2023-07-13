package stack;

import java.util.ArrayList;
import java.util.List;

public class StringStack {

    // todo refactor the Stack classes into a a common class that is type agnostic
    private final int      maxSize;
    private       String[] stackArray;
    private       int      top;

    public StringStack(int maxSize)
    {
        this.maxSize    = maxSize;
        this.stackArray = new String[this.maxSize];
        this.top        = -1;
    }

    public StringStack push(String s)
    {
        stackArray[++top] = s;
        return this;
    }

    public String peek()
    {
        return stackArray[top];
    }

    public String peekN(int n)
    {
        return stackArray[n];
    }

    public int size()
    {
        return top + 1;
    }

    public List<String> popAll()
    {
        List<String> list = new ArrayList<>();
        while (!isEmpty()) {
            list.add(pop());
        }
        return list;
    }

    public boolean isEmpty()
    {
        return top == -1;
    }

    private String pop()
    {
        return (top >= 0) ? stackArray[top--] : null;
    }

}
