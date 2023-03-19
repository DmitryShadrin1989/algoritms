package yandex.practicum.sprint6.exam.B;

/*
B. Железные дороги

В стране X есть n городов, которым присвоены номера от 1 до n.
Столица страны имеет номер n.
Между городами проложены железные дороги.

Однако дороги могут быть двух типов по ширине полотна.
Любой поезд может ездить только по одному типу полотна.
Условно один тип дорог помечают как R, а другой как B.
То есть если маршрут от одного города до другого имеет как дороги типа R, так и дороги типа B,
то ни один поезд не сможет по этому маршруту проехать.
От одного города до другого можно проехать только по маршруту, состоящему исключительно из дорог типа R или только из дорог типа B.

Но это ещё не всё.
По дорогам страны X можно двигаться только от города с меньшим номером к городу с большим номером.
Это объясняет большой приток жителей в столицу, у которой номер n.

Карта железных дорог называется оптимальной, если не существует пары городов A и B такой,
что от A до B можно добраться как по дорогам типа R, так и по дорогам типа B.
Иными словами, для любой пары городов верно,
что от города с меньшим номером до города с бОльшим номером можно добраться по дорогам только какого-то одного типа или же что маршрут построить вообще нельзя.
Выясните, является ли данная вам карта оптимальной.

Формат ввода
В первой строке дано число n (1 ≤ n ≤ 5000) — количество городов в стране.
Далее задана карта железных дорог в следующем формате.

Карта задана n-1 строкой. В i-й строке описаны дороги из города i в города i+1, i+2, ..., n.
В строке записано n - i символов, каждый из которых либо R, либо B.
Если j-й символ строки i равен «B», то из города i в город i + j идет дорога типа «B». Аналогично для типа «R».

Формат вывода
Выведите «YES», если карта оптимальна, и «NO» в противном случае.

Пример 1
Ввод:
3
RB
R

Вывод:
NO


Пример 2
Ввод:
4
BBB
RB
B

Вывод:
YES

Пример 3
Ввод:
5
RRRB
BRR
BR
R

Вывод:
NO

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int countVertex = Integer.parseInt(reader.readLine());
            HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
            for (int i = 1; i < countVertex; i++) {
                String[] strings = reader.readLine().split("");
                for (int j = 0; j < strings.length; j++) {
                    int key;
                    int value;
                    if (strings[j].equals("R")) {
                        key = i;
                        value = j+1+i;
                    } else {
                        key = j+1+i;
                        value = i;
                    }
                    if (map.containsKey(key)) {
                        map.get(key).add(value);
                    } else {
                        ArrayList<Integer> arrayList = new ArrayList<>();
                        arrayList.add(value);
                        map.put(key, arrayList);
                    }
                }
            }

            int[] color = new int[countVertex];
            for (int i = 0; i < countVertex; i++) {
                if (color[i] == 0 && checkingForCycle(map, color, i+1)) {
                    System.out.println("NO");
                    return;
                }
            }
            System.out.println("YES");
        }
    }

    public static boolean checkingForCycle(HashMap<Integer, ArrayList<Integer>> map, int[] color, int startVertex) {
        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (color[v-1] == 0) {
                color[v-1] = 1;
                stack.push(v);
                ArrayList<Integer> arrayList = map.get(v);
                if (arrayList == null) continue;
                for (int i = 0; i < arrayList.size(); i++) {
                    int u = arrayList.get(i);
                    if (color[u-1] == 0) {
                        stack.push(arrayList.get(i));
                    } else if (color[u-1] == 1) {
                        return true;
                    }
                }
            } else if (color[v-1] == 1) {
                color[v-1] = 2;
            }
        }
        return false;
    }
}