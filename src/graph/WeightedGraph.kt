package graph

import queue.PriorityQueue

private const val INF = Int.MAX_VALUE

class WeightedGraph(
    maxSize: Int,
) : DirectedGraph(maxSize) {
    // Store edge weights in a matrix
    // suggest we could make this less fragile by using `null` in place of a constant.
    //  that keeps us safe from overflow while keeping the data meaningful.
    val weightMatrix = Array(maxSize) { Array(maxSize) { INF } }

    // Store edges for minimum spanning tree algorithms
    val edges = mutableListOf<IntEdge>()

    /**
     * Adds a weighted edge between two vertices
     */
    fun addEdge(
        start: Int,
        end: Int,
        weight: Int,
    ) { // Call the parent class method to update adjacency structures
        super.addEdge(start, end)

        // Store the weight in our weight matrix (both directions for undirected graph)
        weightMatrix[start][end] = weight

        // Store the edge object for algorithms that need it
        edges.add(IntEdge(start, end, weight))
    }

    /**
     * Override the parent class method to provide a weighted version
     */
    override fun addEdge(
        start: Int,
        end: Int,
    ) { // Default weight of 1 if not specified
        addEdge(start, end, 1)
    }

    override fun minimumSpanningTree() {
        val thePQ = PriorityQueue<IntEdge>(numVertex)
        var currentVertex = 0
        numInTree = 0
        while (numInTree < numVertex - 1) {
            vertexList[currentVertex]?.isInTree = true
            numInTree++
            for (i in 0 until numVertex) {
                if (i == currentVertex) continue
                if (vertexList[i]?.isInTree == true) continue
                val distance = weightMatrix[currentVertex][i]
                if (distance == INF) continue
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
        thePQ: PriorityQueue<IntEdge>,
    ) {
        val queueIndex = findEdgeIndexByDestination(newVertex, thePQ)
        if (queueIndex != -1) {
            val temp = thePQ.peek(queueIndex)
            val oldDistance = temp.weight
            if (oldDistance > newDistance) {
                thePQ.remove(queueIndex)
                val theEdge = IntEdge(currentVertex, newVertex, newDistance)
                thePQ.insert(theEdge)
            }
        } else {
            val theEdge = IntEdge(currentVertex, newVertex, newDistance)
            thePQ.insert(theEdge)
        }
    }

    private data class DistPar(
        var parent: Int,
        var dist: Int,
    )

    fun floydWarshall() {
        TODO()
    }

    fun dijkstra(startingIndex: Int = 0) {
        var currentVertex: Int
        var startToCurrent: Int
        vertexList[startingIndex]?.isInTree = true
        numInTree = 1
        val sPath: Array<DistPar> =
            Array(numVertex) { i ->
                DistPar(startingIndex, weightMatrix[startingIndex][i])
            }
        var indexMin: Int
        while (numInTree < numVertex) {
            indexMin = getMin(sPath)
            val minDist = sPath[indexMin]

            if (minDist.dist == INF) {
                println("No path from ${vertexList[startingIndex]?.label} to any other vertex")
                break
            } else {
                currentVertex = indexMin
                startToCurrent = sPath[indexMin].dist
            }
            vertexList[currentVertex]?.isInTree = true
            numInTree++
            updateShortestPath(startToCurrent, currentVertex, sPath)
        }
        displayPaths(sPath)

        numInTree = 0
        resetVertices()
    }

    private fun displayPaths(sPath: Array<DistPar>) {
        for (i in 0 until numVertex) {
            print(vertexList[i]?.label + "=")
            print(
                when (sPath[i].dist) {
                    Int.MAX_VALUE -> "inf"
                    else -> sPath[i].dist.toString()
                },
            )
            print("( ${vertexList[sPath[i].parent]?.label} ) ")
        }
        println()
    }

    private fun updateShortestPath(
        startToCurrent: Int,
        currentVert: Int,
        sPath: Array<DistPar>,
    ) {
        var col = 0
        while (col < numVertex) {
            if (vertexList[col]!!.isInTree) {
                col++
                continue
            }
            val currentToFrontier = weightMatrix[currentVert][col]
            val startToFrontier =
                when {
                    currentToFrontier == INF -> INF
                    else -> startToCurrent + currentToFrontier
                }
            val shortestPathDist = sPath[col].dist
            if (startToFrontier < shortestPathDist) {
                sPath[col].parent = currentVert
                sPath[col].dist = startToFrontier
            }
            col++
        }
    }

    private fun getMin(sPath: Array<DistPar>): Int {
        var minDist = INF
        var indexMin = 0
        for (i in 0 until numVertex) {
            if (vertexList[i]?.isInTree == false && sPath[i].dist < minDist) {
                minDist = sPath[i].dist
                indexMin = i
            }
        }
        return indexMin
    }

    /**
     * Display the adjacency matrix with weights instead of just connections
     */
    fun displayWeightMatrix() { // Determine the max width needed for any label
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
        for (i in 0 until numVertex) { // Row header
            print(vertexList[i]?.label?.padEnd(colWidth)) // Row entries
            for (j in 0 until numVertex) {
                val value = if (adjMatrix[i][j]) weightMatrix[i][j].toString() else "-"
                print(value.padEnd(colWidth))
            }
            println()
        }
    }

    fun findEdge(
        destinationVert: Int,
        searchPQ: PriorityQueue<IntEdge>,
    ): IntEdge? {
        for (edge in searchPQ) {
            if (edge.destination == destinationVert) return edge
        }
        return null
    }

    fun findEdgeIndexByDestination(
        destinationVert: Int,
        searchPQ: PriorityQueue<IntEdge>,
    ): Int {
        for (i in searchPQ.indices) {
            if (searchPQ[i].destination == destinationVert) return i
        }
        return -1
    }
}
