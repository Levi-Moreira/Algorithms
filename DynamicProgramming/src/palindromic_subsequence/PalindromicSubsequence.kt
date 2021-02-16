/**
 * Exponential time and linear space
 */
fun recursiveLPS(s: String): Int {
    return helper(s, 0, s.lastIndex)
}

private fun helper(s: String, indexStart: Int, indexEnd: Int): Int {
    if (indexStart > indexEnd) return 0
    if (indexStart == indexEnd) return 1

    if (s[indexStart] == s[indexEnd]) {
        return 2 + helper(s, indexStart + 1, indexEnd - 1)
    } else {
        val op1 = helper(s, indexStart + 1, indexEnd)
        val op2 = helper(s, indexStart, indexEnd - 1)
        return maxOf(op1, op2)
    }
}

/**
 * N^2 time and  N^2 space
 */
fun recursiveLPSWithCache(s: String): Int {
    val cache = Array(s.length) { IntArray(s.length) { -1 } }
    return helper(s, 0, s.lastIndex, cache)
}

private fun helper(
    s: String,
    indexStart: Int,
    indexEnd: Int,
    cache: Array<IntArray>
): Int {
    if (indexStart > indexEnd) return 0
    if (indexStart == indexEnd) return 1

    if (cache[indexStart][indexEnd] != -1) return cache[indexStart][indexEnd]

    cache[indexStart][indexEnd] = if (s[indexStart] == s[indexEnd]) {
        2 + helper(s, indexStart + 1, indexEnd - 1, cache)
    } else {
        val op1 = helper(s, indexStart + 1, indexEnd, cache)
        val op2 = helper(s, indexStart, indexEnd - 1, cache)
        maxOf(op1, op2)
    }
    return cache[indexStart][indexEnd]
}

/**
 * N ^ 2 time and N ^ 2 space
 */
fun lpsWithTabulation(s: String): Int {
    val cache = Array(s.length) { IntArray(s.length) { 0 } }

    for (index in cache.indices) {
        cache[index][index] = 1
    }

    for (startIndex in cache.lastIndex downTo 0) {
        for (endIndex in startIndex + 1..cache.lastIndex) {
            cache[startIndex][endIndex] = if (s[startIndex] == s[endIndex]) {
                2 + cache[startIndex + 1][endIndex - 1]
            } else {
                val op1 = cache[startIndex + 1][endIndex]
                val op2 = cache[startIndex][endIndex - 1]
                maxOf(op1, op2)
            }
        }
    }
    return cache[0].last()
}