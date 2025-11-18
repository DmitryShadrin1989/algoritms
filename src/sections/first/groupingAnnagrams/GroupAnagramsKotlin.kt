package sections.first.groupingAnnagrams

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
fun main() {
    val items = listOf("eat", "tea", "tan", "ate", "nat", "bat")
    val result: List<List<String>> = groupAnagrams(items)

    for (group in result) {
        println(group)
    }
}

private fun groupAnagrams(items: List<String>): List<List<String>> {
    val map = mutableMapOf<String, MutableList<String>>()

    for (item in items) {
        val sorted = item.toCharArray().sorted().joinToString("")
        map.getOrPut(sorted) { mutableListOf() }.add(item)
    }

    return map.values.map { it.sorted() }.toList()
}