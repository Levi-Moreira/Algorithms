fun quickSort(start: Int, end: Int, arr: IntArray) {
    if (start >= end) return
    val pivot = quickSelect(start, end, arr)

    quickSort(start, pivot - 1, arr)
    quickSort(pivot + 1, end, arr)
}

private fun quickSelect(start: Int, end: Int, arr: IntArray): Int {
    val position = (start..end).random()
    swap(position, end, arr)
    var placing = 0

    for (index in start until end) {
        if (arr[index] < arr[end]) {
            swap(placing, index, arr)
            placing++
        }
    }

    swap(placing, end, arr)
    return placing

}

private fun swap(start: Int, end: Int, arr: IntArray) {
    val temp = arr[start]
    arr[start] = arr[end]
    arr[end] = temp
}