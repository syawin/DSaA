package tree;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class NodeLL implements Iterable<LinkedNode> {

    private LinkedNode first;

    public NodeLL()
    {
        first = null;
    }

    public LinkedNode getFirst()
    {
        return first;
    }

    public boolean isEmpty()
    {
        return first == null;
    }

    public void insertFirst(Node node)
    {
        this.first = new LinkedNode.Builder(node.getKey(), node.getVal())
                             .next(first)
                             .build();
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
        newLink.setNext(curr);
    }

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

    @NotNull
    @Override
    public Iterator<LinkedNode> iterator()
    {
        return new LinkedListIterator(this);
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

            linkedNodes.insertFirst(new LinkedNode.Builder(1, 'a').build());
            linkedNodes.insertFirst(new LinkedNode.Builder(1, 'b').build());
            linkedNodes.insertFirst(new LinkedNode.Builder(1, 'c').build());
            linkedNodes.insertFirst(new LinkedNode.Builder(1, 'd').build());
            linkedNodes.insertFirst(new LinkedNode.Builder(1, 'e').build());

            System.out.println(linkedNodes);
        }

    }

}
