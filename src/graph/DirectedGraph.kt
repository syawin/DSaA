@file:Suppress("DuplicatedCode")

package graph

open class DirectedGraph(
    maxSize: Int,
) : Graph(maxSize) {
    private var sortedArray = Array(maxSize) { "" }

    override fun addEdge(
        start: Int,
        end: Int,
    ) {
        adjMatrix[start][end] = true
    }

    /**
     * Performs a topological sort on a DAG. Identifies and removes vertices with no successors,
     * adding them to a sorted order. Empties graph when run. Aborts if cycle detected.
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

    /**
     * Applies Warshall's algorithm to compute the transitive closure of a directed graph.
     * Modifies the adjacency matrix in-place to show reachability between vertices.
     * If there is any path from vertex i to j (direct or indirect), adjMatrix[i][j] will be true.
     * O(v^3) time complexity; v = # vertices
     */
    fun computeTransitiveClosure() {
        // iterate thru the rows of the adjacency matrix.
        for (i in 0 until numVertex) {
            // iterate thru the columns of the current row matrix[i]
            for (j in 0 until numVertex) {
                // if a 1 is found, start transitive closure loop.
                if (adjMatrix[i][j]) {
                    for (k in 0 until numVertex) {
                        if (adjMatrix[k][i]) {
                            adjMatrix[k][j] = true
                        }
                    }
                }
            }
        }
    }
}

fun main() {
    val theGraph = DirectedGraph(8)
    theGraph.addVertex("A")
    theGraph.addVertex("B")
    theGraph.addVertex("C")
    theGraph.addVertex("D")
    theGraph.addVertex("E")
    theGraph.addVertex("F")
    theGraph.addVertex("G")
    theGraph.addVertex("H")
    theGraph.addEdge(0, 3) // AD
    theGraph.addEdge(0, 4) // AE
    theGraph.addEdge(1, 4) // BE
    theGraph.addEdge(2, 5) // CF
    theGraph.addEdge(3, 6) // DG
    theGraph.addEdge(4, 6) // EG
    theGraph.addEdge(5, 7) // FH
    theGraph.addEdge(6, 7) // GH

    theGraph.connectivityTable()
    println("\nAdjacency Matrix:")
    theGraph.displayAdjacencyMatrix()

    theGraph.computeTransitiveClosure()
    println("\nAdjacency Matrix With Transitive Closure:")
    theGraph.displayAdjacencyMatrix()

    theGraph.topologicalSort() // do the sort
}
