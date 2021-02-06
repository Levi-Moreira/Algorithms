/**
 * Most basic and elementary form of Binary Search
 * Search Condition can be determined without comparing to the element's neighbors (or use specific elements around it)
 * No post-processing required because at each step, you are checking to see if the element has been found.
 * If you reach the end, then you know the element is not found
 * Initial Condition: left = 0, right = length-1
 * Termination: left > right
 * Searching Left: right = mid-1
 * Searching Right: left = mid+1
 */
fun binarySearch1(arr: IntArray, target: Int): Boolean {
    var start = 0
    var end = arr.lastIndex

    while (start <= end) {
        val middle = start + (end - start) / 2

        if (arr[middle] == target) return true

        if (target > arr[middle]) {
            start = middle + 1
        } else {
            end = middle - 1
        }
    }

    return false
}

/**
 * An advanced way to implement Binary Search.
 * Search Condition needs to access element's immediate right neighbor
 * Use element's right neighbor to determine if condition is met and decide whether to go left or right
 * Gurantees Search Space is at least 2 in size at each step
 * Post-processing required. Loop/Recursion ends when you have 1 element left.
 * Need to assess if the remaining element meets the condition.
 * Initial Condition: left = 0, right = length
 * Termination: left == right
 * Searching Left: right = mid
 * Searching Right: left = mid+1
 */
fun binarySearch2(arr: IntArray, target: Int): Boolean {
    var start = 0
    var end = arr.lastIndex

    while (start < end) {
        val middle = start + (end - start) / 2

        if (arr[middle] == target) return true

        if (target > arr[middle]) {
            start = middle + 1
        } else {
            end = middle
        }
    }

    if (start <= arr.lastIndex) {
        return arr[start] == target
    }
    return false
}

/**
 * An alternative way to implement Binary Search
 * Search Condition needs to access element's immediate left and right neighbors
 * Use element's neighbors to determine if condition is met and decide whether to go left or right
 * Gurantees Search Space is at least 3 in size at each step
 * Post-processing required. Loop/Recursion ends when you have 2 elements left. Need to assess if the remaining elements meet the condition.
 * Initial Condition: left = 0, right = length-1
 * Termination: left + 1 == right
 * Searching Left: right = mid
 * Searching Right: left = mid
 */
fun binarySearch3(arr: IntArray, target: Int): Boolean {
    var start = 0
    var end = arr.lastIndex

    while (start + 1 < end) {
        val middle = start + (end - start) / 2

        if (arr[middle] == target) return true

        if (target > arr[middle]) {
            start = middle
        } else {
            end = middle
        }
    }

    if (arr[start] == target || arr[end] == target) return true
    return false
}