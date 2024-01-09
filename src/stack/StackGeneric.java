package stack;

import java.util.ArrayList;
import java.util.List;

// Reversing a String with Stacks
// Stack push/pop = O(1)
@SuppressWarnings("BooleanMethodIsAlwaysInverted")
public class StackGeneric<T> {

    private List<T> stackArray;
    private int     top;

    public StackGeneric()
    {
        this.stackArray = new ArrayList<>();
        this.top        = -1;
    }

    public StackGeneric<T> push(T c)
    {
        ++top;
        stackArray.add(c);
        return this;
    }

    public T peek()
    {
        return stackArray.get(top);
    }

    public void displayStack(String s)
    {
        System.out.print(s);
        System.out.print("Stack (bottom->top):\t");
        for (int i = 0; i < size(); i++) {
            System.out.print(peekN(i) + " ");
        }
        System.out.println();
    }

    public int size()
    {
        return top + 1;
    }

    public String peekN(int n)
    {
        return String.valueOf(stackArray.get(n));
    }

    public List<T> popAll()
    {
        List<T> list = new ArrayList<>();
        while (!isEmpty()) {
            list.add(pop());
        }
        return list;
    }

    public boolean isEmpty()
    {
        return top == -1;
    }

    public T pop()
    {
        T temp = null;
        if (top >= 0) {
            temp = stackArray.get(top);
            stackArray.remove(top--);
        }
        return temp;
    }

}
