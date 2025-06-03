package tree

// suggest parameterize class Node
open class Node internal constructor(
    builder: Builder,
) {
    val key: Int = builder.key
    val value: Any? = builder.value

    override fun toString(): String = "Node{key=$key, val=$value}"

    open class Builder(
        internal val key: Int,
        val value: Any?,
    ) {
        open fun build(): Node? = Node(this)
    }
}
