package recursion;

import stack.ParamStack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StackTriangleGoTo {
    static int num, ans, codePart;
    static ParamStack stack;
    static Params params;

    public static void recTriangle() {
        stack = new ParamStack(1000);
        codePart = 1;
        while (step() == false)
            /*noop statement*/ ;
    }

    private static boolean step() {
        switch (codePart) {
            case 1:                         // init
                params = new Params(num, 6);
                stack.push(params);
                codePart = 2;
                break;
            case 2:                         // method entry point
                params = stack.peek();
                if (params.n == 1) {        // test
                    ans = 1;
                    codePart = 5;           // goto exit
                } else
                    codePart = 3;           // recursive call
                break;
            case 3:                         // method call
                Params newParams = new Params(params.n - 1, 4);
                stack.push(newParams);
                codePart = 2;               // goto method entry
                break;
            case 4:                         // calculation
                params = stack.peek();
                ans += params.n;
                codePart = 5;
                break;
            case 5:                         // method exit
                params = stack.peek();
                codePart = params.retAddr;  // 4 or 6
                stack.pop();
                break;
            case 6:                         // return point
                return true;
        }
        return false;
    }

    private static final class StackTriangleApp {
        public static void main(String[] args) throws IOException {
            System.out.print("Enter a number:\t");
            num = getInt();
            recTriangle();
            System.out.println("Triangle =\t\t" + ans);
        }

        public static String getString() throws IOException {
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            return bufferedReader.readLine();
        }

        public static int getInt() throws IOException {
            return Integer.parseInt(getString());
        }
    }
}
