@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package graph

import stack.IntegerStack

/**
 * A class representing a graph data structure supporting basic vertex and edge management,
 * as well as depth-first search traversal.
 *
 * The graph uses an adjacency matrix to store connections between vertices and supports
 * undirected edges. Vertices are represented by the Vertex class, and the graph can hold
 * up to a maximum number of vertices determined by the `maxSize` parameter.
 *
 * @property maxSize The maximum number of vertices in the graph.
 * @property vertexList An array used to store the vertices of the graph.
 * @property adjMatrix A 2D array representing the adjacency matrix of the graph, where each
 * entry indicates whether an edge exists between two vertices.
 * @property numVertex The current number of vertices in the graph.
 */
class Graph(
    val maxSize: Int,
) {
    var vertexList = arrayOfNulls<Vertex?>(maxSize)
    var adjMatrix = Array(maxSize) { Array(maxSize) { false } }
    var numVertex = 0
    private var theStack = IntegerStack(maxSize)

    /**
     * Adds a new vertex to the graph with the specified label.
     *
     * @param label The label of the vertex to be added
     */
    fun addVertex(label: Char) {
        vertexList[numVertex++] = Vertex(label)
    }

    /**
     * Adds an edge between two vertices in the graph.
     *
     * This method updates the adjacency matrix to indicate a bidirectional connection
     * between the given vertices.
     *
     * @param start The index of the starting vertex
     * @param end The index of the ending vertex
     */
    fun addEdge(
        start: Int,
        end: Int,
    ) {
        adjMatrix[start][end] = true
        adjMatrix[end][start] = true
    }

    /**
     * Displays the label of the vertex at the specified index.
     *
     * The method retrieves the vertex label from the `vertexList` array
     * and prints it to the standard output.
     *
     * @param vertIndex The index of the vertex to display
     */
    fun displayVertex(vertIndex: Int) {
        print("${vertexList[vertIndex]?.label} ")
    }

    /**
     * Performs a depth-first search (DFS) traversal on the graph starting from the first vertex.
     *
     * The method uses a stack-based approach to traverse the graph and visit all reachable vertices
     * from the starting vertex (index 0). During the traversal:
     * - Each visited vertex is marked as visited to prevent revisiting.
     * - Each visited vertex's label is displayed.
     * - Once all adjacent unvisited vertices of a vertex are visited, the method backtracks
     *   using the stack.
     *
     * After completing the traversal, the `wasVisited` flags of all vertices are reset to `false`.
     *
     * Implementation Details:
     * - The traversal begins at vertex 0.
     * - Uses the `getAdjacentUnvisitedVertexIndex` method to find unvisited adjacent vertices.
     * - Utilizes the stack to facilitate backtracking when no unvisited adjacent vertices remain.
     * - The `resetFlags` method is called at the end to prepare the graph for future traversals.
     */
    fun depthFirstSearch() {
        vertexList[0]?.wasVisited = true
        displayVertex(0)
        theStack.push(0)
        while (!theStack.isEmpty) {
            val v = getAdjacentUnvisitedVertexIndex(theStack.peek())
            if (v == -1) {
                theStack.pop()
            } else {
                vertexList[v]?.wasVisited = true
                displayVertex(v)
                theStack.push(v)
            }
        }
        println()
        resetFlags()
    }

    /**
     * Resets the `wasVisited` flags of all vertices in the graph to `false`.
     *
     * This function iterates through the `vertexList` array and sets the
     * `wasVisited` property of each vertex to `false`, ensuring that the
     * graph is ready for a fresh traversal or search operation.
     *
     * This method is private and intended for internal use only, typically
     * used after a traversal to reset the state of the graph.
     */
    private fun resetFlags() {
        for (i in 0 until numVertex) vertexList[i]?.wasVisited = false
    }

    /**
     * Finds the index of the first adjacent unvisited vertex for the given vertex index.
     *
     * The method iterates through the adjacency matrix for the given vertex index
     * and checks if there exists an adjacent vertex that hasn't been visited.
     * If such a vertex is found, its index is returned. If no unvisited adjacent
     * vertex exists, the method returns -1.
     *
     * @param vertIndex The index of the vertex whose adjacent vertices are to be checked
     * @return The index of the first adjacent unvisited vertex, or -1 if no such vertex exists
     */
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
    print("Visits: ")
    graph.depthFirstSearch()
    println()
}
