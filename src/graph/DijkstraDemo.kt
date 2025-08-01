@file:Suppress("DuplicatedCode")

package graph

fun dijkstraDemo() {
    val theGraph = WeightedGraph(5)
    theGraph.addVertex("A")
    theGraph.addVertex("B")
    theGraph.addVertex("C")
    theGraph.addVertex("D")
    theGraph.addVertex("E")

    theGraph.addEdge(0, 1, 50)
    theGraph.addEdge(0, 3, 80)
    theGraph.addEdge(1, 2, 60)
    theGraph.addEdge(1, 3, 90)
    theGraph.addEdge(2, 4, 40)
    theGraph.addEdge(3, 2, 20)
    theGraph.addEdge(3, 4, 70)
    theGraph.addEdge(4, 1, 50)

    theGraph.displayWeightMatrix()
    println()
    print("Shortest Path: ")
    theGraph.dijkstra()
    println()
}

fun main() {
    dijkstraDemo()
}
