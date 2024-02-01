package tree;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Huffman {

    private static final List<Character>         SIMPLE_ALPHA = List.of('A',
                                                                        'B',
                                                                        'C',
                                                                        'D',
                                                                        'E',
                                                                        'F',
                                                                        'G',
                                                                        'H',
                                                                        'I',
                                                                        'J',
                                                                        'K',
                                                                        'L',
                                                                        'M',
                                                                        'N',
                                                                        'O',
                                                                        'P',
                                                                        'Q',
                                                                        'R',
                                                                        'S',
                                                                        'T',
                                                                        'U',
                                                                        'V',
                                                                        'W',
                                                                        'X',
                                                                        'Y',
                                                                        'Z',
                                                                        ' ',
                                                                        '\n');
    private final        char[]                  codeTable    = new char[SIMPLE_ALPHA.size()];
    private final        String                  msg;
    private              Map<Character, Integer> fTable;
    private              Tree                    hTree;

    public Huffman(String msg)
    {
        this.msg = msg.toUpperCase();
    }

    // todo refactor to use a String literal of '1' & '0' chars. passing an instance of StringBuilder is probably the
    //  best approach
    public void createCodeTable()
    {
        Arrays.fill(codeTable, ((char) -1));
        createCodeTableRecur(this.hTree.getRoot(), (char) 0);
    }

    public void createFreqTable()
    {
        char[] ca = this.msg.toCharArray();
        HashMap<Character, Integer> freq = new HashMap<>();
        for (char c : ca) {
            if (freq.containsKey(c)) {
                freq.put(c, freq.get(c) + 1);
            }
            else {
                freq.put(c, 1);
            }
        }
        this.fTable = freq;
    }

    public void createHuffmanTree()
    {
        NodeLL priorityQueue = new NodeLL();
        for (Map.Entry<Character, Integer> entry : fTable.entrySet()) {
            TreeNode treeNode = new TreeNode.Builder(entry.getValue(), entry.getKey()).build();
            Node wrapper = new Node.Builder(entry.getValue(), treeNode).build();
            priorityQueue.insertInOrder(wrapper);
        }
        while (priorityQueue.length() > 1) {
            TreeNode x = (TreeNode) priorityQueue
                                            .removeFirst()
                                            .getVal();
            TreeNode y = (TreeNode) priorityQueue
                                            .removeFirst()
                                            .getVal();
            int sum = x.getKey() + y.getKey();
            TreeNode newRoot = new TreeNode.Builder(sum, null)
                                       .leftChild(x)
                                       .rightChild(y)
                                       .build();
            Node wrapper = new Node.Builder(sum, newRoot).build();
            priorityQueue.insertInOrder(wrapper);
        }
        this.hTree = new Tree((TreeNode) priorityQueue
                                                 .getFirst()
                                                 .getVal());
        hTree.displayTree(64);
    }

    public char[] decodeMessage(final char[] encoded)
    {
        char[] decoded = new char[encoded.length];
        for (int i = 0; i < encoded.length; i++) {
            char c = encoded[i];
            int codeIndex = ArrayUtils.indexOf(codeTable, c);
            decoded[i] = SIMPLE_ALPHA.get(codeIndex);
        }
        return decoded;
    }

    public char[] encodeMessage()
    {
        char[] encoded = this.msg.toCharArray();
        for (int i = 0; i < encoded.length; i++) {
            encoded[i] = codeTable[SIMPLE_ALPHA.indexOf(encoded[i])];
        }
        return encoded;
    }

    private void createCodeTableRecur(TreeNode root, char bitString)
    {
        if (root == null) return;
        if (root.isLeaf()) {
            int i = SIMPLE_ALPHA.indexOf((char) root.getVal());
            codeTable[i] = bitString;
        }
        else {
            createCodeTableRecur(root.getlChild(), (char) (bitString << 1));
            createCodeTableRecur(root.getrChild(), (char) (bitString << 1 | 1));
        }
    }

    private static class Demo {

        public static void main(String[] args)
        {
            // final String message = "stef dehan made a summertime banger";
            final String message = "SUSIE SAYS IT IS EASY\n";
            Huffman huffman = new Huffman(message);
            huffman.createFreqTable();
            huffman.createHuffmanTree();
            huffman.createCodeTable();
            System.out.println(Arrays.toString(huffman.codeTable));
            char[] encoded = huffman.encodeMessage();
            char[] decoded = huffman.decodeMessage(encoded);
            System.out.println(encoded);
            System.out.println(decoded);
        }

    }

}
