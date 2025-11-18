package sections.first.sortedSquares;

import java.util.Arrays;

/**
 * Дан массив целых чисел x длиной N.
 * Массив упорядочен по возрастанию.
 * Написать функцию, которая из этого массива
 * получит массив квадратов чисел, упорядоченный по возрастанию.
 Тесты:
 1) [0, 1, 3, 5] -> [0, 1, 9, 25]
 2) [-5, -3, 1, 2] -> [1, 4, 9, 25]

 Слабые места:
  - как обходит цикл с конца или сначала?
  - правильно ли сравнивает значения?
 */
public class SortedSquaresJava {

    public static void main(String[] args) {
        int[] numbers = {-5, -3, 1, 2};
        numbers = square(numbers);
        System.out.println(Arrays.toString(numbers));
    }

    public static int[] square(int[] numbers) {
        int[] result = new int[numbers.length];
        int left = 0;
        int right = numbers.length - 1;
        int current = numbers.length - 1;

        while(current >= 0) {
            int sqrLeft = numbers[left] * numbers[left];
            int sqrRight = numbers[right] * numbers[right];

            if(sqrLeft > sqrRight) {
                result[current] = sqrLeft;
                left++;
            } else {
                result[current] = sqrRight;
                right--;
            }
            current--;
        }
        return result;
    }

    public static int[] square2(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return new int[0];
        }

        int[] result = new int[numbers.length];
        int left = 0;
        int right = numbers.length - 1;

        for (int i = numbers.length - 1; i >= 0; i--) {
            int leftAbs = Math.abs(numbers[left]);
            int rightAbs = Math.abs(numbers[right]);

            if (leftAbs > rightAbs) {
                result[i] = numbers[left] * numbers[left];
                left++;
            } else {
                result[i] = numbers[right] * numbers[right];
                right--;
            }
        }
        return result;
    }

}
