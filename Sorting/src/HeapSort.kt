/**
 * Parent of a node = (i - 1) / 2
 * Left Child of a node = (2 * i) + 1
 * Right child of a node = (2 * i) + 2
 **/

/**
 * O(LogN)
 * Find largest from root
 * If root is largest, stop
 * If not, swap largest with root
 * Heapify on the entire array starting from largest
 */
private fun heapify(arr: IntArray, size: Int, rootIndex: Int) {
    var largest = rootIndex
    val left = 2 * rootIndex + 1
    val right = 2 * rootIndex + 2

    if (left < size && arr[left] > arr[largest]) {
        largest = left
    }

    if (right < size && arr[right] > arr[largest]) {
        largest = right
    }

    if (largest != rootIndex) {
        swap(arr, largest, rootIndex)
        heapify(arr, size, largest)
    }
}

/**
 * Build a heap from the middle of the array down to 0
 * Swap position 0 with array (index) with index varying from last index down to 1
 */
fun heapsort(arr: IntArray) {
    for (index in arr.size / 2 - 1 downTo 0) {
        heapify(arr, arr.size, index)
    }

    for (index in arr.lastIndex downTo 1) {
        swap(arr, index, 0)
        heapify(arr, index, 0)
    }

    println(arr.joinToString())
}

private fun swap(arr: IntArray, src: Int, dst: Int) {
    val temp = arr[src]
    arr[src] = arr[dst]
    arr[dst] = temp
}
