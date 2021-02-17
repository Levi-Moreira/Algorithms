package fibonacci

/**
 * O(3 ^ N) time and n space
 */
fun numberOfFactorsRecursive(n: Int): Int {
    if (n == 0) return 1
    if (n == 1) return 1
    if (n == 2) return 1
    if (n == 3) return 2
    return numberOfFactorsRecursive(n - 1) + numberOfFactorsRecursive(n - 3) + numberOfFactorsRecursive(n - 4)
}

/**
 * O(N) time and space
 */
fun numberOfFactorsRecursiveWithMemo(n: Int): Int {
    return helper(n, IntArray(n + 1) { -1 })
}

private fun helper(n: Int, cache: IntArray): Int {
    if (n == 0) return 1
    if (n == 1) return 1
    if (n == 2) return 1
    if (n == 3) return 2

    if (cache[n] != -1) return cache[n]
    cache[n] = numberOfFactorsRecursive(n - 1) + numberOfFactorsRecursive(n - 3) + numberOfFactorsRecursive(n - 4)
    return cache[n]
}

/**
 * O(N) time and space
 */
fun numberOfFactorsWithTab(n: Int): Int {
    val cache = IntArray(n + 1) { 0 }
    cache[0] = 1
    cache[1] = 1
    cache[2] = 1
    cache[3] = 2

    for (number in 4..cache.lastIndex) {
        cache[number] = cache[number - 1] + cache[number - 3] + cache[number - 4]
    }

    return cache[n]
}


