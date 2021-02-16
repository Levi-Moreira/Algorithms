/**
 * O(2 ^ N) time and N space
 */
fun subSetSum(arr: IntArray, sum: Int): Boolean {
    return helper(0, arr, sum)
}

private fun helper(index: Int, arr: IntArray, sum: Int): Boolean {
    if (sum == 0) return true
    if (index > arr.lastIndex || sum < 0) return false

    return helper(index + 1, arr, sum) || helper(index + 1, arr, sum - arr[index])
}

/**
 * O(sum * N) time and space
 */
fun subSetSumWithMemo(arr: IntArray, sum: Int): Boolean {
    val cache = Array(arr.size) { Array<Boolean?>(sum + 1) { null } }
    return helper(0, arr, sum, cache)
}

private fun helper(index: Int, arr: IntArray, sum: Int, cache: Array<Array<Boolean?>>): Boolean {
    if (sum == 0) return true
    if (index > arr.lastIndex || sum < 0) return false

    if (cache[index][sum] != null) return cache[index][sum]!!

    cache[index][sum] = helper(index + 1, arr, sum, cache) || helper(index + 1, arr, sum - arr[index], cache)
    return cache[index][sum]!!
}

/**
 * O (sum * N) time and space
 */
fun subSetSumWithTab(arr: IntArray, sumUp: Int): Boolean {
    val cache = Array(arr.size) { BooleanArray(sumUp + 1) { false } }

    for (row in cache.indices) cache[row][0] = true
    for (col in 1..cache[0].lastIndex) cache[0][col] = arr[0] == col


    for (index in 1..arr.lastIndex) {
        for (sum in 1..sumUp) {
            val op1 = cache[index - 1][sum]
            val op2 = if (sum - arr[index] >= 0) cache[index - 1][sum - arr[index]] else false
            cache[index][sum] = op1 || op2
        }
    }


    return cache.last()[sumUp]
}