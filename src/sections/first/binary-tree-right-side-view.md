# Binary Tree Right Side View

## Условие

Дано бинарное дерево. Представьте, что вы стоите **справа** от него. Нужно вернуть значения в узлах, которые **видны**, в порядке **сверху вниз**.

**Строгая формулировка:** на каждой фиксированной глубине упорядочим все вершины **слева направо** (как в обходе в ширину: сначала левое поддерево относительно общего предка, затем правое). Берём **самую правую** вершину на этом уровне. В ответе — массив этих значений в порядке **увеличения глубины** (уровень 0, затем 1, …).

### Пример

```text
    1
  /   \
 2     3
  \     \
   5     4
  /
 6
```

**Ответ:** `[1, 3, 4, 6]`

**Пояснение:**

- глубина 0 → одна вершина `[1]` → **1**;
- глубина 1 → `[2, 3]` → **3**;
- глубина 2 → `[5, 4]` → **4**;
- глубина 3 → `[6]` → **6**.

## Требования к решению

- **Время:** `O(n)`, где `n` — число узлов.
- **Память:** формулировка «без дополнительной памяти, кроме вывода» на практике уточняют так:
  - **DFS (рекурсия):** дополнительно стек вызовов `O(h)`, `h` — высота дерева;
  - **DFS (итеративный со стеком):** тоже `O(h)` в типичной реализации;
  - **BFS (очередь):** до `O(w)`, где `w` — максимальная ширина уровня (в худшем случае `O(n)`).

Строго константная вспомогательная память при произвольном дереве без изменения структуры узлов — нетривиальна; на собеседовании обычно принимают `O(h)` или очередь уровня.

## Источник

LeetCode: [Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view/description/)

## Решение на C++ (DFS: корень → правый → левый)

Идея: обход в глубину, на каждом уровне **первым** посещаем узел, который окажется **видимым справа** — то есть сначала правое поддерево, потом левое. Когда глубина `h` встречается впервые, добавляем значение в ответ.

> Имя `InOrder` в примере исторически неточное: по смыслу это **модифицированный preorder** (`узел → правый → левый`).

```cpp
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
    vector<int> result;

public:
    void InOrder(TreeNode* c, int h) {
        if (c == NULL)
            return;
        if (result.size() < h + 1)
            result.push_back(c->val);
        InOrder(c->right, h + 1);
        InOrder(c->left, h + 1);
    }

    vector<int> rightSideView(TreeNode* root) {
        InOrder(root, 0);
        return result;
    }
};
```

## Решения на Python

Ниже два варианта с одним именем класса `Solution` — для ноутбука оставьте **один** из них (или переименуйте класс).

### BFS по уровням

- **Время:** `O(n)`
- **Память:** `O(n)` на очередь в худшем случае

Нужен `from collections import deque`.

```python
"""
Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
"""

# BFS
# Time O(n)
# Space O(n)
class Solution:
    def rightSideView(self, root: Optional[TreeNode]) -> List[int]:
        if not root:
            return []

        result = []
        queue = deque([root])

        while queue:
            result.append(queue[-1].val)
            for _ in range(len(queue)):
                node = queue.popleft()
                if node.left:
                    queue.append(node.left)
                if node.right:
                    queue.append(node.right)

        return result
```

### DFS без рекурсии (стек)

- **Время:** `O(n)`
- **Память:** `O(h)` под стек

Сначала в стек кладётся левый потомок, затем правый — при `pop` обрабатывается **правый** раньше, на каждом уровне в `result` попадает первый увиденный узел (самый правый на уровне).

```python
# DFS non-recursive
# Time O(n)
# Space O(h)
class Solution:
    def rightSideView(self, root: Optional[TreeNode]) -> List[int]:
        if not root:
            return []

        result = []
        stack = [(root, 0)]

        while stack:
            node, level = stack.pop()

            if len(result) == level:
                result.append(node.val)

            if node.left:
                stack.append((node.left, level + 1))
            if node.right:
                stack.append((node.right, level + 1))

        return result
```
