/**
 * O (2 ^ N) time
 * O (N) space
 */
fun recursiveCanPartition(num: IntArray): Boolean {
    var targetSum = 0
    for (number in num) {
        targetSum += number
    }
    return if (targetSum % 2 != 0) false else helper(0, num, targetSum / 2, 0)
}

private fun helper(
    index: Int,
    nums: IntArray,
    targetSum: Int,
    currentSum: Int
): Boolean =
    if (index > nums.lastIndex) {
        currentSum == targetSum
    } else {
        helper(index + 1, nums, targetSum, currentSum) ||
                helper(index + 1, nums, targetSum, currentSum + nums[index])
    }

/**
 * O (N * SUM) time
 * O (N * SUM) space
 */
fun recursiveCanPartitionWithCache(num: IntArray): Boolean {
    var targetSum = 0
    for (number in num) {
        targetSum += number
    }

    if (targetSum % 2 != 0) return false
    val cache = Array(num.size) { Array<Boolean?>(targetSum / 2 + 1) { null } }
    return helper(0, num, targetSum / 2, cache)
}

private fun helper(
    index: Int,
    nums: IntArray,
    targetSum: Int,
    cache: Array<Array<Boolean?>>
): Boolean {
    if (index > nums.lastIndex) {
        return false
    }
    if (targetSum == 0) return true
    if (targetSum < 0) return false

    if (cache[index][targetSum] != null) return cache[index][targetSum]!!

    cache[index][targetSum] = helper(index + 1, nums, targetSum, cache) ||
            helper(index + 1, nums, targetSum - nums[index], cache)

    return cache[index][targetSum]!!
}

/**
 * O (N * SUM) time
 * O (N * SUM) space
 */
fun canPartitionWithTabulation(num: IntArray): Boolean {
    var targetSum = 0
    for (number in num) {
        targetSum += number
    }

    if (targetSum % 2 != 0) return false
    val cache = Array(num.size) { BooleanArray(targetSum / 2 + 1) { false } }

    for (row in cache.indices) cache[row][0] = true
    for (col in 1..cache[0].lastIndex) {
        cache[0][col] = if (num[0] == col) true else false
    }

    for (index in 1..cache.lastIndex) {
        for (sum in 1..cache[0].lastIndex) {
            val op1 = cache[index - 1][sum]
            val op2 = if (sum - num[index] >= 0) cache[index - 1][sum - num[index]] else false
            cache[index][sum] = op1 || op2
        }
    }

    return cache.last().last()
}
