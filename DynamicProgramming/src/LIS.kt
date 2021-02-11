fun lis(arr: IntArray): Int {
    val dp = IntArray(arr.size) { 1 }

    for (i in 1..arr.lastIndex) {
        for (j in 0..i) {
            if (arr[j] > arr[i]) {
                dp[i] = maxOf(dp[i], dp[j] + 1)
            }
        }
    }
    return dp.max() ?: 0
}