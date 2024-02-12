package tree;

import org.junit.Test;

public class RBTreeTest {

    @Test
    public void insertWhenRootIsNull()
    {
        RBTree rbTree = new RBTree();
        assert rbTree.getRoot() == null;
        rbTree.insert(1, 'A');
        assert rbTree.getRoot()
                     .getKey() == 1;
        assert ((RBTreeNode) rbTree.getRoot()).isBlack();
    }

}
