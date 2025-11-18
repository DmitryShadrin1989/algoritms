package sections.first.sortedListIntersecting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
 */

public class IntersectingSortedListsJava {

    public static void main(String[] args) {
        int[] firstArray = {1, 2, 2, 2, 5, 9};
        int[] secondArray = {2, 2, 8, 9};
        int[] result = findIntersection(firstArray, secondArray);
        System.out.println(Arrays.toString(result));
    }

    public static int[] findIntersection(int[] firstArray, int[] secondArray) {
        List<Integer> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        while(i < firstArray.length && j < secondArray.length) {
            if (firstArray[i] == secondArray[j]) {
                result.add(firstArray[i]);
                i++;
                j++;
            } else if(firstArray[i] < secondArray[j]) {
                i++;
            } else {
                j++;
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

}
