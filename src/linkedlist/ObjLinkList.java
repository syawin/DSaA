package linkedlist;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;

public class ObjLinkList<T> {
    private ObjLink<T> first;
    int size;

    public ObjLinkList() {
        first = null;
        size = 0;
    }

    /**
     * Init ObjLinkList as list of given size w/ 'null' data
     *
     * @param size initial size of list
     */
    public ObjLinkList(int size) {
        for (int i = 0; i < size; i++) {
            insertFirst(null);
        }
    }

    /**
     * Init ObjLinkList as list of given size seeded w/ default data.
     * @param size initial size of list
     * @param seed obj of type T to seed list with
     */
    public ObjLinkList(int size, T seed) {
        for (int i = 0; i < size; i++) {
            insertFirst(seed);
        }
    }

    public void insertFirst(T data) {
        ObjLink<T> newLink = new ObjLink<>(data);
        newLink.setNext(first);
        first = newLink;
        size++;
    }

    public void setAt(T data, int index) {
        Optional<ObjLink<T>> link = Optional.ofNullable(getAt(index));
        link.ifPresent(objLink -> objLink.setData(data));
    }

    @Nullable
    public ObjLink<T> getAt(int index) {
        ObjLink<T> curr = first;
        int i = 0;
        while (Objects.nonNull(curr) && curr.hasNext() && i < index) {
            curr = curr.getNext();
            i++;
        }
        return curr;
    }

    public void insertAt(T data, int index) {
        if (index < 1 || isEmpty()) {
            insertFirst(data);
            return;
        }
        ObjLink<T> newLink = new ObjLink<>(data);
        Optional<ObjLink<T>> link = Optional.ofNullable(getAt(index));
        link.ifPresent(objLink -> {
            newLink.setNext(objLink.getNext());
            objLink.setNext(newLink);
            size++;
        });
    }

    private boolean isEmpty() {
        return first == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        ObjLink<T> curr = first;
        for (int i = 0; i < size; i++) {
            sb.append(curr.toString());
            if (i < size - 1) sb.append(',');
            curr = curr.getNext();
        }
        sb.append(']');
        return sb.toString();
    }

    private static final class ObjLinkListDemo {
        public static void main(String[] args) {
            ObjLinkList<Integer> ll = new ObjLinkList<>(3);
            ll.insertAt(5,1);
            System.out.println(ll);
            System.out.println("Done");
        }
    }
}
