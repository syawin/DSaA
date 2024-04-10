package tree.btree;

public class BTree {
    
    private final int       ORDER;
    private       MultiNode root;
    
    public BTree(int order)
    {
        ORDER = order;
        root  = new MultiNode(ORDER);
    }
    
    // getter
    public MultiNode getRoot()
    {
        return root;
    }
    // getter end
    
    // setter
    public void setRoot(MultiNode root)
    {
        this.root = root;
    }
    // setter end
    
    public void displayTree(int spaces)
    {
        // todo impl method to display BTree recursively
    }
    
}
