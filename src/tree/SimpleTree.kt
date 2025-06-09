package tree

import common.convertDecimal2BinaryString

class SimpleTree {
    @Volatile
    var root: SimpleNode? = null
    var itemCount: Int = 0
        private set

    /**
     * Retrieves the node located at the specified index within the binary tree.
     *
     * @param index The 1-based index of the node to retrieve. Must be within the range [1, itemCount].
     * @return The node located at the specified index, or null if the path to the node is broken.
     * @throws IndexOutOfBoundsException If the tree is empty, the index is out of range, or the path is invalid.
     */
    @Synchronized
    operator fun get(index: Int): SimpleNode? {
        if (itemCount == 0) throw IndexOutOfBoundsException("Tree is empty")
        if (index < 1 || index > itemCount) throw IndexOutOfBoundsException("Index $index is invalid")
        if (index == 1) return root
        var result = root
        val path = convertDecimal2BinaryString(index)
        for (char in path) {
            if (result == null) throw NullPointerException("Tree path to $index is broken")
            @Suppress("LiftReturnOrAssignment")
            when (char) {
                '0' -> result = result.left
                '1' -> result = result.right
                else -> throw IndexOutOfBoundsException("Path to $index is invalid")
            }
        }
        return result
    }

    /**
     * Attaches a newly created node to its parent within a binary tree structure.
     *
     * @param parent The parent node to which the created node will be attached. Can be null if the parent does not exist.
     * @param createdNode The newly created node that needs to be attached.
     * @param isRightChild A boolean indicating whether the created node should be attached as a right child (true)
     *                     or as a left child (false).
     */
    private fun attachNewNodeToParent(
        parent: SimpleNode?,
        createdNode: SimpleNode,
        isRightChild: Boolean,
    ) {
        createdNode.parent = parent
        if (isRightChild) {
            parent?.right = createdNode
        } else {
            parent?.left = createdNode
        }
    }

    /**
     * Rearranges elements in the binary heap by moving the node at the specified index upward,
     * ensuring the heap property is maintained. The node is swapped with its parent if its key
     * is greater than the parent's key.
     *
     * @param index The 1-based index of the node to start the upward adjustment.
     *              Must be within the range [1, itemCount].
     */
    private fun trickleUp(index: Int) {
        var heapIndex: Int = index
        while (heapIndex > 1 && this[heapIndex]!!.key!! > this[heapIndex / 2]!!.key!!) {
            // swap with parent
            swapKeys(heapIndex, heapIndex / 2)
            heapIndex /= 2
        }
    }

    /**
     * Inserts a new key into the tree. If the tree is empty, the provided key will initialize the root node.
     * Otherwise, the new key will be added as a child to an appropriate parent node in a binary tree structure.
     *
     * @param key The integer key to insert into the tree.
     */
    @Synchronized
    fun insert(key: Int) {
        if (itemCount == 0) {
            root = root?.apply { this.key = key } ?: SimpleNode(key)
            itemCount++
            return
        }
        itemCount++
        val node = this[itemCount]
        if (node != null) {
            node.key = key
        } else {
            val createdNode = SimpleNode(key)
            val parentNode = this[itemCount / 2]
            val isRightChild = itemCount % 2 == 1
            attachNewNodeToParent(parentNode, createdNode, isRightChild)
        }
        trickleUp(itemCount)
    }

    /**
     * Removes and returns the key of the top (root) node of the binary tree.
     * The top node is removed by swapping it with the last node in the tree
     * and then deleting the last node.
     *
     * @return The key of the removed top node, or null if the tree is empty.
     */
    fun removeTop(): Int? {
        val temp = this[1]?.key
        swapKeys(1, itemCount)
        deleteLast()
        return temp
    }

    /**
     * Swaps the keys of two nodes in the binary tree located at the specified indices.
     *
     * @param index1 The index of the first node whose key is to be swapped.
     * @param index2 The index of the second node whose key is to be swapped.
     */
    private fun swapKeys(
        index1: Int,
        index2: Int,
    ) {
        val temp = this[index1]?.key
        this[index1]?.key = this[index2]?.key
        this[index2]?.key = temp
    }

    /**
     * Deletes the last node in the tree and returns its key.
     * Decreases the total item count of the tree if the last node is successfully deleted.
     *
     * @return The key of the deleted last node, or null if the tree is empty or the last node does not exist.
     */
    private fun deleteLast(): Int? {
        val node = this[itemCount]
        node?.let {
            itemCount--
            return it.key
        }
        return null
    }

    /**
     * Prints the structure of the binary tree up to the specified number of nodes in a layer-order traversal format.
     *
     * @param n The number of nodes to be printed from the tree. Defaults to the current item count in the tree.
     */
    fun printTree(n: Int = itemCount) {
        if (n <= 0 || itemCount == 0) {
            println("Tree is empty")
            return
        }

        val nodesToPrint = minOf(n, itemCount)
        var currentLevel = 1
        var nodesInCurrentLevel = 1
        var nodesPrintedInLevel = 0

        print("Level 1: ")

        for (index in 1..nodesToPrint) {
            val node = this[index]
            val value = node?.key?.toString() ?: "null"
            print("$value ")

            nodesPrintedInLevel++

            // Check if we've printed all nodes for the current level
            if (nodesPrintedInLevel == nodesInCurrentLevel) {
                println() // New line after completing a level
                currentLevel++
                nodesInCurrentLevel *= 2 // Next level has double the nodes
                nodesPrintedInLevel = 0

                // Print level header if there are more nodes to print
                if (index < nodesToPrint) {
                    print("Level $currentLevel: ")
                }
            }
        }

        // Add final newline if the last level wasn't complete
        if (nodesPrintedInLevel > 0) {
            println()
        }
    }
}

fun main() {
    val tree = SimpleTree()
    for (i in 1..10) tree.insert(i)
    tree.printTree()
    println("Count: ${tree.itemCount}")
    println("\nRemoving top...")
    tree.removeTop()
    tree.printTree()
    println("Count: ${tree.itemCount}")
}
