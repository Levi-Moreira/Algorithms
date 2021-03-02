data class BSTNode(var value: Int, var left: BSTNode? = null, var right: BSTNode? = null, var height: Int = 1) {

    fun balance(): Int {
        val left = left?.height ?: 0
        val right = right?.height ?: 0
        return left - right
    }
}
