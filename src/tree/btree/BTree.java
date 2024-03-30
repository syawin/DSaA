package tree.btree;

public class BTree<T> {
    
    private MultiNode<T> root;
    
    public BTree(MultiNode<T> root)
    {
        this.root = root;
    }
    
    // getter
    public MultiNode<T> getRoot()
    {
        return root;
    }
    // getter end
    
    // setter
    public void setRoot(MultiNode<T> root)
    {
        this.root = root;
    }
    // setter end
    
    // todo display method; prints data items and all children recursively
    public static void display()
    {
    
    }
    
}
