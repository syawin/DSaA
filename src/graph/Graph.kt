@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package graph

import queue.IntegerQ
import stack.IntegerStack

open class Graph(
    val maxSize: Int,
) {
    var vertexList = arrayOfNulls<Vertex?>(maxSize)
    var adjMatrix = Array(maxSize) { Array(maxSize) { false } }
    var numVertex = 0

    fun addVertex(label: Char) {
        vertexList[numVertex++] = Vertex(label)
    }

    open fun addEdge(
        start: Int,
        end: Int,
    ) {
        adjMatrix[start][end] = true
        adjMatrix[end][start] = true
    }

    fun displayVertex(vertIndex: Int) {
        print("${vertexList[vertIndex]?.label} ")
    }

    /**
     * Performs depth-first search using a stack
     */
    fun depthFirstSearch() {
        val collection = IntegerStack(maxSize)
        vertexList[0]?.wasVisited = true
        displayVertex(0)
        collection.add(0)
        while (!collection.isEmpty) {
            val currentVertex = collection.peek()

            val adjacentVertex = getAdjacentUnvisitedVertexIndex(currentVertex)

            if (adjacentVertex == -1) {
                collection.remove()
            } else {
                vertexList[adjacentVertex]?.wasVisited = true
                displayVertex(adjacentVertex)
                collection.add(adjacentVertex)
            }
        }
        println()
        resetFlags()
    }

    fun minimumSpanningTree() {
        val collection = IntegerStack(maxSize)
        vertexList[0]?.wasVisited = true
        collection.add(0)

        while (!collection.isEmpty) {
            val currentVertex = collection.peek()
            val v = getAdjacentUnvisitedVertexIndex(currentVertex)
            if (v == -1) {
                collection.remove()
            } else {
                vertexList[v]?.wasVisited = true
                collection.add(v)

                displayVertex(currentVertex)
                displayVertex(v)
                print(" ")
            }
        }
        println()
        resetFlags()
    }

    /**
     * Performs breadth-first search using a queue
     */
    fun breadthFirstSearch() {
        val collection = IntegerQ(maxSize)
        vertexList[0]?.wasVisited = true
        displayVertex(0)
        collection.add(0)
        var v2: Int
        while (!collection.isEmpty) {
            val currentVertex = collection.remove()
            while ((getAdjacentUnvisitedVertexIndex(currentVertex).also { v2 = it }) != -1) {
                vertexList[v2]?.wasVisited = true
                displayVertex(v2)
                collection.add(v2)
            }
        }
        println()
        resetFlags()
    }

    private fun resetFlags() {
        for (i in 0 until numVertex) vertexList[i]?.wasVisited = false
    }

    private fun getAdjacentUnvisitedVertexIndex(vertIndex: Int): Int {
        for (i in 0 until numVertex) {
            if (adjMatrix[vertIndex][i] && (vertexList[i]?.wasVisited) == false) return i
        }
        return -1
    }
}

fun main() {
    val graph = Graph(6)
    graph.addVertex('A') // 0
    graph.addVertex('B') // 1
    graph.addVertex('C') // 2
    graph.addVertex('D') // 3
    graph.addVertex('E') // 4
    graph.addEdge(0, 1) // AB
    graph.addEdge(1, 2) // BC
    graph.addEdge(0, 3) // AD
    graph.addEdge(3, 4) // DE

    print("DFS Visits: ")
    graph.depthFirstSearch()

    print("BFS Visits: ")
    graph.breadthFirstSearch()

    print("MST Visits: ")
    graph.minimumSpanningTree()
}
