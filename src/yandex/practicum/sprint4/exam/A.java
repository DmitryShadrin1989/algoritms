package yandex.practicum.sprint4.exam;

/*
A. Поисковая система

В этой задаче можно пользоваться хеш-таблицами из стандартных библиотек.
Тимофей пишет свою поисковую систему.
Имеется n документов, каждый из которых представляет собой текст из слов. По этим документам требуется построить поисковый индекс. На вход системе будут подаваться запросы. Запрос —– некоторый набор слов. По запросу надо вывести 5 самых релевантных документов.
Релевантность документа оценивается следующим образом: для каждого уникального слова из запроса берётся число его вхождений в документ, полученные числа для всех слов из запроса суммируются. Итоговая сумма и является релевантностью документа. Чем больше сумма, тем больше документ подходит под запрос.
Сортировка документов на выдаче производится по убыванию релевантности. Если релевантности документов совпадают —– то по возрастанию их порядковых номеров в базе (то есть во входных данных).
Подумайте над случаями, когда запросы состоят из слов, встречающихся в малом количестве документов. Что если одно слово много раз встречается в одном документе?

Формат ввода
В первой строке дано натуральное число n —– количество документов в базе (1 ≤ n ≤ 104).
Далее в n строках даны документы по одному в строке. Каждый документ состоит из нескольких слов, слова отделяются друг от друга одним пробелом и состоят из маленьких латинских букв. Длина одного текста не превосходит 1000 символов. Текст не бывает пустым.
В следующей строке дано число запросов —– натуральное число m (1 ≤ m ≤ 104). В следующих m строках даны запросы по одному в строке. Каждый запрос состоит из одного или нескольких слов. Запрос не бывает пустым. Слова отделяются друг от друга одним пробелом и состоят из маленьких латинских букв. Число символов в запросе не превосходит 100.

Формат вывода
Для каждого запроса выведите на одной строке номера пяти самых релевантных документов. Если нашлось менее пяти документов, то выведите столько, сколько нашлось. Документы с релевантностью 0 выдавать не нужно.
*/

