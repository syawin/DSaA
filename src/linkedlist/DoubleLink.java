package linkedlist;

public class DoubleLink {
    int iData;
    DoubleLink next;
    DoubleLink prev;

    public DoubleLink(int iData) {
        this.iData = iData;
    }

    public int getiData() {
        return iData;
    }

    public void print() {
        System.out.printf("{%d} ", iData);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(this.iData);
        sb.append("} ");
        return sb.toString();
    }

    public DoubleLink getNext() {
        return this.next;
    }

    public boolean hasNext() {
        return this.next != null;
    }

    public void setNext(DoubleLink next) {
        this.next = next;
    }

    public void setPrev(DoubleLink prev) {
        this.prev = prev;
    }

    public boolean hasPrev() {
        return this.prev != null;
    }

    public DoubleLink getPrev() {
        return this.prev;
    }
}
