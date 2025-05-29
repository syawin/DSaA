package tree

// suggest parameterize class Node
open class Node internal constructor(builder: Builder) {
    val key: Int = builder.key
    val `val`: Any? = builder.`val`

    override fun toString(): String {
        return "Node{key=$key, val=$`val`}"
    }

    open class Builder(internal val key: Int, val `val`: Any?) {
        open fun build(): Node? {
            return Node(this)
        }
    }
}