/*
-- ПРИНЦИП РАБОТЫ --
Для индекса поиска ипользовал Map, в качестве ключа используется слова,
а значение это тоже Map, где ключ - это номер документа и значение - это количество повторений этого слова в документе.
При обработке поискового запроса, чтобы исключить дублирование слов c помощью Stream API осуществляется выборка уникальных слов и выгрузка List.
Далее по каждому слову из List выполняется поиск в построеном индексе поиска и результат поиска по всем словам запроса накапливается в Map.
В котором ключ - это номер документа и значение это количество слов из запроса в этом документе.
Далее также с помощью Stream API осуществляется сортировка результата запроса и сохрание этого результата в List.
Далее идет вывод результата на экран, для удобства тестирования, результат сначала собирается по всем запросам.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Поисковый индекс строится на структуре HashMap<String, HashMap<Integer, Integer>>, что дает уже максимально агрегированные в разрезе слов данные.
Т.е. при выполнении поиска по запросу (из несеольких слов) необходимо только сложить результаты по каждому слову.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Попробую оценить отдельные логические части программы, тогда временная сложность самой "тяжелой" части и будет временной сложностью всей программы.

1. Построение поискового индекса.
По входящим данным мы проходим только один раз, дополнительный внутренний цикл возникает из за разбивки на документы.
Временная сложность будет линейно зависеть от количества слов во всех документах - O(∑N(i)) ~ O(n), где i - это номер документа, N(i) - это количество слов в i-ом документе,
a n - общее количество слов во всех документах.
Иван Самсонов - Что такое n?
Дмитрий Шадрин - Немного изменил и сократил описание, сделал пояснение.


2. Выполнение поиска по строке запроса.
Тут циклов еще больше. Первый это Stream с выполнением distinct() над строкой запроса.
Дальше идут вложенные циклы, внешний идет по запросам, дальше вложенный по словам запроса и внутренний нужен, чтобы сделать агрегацию результатов по каждому документу.
Cложность этой части программы будет линейной и зависить от суммы результатов поиска по каждому слову каждого запроса O(M * K * O) ~ O(m) , где M - это количество запросов,
K - это среднее количество слов в запросе, O - это среднее количество результатов поиска по слову, m - это количество результатов поиска по словам всех запросов.
Алгоритм поиска использует HashMap, он дает в среднем и лучшем случае O(1), поэтому его не будем учитывать.

3. Вывод результата поиска.
Тут не более 5и раз производится проход по результату поиска (результирующей Map).
На каждой интерации производится вычисление максимального значения и этот результат удаляется из результирующей Map.
Сложность O(5*R) ~ O(R), где R - результат поиска по запросу.

Итог.
Самый "тяжелый" участок программы - это поиск, он работает за O(m).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
В этой программе используется дополнительная память:

1. При построении поискового индекса.
Для хранения поискового индекса создается структура HashMap<String, HashMap<Integer, Integer>>.
Причем вложенная структура будет увеличиваться если слово будет встречаться в разных документах. Делаю вывод, что это линейное увеличение памяти O(N) + O(I) ~ O(N),
где N - это количество слов со всех документов, I - это агрегация повторений слов в документах.

2. При выполнении поискового запроса.
Сам запрос перегоняется в List<String> request. Также есть HashMap<Integer, Integer> для сохранения результатов поиска.
Линейное увеличение памяти O(M) + O(R) ~ O(M), где M - количество слов в запросе, R - результаты поиска.

3. При выводе результата.
Дополнительных коллекций не создается.

Итог.
Складываем дополнитнльную память со всех частей программы, получается O(N) + O(M).

Успешная посылка - 80009579

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class A {
    public static void main(String[] args) throws IOException {        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int countDocument = Integer.parseInt(reader.readLine());
            HashMap<String, HashMap<Integer, Integer>> indexSearchMap = new HashMap<>();
            for (int i = 0; i<countDocument; i++) {
                String[] document = reader.readLine().split(" ");
                Integer numberDoc = i + 1;
                for (String word : document) {
                    if (indexSearchMap.containsKey(word)) {
                        HashMap<Integer, Integer> wordsDocumentsMap = indexSearchMap.get(word);
                        if (wordsDocumentsMap.containsKey(numberDoc)) {
                            wordsDocumentsMap.put(numberDoc, wordsDocumentsMap.get(numberDoc)+1);
                        } else {
                            wordsDocumentsMap.put(numberDoc, 1);
                        }
                    } else {
                        HashMap<Integer, Integer> wordsDocuments = new HashMap<>();
                        wordsDocuments.put(numberDoc, 1);
                        indexSearchMap.put(word, wordsDocuments);
                    }
                }
            }

            StringBuilder resultBuilder = new StringBuilder();
            int countRequest = Integer.parseInt(reader.readLine());
            for (int i = 0; i<countRequest; i++) {
                List<String> request = Arrays.stream(reader.readLine().split(" "))
                        .distinct()
                        .collect(Collectors.toList());
                HashMap<Integer, Integer> resultRequestMap = new HashMap<>();
                for (String word : request) {
                    if (indexSearchMap.containsKey(word)) {
                        for (Map.Entry<Integer, Integer> entry : indexSearchMap.get(word).entrySet()) {
                            Integer key = entry.getKey();
                            Integer value = entry.getValue();
                            if (resultRequestMap.containsKey(key)) {
                                resultRequestMap.put(key, resultRequestMap.get(key)+value);
                            } else {
                                resultRequestMap.put(key, value);
                            }
                        }
                    }
                }

                if (i>0) {
                    resultBuilder.append("\n");
                }
                int size = Math.min(resultRequestMap.size(), 5);
                for (int j=0; j<size; j++) {
                    int numberDoc = 0;
                    int maxResultSearch = 0;
                    for (Map.Entry<Integer, Integer> entry : resultRequestMap.entrySet()) {
                        if (entry.getValue() > maxResultSearch) {
                            maxResultSearch = entry.getValue();
                            numberDoc = entry.getKey();
                        } else if (entry.getValue() == maxResultSearch) {
                            numberDoc = Math.min(numberDoc, entry.getKey());
                        }
                    }
                    if (j>0) {
                        resultBuilder.append(" ");
                    }
                    resultBuilder.append(numberDoc);
                    resultRequestMap.remove(numberDoc);
                }

            }
            System.out.println(resultBuilder);
        }
    }
}
