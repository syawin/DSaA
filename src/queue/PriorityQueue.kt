package queue

/**
 * A priority queue implementation that orders elements by their natural ordering.
 *
 * Elements are stored in descending order (largest at the front, smallest at the end).
 * The implementation provides O(n) insertion and O(1) removal of the minimum element.
 *
 * @param E The type of elements in this priority queue, must implement Comparable
 * @property maxSize The maximum number of elements the queue can hold
 */
class PriorityQueue<E : Comparable<E>>(
    private val maxSize: Int,
) {
    private val qArray: Array<Any?> = arrayOfNulls(maxSize)
    private var nItems: Int = 0

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

        if (nItems == 0) {
            qArray[nItems++] = item
        } else {
            i = nItems - 1
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
            nItems++
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
        return qArray[--nItems] as E
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
        return qArray[nItems - 1] as E
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

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    fun isEmpty(): Boolean = nItems == 0

    /**
     * Checks if the queue is full.
     *
     * @return true if the queue is full, false otherwise
     */
    fun isFull(): Boolean = nItems == maxSize

    /**
     * Returns the current number of items in the queue.
     *
     * @return The number of items in the queue
     */
    fun size(): Int = nItems

    /**
     * Returns an iterator over the elements in this queue.
     * Elements are returned in order from maximum to minimum.
     *
     * @return An iterator over the elements in this queue
     */
    fun iterator(): Iterator<E> =
        object : Iterator<E> {
            private var currentIndex = 0

            override fun hasNext(): Boolean = currentIndex < nItems

            @Suppress("UNCHECKED_CAST")
            override fun next(): E {
                if (!hasNext()) {
                    throw NoSuchElementException("No more elements in queue")
                }
                return qArray[currentIndex++] as E
            }
        }
}
