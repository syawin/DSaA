package queue

/**
 * A priority queue implementation that orders elements by their natural ordering.
 *
 * Elements are stored in descending order (largest at the front, smallest at the end).
 * The implementation provides O(n) insertion and O(1) removal of the minimum element.
 *
 * @param E The type of elements in this priority queue; it must implement Comparable
 * @property maxSize The maximum number of elements the queue can hold
 */
class PriorityQueue<E : Comparable<E>>(
    private val maxSize: Int,
) : Collection<E> {
    private val qArray: Array<Any?> = arrayOfNulls(maxSize)
    override var size: Int = 0

    /**
     * Checks if all elements in the specified collection are contained in this collection.
     */
    override fun containsAll(elements: Collection<E>): Boolean {
        for (element in elements) {
            if (!contains(element)) {
                return false
            }
        }
        return true
    }

    /**
     * Checks if the specified element is contained in this collection.
     */
    override fun contains(element: E): Boolean {
        for (i in indices) {
            @Suppress("UNCHECKED_CAST")
            if (element == (qArray[i] as E)) {
                return true
            }
        }
        return false
    }

    /**
     * Inserts an item into the priority queue maintaining the ordering.
     * Elements are arranged in descending order (largest at index 0).
     * O(n) time complexity.
     *
     * @param item The item to be inserted
     * @throws IllegalStateException if the queue is full
     */
    fun insert(item: E) {
        if (isFull()) {
            throw IllegalStateException("Queue is full")
        }

        var i: Int

        if (isEmpty()) {
            qArray[size++] = item
        } else {
            i = size - 1
            // Shift smaller items to the right
            while (i >= 0) {
                @Suppress("UNCHECKED_CAST")
                if (item > (qArray[i] as E)) {
                    qArray[i + 1] = qArray[i]
                    i--
                } else {
                    break
                }
            }
            qArray[i + 1] = item
            size++
        }
    }

    /**
     * Removes and returns the minimum item from the queue.
     * O(1) time complexity.
     *
     * @return The minimum item in the queue
     * @throws NoSuchElementException if the queue is empty
     */
    @Suppress("UNCHECKED_CAST")
    fun remove(): E {
        if (isEmpty()) {
            throw NoSuchElementException("Queue is empty")
        }
        return qArray[--size] as E
    }

    /**
     * Removes and returns the item at the specified index in the queue.
     * The subsequent items are shifted to maintain the order.
     *
     * @param n The index of the item to remove
     * @return The removed item
     * @throws NoSuchElementException If the queue is empty
     * @throws IndexOutOfBoundsException If the index is out of bounds
     */
    fun remove(n: Int): E {
        if (isEmpty()) {
            throw NoSuchElementException("Queue is empty")
        }
        if (n < 0 || n >= size) {
            throw IndexOutOfBoundsException("Index $n is out of bounds for queue of size $size")
        }
        @Suppress("UNCHECKED_CAST")
        val item = qArray[n] as E
        for (i in n until size) {
            qArray[i] = qArray[i + 1]
        }
        size--
        return item
    }

    /**
     * Returns the minimum item without removing it.
     * O(1) time complexity.
     *
     * @return The minimum item in the queue
     * @throws NoSuchElementException if the queue is empty
     */
    @Suppress("UNCHECKED_CAST")
    fun peekMin(): E {
        if (isEmpty()) {
            throw NoSuchElementException("Queue is empty")
        }
        return qArray[size - 1] as E
    }

    /**
     * Returns the maximum item without removing it.
     * O(1) time complexity.
     *
     * @return The maximum item in the queue
     * @throws NoSuchElementException if the queue is empty
     */
    @Suppress("UNCHECKED_CAST")
    fun peekMax(): E {
        if (isEmpty()) {
            throw NoSuchElementException("Queue is empty")
        }
        return qArray[0] as E
    }

    fun peek(n: Int): E {
        return get(n)
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    @Suppress("ReplaceSizeZeroCheckWithIsEmpty")
    override fun isEmpty(): Boolean = size == 0

    /**
     * Checks if the queue is full.
     *
     * @return true if the queue is full, false otherwise
     */
    fun isFull(): Boolean = size == maxSize

    override fun iterator(): Iterator<E> =
        object : Iterator<E> {
            private var currentIndex = 0

            override fun hasNext(): Boolean = currentIndex < size

            @Suppress("UNCHECKED_CAST")
            override fun next(): E {
                if (!hasNext()) {
                    throw NoSuchElementException("No more elements in queue")
                }
                return qArray[currentIndex++] as E
            }
        }

    /**
     * Retrieves the element at the specified index in the priority queue.
     *
     * @param index The index of the element to retrieve.
     * @return The element at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of the valid range (0 until size).
     */
    operator fun get(index: Int): E {
        if (index < 0 || index >= size) {
            throw IndexOutOfBoundsException("Index $index is out of bounds for queue of size $size")
        }
        @Suppress("UNCHECKED_CAST")
        return qArray[index] as E
    }
}
