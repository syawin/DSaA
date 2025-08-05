package graph

class ObjGraph(
    override val maxSize: Int,
) : GraphInterface {
    var vertexList = arrayOfNulls<Vertex?>(maxSize)
}
