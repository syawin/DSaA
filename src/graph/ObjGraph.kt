package graph

import queue.PriorityQueue

class ObjGraph(
    maxSize: Int,
) : AbstractGraph(maxSize) {
    var vertexList = arrayOfNulls<Vertex?>(maxSize)

    // Store edges in a priority queue. Really the PQ should be dynamic so that we can add an arbitrary number of
    //  edges.
    var adjList = Array(maxSize) { PriorityQueue<ObjEdge>(maxSize) }
    var numVertex = 0

    override fun addVertex(label: String) {
        vertexList[numVertex++] = Vertex(label)
    }

    override fun addEdge(
        start: Int,
        end: Int,
    ) {
        addWeightedEdge(start, end)
    }

    fun addWeightedEdge(
        start: Int,
        end: Int,
        weight: Int = 1,
    ) {
        if (start < 0 || end < 0 || start >= numVertex || end >= numVertex) {
            throw IndexOutOfBoundsException("Vertex indices must be between 0 and ${numVertex - 1}")
        }

        val s = vertexList[start]!!
        val d = vertexList[end]!!
        val newEdge = ObjEdge(s, d, weight)
        adjList[start].insert(newEdge)
    }

    fun getEdge(
        start: Int,
        end: Int,
    ): ObjEdge? {
        if (start < 0 || end < 0 || start >= numVertex || end >= numVertex) {
            throw IndexOutOfBoundsException("Vertex indices must be between 0 and ${numVertex - 1}")
        }
        for (edge in adjList[start]) {
            if (edge.destination.label == vertexList[end]?.label) {
                return edge
            }
        }
        return null
    }

    /**
     * Display the adjacency matrix with weights instead of just connections
     */
    fun displayGraph() {
        val labelWidth = vertexList.take(numVertex).maxOf { it?.label?.length ?: 1 }
        val weightWidth =
            adjList.take(numVertex).maxOfOrNull {
                it
                    .peekMax()
                    ?.weight
                    ?.toString()
                    ?.length ?: 1
            } ?: 1
        val colWidth = maxOf(labelWidth, weightWidth) + 1 // Add spacing buffer // Print header row
        print("".padEnd(colWidth)) // Top-left corner cell
        for (i in 0 until numVertex) {
            print(vertexList[i]?.label?.padEnd(colWidth))
        }
        println()

        // Print matrix rows
        for (i in 0 until numVertex) { // Row header
            print(vertexList[i]?.label?.padEnd(colWidth)) // Row entries
            for (j in 0 until numVertex) {
                val value = getEdge(i, j)?.weight?.toString() ?: "-"
                print(value.padEnd(colWidth))
            }
            println()
        }
    }
}

fun main() {
    val graph = ObjGraph(9)
    graph.addVertex("A") // 0
    graph.addVertex("B") // 1
    graph.addVertex("C") // 2
    graph.addVertex("D") // 3
    graph.addVertex("E") // 4
    graph.addVertex("F") // 5
    graph.addVertex("G") // 6
    graph.addVertex("H") // 7
    graph.addVertex("I") // 8

    graph.displayGraph()
    println()

    graph.addWeightedEdge(0, 1, 40) // AB
    graph.addWeightedEdge(0, 3, 70) // AD
    graph.addWeightedEdge(1, 2, 30) // BC
    graph.addWeightedEdge(1, 4, 60) // BE
    graph.addWeightedEdge(2, 5, 20) // CF
    graph.addWeightedEdge(3, 4, 50) // DE
    graph.addWeightedEdge(3, 6, 80) // DG
    graph.addWeightedEdge(4, 5, 40) // EF
    graph.addWeightedEdge(4, 7, 30) // EH
    graph.addWeightedEdge(5, 8, 60) // FI
    graph.addWeightedEdge(6, 7, 20) // GH
    graph.addWeightedEdge(7, 8, 50) // HI
    println("Display Graph: ")
    graph.displayGraph()
}
