@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package graph

import linkedlist.LinkedList
import queue.IntegerQ
import stack.IntegerStack

open class Graph(
    val maxSize: Int,
) {
    var vertexList = arrayOfNulls<Vertex?>(maxSize)
    var adjMatrix = Array(maxSize) { Array(maxSize) { false } }
    var adjList = Array(maxSize) { LinkedList() }
    var numVertex = 0

    fun addVertex(label: String) {
        vertexList[numVertex++] = Vertex(label)
    }

    open fun addEdge(
        start: Int,
        end: Int,
    ) {
        adjList[start].insertFirst(end.toLong(), 0.0)
        adjList[end].insertFirst(start.toLong(), 0.0)
        adjMatrix[start][end] = true
        adjMatrix[end][start] = true
    }

    fun displayVertex(vertIndex: Int) {
        print("${vertexList[vertIndex]?.label} ")
    }

    /**
     * Performs depth-first search using a stack
     */
    fun depthFirstSearch(startIndex: Int) {
        val collection = IntegerStack(maxSize)
        vertexList[startIndex]?.wasVisited = true
        displayVertex(startIndex)
        collection.add(startIndex)
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

    fun depthFirstSearchAdjList() {
        val collection = IntegerStack(maxSize)
        vertexList[0]?.wasVisited = true
        displayVertex(0)
        collection.add(0)
        while (!collection.isEmpty) {
            val currentVertex = collection.peek()
            val adjacentVertex = getAdjacentUnvisitedVertexIndexAdjList(currentVertex)
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

    fun minimumSpanningTreeBFS() {
        val collection = IntegerQ(maxSize)
        vertexList[0]?.wasVisited = true
        collection.add(0)
        var v2: Int
        while (!collection.isEmpty) {
            val currentVertex = collection.remove()
            while ((getAdjacentUnvisitedVertexIndex(currentVertex).also { v2 = it }) != -1) {
                vertexList[v2]?.wasVisited = true
                collection.add(v2)
                displayVertex(currentVertex)
                displayVertex(v2)
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

    private fun getAdjacentUnvisitedVertexIndexAdjList(vertIndex: Int): Int {
        for (node in adjList[vertIndex]) {
            if (vertexList[node.key.toInt()]?.wasVisited == false) return node.key.toInt()
        }
        return -1
    }

    /**
     * Displays the adjacency matrix with vertex labels as headers.
     * Allows customization of symbols for connections and empty spaces.
     *
     * @param connectionSymbol Symbol to represent a connection (default: "1")
     * @param emptySymbol Symbol to represent no connection (default: " ")
     */
    fun displayAdjacencyMatrix(
        connectionSymbol: String = "1",
        emptySymbol: String = " ",
    ) {
        // Determine max width needed for any label
        val labelWidth = vertexList.take(numVertex).maxOf { it?.label?.length ?: 1 }
        val colWidth = labelWidth + 1 // Add spacing buffer

        // Print header row
        print("".padEnd(colWidth)) // Top-left corner cell
        for (i in 0 until numVertex) {
            print(vertexList[i]?.label?.padEnd(colWidth))
        }
        println()

        // Print matrix rows
        for (i in 0 until numVertex) {
            // Row header
            print(vertexList[i]?.label?.padEnd(colWidth))
            // Row entries
            for (j in 0 until numVertex) {
                val symbol = if (adjMatrix[i][j]) connectionSymbol else emptySymbol
                print(symbol.padEnd(colWidth))
            }
            println()
        }
    }
}

fun main() {
    val graph = Graph(9)
    graph.addVertex("A") // 0
    graph.addVertex("B") // 1
    graph.addVertex("C") // 2
    graph.addVertex("D") // 3
    graph.addVertex("E") // 4
    graph.addVertex("F") // 5
    graph.addVertex("G") // 6
    graph.addVertex("H") // 7
    graph.addVertex("I") // 8

    graph.addEdge(0, 1) // AB
    graph.addEdge(0, 3) // AD
    graph.addEdge(1, 2) // BC
    graph.addEdge(1, 4) // BE
    graph.addEdge(2, 5) // CF
    graph.addEdge(3, 4) // DE
    graph.addEdge(3, 6) // DG
    graph.addEdge(4, 5) // EF
    graph.addEdge(4, 7) // EH
    graph.addEdge(5, 8) // FI
    graph.addEdge(6, 7) // GH
    graph.addEdge(7, 8) // HI

    print("DFS Visits: ")
    graph.depthFirstSearch(0)

    print("DFS Visits (adjacency list): ")
    graph.depthFirstSearchAdjList()

    print("BFS Visits: ")
    graph.breadthFirstSearch()

    print("MST Visits (DFS): ")
    graph.minimumSpanningTree()

    print("MST Visits (BFS): ")
    graph.minimumSpanningTreeBFS()

    println("\nAdjacency Matrix:")
    graph.displayAdjacencyMatrix(emptySymbol = "-")
}
