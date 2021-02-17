package fibonacci

/**
 * O (3 ^ n)
 */
fun stairCaseWithRecursion(n: Int): Int {
    if (n < 3) return n
    return stairCaseWithRecursion(n - 1) + stairCaseWithRecursion(n - 2) + stairCaseWithRecursion(n - 3)
}

/**
 * O (N) time and space
 */
fun stairCaseWithRecursionAndMemo(n: Int): Int {
    val cache = IntArray(n + 1) { -1 }
    cache[0] = 1
    cache[1] = 1
    cache[2] = 2
    return helper(n, cache)
}

private fun helper(n: Int, cache: IntArray): Int {
    if (cache[n] != -1) return cache[n]
    cache[n] = helper(n - 1, cache) + helper(n - 2, cache) + helper(n - 3, cache)
    return cache[n]
}

/**
 * O (N) time and space
 */
fun stairCaseWithTabulation(n: Int): Int {
    val cache = IntArray(n + 1) { -1 }
    cache[0] = 1
    cache[1] = 1
    cache[2] = 2

    for (index in 3..n) {
        cache[index] = cache[index - 1] + cache[index - 2] + cache[index - 3]
    }
    return cache.last()
}

/**
 * O(N) time and O(1) space
 */
fun stairCaseWithConstantSpace(n: Int): Int {
    val cache = IntArray(n + 1) { -1 }

    var step1 = 1
    var step2 = 1
    var step3 = 2

    for (index in 3..n) {
        val temp = step1 + step2 + step3
        step1 = step2
        step2 = step3
        step3 = temp
    }
    return step3
}