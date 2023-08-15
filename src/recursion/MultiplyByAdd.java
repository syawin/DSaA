package recursion;

import java.util.Scanner;

public class MultiplyByAdd {

    public static int mult(int x, int y)
    {
        return multByAdd(x, y, 0);
    }

    private static int multByAdd(int x, int y, int ans)
    {
        if (y < 1) return ans;
        return multByAdd(x, y - 1, ans + x);
    }

    private static class Demo {

        public static void main(String[] args)
        {
            System.out.println("Multiply the two ints:");
            Scanner scan = new Scanner(System.in);
            int x = scan.nextInt();
            int y = scan.nextInt();
            System.out.println("\nResult: " + mult(x, y));
        }

    }

}
