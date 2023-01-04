package yandex.practicum.sprint3.exam;

/*
A. Поиск в сломанном массиве
 */

/*
-- ПРИНЦИП РАБОТЫ --
Для реализации я использовал алгоритм бинарного поиска.
Изначально задаю левый и правый индекс, вычисляю опрный индекс в середине, проверяю на равенство искомого значения, если не равно, то смещаю указатель, чтобы проделать то же самое с подмассивом.
Но так как сортировка была частично поломана - есть сдвиг и минимальное значение может находиться не на индексе 0.
То необходимо добавить дополнительные проверки, которые будут влиять дальше переходить в левый или правый подмосив. Если значение на левом индексе меньше чем на пилотном,
т.е. правильная ситуация для отсортированного массива то проверим, что искомое значение находится между ними в этом случае уйдем в левый подмассив, если иначе то в правый.
Если значение на левом индексе больше пилотного то проверим, что искомое значение лежит между значением пилотного и правого индекса - в этом случае пойдем в правый подмассив иначе в левый.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Первой проверкой if (arr[leftIndex]<= arr[pilotIndex]) мы выясняем в левой части сдвиг или в правой, и следующим шагом проверяем ту часть которая без сдвига,
т.е. которая точно отсортирована правильно на вхождение в нее искомого значение, если входит то дальше по сути работает обычный бинарный поиск,
если нет то переходим в подмассив в котором имеется сдвиг. Снова его делим пополам и делаем те же проверки.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Это немного усложненный бинарный поиск, т.е. сложность O(log(n))

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Алгоритм не использует дополнительную память поэтому О(1)

 Успешная посылка - 79530532
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int count = Integer.parseInt(reader.readLine());
            int k = Integer.parseInt(reader.readLine());
            String[] strings = reader.readLine().split(" ");
            int arr[] = new int[count];
            for (int i=0; i<count; i++) {
                arr[i] = Integer.parseInt(strings[i]);
            }
            System.out.println(brokenSearch(arr, k));
        }
    }

    public static int brokenSearch(int[] arr, int k) {
        int leftIndex = 0;
        int rightIndex = arr.length-1;

        while (leftIndex<=rightIndex) {
            int pilotIndex = (leftIndex + rightIndex)/2;

            if (arr[pilotIndex]==k) return pilotIndex;
            else if (arr[leftIndex]==k) return leftIndex;
            else if (arr[rightIndex]==k) return rightIndex;

            if (arr[leftIndex]<= arr[pilotIndex]) {
                if (arr[leftIndex] < k && k < arr[pilotIndex]) {
                    rightIndex = pilotIndex - 1;
                } else {
                    leftIndex = pilotIndex + 1;
                }
            } else {
                if (arr[pilotIndex] < k && k < arr[rightIndex]) {
                    leftIndex = pilotIndex + 1;
                } else {
                    rightIndex = pilotIndex - 1;
                }
            }
        }
        return -1;
    }
}
