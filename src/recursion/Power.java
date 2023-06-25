package recursion;

public class Power {
    public static int power(int x, int y) {
        System.out.println("x = " + x + ", y = " + y);
        if (y == 1) return x;
        if (y % 2 != 0) return x * power(x * x, y / 2);
        else return power(x * x, y / 2);
    }

    private static class PowerApp {
        public static void main(String[] args) {
            System.out.println(power(3, 18));
        }
    }
}
