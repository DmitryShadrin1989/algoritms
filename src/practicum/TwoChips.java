package practicum;

//D. Две фишки
//        Рита и Гоша играют в игру. У Риты есть n фишек, на каждой из которых написано количество очков. Сначала Гоша называет число k, затем Рита должна выбрать две фишки, сумма очков на которых равна заданному числу.
//
//        Рите надоело искать фишки самой, и она решила применить свои навыки программирования для решения этой задачи. Помогите ей написать программу для поиска нужных фишек.
//
//        Формат ввода
//        В первой строке записано количество фишек n, 2 ≤ n ≤ 104.
//
//        Во второй строке записано n целых чисел —– очки на фишках Риты в диапазоне от -105 до 105.
//
//        В третьей строке —– загаданное Гошей целое число k, -105 ≤ k ≤ 105.
//
//        Формат вывода
//        Нужно вывести два числа —– очки на двух фишках, в сумме дающие k.
//
//        Если таких пар несколько, то можно вывести любую из них.
//
//        Если таких пар не существует, то вывести «None».

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TwoChips {
    public static void main(String[] args) throws IOException {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int count = Integer.parseInt(reader.readLine());
            String[] arr = reader.readLine().split(" ");
            int exam = Integer.parseInt(reader.readLine());

            for(int i=0; i<count; i++) {
                int valueI = Integer.parseInt(arr[i]);

                for(int j=i+1; j<count; j++) {
                    int valueJ = Integer.parseInt(arr[j]);
                    int valueSum = valueI + valueJ;

                    if(valueSum == exam) {
                        System.out.print(valueI + " " + valueJ);
                        return;
                    }
                }
            }
            System.out.print("None");
        }
    }

    public static class TwoChips2 {
        public static void main(String[] args) throws IOException {
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
                int count = Integer.parseInt(reader.readLine());
                String[] arr = reader.readLine().split(" ");
                int exam = Integer.parseInt(reader.readLine());

                Set<Integer> integerSet = new HashSet<>();
                for (int i=0; i<count; i++) {
                    int a = Integer.parseInt(arr[i]);
                    int y = exam-a;
                    if (integerSet.contains(y)) {
                        System.out.println(a + " " + y);
                        return;
                    } else {
                        integerSet.add(a);
                    }
                }
                System.out.println("None");
            }
        }
    }

    public static class TwoChips3 {
        public static void main(String[] args) throws IOException {
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                int count = Integer.parseInt(reader.readLine());
                Integer[] arr = Arrays.stream(reader.readLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
                int exam = Integer.parseInt(reader.readLine());

                Arrays.sort(arr);
                int i=0;
                int j=count-1;

                while (i<j){
                    int valueI = arr[i];
                    int valueJ = arr[j];
                    int sum = valueI+valueJ;
                    if(sum==exam) {
                        System.out.println(valueI + " " + valueJ);
                        return;
                    } else if (sum<exam) {
                        i++;
                    } else j--;
                }
                System.out.println("None");
            }
        }
    }
}
