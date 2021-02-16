/**
 * Exponential time and N space
 */
fun rodCurringRecursive(lengths: IntArray, prices: IntArray, length: Int): Int {
    return helper(index = 0, lengths, prices, length, 0)
}

private fun helper(index: Int, lengths: IntArray, prices: IntArray, length: Int, paid: Int): Int {
    if (length == 0) return paid
    if (index > lengths.lastIndex) {
        return 0
    }

    val op1 = helper(index + 1, lengths, prices, length, paid)
    val op2 = helper(index, lengths, prices, length - lengths[index], paid + prices[index])
    return minOf(op1, op2)
}

/**
 * R * C time and space
 */
fun rodCurringWithTabulation(lengths: IntArray, prices: IntArray, length: Int): Int {
    val cache = Array(lengths.size) { IntArray(length + 1) { 0 } }


    for (index in cache.indices) {
        for (len in 1..cache[0].lastIndex) {
            val op1 = if (index > 0) cache[index - 1][len] else 0
            val op2 = if (len - lengths[index] >= 0) cache[index][len - lengths[index]] + prices[index] else 0

            cache[index][len] = maxOf(op1, op2)
        }
    }

    return cache.last().last()
}