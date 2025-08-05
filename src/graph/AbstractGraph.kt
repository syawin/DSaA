package graph

abstract class AbstractGraph(
    val maxSize: Int,
) {
    abstract fun addVertex(label: String)

    abstract fun addEdge(
        start: Int,
        end: Int,
    )
}
