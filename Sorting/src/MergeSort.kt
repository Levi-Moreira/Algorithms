fun mergeSort(start: Int, end: Int, arr: IntArray) {
    if (start >= end) return
    val middle = start + (end - start) / 2
    mergeSort(start, middle, arr)
    mergeSort(middle + 1, end, arr)

    merge(start, middle, end, arr)
}

private fun merge(start: Int, middle: Int, end: Int, arr: IntArray) {

    var p1 = start
    var p2 = middle + 1
    val ordered = mutableListOf<Int>()

    while (p1 <= middle && p2 <= end) {
        if (arr[p1] < arr[p2]) {
            ordered.add(arr[p1++])
        } else {
            ordered.add(arr[p2++])
        }
    }

    while (p1 <= middle) {
        ordered.add(arr[p1++])
    }

    while (p2 <= end) {
        ordered.add(arr[p2++])
    }
    var index = start
    for (item in ordered) {
        arr[index++] = item
    }
}