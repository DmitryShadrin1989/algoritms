package sections.first.groupingAnnagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
public class GroupAnagramsJava {

    public static void main(String[] args) {
        List<String> items = Arrays.asList("eat", "tea", "tan", "ate", "nat", "bat");
        List<List<String>> result = groupAnagrams(items);
        for (List<String> group : result) {
            System.out.println(group);
        }
    }

    private static List<List<String>> groupAnagrams(List<String> items) {
        Map<String, List<String>> map = new HashMap<>();
        for (String item : items) {
            char[] chars = item.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            map.computeIfAbsent(sorted, k -> new ArrayList<>()).add(item);
        }
        List<List<String>> result = new ArrayList<>(map.values());
        for (List<String> group : result) {
            Collections.sort(group);
        }
        return result;
    }

    // Плохое решение за O(n^2 * m log m), где n — количество строк, а m — средняя длина строки (из-за вложенных циклов и сортировки строк)
    private static List<List<String>> groupAnagrams2(List<String> items) {
        List<List<String>> result = new ArrayList<>();
        int left = 0;
        while (left < items.size()) {
            String item = items.get(left);
            if (item != null) {
                List<String> group = new ArrayList<>();
                group.add(item);
                items.set(left, null);

                for (int right = left + 1; right < items.size(); right++) {
                    String rightItem = items.get(right);
                    if (rightItem != null && isAnagram(item, rightItem)) {
                        group.add(items.get(right));
                        items.set(right, null);
                    }
                }
                Collections.sort(group);
                result.add(group);
            }
            left++;
        }
        return result;
    }

    private static boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();

        Arrays.sort(chars1);
        Arrays.sort(chars2);

        return Arrays.equals(chars1, chars2);
    }
}
