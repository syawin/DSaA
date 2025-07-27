package graph

data class Vertex(
    var label: String,
) {
    var wasVisited = false
    var isInTree = false
}

fun Vertex.reset() {
    wasVisited = false
    isInTree = false
}
