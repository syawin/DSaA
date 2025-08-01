package graph

fun mstDemo() {
    val theGraph = WeightedGraph(9)
    theGraph.addVertex("A")
    theGraph.addVertex("B")
    theGraph.addVertex("C")
    theGraph.addVertex("D")
    theGraph.addVertex("E")
    theGraph.addVertex("F")

    theGraph.addEdge(0, 1, 6)
    theGraph.addEdge(0, 3, 4)
    theGraph.addEdge(1, 2, 10)
    theGraph.addEdge(1, 3, 7)
    theGraph.addEdge(1, 4, 7)
    theGraph.addEdge(2, 3, 8)
    theGraph.addEdge(2, 4, 5)
    theGraph.addEdge(2, 5, 6)
    theGraph.addEdge(3, 4, 12)
    theGraph.addEdge(4, 5, 7)

    theGraph.displayWeightMatrix()
    println()
    print("Minimum Spanning Tree: ")
    theGraph.minimumSpanningTree()
    println()
}

fun main() {
    mstDemo()
}
