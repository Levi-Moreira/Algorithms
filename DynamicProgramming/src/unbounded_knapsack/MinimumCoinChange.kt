package unbounded_knapsack

/**
 * Exponential time and linear space
 */
fun minCoinChangeRecursive(coins: IntArray, amount: Int): Int {
    return helper(0, coins, amount)
}

private fun helper(index: Int, coins: IntArray, amount: Int): Int {
    if (amount == 0) return 0
    if (index > coins.lastIndex) return Int.MAX_VALUE

    val op1 = helper(index + 1, coins, amount)

    var op2 = Int.MAX_VALUE
    if (amount - coins[index] >= 0) {
        val res = helper(index, coins, amount - coins[index])
        if (res != Int.MAX_VALUE) op2 = res + 1
    }

    return minOf(op1, op2)
}

/**
 * O(coins * amount) time and space
 */
fun minCoinChangeRecursiveWithMemo(coins: IntArray, amount: Int): Int {
    val cache = Array(coins.size) { IntArray(amount + 1) { -1 } }
    return helper(0, coins, amount, cache)
}

private fun helper(index: Int, coins: IntArray, amount: Int, cache: Array<IntArray>): Int {
    if (amount == 0) return 0
    if (index > coins.lastIndex) return Int.MAX_VALUE

    if (cache[index][amount] != -1) return cache[index][amount]

    val op1 = helper(index + 1, coins, amount, cache)

    var op2 = Int.MAX_VALUE
    if (amount - coins[index] >= 0) {
        val res = helper(index, coins, amount - coins[index], cache)
        if (res != Int.MAX_VALUE) op2 = res + 1
    }

    cache[index][amount] = minOf(op1, op2)
    return cache[index][amount]
}

/**
 * O(coins * amount) time and space
 */
fun minCoinChangeWithTab(coins: IntArray, amount: Int): Int {
    val cache = Array(coins.size) { IntArray(amount + 1) { 0 } }

    for (amount in 1..amount) cache[0][amount] = if (amount % coins[0] == 0) {
        amount / coins[0]
    } else {
        Int.MAX_VALUE
    }

    for (index in 1..coins.lastIndex) {
        for (amount in 1..amount) {
            val op1 = cache[index - 1][amount]
            val op2 = if (amount - coins[index] >= 0) {
                if (cache[index - 1][amount - coins[index]] != Int.MAX_VALUE) {
                    cache[index - 1][amount - coins[index]] + 1
                } else {
                    Int.MAX_VALUE
                }
            } else {
                Int.MAX_VALUE
            }
            cache[index][amount] = minOf(op1, op2)
        }
    }
    return cache.last().last()
}