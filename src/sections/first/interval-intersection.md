# Пересечение интервалов

## English version

Given two sorted lists of time intervals representing when each user was online. For every interval, the start is strictly less than the end. Compute the intervals when **both** users were online at the same time.

## Условие

Даны два отсортированных списка с интервалами присутствия пользователей в онлайне. Начало интервала строго меньше конца. Нужно вычислить интервалы, когда оба пользователя были в онлайне.

### Примеры

```text
user1 [(8, 12), (17, 22)]
user2 [(5, 11), (14, 18), (20, 23), (42, 55)]
=> [(8, 11), (17, 18), (20, 22)]

user1 [(9, 15), (18, 21)]
user2 [(10, 14), (21, 22)]
=> [(10, 14)]
```

## Ожидания

- Алгоритм за **линейное время** (от суммы длин списков).
- Корректность для пересекающихся, непересекающихся и вложенных интервалов.

## Шаблоны для ноутбука

### C++

```cpp
/*
Даны два отсортированных списка с интервалами присутствия
пользователей в онлайне в течение дня. Начало интервала строго меньше конца.
Нужно вычислить интервалы, когда оба пользователя были в онлайне.
*/

intersection(
    {{8, 12}, {17, 22}},
    {{5, 11}, {14, 18}, {20, 23}}
); // {{8, 11}, {17, 18}, {20, 22}}

intersection(
    {{9, 15}, {18, 21}},
    {{10, 14}, {21, 22}}
); // {{10, 14}}

using Intervals = std::vector<std::pair<int, int>>;

Intervals inter(const Intervals& i1, const Intervals& i2) {
    // your code here
}
```

### JavaScript

```javascript
/*
Даны два отсортированных списка с интервалами присутствия
пользователей в онлайне в течение дня. Начало интервала строго меньше конца.
Нужно вычислить интервалы, когда оба пользователя были в онлайне.
*/

intersection(
    [[8, 12], [17, 22]],
    [[5, 11], [14, 18], [20, 23]]
) // [[8, 11], [17, 18], [20, 22]]

intersection(
    [[9, 15], [18, 21]],
    [[10, 14], [21, 22]]
) // [[10, 14]]

function intersection(user1, user2) {
    // your code here
}
```

### Python

```python
"""
Даны два отсортированных списка с интервалами присутствия
пользователей в онлайне в течение дня. Начало интервала строго меньше конца.
Нужно вычислить интервалы, когда оба пользователя были в онлайне.
"""

intersection(
    [(8, 12), (17, 22)],
    [(5, 11), (14, 18), (20, 23)]
)  # [(8, 11), (17, 18), (20, 22)]

intersection(
    [(9, 15), (18, 21)],
    [(10, 14), (21, 22)]
)  # [(10, 14)]

def intersection(user1, user2):
    # your code here
```

### PHP

```php
/*
Даны два отсортированных списка с интервалами присутствия
пользователей в онлайне в течение дня. Начало интервала строго меньше конца.
Нужно вычислить интервалы, когда оба пользователя были в онлайне.
*/

intersection(
    [[8, 12], [17, 22]],
    [[5, 11], [14, 18], [20, 23]]
) // [[8, 11], [17, 18], [20, 22]]

intersection(
    [[9, 15], [18, 21]],
    [[10, 14], [21, 22]]
) // [[10, 14]]

function intersection($user1, $user2) {
    // your code here
}
```

### Java

```java
/*
Даны два отсортированных списка с интервалами присутствия
пользователей в онлайне. Начало интервала строго меньше конца.
Нужно вычислить интервалы, когда оба пользователя были в онлайне.
*/

intersection(
    [(8, 12), (17, 22)],
    [(5, 11), (14, 18), (20, 23), (42, 55)]
); // [(8, 11), (17, 18), (20, 22)]

intersection(
    [(9, 15), (18, 21)],
    [(10, 14), (21, 22)]
); // [(10, 14)]

record Interval(int from, int to) {
    @Override
    public String toString() {
        return "(%d, %d)".formatted(from, to);
    }
}

public List<Interval> findIntersection(List<Interval> user1, List<Interval> user2) {
    // your code here
}
```

