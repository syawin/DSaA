public class bubbleSort {

  private long[] a;
  private int nElems;

  public bubbleSort(int max) {
    this.a = new long[max];
    nElems = 0;
  }

  public void insert(long value) {
    a[nElems] = value;
  }

  public void display() {
    for (int j = 0; j < nElems; j++) {
      System.out.print(a[j] + " ");
    }
    System.out.println(" ");
  }
}
