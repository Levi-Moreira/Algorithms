package fibonacci

/**
 * O(2 ^ N) time and N space
 */
fun minimumJumpsToReachEndRecursive(arr: IntArray): Int {
    val cache = IntArray(arr.size) { Int.MAX_VALUE }
    return helper(0, arr)
}

private fun helper(index: Int, arr: IntArray): Int {
    if (index == arr.lastIndex) return 0

    var minJumps = Int.MAX_VALUE
    for (jumps in 1..arr[index]) {
        if (index + jumps <= arr.lastIndex)
            minJumps = minOf(minJumps, helper(index + jumps, arr))
    }
    return if (minJumps == Int.MAX_VALUE) 0 else minJumps + 1

}

/**
 * N time and space
 */
fun minimumJumpsToReachEndRecursiveWithMemo(arr: IntArray): Int {
    val cache = IntArray(arr.size) { -1 }
    return helper(0, arr, cache)
}

private fun helper(index: Int, arr: IntArray, cache: IntArray): Int {
    if (index == arr.lastIndex) return 0

    if (cache[index] != -1) return cache[index]
    var minJumps = Int.MAX_VALUE
    for (jumps in 1..arr[index]) {
        if (index + jumps <= arr.lastIndex)
            minJumps = minOf(
                minJumps, helper(index + jumps, arr, cache)
            )
    }
    cache[index] = if (minJumps == Int.MAX_VALUE) 0 else minJumps + 1
    return cache[index]
}

/**
 * O(N ^ 2) time and N space
 */
fun minimumJumpsToReachEndWithTab(arr: IntArray): Int {
    val cache = IntArray(arr.size) { Int.MAX_VALUE }
    cache[0] = 0

    for (index in cache.indices) {
        for (jumps in index + 1..index + arr[index]) {
            if (jumps <= cache.lastIndex) {
                cache[jumps] = minOf(cache[jumps], cache[index] + 1)
            }
        }
    }
    return cache.last()
}