### Kotlin

```kotlin
/*
Даны два отсортированных списка с интервалами присутствия
пользователей в онлайне. Начало интервала строго меньше конца.
Нужно вычислить интервалы, когда оба пользователя были в онлайне.
*/

fun intersection(
    user1: List<Pair<Int, Int>>,
    user2: List<Pair<Int, Int>>,
): List<Pair<Int, Int>> {
    // your code here
}
```

### Swift

```swift
/*
Даны два отсортированных списка с интервалами присутствия
пользователей в онлайне. Начало интервала строго меньше конца.
Нужно вычислить интервалы, когда оба пользователя были в онлайне.
*/

typealias Intervals = [(Int, Int)]

func intersect(_ first: Intervals, _ second: Intervals) -> Intervals {
    // ...
}

intersect(
    [(8, 12), (17, 22)],
    [(5, 11), (14, 18), (20, 23)]
) // => [(8, 11), (17, 18), (20, 22)]

intersect(
    [(9, 15), (18, 21)],
    [(10, 14), (21, 22)]
) // => [(10, 14)]
```

## Примеры решения

### C++

```cpp
using Intervals = std::vector<std::pair<int, int>>;

Intervals intersection(const Intervals& user1, const Intervals& user2) {
    Intervals res;
    auto it1 = user1.begin();
    auto it2 = user2.begin();

    while (it1 != user1.end() && it2 != user2.end()) {
        // 1. find intersection
        // 2. increment lowest it
        int left = std::max(it1->first, it2->first);
        int right = std::min(it1->second, it2->second);

        if (left < right) {
            res.emplace_back(left, right);
        }
        if (it1->second < it2->second) {
            ++it1;
        } else {
            ++it2;
        }
    }

    return res;
}
```

### JavaScript

```javascript
function intersection(user1, user2) {
    const list = [];
    let i1 = 0;
    let i2 = 0;

    while (i1 < user1.length && i2 < user2.length) {
        const leftOffset = Math.max(user1[i1][0], user2[i2][0]);
        const rightOffset = Math.min(user1[i1][1], user2[i2][1]);

        if (rightOffset > leftOffset) {
            list.push([leftOffset, rightOffset]);
        }

        user1[i1][1] < user2[i2][1] ? ++i1 : ++i2;
    }

    return list;
}
```

### Python

```python
def intersection(user1, user2):
    i1 = 0
    i2 = 0
    res = []

    while i1 < len(user1) and i2 < len(user2):
        left = max(user1[i1][0], user2[i2][0])
        right = min(user1[i1][1], user2[i2][1])

        if right > left:
            res.append((left, right))

        if user1[i1][1] < user2[i2][1]:
            i1 += 1
        else:
            i2 += 1

    return res
```

### PHP

```php
<?php

function intersection($user1, $user2) {
    $i1 = 0;
    $i2 = 0;
    $result = [];

    while (isset($user1[$i1], $user2[$i2])) {
        $point1 = max($user1[$i1][0], $user2[$i2][0]);
        $point2 = min($user1[$i1][1], $user2[$i2][1]);

        if ($point1 < $point2) {
            $result[] = [$point1, $point2];
        }

        if ($user1[$i1][1] > $user2[$i2][1]) {
            $i2++;
        } else {
            $i1++;
        }
    }

    return $result;
}
```

### Swift

```swift
typealias Intervals = [(Int, Int)]

func intersect(_ first: Intervals, _ second: Intervals) -> Intervals {
    var result: Intervals = []

    var i = 0
    var j = 0
    while i < first.count && j < second.count {
        let x = max(first[i].0, second[j].0)
        let y = min(first[i].1, second[j].1)
        if x < y {
            result.append((x, y))
        }
        if first[i].1 < second[j].1 {
            i += 1
        } else {
            j += 1
        }
    }

    return result
}
```
