package tree;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class NodeLL implements Iterable<LinkedNode> {

    private LinkedNode first, last;
    private int size;

    public NodeLL()
    {
        last = first = null;
        size = 0;
    }

    // getter
    public LinkedNode getFirst()
    {
        return first;
    }

    public LinkedNode getLast()
    {
        return last;
    }
    // getter end

    public LinkedNode find(int key)
    {
        if (isEmpty()) return null;
        LinkedNode curr = this.first;
        while (curr.getKey() != key) {
            if (curr.hasNext()) {
                curr = curr.getNext();
            }
            else {
                return null;
            }
        }
        return curr;
    }

    public boolean isEmpty()
    {
        return first == null;
    }

    public void insertFirst(Node node)
    {
        LinkedNode linkedNode = new LinkedNode.Builder(node.getKey(), node.getVal())
                                        .next(first)
                                        .build();
        if (size == 0) this.last = linkedNode;
        this.first = linkedNode;
        size++;
    }

    public void insertInOrder(Node node)
    {
        LinkedNode newLink, prev, curr;
        newLink = new LinkedNode.Builder(node.getKey(), node.getVal()).build();
        prev    = null;
        curr    = this.first;

        while (curr != null && node.getKey() > curr.getKey()) {
            prev = curr;
            curr = curr.getNext();
        }
        if (prev == null) {
            first = newLink;
        }
        else {
            prev.setNext(newLink);
        }
        if (curr == null) last = newLink;
        newLink.setNext(curr);
        size++;
    }

    public void insertLast(Node node)
    {
        LinkedNode linkedNode = new LinkedNode.Builder(node.getKey(), node.getVal()).build();
        if (isEmpty()) {
            first = last = linkedNode;
        }
        else {
            last.setNext(linkedNode);
            last = linkedNode;
        }
        size++;
    }

    @NotNull
    @Override
    public Iterator<LinkedNode> iterator()
    {
        return new LinkedListIterator(this);
    }

    public int length()
    {
        return size;
    }

    public Node removeFirst()
    {
        if (isEmpty()) return null;
        Node res = first;
        first = first.getNext();
        size--;
        return res;
    }

    @Override
    public String toString()
    {
        final StringBuffer sb = new StringBuffer("NodeLL{");
        sb.append('\n');
        for (LinkedNode node : this) {
            sb.append('\t')
              .append(node)
              .append('\n');
        }
        sb.append('}');
        return sb.toString();
    }

    private static final class LinkedListIterator implements Iterator<LinkedNode> {

        private final NodeLL     linkedList;
        private       LinkedNode curr, prev;

        private LinkedListIterator(NodeLL list)
        {
            linkedList = list;
            curr       = linkedList.first;
            prev       = null;
        }

        @Override
        public boolean hasNext()
        {
            return curr != null;
        }

        @Override
        public LinkedNode next()
        {
            prev = curr;
            curr = curr.getNext();
            return prev;
        }

        public void reset()
        {
            curr = linkedList.first;
            prev = null;
        }

    }

    private static final class Demo {

        public static void main(String[] args)
        {
            NodeLL linkedNodes = new NodeLL();

            linkedNodes.insertInOrder(new LinkedNode.Builder(5, 'a').build());
            linkedNodes.insertInOrder(new LinkedNode.Builder(4, 'b').build());
            linkedNodes.insertInOrder(new LinkedNode.Builder(3, 'c').build());
            linkedNodes.insertInOrder(new LinkedNode.Builder(2, 'd').build());
            linkedNodes.insertLast(new LinkedNode.Builder(1, 'e').build());

            System.out.println(linkedNodes);

            System.out.println(linkedNodes.removeFirst());
            System.out.println(linkedNodes.removeFirst());
            System.out.println(linkedNodes.removeFirst());
            System.out.println(linkedNodes.removeFirst());
            System.out.println(linkedNodes.removeFirst());
            System.out.println(linkedNodes.removeFirst());
            System.out.println(linkedNodes.removeFirst());
        }

    }

}
