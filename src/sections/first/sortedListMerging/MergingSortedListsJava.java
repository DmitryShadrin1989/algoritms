package sections.first.sortedListMerging;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class MergingSortedListsJava {

    public static void main(String[] args) {
        int[] firstArray = {1, 2, 2, 5, 7, 14};
        int[] secondArray = {4, 6, 6, 7, 9, 14, 15};
        int[] result = merging(firstArray, secondArray);
        System.out.println(Arrays.toString(result));
    }

    public static int[] merging(int[] firstArray, int[] secondArray) {
        List<Integer> result = new ArrayList<>();
        int j = 0;
        for (int k : firstArray) {
            while (j < secondArray.length && secondArray[j] <= k) {
                result.add(secondArray[j]);
                j++;
            }
            result.add(k);
        }
        while (j < secondArray.length) {
            result.add(secondArray[j]);
            j++;
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int[] merging2(int[] firstArray, int[] secondArray) {
        int[] result = new int[firstArray.length + secondArray.length];
        int i = 0, j = 0, k = 0;

        while (i < firstArray.length && j < secondArray.length) {
            if (firstArray[i] < secondArray[j]) {
                result[k++] = firstArray[i++];
            } else {
                result[k++] = secondArray[j++];
            }
        }

        while (i < firstArray.length) {
            result[k++] = firstArray[i++];
        }

        while (j < secondArray.length) {
            result[k++] = secondArray[j++];
        }

        return result;
    }
}
