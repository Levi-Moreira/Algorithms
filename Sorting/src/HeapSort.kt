/**
 * Parent of a node = (i - 1) / 2
 * Left Child of a node = (2 * i) + 1
 * Right child of a node = (2 * i) + 2
 **/

private fun heapify(arr: IntArray, size: Int, rootIndex: Int) {
    var largest = rootIndex
    val left = 2 * rootIndex + 1
    val right = 2 * rootIndex + 2

    if (left < size && arr[left] > arr[rootIndex]) {
        largest = left
    }

    if (right < size && arr[right] > arr[rootIndex]) {
        largest = right
    }

    if (largest != rootIndex) {
        swap(arr, largest, rootIndex)
        heapify(arr, size, largest)
    }
}

fun heapsort(arr: IntArray) {
    for (index in arr.size / 2 - 1 downTo 0) {
        heapify(arr, arr.size, index)
    }

    for (index in arr.lastIndex downTo 1) {
        swap(arr, 0, arr.lastIndex)
        heapify(arr, index, 0)
    }
}

private fun swap(arr: IntArray, src: Int, dst: Int) {
    val temp = arr[src]
    arr[src] = arr[dst]
    arr[dst] = temp
}

