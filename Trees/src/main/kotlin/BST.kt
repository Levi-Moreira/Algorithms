open class BST {

    private var root: BSTNode? = null

    fun insert(element: Int) {
        root = bstInsert(element, root)
    }

    private fun bstInsert(element: Int, node: BSTNode?): BSTNode {
        if (node == null) return BSTNode(element)

        if (element > node.value) {
            node.right = bstInsert(element, node.right)
        } else {
            node.left = bstInsert(element, node.left)
        }
        return node
    }

    fun showTreeElements() {
        inorder(root)
    }

    private fun inorder(node: BSTNode?) {
        if (node == null) return

        inorder(node.left)
        println(node)
        inorder(node.right)
    }
}

fun main() {
    val bst = AVL()
    bst.insert(1)
    bst.insert(2)
    bst.insert(3)
    bst.insert(4)
    bst.insert(5)
    bst.insert(6)
    bst.showTreeElements()
}
