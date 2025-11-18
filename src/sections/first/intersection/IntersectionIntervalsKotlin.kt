package sections.first.intersection

/*
Даны два отсортированных списка с интервалами присутствия
пользователей в онлайне. Начало интервала строго меньше конца.
Нужно вычислить интервалы, когда оба пользователя были в онлайне.

1)
user1 [(8, 12), (17, 22)]
user2 [(5, 11), (14, 18), (20, 23), (42, 55)]
   => [(8, 11), (17, 18), (20, 22)]

user1 [(9, 15), (18, 21)]
user2 [(10, 14), (21, 22)]
   => [(10, 14)]

2)
user1 [(0, 5), (8, 12), (15, 20)]
user2 [(4, 11), (13, 17), (18, 20), (21, 23)]
   => [(4, 5), (8, 11), (15, 17), (18, 20)]
*/

fun main() {
    val user1 = listOf(8 to 12, 17 to 22)
    val user2 = listOf(5 to 11, 14 to 18, 20 to 23, 42 to 55)

    println(intersection(user1, user2))
}

fun intersection(
    user1: List<Pair<Int, Int>>,
    user2: List<Pair<Int, Int>>): List<Pair<Int, Int>> {

    val result = mutableListOf<Pair<Int, Int>>()

    var i = 0
    var j = 0
    while (i < user1.size && j < user2.size) {
        val from = maxOf(user1[i].first, user2[j].first)
        val to  = minOf(user1[i].second, user2[j].second)
        if (from < to) {
            result.add(from to to)
        }
        if (user1[i].second < user2[j].second) {
            i++
        } else {
            j++
        }
    }
    return result
}