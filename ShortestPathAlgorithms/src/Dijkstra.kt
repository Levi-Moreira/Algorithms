import java.util.*

/**
 * O ((E + V)* LogV)
 * - Must contain only non-negative edges
 * - Inefficient for dense graph (better use an indexed PQ)
 */
fun dijkstra(source: Int, dest: Int, graph: Map<Int, List<Pair<Int, Int>>>, N: Int): IntArray {
    val dist = IntArray(N) { Int.MAX_VALUE }
    val visited = BooleanArray(N)
    val prev = IntArray(N) { -1 }
    dist[source] = 0
    val pq = PriorityQueue<Pair<Int, Int>>()
    pq.add(source to 0)
    while (pq.isNotEmpty()) {
        val (currentNode, currentMinDist) = pq.poll()
        visited[currentNode] = true
        if (dist[currentNode] < currentMinDist) continue
        for ((destNode, cost) in graph[currentNode] ?: listOf<Pair<Int, Int>>()) {
            if (visited[destNode]) continue

            // Edge relaxation
            val newDist = dist[currentNode] + cost
            if (newDist < dist[destNode]) {
                prev[destNode] = currentNode
                dist[destNode] = newDist
                pq.add(destNode to newDist)
            }
        }
        if (currentNode == dest) {
            return dist
        }
    }

    return dist
}


fun findShortestPath(
    source: Int,
    dest: Int,
    prev: IntArray,
    dist: IntArray,
    graph: Map<Int, List<Pair<Int, Int>>>,
    N: Int
): List<Int> {
    val path = mutableListOf<Int>()

    if (dist[dest] == Int.MAX_VALUE) return listOf()

    var node = dest
    while (prev[node] != -1) {
        path.add(node)
        node = prev[node]
    }

    return path.reversed()
}