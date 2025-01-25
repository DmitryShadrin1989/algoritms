package practicum.course_2022.sprint4.exam;

/*
B. Хеш-таблица

Тимофей, как хороший руководитель, хранит информацию о зарплатах своих сотрудников в базе данных и постоянно её обновляет. Он поручил вам написать реализацию хеш-таблицы, чтобы хранить в ней базу данных с зарплатами сотрудников.

Хеш-таблица должна поддерживать следующие операции:

put key value —– добавление пары ключ-значение. Если заданный ключ уже есть в таблице, то соответствующее ему значение обновляется.
get key –— получение значения по ключу. Если ключа нет в таблице, то вывести «None». Иначе вывести найденное значение.
delete key –— удаление ключа из таблицы. Если такого ключа нет, то вывести «None», иначе вывести хранимое по данному ключу значение и удалить ключ.
В таблице хранятся уникальные ключи.

Требования к реализации:

Нельзя использовать имеющиеся в языках программирования реализации хеш-таблиц (std::unordered_map в С++, dict в Python, HashMap в Java, и т. д.)
Разрешать коллизии следует с помощью метода цепочек или с помощью открытой адресации.
Все операции должны выполняться за O(1) в среднем.
Поддерживать рехеширование и масштабирование хеш-таблицы не требуется.
Ключи и значения, id сотрудников и их зарплата, —– целые числа. Поддерживать произвольные хешируемые типы не требуется.
Формат ввода
В первой строке задано общее число запросов к таблице n (1≤ n≤ 106).

В следующих n строках записаны запросы, которые бывают трех видов –— get, put, delete —– как описано в условии.

Все ключи и значения –— целые неотрицательные числа, не превосходящие 109.

При любой последовательности команд, количество ключей в хеш-таблице не может превышать 105.

Формат вывода
На каждый запрос вида get и delete выведите ответ на него в отдельной строке.
 */

/*
-- ПРИНЦИП РАБОТЫ --
В основе хэш таблицы лежит массив связных списков (LinkedList) - массив баккетов/корзины. Элементами LinkedList являются экземляры класа Node.
Node имеет два свойства: ключ (Integer key) и значение (Integer value).
Поиск значения по ключу (для всех операций выполняется сначала поиск) начинается с вычисления индекса корзины, для этого получаем хэш кода ключа
и потом вычисляем остаток от деления на размер массива - это будет индесом корзины.
Далее если корзина не пустая то запускаем обход LinkedList, сравнивая ключ с ключами Node из связанного списка.
Далее с найденным элементом можно уже выполнить необходимую операцию.


-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Размер массива корзин в моей реализации фиксированный - 1000003. Исходил из условия задачи, что реализовывать операцию рехеширования не нужно,
а максимальное количество ключей в хеш-таблице не может превышать 1000000. 1000003 - это ближайшее простое число.
Операции с хэш таблицей:
 - При добавлении элемента происходит проверка - если заданный ключ уже есть в таблице, то соответствующее ему значение обновляется.
 - Получение значения по ключу. Если ключа нет в таблице, то возвращается Null (далее в результат выводиться "None"). Иначе возвращается и выводиться найденное значение.
 - Удаление ключа из таблицы. Если ключ не найден, то возвращается Null (далее в результат выводиться «None»).
 Иначе возвращается и выводиться хранимое по данному ключу значение и удаляется из хэш таблицы.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
За счет быстрого арифметического вычисления индекса корзины (на основании хэш кода)
и минимально возможного числа коллизий (исходя из условия задачи об ограниченности количества ключей (1000000).
Временная сложность в среднем и лучшем случае для одного запроса получается O(1).
Для программы в целом сложность линейная O(N*1) ~ O(N), где N - это количество команд.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Будет выделяться память для массива корзин, который будет содержать ссылки на связанные списки - N, память для связанных списков (хранящих ссылки на ноды) - M.
Пространственная сложность будет O(N+M) ~ O(M)

 Успешная посылка - 79840490
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;

public class B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int countCommands = Integer.parseInt(reader.readLine());
            MyHashTable hashTable = new MyHashTable();

            StringBuilder resultBuilder = new StringBuilder();
            for (int i = 0; i<countCommands; i++) {
                String[] command = reader.readLine().split(" ");
                if (command[0].equals("get")) {
                    Integer value = hashTable.get(Integer.parseInt(command[1]));
                    if (resultBuilder.length()>0) resultBuilder.append("\n");
                    resultBuilder.append((value != null ? value : "None"));
                } else if (command[0].equals("put")) {
                    hashTable.put(Integer.parseInt(command[1]), Integer.parseInt(command[2]));
                } else if (command[0].equals("delete")) {
                    Integer value = hashTable.delete(Integer.parseInt(command[1]));
                    if (resultBuilder.length()>0) resultBuilder.append("\n");
                    resultBuilder.append((value != null ? value : "None"));
                }
            }
            System.out.println(resultBuilder);
        }
    }

    static class MyHashTable {
        LinkedList[] baskets = new LinkedList[1000003];
        int size = 1000003;

        public void put(Integer key, Integer value) {
            int indexBasket = getIndexBasket(key);
            LinkedList<Node> linkedList = baskets[indexBasket];
            if (linkedList == null) {
                linkedList = new LinkedList<>();
                baskets[indexBasket] = linkedList;
            }
            if (linkedList.isEmpty()) {
                linkedList.add(new Node(key, value));
            } else {
                for (Node node : linkedList) {
                    if (node.key.equals(key)) {
                        node.value = value;
                        return;
                    }
                }
                linkedList.add(new Node(key, value));
            }
        }

        public Integer get(Integer key) {
            LinkedList<Node> linkedList = baskets[getIndexBasket(key)];
            if (linkedList != null) {
                for (Node node : linkedList) {
                    if (node.key.equals(key)) {
                        return node.value;
                    }
                }
            }
            return null;
        }

        public Integer delete(Integer key) {
            LinkedList<Node> linkedList = baskets[getIndexBasket(key)];
            if (linkedList != null) {
                Iterator<Node> iterator = linkedList.iterator();
                while (iterator.hasNext()){
                    Node node = iterator.next();
                    if (node.key.equals(key)) {
                        iterator.remove();
                        return node.value;
                    }
                }
            }
            return null;
        }

        private int getIndexBasket(Integer key) {
            return key.hashCode() % size;
        }

        class Node {
            private Integer key;
            private Integer value;

            public Node(Integer key, Integer value) {
                this.key = key;
                this.value = value;
            }
        }
    }
}
