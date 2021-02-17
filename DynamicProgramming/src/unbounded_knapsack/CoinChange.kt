package unbounded_knapsack

/**
 * O(2 ^ N) time and N space
 */
fun coinChangeWithRecurssion(coins: IntArray, amount: Int): Int {
    return helper(0, coins, amount)
}

private fun helper(index: Int, coins: IntArray, amount: Int): Int {
    if (amount == 0) return 1
    if (index > coins.lastIndex || amount < 0) return 0

    val op1 = helper(index + 1, coins, amount)
    val op2 = helper(index + 1, coins, amount - coins[index])

    return op1 + op2
}

/**
 * O( amount * coins) time and space
 */
fun coinChangeWithRecurssionAndMemo(coins: IntArray, amount: Int): Int {
    val cache = Array(coins.size) { IntArray(amount + 1) { -1 } }
    return helper(0, coins, amount, cache)
}

private fun helper(index: Int, coins: IntArray, amount: Int, cache: Array<IntArray>): Int {
    if (amount == 0) return 1

    if (index > coins.lastIndex || amount < 0) return 0

    if (cache[index][amount] != -1) return cache[index][amount]

    val op1 = helper(index + 1, coins, amount, cache)
    val op2 = helper(index, coins, amount - coins[index], cache)

    cache[index][amount] = op1 + op2
    return cache[index][amount]
}

/**
 * O( amount * coins) time and space
 */
fun coinChangeWithTab(coins: IntArray, amount: Int): Int {
    val cache = Array(coins.size) { IntArray(amount + 1) { 0 } }

    for (row in cache.indices) cache[row][0] = 1

    for (index in cache.indices) {
        for (amount in 1..cache[0].lastIndex) {
            val op1 = if (index - 1 >= 0) cache[index - 1][amount] else 0
            val op2 = if (amount - coins[index] >= 0) cache[index][amount - coins[index]] else 0
            cache[index][amount] = op1 + op2
        }
    }
    return cache.last().last()
}