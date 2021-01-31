package main.kotlin

fun main() {
    println(
        findCycleDfs(
            hashMapOf(
                0 to listOf(1, 2),
                1 to listOf(2),
                2 to listOf(0, 3),
                3 to listOf(3)
            )
        )
    )
}