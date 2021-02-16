fun fib(n: Int): Int {
    if (n == 0) return 0
    if (n == 1) return 1

    return fib(n - 1) + fib(n - 2)
}

fun fibWithMemo(n: Int, cache: IntArray): Int {
    if (n < 2) return n

    if (cache[n] != 0) return cache[n]

    cache[n] = fib(n - 1) + fib(n - 2)
    return cache[n]
}

fun fibWithTab(n: Int): Int {
    val cache = IntArray(n + 1) { 0 }

    cache[1] = 1

    for (number in 2..n) {
        cache[number] = cache[number - 1] + cache[number - 2]
    }

    return cache.last()
}

fun finWithContantSpace(n: Int): Int {
    var first = 0
    var second = 1

    for (number in 2..n) {
        val nextFirst = second
        second += first
        first = nextFirst
    }
    return second
}

fun main() {
    print(finWithContantSpace(5))
}