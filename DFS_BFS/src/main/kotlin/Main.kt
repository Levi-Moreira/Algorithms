package main.kotlin

import java.util.*

fun main() {

}

fun bfs(node: Int, graph: Map<Int, List<Int>>, N: Int) {
    val visited = BooleanArray(N) { false }
    val bfsQueue: Queue<Int> = LinkedList<Int>()
    bfsQueue.add(node)
    while (bfsQueue.isNotEmpty()) {
        val currentNode = bfsQueue.poll()
        visited[currentNode] = true
        for (neighbour in graph.getOrDefault(currentNode, listOf())) {
            if (visited[neighbour]) continue
            bfsQueue.add(currentNode)
        }
    }
}

fun dfs(node: Int, graph: Map<Int, List<Int>>, visited: BooleanArray) {
    if (visited[node]) return
    visited[node] = true
    for (neigh in graph.getOrDefault(node, listOf())) {
        if (visited[neigh]) continue
        dfs(neigh, graph, visited)
    }
}