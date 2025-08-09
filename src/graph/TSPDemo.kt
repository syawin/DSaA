package graph

fun tspDemo(): WeightedGraph {
    val theGraph = WeightedGraph(5)
    theGraph.addVertex("A")
    theGraph.addVertex("B")
    theGraph.addVertex("C")
    theGraph.addVertex("D")
    theGraph.addVertex("E")

    // A-B
    theGraph.addEdge(0, 1, 91)
    theGraph.addEdge(1, 0, 91)

    // A-C
    theGraph.addEdge(0, 2, 62)
    theGraph.addEdge(2, 0, 62)

    // A-D
    theGraph.addEdge(0, 3, 55)
    theGraph.addEdge(3, 0, 55)

    // B-C
    theGraph.addEdge(1, 2, 44)
    theGraph.addEdge(2, 1, 44)

    // B-E
    theGraph.addEdge(1, 4, 31)
    theGraph.addEdge(4, 1, 31)

    // C-D
    theGraph.addEdge(2, 3, 52)
    theGraph.addEdge(3, 2, 52)

    // C-E
    theGraph.addEdge(2, 4, 45)
    theGraph.addEdge(4, 2, 45)

    // D-E
    theGraph.addEdge(3, 4, 83)
    theGraph.addEdge(4, 3, 83)

    theGraph.displayWeightMatrix()
    return theGraph
}

fun main() {
    tspDemo()
}
