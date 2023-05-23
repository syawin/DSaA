package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Anagram {
    static int size, count;
    static char[] arrChar = new char[100];

    public static void doAnagram(int newSize) {
        if (newSize == 1) return;
        for (int i = 0; i < newSize; i++) {
            doAnagram(newSize - 1);
            if (newSize == 2) displayWord();
            rotate(newSize);
        }
    }

    public static void rotate(int newSize) {
        int i;
        int position = size - newSize;
        char temp = arrChar[position];
        for (i = position + 1; i < size; i++) {
            arrChar[i - 1] = arrChar[i];
        }
        arrChar[i - 1] = temp;
    }

    public static void displayWord() {
        if (count < 99) System.out.print(" ");
        if (count < 9) System.out.print(" ");
        System.out.print(++count + " ");
        for (int i = 0; i < size; i++) {
            System.out.print(arrChar[i]);
        }
        System.out.print("   ");
        System.out.flush();
        if (count % 6 == 0) System.out.println();
    }

    private static final class AnagramApp {
        public static void main(String[] args) throws IOException {
            System.out.print("Enter a word: ");
            String in = getString();
            size = in.length();
            count = 0;
            for (int i = 0; i < size; i++) {
                arrChar[i] = in.charAt(i);
            }
            doAnagram(size);
        }

        private static String getString() throws IOException {
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            return bufferedReader.readLine();
        }
    }
}
