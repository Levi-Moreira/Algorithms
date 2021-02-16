/**
 * 2 ^(N + M) time and minOf(M, N) space
 */
fun longestCommonSubsequence(s1: String, s2: String): Int {
    return helper(0, 0, s1, s2)
}

fun helper(index1: Int, index2: Int, s1: String, s2: String): Int {
    if (index1 > s1.lastIndex || index2 > s2.lastIndex) return 0

    if (s1[index1] == s2[index2]) {
        return 1 + helper(index1 + 1, index2 + 1, s1, s2)
    } else {
        val op1 = helper(index1 + 1, index2, s1, s2)
        val op2 = helper(index1, index2 + 1, s1, s2)
        return maxOf(op1, op2)
    }
}

/**
 * N * M time and space
 */
fun longestCommonSubsequenceWithMemo(s1: String, s2: String): Int {
    val cache = Array(s1.length) { IntArray(s2.length) { -1 } }

    return helper(0, 0, s1, s2, cache)
}

fun helper(index1: Int, index2: Int, s1: String, s2: String, cache: Array<IntArray>): Int {
    if (index1 > s1.lastIndex || index2 > s2.lastIndex) return 0

    if (cache[index1][index2] != -1) return cache[index1][index2]

    cache[index1][index2] = if (s1[index1] == s2[index2]) {
        1 + helper(index1 + 1, index2 + 1, s1, s2, cache)
    } else {
        val op1 = helper(index1 + 1, index2, s1, s2, cache)
        val op2 = helper(index1, index2 + 1, s1, s2, cache)
        maxOf(op1, op2)
    }

    return cache[index1][index2]
}


/**
 * N * M time and space
 */
fun longestCommonSubsequenceWithTab(s1: String, s2: String): Int {
    val cache = Array(s1.length) { IntArray(s2.length) { -1 } }

    for (index1 in s1.indices) {
        for (index2 in s2.indices) {
            cache[index1][index2] = if (s1[index1] == s2[index2]) {
                1 + if (index1 - 1 >= 0 && index2 - 1 >= 0) {
                    cache[index1 - 1][index2 - 1]
                } else {
                    0
                }
            } else {
                val op1 = if (index1 - 1 >= 0) cache[index1 - 1][index2] else 0
                val op2 = if (index2 - 1 >= 0) cache[index1][index2 - 1] else 0
                maxOf(op1, op2)
            }
        }
    }

    return cache.last().last()
}
