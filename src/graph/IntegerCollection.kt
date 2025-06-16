package graph

/**
 * Represents a collection of integers with methods to manage its state and contents.
 *
 * This interface defines operations for adding, removing, and peeking at integers in the collection.
 * It also provides properties to query the current state and size of the collection. The implementation
 * details, such as the underlying data structure or constraints (e.g., fixed size), are left to the
 * implementing class.
 */
interface IntegerCollection {
    val isEmpty: Boolean
    val isFull: Boolean
    val size: Int

    fun add(value: Int): IntegerCollection

    fun remove(): Int

    fun peek(): Int
}
