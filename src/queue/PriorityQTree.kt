package queue;

import tree.INode;
import tree.ITree;

public class PriorityQTree {
    
    private ITree tree;
    
    public PriorityQTree()
    {
        tree = new ITree();
    }
    
    // getter
    public ITree getTree()
    {
        return tree;
    }
    // getter end
    
    // setter
    public void setTree(ITree tree)
    {
        this.tree = tree;
    }
    // setter end
    
    public void insert(int key) {
        tree.insert(key,0.0);
    }
    
    public INode peek() {
        return tree.max();
    }
 
    public INode removeMox() {
        INode max = tree.max();
        tree.delete(max.getKey());
        return max;
    }
}
