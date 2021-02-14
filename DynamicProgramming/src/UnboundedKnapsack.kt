/**
 * Exponential Time  O(2^{N+C})
 */
fun recursiveUnKnapsack(w: IntArray, p: IntArray, capacity: Int): Int {
    return helper(0, w, p, capacity)
}

private fun helper(
    index: Int,
    w: IntArray,
    p: IntArray,
    capacity: Int
): Int {
    if (index > w.lastIndex || capacity <= 0) {
        return 0
    }
    // Do not add
    val op1 = helper(index + 1, w, p, capacity)
    var op2 = 0
    if (capacity - w[index] >= 0) {
        op2 = helper(index, w, p, capacity - w[index]) + p[index]
    }

    return maxOf(op1, op2)
}

/**
 * W * C time and space
 */
fun recursiveUnKnapsackWithMemo(w: IntArray, p: IntArray, capacity: Int): Int {
    val cache = Array(w.size) { IntArray(capacity + 1) { -1 } }
    return helper(0, w, p, capacity, cache)
}

private fun helper(
    index: Int,
    w: IntArray,
    p: IntArray,
    capacity: Int,
    cache: Array<IntArray>
): Int {
    if (index > w.lastIndex || capacity <= 0) {
        return 0
    }

    if (cache[index][capacity] != -1) return cache[index][capacity]
    // Do not add
    val op1 = helper(index + 1, w, p, capacity, cache)
    var op2 = 0
    if (capacity - w[index] >= 0) {
        op2 = helper(index, w, p, capacity - w[index], cache) + p[index]
    }

    cache[index][capacity] = maxOf(op1, op2)
    return cache[index][capacity]
}

/**
 * W * C time and space
 */
fun unKnapsackWithTab(w: IntArray, p: IntArray, capacity: Int): Int {
    val cache = Array(w.size + 1) { IntArray(capacity + 1) { 0 } }

    for (index in 1..cache.lastIndex) {
        for (cap in 1..cache[0].lastIndex) {
            val profit1 = cache[index - 1][cap]
            var profit2 = 0
            if (cap - w[index - 1] >= 0) {
                profit2 = cache[index][cap - w[index - 1]] + p[index - 1]
            }
            cache[index][cap] = maxOf(profit1, profit2)
        }
    }

    return cache.last().last()
}

/**
 * W * C time and C space
 */
fun unKnapsackWithTabLinearSpace(w: IntArray, p: IntArray, capacity: Int): Int {
    val cache = Array(2) { IntArray(capacity + 1) { 0 } }

    repeat(w.size) { pIndex ->
        for (cap in capacity downTo 0) {
            val profit1 = cache[0][cap]
            var profit2 = 0

            if (cap - w[pIndex] >= 0) {
                profit2 = cache[0][cap - w[pIndex]] + p[pIndex]
            }
            cache[1][cap] = maxOf(profit1, profit2)
        }

        cache[0] = cache[1].copyOf()
    }

    return cache.last().last()
}



