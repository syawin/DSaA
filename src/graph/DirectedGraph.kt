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
    theGraph.topologicalSort() // do the sort
}
