package main.kotlin

import UnionFind

data class Edge(val from: Int, val to: Int, val weight: Int)

fun MSTKruskal(edges: Array<Edge>, v: Int) {
    val minimumSpanningTree = mutableListOf<Edge>()

    // E Log E
    edges.sortBy { it.weight }
    val dsu = UnionFind(v)

    var currentEdgeIndex = 0

    // E * Log V
    while (minimumSpanningTree.size < v - 1) {
        val currentEdge = edges[currentEdgeIndex++]

        val root1 = dsu.findRoot(currentEdge.from)
        val root2 = dsu.findRoot(currentEdge.to)

        if (root1 != root2) {
            minimumSpanningTree.add(currentEdge)
            dsu.union(currentEdge.from, currentEdge.to)
        }

    }

    minimumSpanningTree.forEach(::println)
}

fun main() {
    val edges = arrayOf(
        Edge(0, 1, 10),
        Edge(0, 2, 6),
        Edge(0, 3, 5),
        Edge(1, 3, 15),
        Edge(2, 3, 4)
    )

    MSTKruskal(edges, 4)
}