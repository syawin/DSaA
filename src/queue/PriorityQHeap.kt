package queue

import heap.Heap
import tree.Node

internal class PriorityQHeap(
    val maxSize: Int,
) {
    var priorityHeap: Heap = Heap(maxSize)

    fun getnItems(): Int = priorityHeap.currentSize

    fun insert(key: Int) {
        priorityHeap.insert(key)
    }

    val isEmpty: Boolean
        get() = priorityHeap.isEmpty

    fun peek(): Node = priorityHeap.heap[0]

    fun remove(): Node = priorityHeap.remove()
}
