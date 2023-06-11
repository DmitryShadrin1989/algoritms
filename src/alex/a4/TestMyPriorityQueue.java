package alex.a4;
/*
Задание 6: Простая очередь с приоритетом (6 баллов)

Для выполнения этой задачи сохраните свою работу в smallman; MyPriorityQueue.java
Как и очередь, приоритетная очередь - это контейнер, который позволяет пользователям добавлять и удалять объекты.
Операция добавления позволяет пользователям свободно добавлять объекты в любое время.
Однако, в отличие от очереди, операция удаления в приоритетной очереди всегда удаляет самый маленький объект в контейнере.
В этой задаче вы должны реализовать простую очередь приоритетов, объявленную как
открытый класс MyPriorityQueue<T>, реализующий IPriorityQueue<T>.
Параметр общего типа T определяет тип объекта, который может быть добавлен в контейнер. Класс имеет
только один конструктор. Поскольку нам понадобится способ сравнения элементов типа T, конструктор принимает
Сравните с<T>, например, так:
публичный MyPriorityQueue(по сравнению с<T> cc)
Интерфейс CompareWith<T> предоставляет метод boolean LessThan(T a, T b), который возвращает
значение true тогда и только тогда, когда a меньше b. Вот как мы будем сравнивать элементы в
контейнере приоритетной очереди.

Интерфейс IPriorityQueue<T>, который реализует наш класс, ожидает следующие методы:
// добавляет элемент
public void add(T item)

// добавляет список элементов
public void addAll(список<T> элементов)

// возвращает наименьший элемент, находящийся в данный момент в контейнере (если их несколько
// такие предметы, верните любой из них)
public T getMinimum()

// удаляет самый маленький элемент в контейнере (если таких несколько
// items, удалите тот, который вернул бы getMinimum.)
public void removeMinimum()

// возвращает, сколько элементов в контейнере имеет
public int size()

// возвращает итератор, который перечислит все элементы в контейнере из
//
public Iterator<T> iterator()

// возвращает итератор, который перечислит все элементы в контейнере из
// от большого к маленькому. более конкретно, он должен перечислять его в обратном порядке iterator().
public Iterator<T> revIterator()

Хотя Java имеет встроенную очередь приоритетов, вам не разрешается ее использовать. На это есть веские причины:
встроенная очередь приоритетов не обладает всеми необходимыми нам функциональными возможностями, и расширить ее гораздо
сложнее, чем создать новую реализацию с нуля.

Мы рекомендуем вам хранить элементы вашего контейнера в ArrayList<T>, например, с помощью
объявление переменной экземпляра private List<T> queueItems;
Более конкретно, если queueItems сохраняется отсортированным, оба итератора должны быть просты в поддержке.
Основные правила: Единственный файл, который вы можете изменить здесь (помимо создания / записи тестов), - это MyPriorityQueue.java .

 */

import java.util.Iterator;

public class TestMyPriorityQueue {
    public static void main(String[] args) {
        MyPriorityQueue<Integer> queue = new MyPriorityQueue<>(new CompareWith<Integer>() {
            @Override
            public boolean lessThan(Integer a, Integer b) {
                return a.compareTo(b)>0;
            }
        });

        queue.add(2);
        queue.add(1);
        queue.add(5);
        queue.add(7);
        queue.add(6);

        System.out.println("iterator");
        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("----------------------------------");

        System.out.println("revIterator");
        Iterator<Integer> revIterator = queue.revIterator();
        while (revIterator.hasNext()) {
            System.out.println(revIterator.next());
        }
        System.out.println("----------------------------------");

        System.out.println(queue.getMinimum());
        queue.removeMinimum();
        System.out.println(queue.getMinimum());
        queue.removeMinimum();
        System.out.println(queue.getMinimum());
        queue.removeMinimum();
        System.out.println(queue.getMinimum());
    }
}
