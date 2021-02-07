/**
 * O(E * V)
 * Can be used where Dijkstra fails (negative edges)
 **/

fun bellmanFord(N: Int, source: Int, graph: Map<Int, List<Pair<Int, Int>>>) {
    val distance = IntArray(N) { Int.MAX_VALUE }
    distance[source] = 0

    // First Run
    repeat(N - 1) {
        for ((sourceNode, edges) in graph.entries) {
            for ((destNode, cost) in edges) {
                // Relax edge
                val newDist = distance[sourceNode] + cost
                if (newDist < distance[destNode]) {
                    distance[destNode] = newDist
                }
            }
        }
    }

    // Detect negative cycle
    repeat(N - 1) {
        for ((sourceNode, edges) in graph.entries) {
            for ((destNode, cost) in edges) {
                // Relax edge
                val newDist = distance[sourceNode] + cost
                if (newDist < distance[destNode]) {
                    distance[destNode] = Int.MIN_VALUE
                }
            }
        }
    }
}