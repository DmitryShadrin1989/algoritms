package practicum.course_2022.sprint2.exam;

/*
Гоша реализовал структуру данных Дек, максимальный размер которого определяется заданным числом. Методы push_back(x), push_front(x), pop_back(), pop_front() работали корректно. Но, если в деке было много элементов, программа работала очень долго. Дело в том, что не все операции выполнялись за O(1). Помогите Гоше! Напишите эффективную реализацию.

Внимание: при реализации используйте кольцевой буфер.

Формат ввода
В первой строке записано количество команд n — целое число, не превосходящее 100000. Во второй строке записано число m — максимальный размер дека. Он не превосходит 50000. В следующих n строках записана одна из команд:

push_back(value) – добавить элемент в конец дека. Если в деке уже находится максимальное число элементов, вывести «error».
push_front(value) – добавить элемент в начало дека. Если в деке уже находится максимальное число элементов, вывести «error».
pop_front() – вывести первый элемент дека и удалить его. Если дек был пуст, то вывести «error».
pop_back() – вывести последний элемент дека и удалить его. Если дек был пуст, то вывести «error».
Value — целое число, по модулю не превосходящее 1000.
Формат вывода
Выведите результат выполнения каждой команды на отдельной строке. Для успешных запросов push_back(x) и push_front(x) ничего выводить не надо.
 */

/*
-- ПРИНЦИП РАБОТЫ --
Для реализации двусторонней очереди я использовал принцип кольцевого буфера. В конструктор своего Дек я передаю размер очереди, в результате будет создан соотвествующий массив.
Далее я использую два указателя: на начало очереди indexHead и на конец indexTail. Когда очередь пустая указатели смотрят на один элемент массива (не обязательно 0).
Для более удобного тестирования все команды которые создаются я собираю в массив, а результат у меня склеивается в переменной result.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Выполняется проверка при добавлении новых элементов на переполненность очереди.
Если вызывается команда push_front то элемент добавляется в начало очереди, т.е. в следующую ячейку за indexHead.
Если вызывается команда push_back то элемент добавляется в конец очереди, т.е. в ячейку перед indexTail.
При этом делается проверка что очередь пустая countElements == 0, в этом случае вставка делается в тот же элемент на который смотрит indexHead и indexTail.

Команды pop_front и pop_back выводят на экран значения того элемента на который они смотрят и делают сдвиг своего указателя pop_front сдвигает indexHead на -1, а pop_back сдвигает indexTail на +1.
При этом учитывается переход через границу, т.е. если текушее значение у indexTail = размер массива -1 то ему присваевается значение 0 и наоборот если indexHead = 0 то ему присваевается значение размер массива -1.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Во всех механизмах по вставке в очередь и извлечению у меня используется прямое позиционирование, т.е. нет никаких дополнительных обходов.
Поэтому временная сложность О(1)
Иван Самсонов - Но ведь кол-во команд не константно, верно?
Дмитрий Шадрин - хмм.. так мы же должны оценить временную сложность для одной операции (вставка, извлечение),
т.е. например оценивая временную сложность по получению элемента по индексу в ArrayList мы оцениваем именно эту опеацию. У меня эти операции происходят по прямому позиционированию поэтому оставляю О(1):)

Иван Самсонов - Нет, мы должны оценить программу целиком. Считывание данных можно опустить
Дмитрий Шадрин - Тогда получается тут линейная зависимость от количества команд, т.е. O(n).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Пямять потребляется в прямой зависимости от количества которое передается первым аргументов, т.е. именно такой массив и будет создан.
Поэтому пространственная сложность О(1).
Иван Самсонов - O(1) это когда что бы нам ни передали, мы создадим массив одинакового размера (пример - ловкость рук задача из первого спринта). Тут явно другой случай, в 36 строчке это видно
Дмитрий Шадрин - согласен, если честно опечатался и не проверил. Пространственная сложность растет линнейно поэтому О(n)

 Успешная посылка - 79078002
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.OptionalInt;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int amountCommands = Integer.parseInt(reader.readLine());
            Deq deq = new Deq(Integer.parseInt(reader.readLine()));

            StringBuilder result = new StringBuilder();

            for (int i=0; i<amountCommands; i++) {
                String[] command = reader.readLine().split(" ");
                switch (command[0]) {
                    case "push_back":
                        if (!deq.push_back(Integer.parseInt(command[1]))) result.append("error").append("\n");
                        break;
                    case "push_front":
                        if (!deq.push_front(Integer.parseInt(command[1]))) result.append("error").append("\n");
                        break;
                    case "pop_back": {
                        OptionalInt optionalInt = deq.pop_back();
                        if (optionalInt.isPresent()) {
                            result.append(optionalInt.getAsInt()).append("\n");
                        } else result.append("error").append("\n");
                        break;
                    }
                    case "pop_front": {
                        OptionalInt optionalInt = deq.pop_front();
                        if (optionalInt.isPresent()) {
                            result.append(optionalInt.getAsInt()).append("\n");
                        } else result.append("error").append("\n");
                        break;
                    }
                }
            }
            System.out.println(result);
        }
    }

    static class Deq {
        int size;
        int[] ints;
        int indexTail = 0;
        int indexHead = 0;
        int countElements = 0;

        public Deq(int size) {
            this.size = size;
            this.ints = new int[size];
        }

        public boolean push_back(int value) {
            if (countElements==size) {
                return false;
            }
            ints[indexTail] = value;
            indexTail = (indexTail-1+size)%size;
            countElements++;
            return true;
        }

        public boolean push_front(int value) {
            if (countElements==size) {
                return false;
            }
            indexHead = (indexHead+1+size)%size;
            ints[indexHead] = value;
            countElements++;
            return true;
        }

        public OptionalInt pop_front() {
            if (countElements == 0) {
                return OptionalInt.empty();
            }
            OptionalInt optionalInt = OptionalInt.of(ints[indexHead]);
            indexHead = (indexHead-1+size)%size;
            countElements--;
            return optionalInt;
        }

        public OptionalInt pop_back() {
            if (countElements == 0) {
                return OptionalInt.empty();
            }
            indexTail = (indexTail+1+size)%size;
            OptionalInt optionalInt = OptionalInt.of(ints[indexTail]);
            countElements--;

            System.out.println();

            return optionalInt;
        }
    }

}
