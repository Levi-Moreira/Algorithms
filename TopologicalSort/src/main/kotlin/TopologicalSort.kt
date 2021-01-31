package main.kotlin

import java.util.*

class TopologicalSort {
}

/**
 * O(V + E)
 * Topological ordering are not unique
 * Graphs need to be acyclic
 */
fun topSort(graph: Map<Int, List<Int>>): List<Int> {
    if (findCycle(graph)) return emptyList()

    val numberOfNodes = graph.keys.size
    val visited = BooleanArray(numberOfNodes)
    val ordering = LinkedList<Int>()

    for (node in 0 until numberOfNodes) {
        if (visited[node]) continue

        dfs(node, visited, ordering, graph)
    }
    return ordering
}

fun dfs(currentNode: Int, visited: BooleanArray, ordering: LinkedList<Int>, graph: Map<Int, List<Int>>) {
    visited[currentNode] = true
    for (nextNode in graph[currentNode] ?: listOf()) {
        if (visited[nextNode]) continue
        dfs(nextNode, visited, ordering, graph)
    }
    ordering.addFirst(currentNode)
}
