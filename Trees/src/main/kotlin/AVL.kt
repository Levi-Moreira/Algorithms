class AVL {
    private var root: BSTNode? = null

    fun insert(element: Int) {
        root = insertAVL(root, element)
    }

    fun delete(element: Int) {
        root = deleteAVL(root, element)
    }

    private fun insertAVL(node: BSTNode?, element: Int): BSTNode? {
        if (node == null) return BSTNode(element)

        if (element < node.value)
            node.left = insertAVL(node.left, element)
        else
            node.right = insertAVL(node.right, element)

        node.height = 1 + maxOf(node.left?.height ?: 0, node.right?.height ?: 0)

        return performAvlBalanceForInsertion(node, node.balance(), element)
    }

    private fun deleteAVL(node: BSTNode?, element: Int): BSTNode? {
        if (node == null) return null

        var node = node

        // perform a search
        if (element < node.value) {
            node.left = deleteAVL(node.left, element)
        } else if (element > node.value) {
            node.right = deleteAVL(node.right, element)
        } else {
            // found node to delete.
            // easy case first, the node do not have one or no children. The node becoes its child
            if (node.left == null || node.right == null) {
                var temp: BSTNode? = null

                if (temp == node.left) {
                    temp = node.right
                } else {
                    temp = node.left
                }

                if (temp == null) {
                    temp = node
                    node = null
                } else {
                    node = temp
                }
            } else {
                // The node has both children
                // Get in order successor
                val successor = inorderSuccess(node.right)
                node.value = successor.value
                node.right = deleteAVL(node.right, successor.value)

            }
        }

        if (node == null) return node

        node.height = 1 + maxOf(node.left?.height ?: 0, node.right?.height ?: 0)

        return performAvlBalanceForDeletion(node, node.balance())
    }

    private fun inorderSuccess(right: BSTNode?): BSTNode {
        var current = right

        while (current?.left != null) {
            current = current.left
        }

        return current!!
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

private fun performAvlBalanceForDeletion(node: BSTNode, balance: Int): BSTNode? {
    val leftTree = node.left
    val rightTree = node.right
    // Left Left
    if (leftTree != null && balance > 1 && leftTree.balance() >= 0) {
        return rightRotate(node)
    }

    //Left Right
    if (leftTree != null && balance > 1 && leftTree.balance() < 0) {
        node.left = leftRotate(leftTree)
        return rightRotate(node)
    }

    //Right Right
    if (rightTree != null && balance < -1 && rightTree.balance() <= 0) {
        return leftRotate(node)
    }

    //Right Left
    if (rightTree != null && balance < -1 && rightTree.balance() > 0) {
        node.right = rightRotate(rightTree)
        return leftRotate(node)
    }
    return node
}

private fun performAvlBalanceForInsertion(node: BSTNode, balance: Int, element: Int): BSTNode? {
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