package linkedlist;

import java.util.Optional;

public class Josephus {
    
    private final CircleLinkedList list;
    private final int              step;
    
    /**
     * Constructs a circular list to represent the mutual suicide pact that is the Josephus Problem
     *
     * @param count
     *         number of people in circle
     * @param start
     *         person to start at (note that this is **not** zero-indexed)
     * @param step
     *         size of step between people when counting who's next to kill themselves
     */
    public Josephus(int count, int start, int step)
    {
        this.step = step;
        list      = new CircleLinkedList(count);
        // advance the curr pointer on the list to the starting position (default is 1)
        for (int i = 1; i < start; i++) {
            list.step();
        }
    }
    
    /**
     * Eliminate all but the last remaining member of the list according to the start & stop obj
     * vars
     * Collects & prints the eliminated members in order of elimination.
     *
     * @return the number of the last one to leave the circle
     */
    public long execute()
    {
        StringBuilder orderOfElimination = new StringBuilder();
        long          temp;
        while (list.getSize() > 1) {
            temp = advance();
            System.out.println(temp + " has been eliminated.");
            orderOfElimination.append(temp);
            if (list.getSize() > 1) orderOfElimination.append(',');
        }
        System.out.println("Order of elimination: " + orderOfElimination + '\n');
        return advance();
    }
    
    public void print()
    {
        list.print();
    }
    
    /**
     * Advances to the next person to leave the circle (size defined by the step variable) &
     * removes them
     * (atomic operation)
     *
     * @return the original position in the circle of the one removed
     */
    long advance()
    {
        // for number of steps defined, invoke step on list to advance "curr" pointer
        for (int i = 0; i < step; i++) {
            list.step();
        }
        Optional<Link> deleted = Optional.ofNullable(list.delete());
        return deleted.map(Link::getKey).orElse(-1L);
    }
    
    private static final class JosephusProblem {
        
        public static void main(String[] args)
        {
            Josephus test = new Josephus(7, 2, 3);
            test.print();
            System.out.println(test.advance());
            System.out.println(test.advance());
            System.out.println(test.advance());
            System.out.println(test.advance());
            test.print();
            System.out.println(test.advance());
            System.out.println(test.advance());
            System.out.println(test.advance());
            System.out.println(test.advance());
        }
        
    }
    
    private static final class JosephusProblemDemo {
        
        public static void main(String[] args)
        {
            Josephus test = new Josephus(20, 7, 7);
            test.print();
            long result = test.execute();
            System.out.printf("%d is the last to leave.\n", result);
        }
        
    }
    
}
