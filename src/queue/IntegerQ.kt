@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package queue

/**
 * A queue implementation for integer values.
 * Operations insert/remove have O(1) time complexity.
 *
 * @property maxSize Maximum size of the queue.
 */
class IntegerQ(
    private val maxSize: Int,
) {
    private val queueArray = IntArray(maxSize)
    private var front = 0
    private var rear = -1
    private var nItems = 0

    /**
     * Checks if the queue is empty.
     */
    val isEmpty: Boolean
        get() = nItems == 0

    /**
     * Checks if the queue is full.
     */
    val isFull: Boolean
        get() = nItems == maxSize

    /**
     * Returns the current size of the queue.
     */
    val size: Int
        get() = nItems

    /**
     * Inserts an integer at the rear of the queue.
     *
     * @param value The integer to insert
     * @return This queue instance for method chaining
     */
    fun insert(value: Int): IntegerQ {
        if (isFull) return this
        if (rear == maxSize - 1) {
            rear = -1
        }
        queueArray[++rear] = value
        nItems++
        return this
    }

    /**
     * Removes and returns the front element.
     *
     * @return The front element
     * @throws NoSuchElementException if the queue is empty
     */
    fun remove(): Int {
        if (isEmpty) {
            throw NoSuchElementException("Queue is empty")
        }
        val temp = queueArray[front++]
        if (front == maxSize) {
            front = 0
        }
        nItems--
        return temp
    }

    /**
     * Retrieves the front element without removing it.
     *
     * @return The front element
     * @throws NoSuchElementException if the queue is empty
     */
    fun peekFront(): Int {
        if (isEmpty) {
            throw NoSuchElementException("Queue is empty")
        }
        return queueArray[front]
    }

    /**
     * Decrements the value at the front of the queue.
     *
     * @return The decremented value
     * @throws NoSuchElementException if the queue is empty
     */
    fun decrementFront(): Int {
        if (isEmpty) {
            throw NoSuchElementException("Queue is empty")
        }
        return --queueArray[front]
    }

    /**
     * Displays the queue contents from front to rear.
     *
     * @param prefix Optional prefix to display before the queue
     */
    fun displayQueue(prefix: String? = null) {
        if (isEmpty) {
            println("${prefix ?: ""}Queue is empty")
            return
        }

        prefix?.let { print(it) }
        println("Front")
        var f = front
        val r = rear
        while (true) {
            println(queueArray[f])
            if (f == r) break
            f++
            if (f == maxSize) f = 0
        }
        println("Rear")
    }

    /**
     * Removes all elements and returns them as a list.
     *
     * @return List containing all queue elements in removal order
     */
    fun removeAll(): List<Int> =
        buildList {
            while (!isEmpty) {
                add(remove())
            }
        }
}
