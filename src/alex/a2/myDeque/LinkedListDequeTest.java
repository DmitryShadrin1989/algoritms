package alex.a2.myDeque;

/*

Задание 3: Декодирование с использованием двусвязных списков и массивов (8 баллов)
Для выполнения этой задачи сохраните свою работу в mydeque/

В этой задаче вы будете создавать реализации “двусторонней очереди”, используя как списки, так и
массивы. Это расширяет обсуждение нашего связанного списка и списка массивов из класса. Вы можете ознакомиться с
более подробным объяснением в главе 5 книги1.
(Следующее задание будет касаться переупаковки его более подходящим для Java способом.)
API Deque
Двусторонняя очередь аналогична структурам данных linked list и array list, которые вы видели
в классе. Для получения более авторитетного определения (из cplusplus.com ), deque (обычно произносится как
“колода”) - это неправильная аббревиатура от double-ended queue. Двусторонние очереди - это контейнеры последовательностей
с динамическими размерами, которые могут быть расширены или сжаты с обоих концов (как спереди, так и сзади).

Для наших нужд любая реализация deque должна содержать в точности следующие операции:

// Adds an item of type T to the front of the deque.
public void addFirst(T item)

// Adds an item of type T to the back of the deque.
public void addLast(T item)

// Returns true if deque is empty, false otherwise.
public boolean isEmpty()

// Returns the number of items in the deque.
public int size()

// Returns a string showing the items in the deque from first to last,
// separated by a space.
public String toString()

// Removes and returns the item at the front of the deque.
// If no such item exists, returns null.
public T removeFirst()

// Removes and returns the item at the back of the deque.
// If no such item exists, returns null.
public T removeLast()

// Gets the item at the given index, where 0 is the front, 1 is the next item,
// and so forth. If no such item exists, returns null. Must not alter the deque!
public T get(int index)

Ваш класс должен принимать любой универсальный тип (не только целые числа).

Связанный список Deque
Для этой части сохраните свою работу в файле с именем LinkedListDeque.java.
Ваша задача состоит в том, чтобы создать класс LinkedListDeque, который будет основан на (дважды) связанном списке. Ваши
операции регулируются следующими правилами:
• семейства операций .add и .remove не должны включать в себя никакого зацикливания или рекурсии. Следовательно,
одна такая операция должна занимать “постоянное время”. То есть время его выполнения не должно
зависеть от размера deque.
1https://introds.philinelabs.net

• .get должен использовать итерацию, а не рекурсию.
• .размер должен занимать постоянное время.
• У вас не должно быть посторонних/болтающихся узлов. В частности, объем памяти, который ваша
программа использует в любой момент времени, должен быть пропорционален количеству элементов. Например, если вы
добавите 1000 элементов в список, а затем удалите 999 элементов, результирующий размер должен быть больше похож на
список с 1 элементом, чем на 1000. Это означает, что вы не должны сохранять ссылки на элементы, которых
больше нет в списке.
Вы будете реализовывать все методы, перечисленные выше в разделе “Deque API” (выше), вместе
со следующими конструкторами:
// Создает пустой связанный список deque.
общедоступный LinkedListDeque()
// Создает глубокую копию other.
общедоступный LinkedListDeque(LinkedListDeque<T> другое)

Обратите внимание, что создание глубокой копии означает, что вы создаете совершенно новый LinkedListDeque с точно
такими же элементами. Однако это копии, поэтому они должны быть разными объектами. Хорошей лакмусовой бумажкой является то, что если вы
измените other, “скопированный” LinkedListDeque не должен измениться.

ПРИМЕЧАНИЕ: Вам не разрешается использовать встроенную в Java структуру данных LinkedList (или любую структуру данных
из java.util.*) в вашей реализации.

ПРИМЕЧАНИЕ № 2: Мы предоставляем очень простую проверку на вменяемость LinkedListDequeTest.java . Для
в вашу пользу вы должны написать более подробные тесты. Прохождение данных тестов не обязательно
означает, что вы пройдете наш тест или получите полный зачет.

ПРИМЕЧАНИЕ № 3: Возможно, вы захотите реализовать метод printDeque(), который, в отличие от toString, напечатает
подробное представление вашего внутреннего представления — заставьте его печатать все, что вы хотите видеть при реализации/
отладка кода. Это делается для того, чтобы помочь вам отладить и разобраться в вашей структуре deque. Вы не
обязаны сдавать его, но мы рекомендуем вам написать его, чтобы помочь вам справиться с задачей.

Массив Deque
Для этой части сохраните свою работу в файле с именем ArrayDeque.java .
В качестве другой реализации deque вы создадите класс ArrayDeque. Этот запрос должен использовать
массивы фиксированного размера в качестве основной структуры данных. Вы будете реализовывать все перечисленные выше методы в Deque API.
Кроме этого, ваши операции подпадают под действие следующих правил:
• Операции семейства .add и .remove должны занимать постоянное время, за исключением операций изменения размера (увеличения
и, возможно, уменьшения).
• .get и .size должны занимать постоянное время.
• Начальный размер вашего массива должен быть равен 8. Объем памяти, который ваша программа использует в любой
момент времени, должен быть пропорционален количеству элементов. Например, если вы добавляете 10 000 элементов в
список, а затем удаляете 9 999 элементов, вы все равно не должны использовать массив длиной 10 000 иш.
• Кроме того, для массивов длиной 16 и более использование вашего массива (соотношение между используемыми ячейками массива
по сравнению с общей емкостью массива) всегда должно составлять не менее 25%. Для небольших массивов ваш
коэффициент использования может быть сколь угодно низким.
Вы также будете реализовывать следующие конструкторы:

// Создает пустой массив deque.
публичный ArrayDeque()
// Создает глубокую копию other.
публичный массив данных(ArrayDeque<T> other)
Как и раньше, создание глубокой копии означает создание совершенно нового ArrayDeque с точно такими же
элементами, как и у других. Однако они должны быть разными объектами, т.е. если вы измените other, новый
созданный вами ArrayDeque также не должен изменяться. Вы можете добавить любые частные вспомогательные классы или методы в тот же
файл по своему усмотрению.

СОВЕТЫ №1: Самая большая проблема в этой части заключается в том, как поддерживать операции добавления и удаления в
постоянное время (независимо от размера)? Мы настоятельно рекомендуем вам ознакомиться с циклическим
буфером. Глава 5.3 книги объясняет это для очередей и содержит несколько примеров кода. Вы
также можете найти больше вдохновения в Википедии (https://en.wikipedia.org/wiki/Circular_buffer ).
То есть вы будете рассматривать свой массив как круговой. Это означает, например, что если ваш передний указатель находится в
нулевой позиции, и вы добавляете сначала, передний указатель должен вернуться к концу массива (таким образом, новый
элемент front в списке deque будет последним элементом в базовом массиве). Аналогично, если задний конец
deque находится в последнем слоте массива, и вы добавляете Last, он должен обернуться и сохранить элемент в
позиции 0 (если он еще не заполнен, и в этом случае вы бы изменили размер).

СОВЕТ № 2: Подумайте о том, чтобы вообще не изменять размер, пока вы не убедитесь, что ваш код работает без этого. Изменение
размера - это оптимизация производительности (но это необходимо для получения полного кредита). И когда вы изменяете размер, убедитесь, что вы
тщательно продумали, что произойдет, если структура данных изменится с пустой на некоторый ненулевой размер (например, 4
элементы) снова уменьшаются до нуля, а затем возвращаются к некоторому ненулевому размеру. Совет профессионала: 0£ 2 æ 0, но это может быть не
то, что вы хотите.

СОВЕТЫ №3: В главе 4.3 книги обсуждаются некоторые идеи по изменению размера как для увеличения, так и для уменьшения
базового массива.

СОВЕТЫ №4: Как и в версии со связанным списком, вы, возможно, захотите реализовать метод printDeque(), который
напечатает подробное представление вашего внутреннего представления — заставьте его печатать все, что вы хотите видеть при
реализации / отладке кода. Это делается для того, чтобы помочь вам отладить и разобраться в вашей структуре deque.
Вы не обязаны сдавать его, но мы рекомендуем вам написать его, чтобы помочь вам справиться с задачей.
 */

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {
    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");
        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

		LinkedListDeque<String> lld1 = new LinkedListDeque<String>();
		boolean passed = checkEmpty(true, lld1.isEmpty());
		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
		passed = checkSize(1, lld1.size()) && passed;
		passed = checkEmpty(false, lld1.isEmpty()) && passed;
		lld1.addLast("middle");
		passed = checkSize(2, lld1.size()) && passed;
		lld1.addLast("back");
		passed = checkSize(3, lld1.size()) && passed;
		System.out.println("Printing out deque: ");
		lld1.printDeque();
		printTestStatus(passed);
    }

    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public static void addRemoveTest() {

        System.out.println("Running add/remove test.");

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

		LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty
		boolean passed = checkEmpty(true, lld1.isEmpty());
		lld1.addFirst(10);
		// should not be empty
		passed = checkEmpty(false, lld1.isEmpty()) && passed;
		lld1.removeFirst();
		// should be empty
		passed = checkEmpty(true, lld1.isEmpty()) && passed;
		printTestStatus(passed);
    }

    public static void checkFullCopy() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(10);
        lld1.addFirst(11);
        lld1.addFirst(12);
        lld1.addFirst(13);
        lld1.addFirst(14);

        lld1.printDeque();

        LinkedListDeque<Integer> lld2 = new LinkedListDeque<>(lld1);
        lld2.printDeque();
    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        addIsEmptySizeTest();
        addRemoveTest();
        checkFullCopy();
    }
}
