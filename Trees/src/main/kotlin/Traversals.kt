import java.util.*

data class TreeNode(var value: Int, var left: TreeNode? = null, var right: TreeNode? = null)

fun inorder(root: TreeNode) {
    val stack = Stack<TreeNode?>()

    var currentNode: TreeNode? = root

    while (stack.isNotEmpty() || currentNode != null) {
        while (currentNode != null) {
            stack.push(currentNode)
            currentNode = currentNode.left
        }

        if (stack.isNotEmpty()) {
            currentNode = stack.pop()
            println(currentNode)
            currentNode = currentNode?.right
        }
    }
}

fun preorder(root: TreeNode) {
    val stack = Stack<TreeNode?>()

    stack.push(root)

    while (stack.isNotEmpty()) {
        val currentNode = stack.pop()
        print(currentNode)

        if (currentNode?.left != null) stack.push(currentNode.right)
        if (currentNode?.right != null) stack.push(currentNode.left)
    }
}

fun postOrder(root: TreeNode) {
    val stack1 = Stack<TreeNode?>()
    val stack2 = Stack<TreeNode?>()

    stack1.push(root)

    while (stack1.isNotEmpty()) {
        val currentNode = stack1.pop()
        stack2.push(currentNode)


        if (currentNode?.left != null) stack1.push(currentNode.left)
        if (currentNode?.right != null) stack1.push(currentNode.right)
    }

    while (stack2.isNotEmpty()) {
        println(stack2.pop())
    }
}