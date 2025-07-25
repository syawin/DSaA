package queue

import graph.IntegerCollection

/**
 * A circular queue implementation for storing integer values.
 *
 * @property maxSize specifies the maximum number of elements the queue can hold.
 */
class IntegerQ(
    private val maxSize: Int,
) : IntegerCollection {
    private val queueArray = IntArray(maxSize)
    private var front = 0
    private var rear = -1
    private var nItems = 0
    override val isFull: Boolean
        get() = nItems == maxSize

    override val isEmpty: Boolean
        get() = nItems == 0

    override val size: Int
        get() = nItems

    override fun add(value: Int): IntegerQ = insert(value)

    override fun remove(): Int {
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

    override fun peek(): Int = peekFront()

    override fun iterator(): Iterator<Int> =
        object : Iterator<Int> {
            private var index = front

            override fun hasNext(): Boolean = index != rear + 1 || index != front

            override fun next(): Int {
                if (!hasNext()) {
                    throw NoSuchElementException("End of iterator reached")
                }
                return queueArray[index++]
            }
        }

    fun insert(value: Int): IntegerQ {
        if (isFull) return this
        if (rear == maxSize - 1) {
            rear = -1
        }
        queueArray[++rear] = value
        nItems++
        return this
    }

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
