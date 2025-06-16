package recursion;

import stack.IntegerStack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StackTriangle {
    static int num, ans;
    static IntegerStack stack;

    static void stackTriangle() {
        stack = new IntegerStack(1000);
        ans = 0;
        while (num > 0) stack.push(num--);
        while (!stack.isEmpty()) ans += stack.pop();
    }

    private static final class StackTriangleApp {
        public static void main(String[] args) throws IOException {
            System.out.print("Enter a number:\t");
            num = getInt();
            stackTriangle();
            System.out.println("Triangle =\t\t" + ans);
        }

        public static int getInt() throws IOException {
            return Integer.parseInt(getString());
        }

        public static String getString() throws IOException {
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            return bufferedReader.readLine();
        }
    }
}
