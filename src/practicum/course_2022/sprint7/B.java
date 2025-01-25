package practicum.course_2022.sprint7;

/*
B. Расписание
Дано количество учебных занятий, проходящих в одной аудитории. Для каждого из них указано время начала и конца.
Нужно составить расписание, в соответствии с которым в классе можно будет провести как можно больше занятий.

Если возможно несколько оптимальных вариантов, то выведите любой.
Возможно одновременное проведение более чем одного занятия нулевой длительности.

Формат ввода
В первой строке задано число занятий. Оно не превосходит 1000.
Далее для каждого занятия в отдельной строке записано время начала и конца, разделённые пробелом.
Время задаётся одним целым числом h, если урок начинается/заканчивается ровно в h часов.
Если же урок начинается/заканчивается в h часов m минут, то время записывается как h.m.
Гарантируется, что каждое занятие начинается не позже, чем заканчивается. Указываются только значащие цифры.

Формат вывода
Выведите в первой строке наибольшее число уроков, которое можно провести в аудитории.
Далее выведите время начала и конца каждого урока в отдельной строке в порядке их проведения.

Пример 1
Ввод:
5
9 10
9.3 10.3
10 11
10.3 11.3
11 12

Вывод:
3
9 10
10 11
11 12

Пример 2
Ввод:
3
9 10
11 12.25
12.15 13.3

Вывод:
2
9 10
11 12.25

Пример 3
Ввод:
7
19 19
7 14
12 14
8 22
22 23
5 21
9 23

Вывод:
3
7 14
19 19
22 23
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());

            ArrayList<Lesson> lessons = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String[] strings = reader.readLine().split(" ");
                lessons.add(new Lesson(Double.parseDouble(strings[0]), Double.parseDouble(strings[1])));
            }
            ArrayList<Lesson> result = new ArrayList<>();
            Collections.sort(lessons);
            Lesson prev = lessons.get(0);
            result.add(prev);
            for (int i = 1; i < n; i++) {
                Lesson current = lessons.get(i);
                if (!(current.startTime < prev.endTime)) {
                    result.add(current);
                    prev = current;
                } else if (current.startTime == current.endTime) {
                    result.add(current);
                }
            }
            System.out.println(result.size());
            for (Lesson lesson: result) {
                System.out.println(lesson);
            }
        }
    }

    static class Lesson implements Comparable<Lesson> {
        double startTime;
        double endTime;

        public Lesson(double startTime, double endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Lesson other) {
            double result;
            result = this.endTime - other.endTime;
            if (result == 0) {
             result = this.startTime - other.startTime;
            }
            return (int) result;
        }

        @Override
        public String toString() {
            return NumberFormat.getInstance(Locale.US).format(startTime) + " " + NumberFormat.getInstance(Locale.US).format(endTime);
        }
    }
}
