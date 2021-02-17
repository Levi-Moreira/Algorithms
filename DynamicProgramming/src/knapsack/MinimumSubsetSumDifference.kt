package knapsack

/**
 * O(2 ^N) time and N space
 */
fun minimumSubsetSumDiff(arr: IntArray): Int {
    return helper(0, arr, arr.sum(), arr.sum())
}

private fun helper(index: Int, arr: IntArray, sumLeft: Int, totalSum: Int): Int {
    if (index > arr.lastIndex) {
        var sumRight = totalSum - sumLeft
        return Math.abs(sumRight - sumLeft)
    }
    val op1 = helper(index + 1, arr, sumLeft, totalSum)
    val op2 = helper(index + 1, arr, sumLeft - arr[index], totalSum)
    return minOf(op1, op2)
}

/**
 * O(N * sum) time and space
 */
fun minimumSubsetSumDiffWithCache(arr: IntArray): Int {
    val cache = Array(arr.size) { IntArray(arr.sum() + 1) { -1 } }
    return helper(0, arr, arr.sum(), arr.sum(), cache)
}

private fun helper(index: Int, arr: IntArray, sumLeft: Int, totalSum: Int, cache: Array<IntArray>): Int {
    if (index > arr.lastIndex) {
        val sumRight = totalSum - sumLeft
        return Math.abs(sumRight - sumLeft)
    }

    if (cache[index][sumLeft] != -1) return cache[index][sumLeft]
    val op1 = helper(index + 1, arr, sumLeft, totalSum, cache)
    val op2 = helper(index + 1, arr, sumLeft - arr[index], totalSum, cache)
    cache[index][sumLeft] = minOf(op1, op2)
    return cache[index][sumLeft]
}

/**
 * O(N * sum) time and space
 */
fun minimumSubsetSumDiffWithTab(arr: IntArray): Int {
    val cache = Array(arr.size) { BooleanArray(arr.sum() / 2 + 1) { false } }

    for (row in cache.indices) cache[row][0] = true
    for (s in 1..arr.sum() / 2) cache[0][s] = arr[0] == s

    for (index in 1..cache.lastIndex) {
        for (sumLeft in 1..arr.sum() / 2) {
            val op1 = cache[index - 1][sumLeft]
            val op2 = if (sumLeft - arr[index] >= 0) cache[index - 1][sumLeft - arr[index]] else false
            cache[index][sumLeft] = op1 || op2
        }
    }

    var sum1 = 0
    for (index in arr.sum() / 2 downTo 0) {
        if (cache.last()[index]) {
            sum1 = index
            break
        }
    }

    return Math.abs(sum1 - (arr.sum() - sum1))
}

