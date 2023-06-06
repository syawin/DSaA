package recursion;

/**
 * Iterative implementation of merge sort as point of comparison for recursive implementation.
 */
public class MergeSortIter {
    public static void main(String[] args) {
        int[] arrA = {23, 47, 81, 95};
        int[] arrB = {7, 14, 39, 55, 62, 74};
        int[] arrC = new int[arrA.length + arrB.length];

        merge(arrA, arrA.length, arrB, arrB.length, arrC);
        display(arrC, arrC.length);
    }

    private static void display(int[] arrC, int sizeC) {
        for (int i = 0; i < sizeC; i++) {
            System.out.print(arrC[i] + " ");
        }
        System.out.println();
    }

    private static void merge(int[] arrA, int sizeA, int[] arrB, int sizeB, int[] arrC) {
        int aDex, bDex, cDex;
        aDex = bDex = cDex = 0;
        while (aDex < sizeA && bDex < sizeB) {
            arrC[cDex++] = (arrA[aDex] < arrB[bDex]) ? arrA[aDex++] : arrB[bDex++];
        }

        while (aDex < sizeA) {
            arrC[cDex++] = arrA[aDex++];
        }
        while (bDex < sizeB) {
            arrC[cDex++] = arrB[bDex++];
        }
    }
}
