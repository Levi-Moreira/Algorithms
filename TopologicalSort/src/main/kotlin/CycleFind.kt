package main.kotlin

import java.util.*

/**
 * O(V + E)
 */
fun findCycleDfs(graph: Map<Int, List<Int>>): Boolean {
    val numberOfNodes = graph.keys.size
    val visited = BooleanArray(numberOfNodes)
    val recVisited = BooleanArray(numberOfNodes)

    for (node in 0 until numberOfNodes) {
        if (visited[node]) continue

        if (dfs(node, visited, recVisited, graph)) return true
    }
    return false
}

private fun dfs(
    currentNode: Int,
    visited: BooleanArray,
    recVisited: BooleanArray,
    graph: Map<Int, List<Int>>
): Boolean {
    visited[currentNode] = true
    recVisited[currentNode] = true

    for (nextNode in graph[currentNode] ?: listOf()) {
        if (visited[nextNode]) continue
        if (recVisited[nextNode]) return true
        if (dfs(nextNode, visited, recVisited, graph)) return true
    }
    recVisited[currentNode] = false
    return false
}


/**
 * O(V + E)
 */
private fun bfsFindCycle(graph: Map<Int, List<Int>>): Boolean {
    val numberOfNodes = graph.keys.size
    val inDegrees = IntArray(numberOfNodes) { 0 }
    val visited = BooleanArray(numberOfNodes) { false }
    for (list in graph.values) {
        for (node in list) {
            inDegrees[node]++
        }
    }

    val queue = LinkedList<Int>()

    for ((node, indegree) in inDegrees.withIndex()) {
        if (indegree == 0) {
            queue.add(node)
        }
    }

    while (queue.isNotEmpty()) {
        val currentNode = queue.poll()
        visited[currentNode] = true

        for (neighbour in graph[currentNode] ?: emptyList()) {
            inDegrees[neighbour]--
            if (inDegrees[neighbour] == 0) {
                queue.add(neighbour)
            }
        }
    }

    return visited.count { it } == numberOfNodes
}