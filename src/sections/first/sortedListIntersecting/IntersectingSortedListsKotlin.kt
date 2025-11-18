package sections.first.sortedListIntersecting

/**
 * Есть два сортированных массива.
 * Нужно написать функцию, которая делает новый сортированный список
 * с пересечением элементов этих двух списков.
 *
 * Пример:
 * 1-ый список: 1, 2, 2, 5, 7, 14
 * 2-й список: 4, 6, 6, 7, 9, 14, 15
 *
 * Пересечение: 7, 14
 *
 * 1)
 * firstArray = [1, 2, 2, 2, 5, 9]
 * secondArray = [2, 2, 8, 9]
 * result = [2, 2, 9]
 *
 * Обратить внимание:
 * 1) На var и val
 * 2)
 */

fun main() {
    val firstArray = intArrayOf(1, 2, 2, 5, 7, 14)
    val secondArray = intArrayOf(4, 6, 6, 7, 9, 14, 15)
    val result = findIntersection(firstArray, secondArray)
    println(result.contentToString())
}

fun findIntersection(firstArray: IntArray, secondArray: IntArray): IntArray {
    if (firstArray.isEmpty() || secondArray.isEmpty()) return intArrayOf()
    var i = 0
    var j = 0
    val result = mutableListOf<Int>()
    while(i < firstArray.size && j < secondArray.size) {
        when {
            firstArray[i] == secondArray[j] -> {
                result.add(firstArray[i])
                i++
                j++
            }
            firstArray[i] < secondArray[j] -> i++
            else -> j++
        }
    }
    return result.toIntArray()
}

fun intersection2(first: IntArray, second: IntArray): List<Int> {
    val result = mutableListOf<Int>()
    var j = 0

    for (a in first) {
        // Пропускаем элементы во втором массиве, которые меньше текущего
        while (j < second.size && second[j] < a) j++

        if (j >= second.size) break

        if (second[j] == a) {
            result.add(a)
            j++ // Переходим к следующему элементу во втором массиве
        }
    }

    return result
}
