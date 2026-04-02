# Развернуть отдельные слова в строке

Немного усложнённый вариант задачи на разворот строки.

## Условие

Дана строка (возможно, пустая), состоящая из букв **A–Z** и **пробелов**, разделяющих слова. Нужно написать функцию, которая **развернёт каждое слово** (символы внутри слова в обратном порядке), **не меняя** расположение и количество пробелов.

Если на вход пришла **невалидная** строка (не только `A–Z` и пробелы) — **сгенерировать ошибку**.

### Примеры

| Вход | Выход |
|------|--------|
| `"QUICK FOX JUMPS"` | `"KCIUQ XOF SPMUJ"` |
| `"  QUICK FOX   JUMPS "` | `"  KCIUQ XOF   SPMUJ "` |
| `"  "` | `"  "` |
| `""` | `""` |

## Источник (база без валидации алфавита)

LeetCode: [Reverse Words in a String III](https://leetcode.com/problems/reverse-words-in-a-string-iii/)

В постановке выше добавлена **проверка алфавита** и явные требования к пробелам.

## Шаблоны

### Java

```java
/**
 * Дана строка (возможно, пустая), состоящая из букв A-Z и пробелов, разделяющих слова:
 * "QUICK FOX   JUMPS"
 * Нужно написать функцию, которая развернет слова:
 * "KCIUQ XOF   SPMUJ"
 * И сгенерирует ошибку, если на вход пришла невалидная строка.
 */
public String revertWords(String str) {
    // your code
}
```

### C++

```cpp
/**
 * Дана строка (возможно, пустая), состоящая из букв A-Z и пробелов, разделяющих слова:
 * "QUICK FOX   JUMPS"
 * Нужно написать функцию, которая развернет слова:
 * "KCIUQ XOF   SPMUJ"
 * И сгенерирует ошибку, если на вход пришла невалидная строка.
 */
void reverseWords(std::string& s) {
    // place your code here
}
```

### Python

```python
"""
Дана строка (возможно, пустая), состоящая из букв A-Z и пробелов, разделяющих слова:
"QUICK FOX   JUMPS"
Нужно написать функцию, которая развернет слова:
"KCIUQ XOF   SPMUJ"
И сгенерирует ошибку, если на вход пришла невалидная строка.
"""

def reverse_words(s: str) -> str:
    ...  # place your code here
```

## Для секций / ожидания от кандидата

- **Разделение слов:** аккуратно учесть **разное число пробелов** и пробелы в **начале/конце** строки; слова не сливаются, пробелы копируются как есть.
- **C++:** корректный **in-place** разворот каждого слова (`std::reverse` на диапазоне символов слова), без лишнего буфера размера строки (кроме возможных временных в `reverse`).
- **Java:** если кандидат идёт в **`split` + стримы** — стоит убедиться, что он читал **javadoc к `String.split`**: одноаргументный `split(" ")` **сжимает** пустые токены и **теряет** ведущие/хвостовые группы пробелов в типичном использовании — для задачи это частая ловушка.
- **Для «знатоков»:** `String.split(" ", -1)` (лимит **-1**) сохраняет **хвостовые** пустые сегменты; ведущие пустые при этом всё равно могут вести себя особо — см. документацию. **Подсказывать** этот приём кандидату не рекомендуется; если он **сам** его знает — можно принять.

> **Регулярное выражение для валидации:** используйте `"[A-Z ]+"`, а не `"[A-Z| ]+"`: в Java внутри `[]` символ `|` — **обычный символ**, не оператор «или»; иначе в «разрешённые» случайно попадёт символ `|`.

## Тест-кейсы

- пустая строка `""`;
- одна «группа» / одно слово;
- слово из **одного** символа;
- несколько корректных примеров;
- **невалидная** строка (строчные буквы, цифры, знаки и т.д.) → ошибка;
- несколько пробелов **между** словами, в **начале** и **конце**;
- строка **только из пробелов**.

## Решения

### Java (Stream + `split(" ", -1)`)

```java
public String revertWords(String str) {
    if (str == null || str.isEmpty()) {
        return str;
    }
    if (!str.matches("[A-Z ]+")) {
        throw new IllegalArgumentException("Invalid characters!");
    }
    return java.util.stream.Stream.of(str.split(" ", -1))
            .map(s -> new StringBuilder(s).reverse())
            .collect(java.util.stream.Collectors.joining(" "));
}
```

### Java (`split(" ")` + добор хвостовых пробелов)

```java
public String revertWords(String str) {
    if (str == null || str.isEmpty()) {
        return str;
    }
    if (!str.matches("[A-Z ]+")) {
        throw new IllegalArgumentException("Invalid characters!");
    }
    String[] words = str.split(" ");
    StringBuilder res = new StringBuilder();
    for (int i = 0; i < words.length; i++) {
        res.append(new StringBuilder(words[i]).reverse());
        if (i < words.length - 1) {
            res.append(" ");
        }
    }
    for (int i = str.length() - 1; i >= 0; i--) {
        if (str.charAt(i) == ' ') {
            res.append(' ');
        } else {
            break;
        }
    }
    return res.toString();
}
```

### Java (один проход, без `split`)

```java
public String revertWords(String str) {
    if (str == null || str.length() == 0) {
        return str;
    }
    StringBuilder res = new StringBuilder();
    StringBuilder word = new StringBuilder();
    for (int i = 0; i < str.length(); i++) {
        char c = str.charAt(i);
        if (c != ' ' && (c < 'A' || c > 'Z')) {
            throw new IllegalArgumentException("Invalid characters!");
        }
        if (c == ' ') {
            for (int j = word.length() - 1; j >= 0; j--) {
                res.append(word.charAt(j));
            }
            word = new StringBuilder();
            res.append(' ');
        } else {
            word.append(c);
        }
    }
    for (int j = word.length() - 1; j >= 0; j--) {
        res.append(word.charAt(j));
    }
    return res.toString();
}
```

### C++ (in-place)

```cpp
#include <algorithm>
#include <stdexcept>
#include <string>

void reverseWords(std::string& s) {
    for (auto left = std::begin(s);;) {
        while (left != std::end(s) && *left == ' ') {
            ++left;
        }
        if (left == std::end(s)) {
            break;
        }
        // now left points to the first letter of the word
        auto right = left;
        while (right != std::end(s) && *right != ' ') {
            if (*right < 'A' || *right > 'Z') {
                throw std::invalid_argument(
                    "Expected string consisting of A-Z and spaces. Found invalid character: "
                    + std::string(1, *right));
            }
            ++right;
        }
        // now right points to the first space after the word's last character (if any), or end(s)
        std::reverse(left, right);
        left = right;
    }
}
```
