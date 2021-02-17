package longest_common_substring

/**
 * O(N * M). Maps directly to longest common subsequence
 */
fun minimumDeletionInsertion(str1: String, str2: String): Int {
    val cache = Array(str1.length + 1) { IntArray(str2.length) { 0 } }
    var maxLen = 0

    for (index1 in 1..cache.lastIndex) {
        for (index2 in 1..cache[0].lastIndex) {
            cache[index1][index2] = if (str1[index1 - 1] == str2[index2 - 1]) {
                cache[index1 - 1][index2 - 1] + 1
            } else {
                maxOf(cache[index1][index2 - 1], cache[index1 - 1][index2])
            }

            maxLen = maxOf(maxLen, cache[index1][index2])
        }
    }

    return maxLen
}