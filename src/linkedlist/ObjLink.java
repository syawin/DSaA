package linkedlist;

import java.util.Objects;

public class ObjLink<T> {
    ObjLink<T> next;
    T data;

    public ObjLink(T data) {
        this.data = data;
    }

    public ObjLink<T> getNext() {
        return next;
    }

    public void setNext(ObjLink<T> next) {
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean hasNext() {
        return Objects.nonNull(next);
    }

    @Override
    public String toString() {
        return "{" +
                data +
                '}';
    }
}
