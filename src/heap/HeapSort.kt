package heap

import tree.Node
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object HeapSort {
    private val reader: BufferedReader by lazy { BufferedReader(InputStreamReader(System.`in`)) }

    @JvmStatic
    fun main(args: Array<String>) {
        print("Enter number of items: ")
        val size = readInt()
        val heap = Heap(size)
        val uniqueNumbers = HashSet<Int>()

        repeat(size) { i ->
            var random: Int
            do {
                random = (Math.random() * 100).toInt() + 1
            } while (!uniqueNumbers.add(random))
            heap.insertAt(i, Node.Builder(random, null).build())
            heap.incrementSize()
        }

        print("Random: ")
        heap.displayArray()

        for (i in (size / 2 - 1) downTo 0) {
            heap.trickleDown(i)
        }

        print("Heap: ")
        heap.displayArray()
        heap.displayHeap()

        for (i in (size - 1) downTo 0) {
            val bigNode = heap.remove()
            heap.insertAt(i, bigNode)
        }
        print("Sorted: ")
        heap.displayArray()
    }

    private fun readInt(): Int = readLineFromUser().toInt()

    private fun readLineFromUser(): String =
        try {
            reader.readLine() ?: throw RuntimeException("No input provided")
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
}
