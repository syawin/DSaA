package tree;

import java.util.HashMap;

public class Huffman {

    public static HashMap<Character, Integer> createFreqTable(String message)
    {
        char[] ca = message.toCharArray();
        HashMap<Character, Integer> out = new HashMap<>();
        for (char c : ca) {
            if (out.containsKey(c)) {
                out.put(c, out.get(c) + 1);
            }
            else {
                out.put(c, 1);
            }
        }
        return out;
    }

    private static class Demo {

        public static void main(String[] args)
        {
            Huffman.createFreqTable("test");
        }

    }

}
