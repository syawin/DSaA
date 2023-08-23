package recursion;

public class DrawBinaryTree {

    char[][] treeMatrix;

    public DrawBinaryTree(int size)
    {

    }

    public static void makeBranches(int left, int right)
    {
        if ((right - left) <= 2) return;
        StringBuilder builder = new StringBuilder();
        for (int i = left; i < right; i++) {
            if (i == ((right - left) / 2) + left) {
                builder.append('X');
            }
            else {
                builder.append('-');
            }
        }
        System.out.println(builder);
        makeBranches(left, right / 2);
        makeBranches(right / 2 + 1, right);
    }

    private static class BinTreeDemo {

        public static void main(String[] args)
        {
            makeBranches(0, 32);
        }

    }

}
