package queue

import tree.INode
import tree.ITree

class PriorityQTree {
    var tree: ITree = ITree()

    // setter end
    fun insert(key: Int) {
        tree.insert(key, 0.0)
    }

    fun peek(): INode? = tree.max()

    fun removeMox(): INode? {
        val max = tree.max()
        max?.let { tree.delete(it.key) }
        return max
    }
}
