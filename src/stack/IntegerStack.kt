package stack

/**
 * A stack implementation for integer values.
 * Operations push/pop have O(1) time complexity.
 *
 * @property maxSize Maximum size of the stack.
 */
class IntegerStack(
    private val maxSize: Int,
) {
    private val stackArray = IntArray(maxSize)
    private var top = -1

    /**
     * Checks if the stack is empty.
     */
    val isEmpty: Boolean
        get() = top == -1

    /**
     * Returns the current size of the stack.
     */
    val size: Int
        get() = top + 1

    /**
     * Pushes an integer onto the stack.
     *
     * @param value The integer to push
     * @return This stack instance for method chaining
     * @throws ArrayIndexOutOfBoundsException if stack is full
     */
    fun push(value: Int): IntegerStack {
        if (top >= maxSize - 1) {
            throw ArrayIndexOutOfBoundsException("Stack overflow")
        }
        stackArray[++top] = value
        return this
    }

    /**
     * Retrieves the top element without removing it.
     *
     * @return The top element
     * @throws NoSuchElementException if stack is empty
     */
    fun peek(): Int {
        if (isEmpty) {
            throw NoSuchElementException("Stack is empty")
        }
        return stackArray[top]
    }

    /**
     * Removes and returns the top element.
     *
     * @return The top element
     * @throws NoSuchElementException if stack is empty
     */
    fun pop(): Int {
        if (isEmpty) {
            throw NoSuchElementException("Stack is empty")
        }
        return stackArray[top--]
    }

    /**
     * Returns the element at the specified position without removing it.
     *
     * @param index The index of the element
     * @return The element as string
     * @throws IndexOutOfBoundsException if index is invalid
     */
    fun peekAt(index: Int): String {
        if (index < 0 || index > top) {
            throw IndexOutOfBoundsException("Index $index out of bounds for stack of size $size")
        }
        return stackArray[index].toString()
    }

    /**
     * Removes all elements and returns them as a list.
     *
     * @return List containing all stack elements in pop order
     */
    fun popAll(): List<Int> =
        buildList {
            while (!isEmpty) {
                add(pop())
            }
        }

    /**
     * Displays the stack contents from bottom to top.
     *
     * @param prefix Optional prefix to display before the stack
     */
    fun displayStack(prefix: String? = null) {
        prefix?.let { print(it) }
        print("Stack (bottom->top):\t")
        for (i in 0 until size) {
            print("${peekAt(i)} ")
        }
        println()
    }
}
