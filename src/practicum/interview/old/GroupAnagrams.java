package practicum.interview.old;

import java.util.*;

/*
1)
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

2)
Input: strs = [""]
Output: [[""]]

3)
Input: strs = ["a"]
Output: [["a"]]

 */

public class GroupAnagrams {

    public static void main(String[] args) {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        List<List<String>> result = groupAnagrams(strs);
        System.out.print("[");
        for (List<String> strings : result) {
            System.out.print("[");
            for (String str : strings) {
                System.out.print(str + ",");
            }
            System.out.print("]");
        }
        System.out.print("]");
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String s = String.valueOf(chars);
            List<String> stringList = map.getOrDefault(s, new ArrayList<>());
            stringList.add(str);
            map.put(s, stringList);
        }
        List<List<String>> result = new ArrayList<>();
        for (String s : map.keySet()) {
            result.add(map.get(s));
        }
        return result;
    }
}
