package sections.first.sortedSquares

import kotlin.math.abs

/**
 * Дан массив целых чисел x длиной N.
 * Массив упорядочен по возрастанию.
 * Написать функцию, которая из этого массива
 * получит массив квадратов чисел, упорядоченный по возрастанию.
 *
 * Тесты:
 * 1) [0, 2, 4, 6] -> [0, 4, 16, 36]
 * 2) [-5, -2, 3, 4] -> [4, 9, 16, 25]
 */

fun main() {
    val numbers = intArrayOf(0, 2, 4, 6)
    val result = square(numbers)
    result.forEach { println(it) }
}

fun square(numbers: IntArray): IntArray {
    var leftIdx = 0
    var rightIdx = numbers.size - 1
    var currIdx = numbers.size - 1
    val result = IntArray(numbers.size)
    while (currIdx >= 0) {
        val leftSqr = numbers[leftIdx] * numbers[leftIdx]
        val rightSqr = numbers[rightIdx] * numbers[rightIdx]
        result[currIdx] = if (leftSqr > rightSqr) {
            leftIdx++
            leftSqr
        } else {
            rightIdx--
            rightSqr
        }
        currIdx--
    }
    return result
}

fun square2(numbers: IntArray): IntArray {
    if (numbers.isEmpty()) return intArrayOf()

    val result = IntArray(numbers.size)
    var left = 0
    var right = numbers.lastIndex

    for (i in numbers.indices.reversed()) {
        val leftValue = numbers[left]
        val rightValue = numbers[right]

        // Сравнение абсолютных значений для безопасности и ясности
        if (abs(leftValue) > abs(rightValue)) {
            result[i] = leftValue * leftValue
            left++
        } else {
            result[i] = rightValue * rightValue
            right--
        }
    }
    return result
}
