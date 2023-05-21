package linkedlist;

import java.util.ArrayList;
import java.util.List;

public class Matrix<T> {
    List<ObjLinkList<T>> matrix;
    int r, c;

    public Matrix(int height, int width) {
        this.r = height;
        this.c = width;
        matrix = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            matrix.add(new ObjLinkList<>(width));
        }
    }

    public Matrix(int height, int width, T seed) {
        this.r = height;
        this.c = width;
        matrix = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            matrix.add(new ObjLinkList<>(width, seed));
        }
    }

    public void addRow() {
        matrix.add(this.r, new ObjLinkList<>(this.c));
        this.r++;
    }

    public void addRow(T seed) {
        matrix.add(this.r, new ObjLinkList<>(this.c, seed));
        this.r++;
    }

    public void addCol() {
        for (ObjLinkList<T> ll : matrix) {
            ll.insertAt(null,this.c);
        }
        this.c++;
    }

    public void addCol(T seed) {
        for (ObjLinkList<T> ll : matrix) {
            ll.insertAt(seed,this.c);
        }
        this.c++;
    }

    public void setAt(int row, int col, T data) {
        matrix.get(row).setAt(data, col);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < r; i++) {
            sb.append(matrix.get(i));
            if (i < r - 1) sb.append('\n');
        }
        return sb.toString();
    }

    private static final class MatrixDemo {
        public static void main(String[] args) {
            Matrix<Integer> matrix1 = new Matrix<>(4,5, 0);
            System.out.println(matrix1);
            System.out.println();
            matrix1.addRow(5);
            System.out.println(matrix1);
            System.out.println();
            matrix1.addCol(6);
            System.out.println(matrix1);
            System.out.println();
            matrix1.setAt(4,5,1);
            System.out.println(matrix1);
            System.out.println();
        }
    }
}
