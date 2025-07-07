package graph

class DirectedGraph(
    maxSize: Int,
) : Graph(maxSize) {
    private var sortedArray = CharArray(maxSize)

    override fun addEdge(
        start: Int,
        end: Int,
    ) {
        adjMatrix[start][end] = true
    }

    /**
     * Performs a topological sort on a directed acyclic graph (DAG) represented by the current instance.
     * The method sequentially identifies and removes vertices with no successors, adding them to a
     * sorted order. If a cycle is detected in the graph, the operation is aborted and a message is printed.
     * Note that running this method empties the graph of vertices when it runs.
     *
     * The algorithm works as follows:
     * 1. Repeatedly finds a vertex with no outgoing edges (no successors).
     * 2. Appends the label of the vertex to a sorted array.
     * 3. Deletes the vertex, along with its associated edges in the adjacency structure.
     * 4. Continues until all vertices are processed or a cycle is detected.
     *
     * If the graph contains a cycle, "The graph contains cycles." is printed and the method returns
     * without completing the sort.
     *
     * Results are printed in the form "Topological Sort: <sorted_labels>".
     * This method assumes the graph is represented using adjacency structures and vertex lists.
     */
    fun topologicalSort() {
        val totalVertices = numVertex
        while (numVertex > 0) {
            val currentVertex = noSuccessors()
            if (currentVertex == -1) {
                println("The graph contains cycles.")
                return
            }
            sortedArray[numVertex - 1] = vertexList[currentVertex]!!.label
            deleteVertex(currentVertex)
        }
        print("Topological Sort: ")
        for (i in 0 until totalVertices) print(sortedArray[i])
        println()
    }

    fun connectivityTable() {
        for (i in 0 until numVertex) {
            depthFirstSearch(i)
        }
    }

    private fun deleteVertex(delVertex: Int) {
        if (delVertex != numVertex - 1) {
            // delete from vertexList
            for (i in delVertex until numVertex - 1) {
                vertexList[i] = vertexList[i + 1]
            }
            // delete row from adjMatrix
            for (row in delVertex until numVertex - 1) {
                moveRowUp(row, numVertex)
            }
            // delete col from adjMatrix
            for (col in delVertex until numVertex - 1) {
                moveColLeft(col, numVertex - 1)
            }
        }
        numVertex--
    }

    private fun moveColLeft(
        col: Int,
        length: Int,
    ) {
        for (row in 0 until length) {
            adjMatrix[row][col] = adjMatrix[row][col + 1]
        }
    }

    private fun moveRowUp(
        row: Int,
        length: Int,
    ) {
        for (col in 0 until length) {
            adjMatrix[row][col] = adjMatrix[row + 1][col]
        }
    }

    private fun noSuccessors(): Int {
        var isEdge: Boolean
        for (row in 0 until numVertex) {
            isEdge = false
            for (col in 0 until numVertex) {
                if (adjMatrix[row][col]) {
                    isEdge = true
                    break
                }
            }
            if (!isEdge) return row
        }
        return -1
    }
}

fun main() {
    val theGraph = DirectedGraph(8)
    theGraph.addVertex('A')
    theGraph.addVertex('B')
    theGraph.addVertex('C')
    theGraph.addVertex('D')
    theGraph.addVertex('E')
    theGraph.addVertex('F')
    theGraph.addVertex('G')
    theGraph.addVertex('H')
    theGraph.addEdge(0, 3) // AD
    theGraph.addEdge(0, 4) // AE
    theGraph.addEdge(1, 4) // BE
    theGraph.addEdge(2, 5) // CF
    theGraph.addEdge(3, 6) // DG
    theGraph.addEdge(4, 6) // EG
    theGraph.addEdge(5, 7) // FH
    theGraph.addEdge(6, 7) // GH

    theGraph.connectivityTable()
    theGraph.topologicalSort() // do the sort
}
