package tree

data class SimpleNode(
    var key: Int? = null,
) {
    var left: SimpleNode? = null
    var right: SimpleNode? = null
    var parent: SimpleNode? = null
}
