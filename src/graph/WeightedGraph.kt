package graph

import queue.PriorityQueue

private const val MAX = Int.MAX_VALUE

class WeightedGraph(
    maxSize: Int,
) : Graph(maxSize) {
    // Store edge weights in a matrix
    val weightMatrix = Array(maxSize) { Array(maxSize) { MAX } }

    // Store edges for minimum spanning tree algorithms
    val edges = mutableListOf<Edge>()

    /**
     * Adds a weighted edge between two vertices
     */
    fun addEdge(
        start: Int,
        end: Int,
        weight: Int,
    ) {
        // Call the parent class method to update adjacency structures
        super.addEdge(start, end)

        // Store the weight in our weight matrix (both directions for undirected graph)
        weightMatrix[start][end] = weight
        weightMatrix[end][start] = weight

        // Store the edge object for algorithms that need it
        edges.add(Edge(start, end, weight))
    }

    /**
     * Override the parent class method to provide a weighted version
     */
    override fun addEdge(
        start: Int,
        end: Int,
    ) {
        // Default weight of 1 if not specified
        addEdge(start, end, 1)
    }

    override fun minimumSpanningTree() {
        val thePQ = PriorityQueue<Edge>(numVertex)
        var currentVertex = 0
        numInTree = 0
        while (numInTree < numVertex - 1) {
            vertexList[currentVertex]?.isInTree = true
            numInTree++
            for (i in 0 until numVertex) {
                if (i == currentVertex) continue
                if (vertexList[i]?.isInTree == true) continue
                val distance = weightMatrix[currentVertex][i]
                if (distance == MAX) continue
                putInPQ(i, distance, currentVertex, thePQ)
            }
            if (thePQ.isEmpty()) {
                println("Graph is not connected")
                return
            }
            val theEdge = thePQ.remove()
            val sourceVertex = theEdge.source
            currentVertex = theEdge.destination
            print(vertexList[sourceVertex]?.label + vertexList[currentVertex]?.label + " ")
        }
        resetVertices()
    }

    private fun resetVertices() {
        for (vertex in vertexList) vertex?.reset()
    }

    private fun putInPQ(
        newVertex: Int,
        newDistance: Int,
        currentVertex: Int,
        thePQ: PriorityQueue<Edge>,
    ) {
        val queueIndex = findEdgeIndexByDestination(newVertex, thePQ)
        if (queueIndex != -1) {
            val temp = thePQ.peek(queueIndex)
            val oldDistance = temp.weight
            if (oldDistance > newDistance) {
                thePQ.remove(queueIndex)
                val theEdge = Edge(currentVertex, newVertex, newDistance)
                thePQ.insert(theEdge)
            }
        } else {
            val theEdge = Edge(currentVertex, newVertex, newDistance)
            thePQ.insert(theEdge)
        }
    }

    /**
     * Display the adjacency matrix with weights instead of just connections
     */
    fun displayWeightMatrix() {
        // Determine max width needed for any label
        val labelWidth = vertexList.take(numVertex).maxOf { it?.label?.length ?: 1 }
        val weightWidth =
            vertexList
                .take(numVertex)
                .flatMapIndexed { i, _ ->
                    (0 until numVertex).map { j ->
                        if (adjMatrix[i][j]) weightMatrix[i][j].toString().length else 1
                    }
                }.maxOrNull() ?: 1
        val colWidth = maxOf(labelWidth, weightWidth) + 1 // Add spacing buffer

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
                val value = if (adjMatrix[i][j]) weightMatrix[i][j].toString() else "-"
                print(value.padEnd(colWidth))
            }
            println()
        }
    }

    fun findEdge(
        destinationVert: Int,
        searchPQ: PriorityQueue<Edge>,
    ): Edge? {
        for (edge in searchPQ) {
            if (edge.destination == destinationVert) return edge
        }
        return null
    }

    fun findEdgeIndexByDestination(
        destinationVert: Int,
        searchPQ: PriorityQueue<Edge>,
    ): Int {
        for (i in searchPQ.indices) {
            if (searchPQ[i].destination == destinationVert) return i
        }
        return -1
    }
}
