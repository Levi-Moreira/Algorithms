/**
 * O(3^{m + n}) and O(m + n) space
 */
fun recursiveLCS(s1: String, s2: String): Int {
    return helper(0, 0, s1, s2, 0)
}

private fun helper(
    index1: Int,
    index2: Int,
    s1: String,
    s2: String,
    matchCount: Int
): Int {
    if (index1 > s1.lastIndex || index2 > s2.lastIndex) return matchCount

    val resEqual = if (s1[index1] == s2[index2]) {
        helper(index1 + 1, index2 + 1, s1, s2, matchCount + 1)
    } else {
        matchCount
    }

    val resDiff1 = helper(index1 + 1, index2, s1, s2, 0)
    val resDiff2 = helper(index1, index2 + 1, s1, s2, 0)

    return maxOf(resEqual, resDiff1, resDiff2)
}

/**
 * O(M * N * minOf(M,N)) time and space
 */
fun recursiveLCSWithCache(s1: String, s2: String): Int {
    val cache = Array(s1.length) { Array(s2.length) { IntArray(minOf(s1.length, s2.length)) { -1 } } }
    return helper(0, 0, s1, s2, 0, cache)
}

private fun helper(
    index1: Int,
    index2: Int,
    s1: String,
    s2: String,
    matchCount: Int,
    cache: Array<Array<IntArray>>
): Int {
    if (index1 > s1.lastIndex || index2 > s2.lastIndex) return matchCount
    if (cache[index1][index2][matchCount] != -1) return cache[index1][index2][matchCount]
    val resEqual = if (s1[index1] == s2[index2]) {
        helper(index1 + 1, index2 + 1, s1, s2, matchCount + 1, cache)
    } else {
        matchCount
    }

    val resDiff1 = helper(index1 + 1, index2, s1, s2, 0, cache)
    val resDiff2 = helper(index1, index2 + 1, s1, s2, 0, cache)

    cache[index1][index2][matchCount] = maxOf(resEqual, resDiff1, resDiff2)
    return cache[index1][index2][matchCount]
}

/**
 * O(m * n) time and space
 */
fun LCSwithTabulation(s1: String, s2: String): Int {
    val cache = Array(s1.length + 1) { IntArray(s2.length + 1) { 0 } }
    var maxLength = 0

    for (index1 in 1..cache.lastIndex) {
        for (index2 in 1..cache[0].lastIndex) {
            cache[index1][index2] = if (s1[index1 - 1] == s2[index2 - 1]) {
                1 + cache[index1 - 1][index2 - 1]
            } else {
                0
            }
            maxLength = maxOf(maxLength, cache[index1][index2])
        }
    }
    return maxLength
}

/**
 * O(m * n) time and space
 */
fun LCSwithTabulationWithLinearSpace(s1: String, s2: String): Int {
    val cache = Array(2) { IntArray(s2.length + 1) { 0 } }
    var maxLength = 0

    repeat(s1.length - 1) { index1 ->
        for (index2 in 1..cache[0].lastIndex) {
            cache[1][index2] = if (s1[index1 - 1] == s2[index2 - 1]) {
                1 + cache[0][index2 - 1]
            } else {
                0
            }
            maxLength = maxOf(maxLength, cache[1][index2])
        }
        cache[0] = cache[1].copyOf()
    }
    return maxLength
}