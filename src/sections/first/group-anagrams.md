# Группировка анаграмм

Дан массив строк, необходимо сгруппировать анаграммы.

**Анаграммы:** слово `X` является анаграммой слова `Y`, если одно может быть получено из другого перестановкой букв.

**Требования к результату:**

- в **каждой** группе слова отсортированы в **лексикографическом** порядке;
- все слова во входе — только **строчные латинские** буквы.

## Пример

**Sample Input**

```text
["eat", "tea", "tan", "ate", "nat", "bat"]
```

**Sample Output**

```text
[
  ["ate", "eat", "tea"],
  ["nat", "tan"],
  ["bat"]
]
```

Порядок **самих групп** во внешнем списке может быть любым, если в условии не оговорено иное (например, на LeetCode порядок групп не проверяется строго).

## Ожидаемая сложность

Итоговая сложность **не хуже** `O(N · log N)`, где `N` — число слов (для простоты можно считать, что длина каждого слова не превосходит 100 символов).

Типичные ключи группы: отсортированная строка, счётчик частот букв (26 чисел), полиномиальный хеш и т.п.

## Шаблоны

### JavaScript

Имя функции в шаблоне: `groupAnagrams` (аналогично Java/Kotlin).

```javascript
/**
 * Дан массив строк, необходимо сгруппировать анаграммы.
 * Слово X является анаграммой слова Y, если одно может быть получено из другого перестановкой букв.
 * В итоговом массиве каждый массив анаграмм должен быть отсортирован в лексикографическом порядке.
 * Все слова в исходном массиве состоят только из строчных латинских букв
 */

groupAnagrams(["eat", "tea", "tan", "ate", "nat", "bat"])
// =>
// [
//   ["ate", "eat", "tea"],
//   ["nat", "tan"],
//   ["bat"],
// ]

function groupAnagrams(items) {
    // your code
}
```

### Kotlin

```kotlin
/*
Дан массив строк, необходимо сгруппировать анаграммы.

Слово X является анаграммой слова Y, если одно может быть получено из другого перестановкой букв.
В итоговом массиве каждый массив анаграмм должен быть отсортирован в лексикографическом порядке.

Все слова в исходном массиве состоят только из строчных латинских букв

Sample Input
["eat", "tea", "tan", "ate", "nat", "bat"]

Sample Output
[
  ["ate", "eat", "tea"],
  ["nat", "tan"],
  ["bat"]
]

Итоговая сложность не хуже N*logN
 */
fun groupAnagrams(items: Array<String>): List<List<String>> {
    // TODO
}
```

### Java

```java
/*
Дан массив строк, необходимо сгруппировать анаграммы.

Слово X является анаграммой слова Y, если одно может быть получено из другого перестановкой букв.
В итоговом массиве каждый массив анаграмм должен быть отсортирован в лексикографическом порядке.

Все слова в исходном массиве состоят только из строчных латинских букв

Sample Input
["eat", "tea", "tan", "ate", "nat", "bat"]

Sample Output
[
  ["ate", "eat", "tea"],
  ["nat", "tan"],
  ["bat"]
]

 */
List<List<String>> groupAnagrams(List<String> items) {
    // TODO
}
```

## Для кандидата

Аккуратно и быстро реализовать метод с итоговой сложностью **не хуже** `O(N · log N)`, где `N` — количество слов в исходном массиве (для простоты можно считать, что длина каждого слова не превосходит 100 символов).

```java
public List<List<String>> groupAnagrams(String[] items) {
    // TODO
}
```

## Пример решения на C++

Идея: для каждого слова взять ключ — строку из отсортированных букв; отсортировать пары `(ключ, исходное слово)`; соседние с одинаковым ключом образуют группу (внутри группы слова уже в лексикографическом порядке по второму компоненту пары).

```cpp
// Example program
#include <algorithm>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

vector<vector<string>> process(const vector<string>& s) {
    vector<pair<string, string>> ss;
    for (auto& t : s) {
        string tt = t;
        sort(tt.begin(), tt.end());
        ss.push_back(make_pair(tt, t));
    }
    sort(ss.begin(), ss.end());

    string c = "";
    vector<vector<string>> r;
    for (auto& t : ss) {
        if (c != t.first) {
            r.push_back(vector<string>());
            c = t.first;
        }
        r.back().push_back(t.second);
    }

    return r;
}

int main() {
    vector<string> s = {"eat", "tea", "tan", "ate", "nat", "bat"};
    vector<vector<string>> r = process(s);
    for (auto& t : r) {
        for (auto& tt : t) {
            cout << tt << " ";
        }
        cout << endl;
    }

    return 0;
}
```

## Решение на Go

Ключ группы — массив частот букв `a`–`z`. После сборки по `map` каждая группа сортируется через `sort.Strings` (нужен импорт пакета `"sort"`).

```go
func process(words []string) [][]string {
	const (
		lower, upper = 'a', 'z'
	)
	type stats [upper - lower + 1]uint

	anagrams := make(map[stats][]string)
	for _, word := range words {
		var wordStat stats
		for _, letter := range word {
			if lower <= letter && letter <= upper {
				wordStat[letter-lower] += 1
			}
		}
		anagrams[wordStat] = append(anagrams[wordStat], word)
	}

	result := make([][]string, 0, len(anagrams))
	for _, group := range anagrams {
		sort.Strings(group)
		result = append(result, group)
	}

	return result
}
```

Исправление опечатки в цикле: должно быть `for _, group := range anagrams` (не `anagramrams`). I'll fix the Go code in the file - I made a typo "anagramrams" in my write. Let me fix.
</think>
Исправляю опечатку в Go-коде: `anagramrams` → `anagrams`.

<｜tool▁calls▁begin｜><｜tool▁call▁begin｜>
StrReplace