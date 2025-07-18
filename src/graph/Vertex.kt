package graph

data class Vertex(
    var label: String,
) {
    var wasVisited: Boolean = false
}
