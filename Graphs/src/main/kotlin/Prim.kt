package main.kotlin

data class EdgePrim(val weight: Int, var isIncluded: Boolean = false)

data class Vertex(val label: String, val edges: Map<Vertex, EdgePrim> = hashMapOf(), var isVisited: Boolean = false) {

    fun nextMinimum(): Pair<Vertex, EdgePrim> {
        var nextMinimumEdge = EdgePrim(Int.MAX_VALUE)
        var nextVertex = this

        val edgesEntries = edges.entries.iterator()

        while (edgesEntries.hasNext()) {
            val (currentVertex, currentEdge) = edgesEntries.next()
            if (currentVertex.isVisited) continue
            if (currentEdge.isIncluded) continue

            if (currentEdge.weight < nextMinimumEdge.weight) {
                nextMinimumEdge = currentEdge
                nextVertex = currentVertex
            }
        }

        return nextVertex to nextMinimumEdge
    }
}

class Prim(val graph: List<Vertex>) {

    fun run() {
        if (graph.isNotEmpty()) graph[0].isVisited = true

        while (isDisconnected()) {
            var nexMinimum = EdgePrim(Int.MAX_VALUE)
            var nextVertex = graph.first()

            for (vertex in graph) {
                if (!vertex.isVisited) continue
                val candidate: Pair<Vertex, EdgePrim> = vertex.nextMinimum();
                val (candidateVertex, candidateEdge) = candidate
                if (candidateEdge.weight < nexMinimum.weight) {
                    nexMinimum = candidateEdge
                    nextVertex = candidateVertex
                }
            }
            nextVertex.isVisited = true
            nexMinimum.isIncluded = true
        }
    }

    private fun isDisconnected(): Boolean {
        for (vertex in graph) {
            if (!vertex.isVisited) return true
        }
        return false
    }


}