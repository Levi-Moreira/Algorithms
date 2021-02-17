package palindromic_subsequence

/**
 * O(N ˆ 2) time and space
 */
fun countOfPalindromicSubstrings(s: String): Int {
    val cache = Array(s.length) { BooleanArray(s.length) { false } }
    for (index in cache.indices) cache[index][index] = true
    var count = 0
    for (begin in cache.lastIndex downTo 0) {
        for (end in begin + 1..cache.lastIndex) {
            if (s[begin] == s[end]) {
                if (end - begin == 1 || cache[begin + 1][end - 1]) {
                    cache[begin][end] = true
                    count++
                }
            }
        }
    }
    return count + s.length
}