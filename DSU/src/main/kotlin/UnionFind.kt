class UnionFind(private val size: Int) {
    private val componentSize = IntArray(size) { 1 }
    private val id = IntArray(size) { index -> index }

    var componentNumber = size
        private set


    fun findRoot(node: Int): Int {
        var root = node

        while (root != id[root]) {
            root = id[root]
        }
        compressPath(root, node)
        return root
    }

    fun union(node1: Int, node2: Int) {
        var root1 = findRoot(node1)
        val root2 = findRoot(node2)
        if (root1 == root2) return

        if (componentSize[root1] < componentSize[root2]) {
            componentSize[root2] += componentSize[root1]
            id[root1] = root2
        } else {
            componentSize[root1] += componentSize[root2]
            id[root2] = root1
        }

        componentNumber--
    }

    fun isConnected(node1: Int, node2: Int) = findRoot(node1) == findRoot(node2)

    fun componentSize(node: Int) = componentSize[findRoot(node)]

    private fun compressPath(root: Int, element: Int) {
        var node = element;

        while (node != root) {
            val next = id[node]
            id[node] = root
            node = next
        }
    }
}