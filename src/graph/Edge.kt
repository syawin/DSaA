package graph

data class Edge(
    val source: Int,
    val destination: Int,
    val weight: Int = 1,
)
