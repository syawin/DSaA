package graph

import linkedlist.ObjLinkList

class ObjGraph(
    maxSize: Int,
) : AbstractGraph(maxSize) {
    var vertexList = arrayOfNulls<Vertex?>(maxSize)
    var adjList = Array(maxSize) { ObjLinkList<ObjEdge>() }
    var numVertex = 0

    override fun addVertex(label: String) {
        vertexList[numVertex++] = Vertex(label)
    }

    override fun addEdge(
        start: Int,
        end: Int,
    ) {
        TODO("Not yet implemented")
    }
}
