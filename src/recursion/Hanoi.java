package recursion;

public class Hanoi {
    static int nDisks = 3;

    public static void main(String[] args) {
        doTowers(nDisks, 'A', 'B', 'C');
    }

    private static void doTowers(int topN, char from, char inter, char dest) {
        if (topN == 1)
            System.out.println("Disk " + topN + " from " + from + " to " + dest);
        else {
            doTowers(topN - 1, from, dest, inter);
            System.out.println("Disk " + topN + " from " + from + " to " + dest);
            doTowers(topN - 1, inter, from, dest);
        }
    }
}
