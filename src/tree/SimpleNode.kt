package tree

data class SimpleNode(
    var key: Int? = null,
) {
    fun isLeaf(): Boolean = left == null && right == null

    var left: SimpleNode? = null
    var right: SimpleNode? = null
    var parent: SimpleNode? = null
}
