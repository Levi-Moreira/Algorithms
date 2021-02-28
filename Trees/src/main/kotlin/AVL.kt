class AVL {
    private var root: BSTNode? = null

    fun insert(element: Int) {
        root  = insertAVL(root, element)
    }

    private fun insertAVL(node: BSTNode?, element: Int): BSTNode? {
        if (node == null) return BSTNode(element)

        if (element < node.value)
            node.left = insertAVL(node.left, element)
        else
            node.right = insertAVL(node.right, element)

        node.height = 1 + maxOf(node.left?.height ?: 0, node.right?.height ?: 0)

        val balance = node.balance()

        val leftTree = node.left
        val rightTree = node.right
        // Left Left
        if (leftTree != null && balance > 1 && element < leftTree.value) {
            return rightRotate(node)
        }

        //Left Right
        if (leftTree != null && balance > 1 && element > leftTree.value) {
            node.left = leftRotate(leftTree)
            return rightRotate(node)
        }

        //Right Right
        if (rightTree != null && balance < -1 && element > rightTree.value) {
            return leftRotate(node)
        }

        //Right Left
        if (rightTree != null && balance < -1 && element < rightTree.value) {
            node.right = rightRotate(rightTree)
            return leftRotate(node)
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

private fun rightRotate(oldRoot: BSTNode): BSTNode? {
    val newRoot = oldRoot.left
    val t2 = newRoot?.right

    newRoot?.right = oldRoot
    oldRoot.left = t2

    updateHeight(oldRoot)
    updateHeight(newRoot)

    return newRoot
}

private fun updateHeight(node: BSTNode?) {
    node?.height = maxOf(node?.left?.height ?: 0, node?.right?.height ?: 0) + 1
}

private fun leftRotate(oldRoot: BSTNode): BSTNode? {
    val newRoot = oldRoot.right
    val t2 = newRoot?.left

    newRoot?.left = oldRoot
    oldRoot.right = t2

    updateHeight(oldRoot)
    updateHeight(newRoot)

    return newRoot
}