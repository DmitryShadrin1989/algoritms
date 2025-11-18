package sections.first.sortedListMerging

/**
 * Есть два сортированных массива.
 * Нужно написать функцию, которая делает новый сортированный список
 * со слиянием элементов этих двух списков.
 *
 * Пример:
 * 1)
 * firstArray = [1, 2, 2, 5, 7, 14]
 * secondArray = [4, 6, 6, 7, 9, 14, 15]
 * result = [1, 2, 2, 4, 5, 6, 6, 7, 7, 9, 14, 14, 15]
 *
 * 2)
 * firstArray = [1, 2, 2, 2, 5, 9]
 * secondArray = [2, 2, 8, 9]
 * result = [1, 2, 2, 2, 2, 2, 5, 8, 9, 9]
 */

fun main() {
    val firstArray = intArrayOf(1, 2, 2, 5, 7, 14)
    val secondArray = intArrayOf(4, 6, 6, 7, 9, 14, 15)
    println(merging(firstArray, secondArray).joinToString())
}

fun merging(firstArray: IntArray, secondArray: IntArray): IntArray {
    val result = IntArray(firstArray.size + secondArray.size)
    var i = 0
    var j = 0
    var k = 0
    while(i < firstArray.size && j < secondArray.size) {
        if(firstArray[i] < secondArray[j]) {
            result[k] = firstArray[i]
            i++
        } else {
            result[k] = secondArray[j]
            j++
        }
        k++
    }
    while(i < firstArray.size) {
        result[k] = firstArray[i]
        i++
        k++
    }
    while(j < secondArray.size) {
        result[k] = secondArray[j]
        j++
        k++
    }
    return result
}