package linkedlist;

public class Link {
    int iData;
    double dData;
    Link next;

    public Link(int iData, double dData) {
        this.iData = iData;
        this.dData = dData;
    }

    public void print() {
        System.out.printf("{%d, %f} ", iData, dData);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(this.iData);
        sb.append(", ");
        sb.append(this.dData);
        sb.append("} ");
        return sb.toString();
    }

    public Link getNext() {
        return next;
    }

    public boolean hasNext() {
        return next != null;
    }

    public void setNext(Link next) {
        this.next = next;
    }
}
