/**
 * O(2 ^N) time and N space
 */
fun longestPalindromicSubstring(s: String): Int {
    return helper(0, s.lastIndex, s)
}

private fun helper(begin: Int, end: Int, s: String): Int {
    if (begin > end) return 0
    if (begin == end) return 1

    if (s[begin] == s[end]) {
        return 2 + helper(begin + 1, end - 1, s)
    } else {
        val op1 = helper(begin + 1, end, s)
        val op2 = helper(begin, end - 1, s)
        return maxOf(op1, op2)
    }
}

/**
 * N * N time and space
 */
fun longestPalindromicSubstringWithMemo(s: String): Int {
    val cache = Array(s.length) { IntArray(s.length) { -1 } }
    return helper(0, s.lastIndex, s, cache)
}

private fun helper(begin: Int, end: Int, s: String, cache: Array<IntArray>): Int {
    if (begin > end) return 0
    if (begin == end) return 1

    if (cache[begin][end] != -1) return cache[begin][end]

    cache[begin][end] = if (s[begin] == s[end]) {
        2 + helper(begin + 1, end - 1, s, cache)
    } else {
        val op1 = helper(begin + 1, end, s, cache)
        val op2 = helper(begin, end - 1, s, cache)
        maxOf(op1, op2)
    }
    return cache[begin][end]
}

/**
 * N * N time and space
 */
fun longestPalindromicSubstringWithTab(s: String): Int {
    val cache = Array(s.length) { BooleanArray(s.length) { false } }

    for (index in cache.indices) cache[index][index] = true
    var maxLength = 1
    for (begin in cache.lastIndex downTo 0) {
        for (end in begin + 1..cache.lastIndex) {
            if (s[begin] == s[end]) {
                if (end - begin == 1 || cache[begin + 1][end - 1]) {
                    cache[begin][end] = true
                    maxLength = maxOf(maxLength, end - begin + 1)
                }
            }
        }
    }
    return maxLength
}