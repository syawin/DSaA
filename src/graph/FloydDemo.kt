@file:Suppress("DuplicatedCode")

package graph

fun floydDemo() {
    val theGraph = WeightedGraph(4)
    theGraph.addVertex("A")
    theGraph.addVertex("B")
    theGraph.addVertex("C")
    theGraph.addVertex("D")

    theGraph.addEdge(1, 0, 70)
    theGraph.addEdge(1, 3, 10)
    theGraph.addEdge(2, 0, 30)
    theGraph.addEdge(3, 2, 20)

    theGraph.displayWeightMatrix()
    println()
    println("After Floyd:")
    theGraph.floydWarshall()
    theGraph.displayWeightMatrix()
    println()
}

fun main() {
    floydDemo()
}
