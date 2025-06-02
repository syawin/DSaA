package tree

import stack.StackGeneric

@Suppress("unused")
class ITree {
    private var root: INode? = null

    fun delete(key: Int): INode? {
        var parent: INode
        var curr: INode
        curr = root!!
        parent = curr
        var isLeftChild = true
        while (curr.getKey() != key) {
            parent = curr
            if (key < curr.getKey()) {
                isLeftChild = true
                curr = curr.getlChild()
            } else {
                isLeftChild = false
                curr = curr.getrChild()
            }
            if (curr == null) return null
        }
        val deleted = curr
        // case 0 children
        if (curr.getlChild() == null && curr.getrChild() == null) {
            if (curr === root) {
                root = null
            } else if (isLeftChild) {
                parent.setlChild(null)
            } else {
                parent.setrChild(null)
            }
        } else if (curr.getrChild() == null) {
            if (curr === root) {
                root = curr.getlChild()
            } else if (isLeftChild) {
                parent.setlChild(curr.getlChild())
            } else {
                parent.setrChild(curr.getlChild())
            }
        } else if (curr.getlChild() == null) {
            if (curr === root) {
                root = curr.getrChild()
            } else if (isLeftChild) {
                parent.setlChild(curr.getrChild())
            } else {
                parent.setrChild(curr.getrChild())
            }
        } else {
            val successor = getSuccessor(curr)
            if (curr === root) {
                root = successor
            } else if (isLeftChild) {
                parent.setlChild(successor)
            } else {
                parent.setrChild(successor)
            }
            successor.setlChild(curr.getlChild())
        }
        return deleted
    }

    @JvmOverloads
    fun displayTree(spaces: Int = 32) {
        val globalStack = StackGeneric<INode?>()
        globalStack.push(root)
        var nBlanks = spaces
        var isRowEmpty = false
        val lineBreak = StringBuilder()
        lineBreak.append("•".repeat(nBlanks * 2))
        println(lineBreak)

        while (!isRowEmpty) {
            val localStack = StackGeneric<INode?>()
            isRowEmpty = true
            for (i in 0 until nBlanks) {
                print(' ')
            }
            while (!globalStack.isEmpty) {
                val temp = globalStack.pop()
                if (temp != null) {
                    print(temp.getKey())
                    localStack.push(temp.getlChild())
                    localStack.push(temp.getrChild())
                    if (temp.getlChild() != null || temp.getrChild() != null) {
                        isRowEmpty = false
                    }
                } else {
                    print("--")
                    localStack.push(null)
                    localStack.push(null)
                }
                for (i in 0 until (nBlanks * 2) - 2) {
                    print(' ')
                }
            }
            println()
            nBlanks /= 2
            while (!localStack.isEmpty) {
                globalStack.push(localStack.pop())
            }
        }
        println(lineBreak)
    }

    fun find(key: Int): INode? {
        var current = root
        while (current != null && current.getKey() != key) {
            current =
                if (key < current.getKey()) {
                    current.getlChild()
                } else {
                    current.getrChild()
                }
        }
        return current
    }

    fun insert(
        key: Int,
        `val`: Double,
    ) {
        val insert = INode(key, `val`)
        if (root == null) {
            root = insert
        } else {
            var current = root
            var parent: INode?
            while (true) {
                parent = current
                if (key < current!!.getKey()) {
                    current = current.getlChild()
                    if (current == null) {
                        parent!!.setlChild(insert)
                        return
                    }
                } else {
                    current = current.getrChild()
                    if (current == null) {
                        parent!!.setrChild(insert)
                        return
                    }
                }
            }
        }
    }

    fun max(): INode? {
        var max: INode? = null
        var current = root
        while (current != null) {
            max = current
            current = current.getrChild()
        }
        return max
    }

    fun min(): INode? {
        var min: INode? = null
        var current = root
        while (current != null) {
            min = current
            current = current.getlChild()
        }
        return min
    }

    fun traverse(traverseType: Int) {
        when (traverseType) {
            1 -> {
                print("\nPreorder traversal: ")
                preOrder(root)
            }

            2 -> {
                print("\nInorder traversal")
                inOrder(root)
            }

            3 -> {
                print("\nPostorder traversal")
                postOrder(root)
            }
        }
    }

    fun inOrder(localRoot: INode?) {
        if (localRoot != null) {
            inOrder(localRoot.getlChild())
            println(localRoot.getKey().toString() + " ")
            inOrder(localRoot.getrChild())
        }
    }

    fun postOrder(localRoot: INode?) {
        if (localRoot != null) {
            postOrder(localRoot.getlChild())
            postOrder(localRoot.getrChild())
            println(localRoot.getKey().toString() + " ")
        }
    }

    fun preOrder(localRoot: INode?) {
        if (localRoot != null) {
            println(localRoot.getKey().toString() + " ")
            preOrder(localRoot.getlChild())
            preOrder(localRoot.getrChild())
        }
    }

    private fun getSuccessor(delNode: INode): INode {
        var parent = delNode
        var successor = delNode
        var curr = delNode.getrChild()
        while (curr != null) {
            parent = successor
            successor = curr
            curr = curr.getlChild()
        }
        if (successor !== delNode.getrChild()) {
            parent.setlChild(successor.getrChild())
            successor.setrChild(delNode.getrChild())
        }
        return successor
    }

    // DEMO
    private object TreeDemo {
        @JvmStatic
        fun main(args: Array<String>) {
            val tree = ITree()
            tree.insert(4, 2.0)
            tree.insert(3, 2.0)
            tree.insert(6, 2.0)
            tree.insert(5, 2.0)
            tree.displayTree(16)
            tree.displayTree()
        }
    } // DEMO end
}
