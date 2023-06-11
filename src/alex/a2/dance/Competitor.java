package alex.a2.dance;

/*
Задание 2: Танцевальный конкурс (8 баллов)
Для выполнения этой задачи сохраните свою работу в dance/
Вы были наняты DSA для внедрения системы для проведения танцевального конкурса. В этом сезоне все
будет происходить на Zoom, поэтому им нужна онлайн-система.
Для краткого обзора отметим, что на этот раз в соревнованиях будет участвовать несколько участников (игроков). Каждый должен будет
исполнить два стиля танцев: поп-дэнс и хип-хоп. Они будут оцениваться комиссией из
2
Домашнее задание 2 Структура данных.
судьи. Наша система будет отслеживать баллы и суммировать их для определения золотых медалистов (т.е.,
победители) конкурса. Полная информация приведена в описании программы в стартовом коде.
Ассоциация волшебников злого танца (DSA) хочет, чтобы вы внедрили следующие три класса, которые
они тщательно разработали и скомпоновали (вам рекомендуется выполнять их в таком порядке).:
• Класс ScoreKeeper хранит баллы и отвечает на основные статистические данные об этих баллах. Он
предназначен для использования как часть другого класса для хранения баллов.
• Класс участника представляет участника танцевального соревнования. Он сохраняет связанные атрибуты
участнику, включая оценки участника по танцам.
• Класс DanceCompetition представляет собой танцевальное соревнование, в котором участвуют несколько участников.
Как упоминалось ранее, были запланированы два вида танцев: поп-дэнс и хип-хоп.
Итоговый балл для каждого участника рассчитывается с использованием средневзвешенного значения. Этот класс хранит
веса, заданные в конструкторах.
Несколько файлов? Для пользователей командной строки: чтобы скомпилировать взаимозависимые классы Java, перейдите в соответствующую
папку и запустите javac *.java, который скомпилирует все файлы Java в этой папке. Ты все еще можешь
выборочно запустите класс, запустив java className, где className - это имя класса, который вы хотите
запустить в качестве основного.
Специалисты IntelliJ/VSCode peeps должны создать совершенно новый проект для этой задачи, чтобы избежать непреднамеренного
попадания вашего кода в пакет.
Шаг 0: Реализуйте класс ScoreKeeper
Класс ScoreKeeper хранит баллы и отвечает на основные статистические данные об этих баллах. В частности,
он внутренне сохраняет те баллы, которые были установлены. Чтобы установить баллы, вызовите метод setScores. Чтобы
получить баллы, используйте метод getScores.
Чтобы найти откалиброванное среднее значение балла, вызовите метод getCalibratedAverage. Откалиброванное
среднее значение - это среднее значение баллов после исключения минимального и максимального значений.
Вы заглянете в ScoreKeeper.java и заполните код. Мы предоставили основной метод
, который демонстрирует базовое использование класса, и ниже приведен ожидаемый результат:
=== Счетовод ===
Счетовод 0: [2.5, 1.0, 9.8, 5.4, 3.3, 0.25, 4.25]
Откалиброванное среднее значение: 3,29000000000000005
Оценщик 1: [0,5, 9,0]
Откалиброванное среднее значение: NaN
Шаг 1: Внедрите класс конкурентов
Участник в классе сохраняет баллы, выставленные ему судьями. Помимо методов setter и
getter, класс предоставляет методы для распечатки необработанных результатов для каждого типа танца, а также
методы для вычисления средних значений.
Наиболее примечательно, что метод вычисления общего балла участника в танцах, getTotalDanceScore,
возвращает средневзвешенное значение баллов в танцах. Веса указаны в объекте соревнования следующим
образом.
Пусть
p = competition.getPoppingDanceFraction();
h = конкуренция.getHipHopFraction();
3
Домашнее задание 2 Структура данных.
Однако, если объект соревнования равен null, используйте p = 0.6 и h = 0.4. Затем средневзвешенное значение равно
p * (среднее значение, откалиброванное по поп-танцу) + h * (среднее значение, откалиброванное по хип-хоп танцу)
Помните, что откалиброванное среднее значение танца - это среднее значение, вычисленное после исключения минимального и
максимального значений. Удобно, что это то, что вычисляется классом ScoreKeeper.
Вы заглянете в Competitor.java и введите недостающий код. Мы предоставили основной
метод, который демонстрирует, как использовать класс, и ниже приведен ожидаемый результат:
== Питер Си: Очки ===
[Хлопает]
[9.0, 8.0, 8.5, 9.5, 8.0, 7.5]
СРЕДНЯЯ оценка Popping Dance: 8.375
[Хип-хоп]
[10.0, 9.0, 9.5, 8.0, 8.5, 9.0]
СРЕДНИЙ балл по хип-хоп танцам: 9.0
Общий балл: 8.625
========================
=== Обезьяна D: Баллы ===
[Хлопает]
[10.0, 9.0, 9.5, 10.0, 9.0, 9.5]
СРЕДНЯЯ оценка Popping Dance: 9.5
[Хип-хоп]
[10.0, 9.0, 9.0, 9.0, 9.5, 9.5]
СРЕДНИЙ балл по хип-хоп танцам: 9.25
Общий балл: 9.4
========================

Шаг 2: Проведите урок танцевального соревнования
Наконец, вы будете работать над DanceCompetition, который представляет собой танцевальное соревнование с участием нескольких
участников. Этот класс отслеживает веса для различных видов танцев и ведет список
участников. Кроме того, он предоставляет метод getGoldMedal, который возвращает список золотых медалистов
(определяется как каждый участник, общий балл которого составляет не менее 8,0).
Вы будете участвовать в танцевальных соревнованиях.java и завершите его. Мы предоставили основной метод
, который демонстрирует, как использовать класс, и ниже приведен ожидаемый результат:
== ВСЕ результаты соревнований ==
"Блокировка J": 8.6124999999999999
"Пробой B": 4.83749999999999995
"Выскакивающий C": 9.42499999999999999
==============================
'Locking J' получит золотую медаль с результатом = 8,6124999999999999
'Popping C' получит золотую медаль с результатом = 9,42499999999999999
 */

/**
 * Put your name and student id here
 */


/*
 * The Competitor class represents a competitor in a dance competition. It keeps
 * attributes related to the competitor, including the competitor's scores from
 * dances.
 */

public class Competitor {
    // Do not modify or add the member variables
    private String aliasName;
    private ScoreKeeper poppingDanceScores;
    private ScoreKeeper hipHopDanceScores;
    private DanceCompetition competition;
    // -----------------------------------------

    // README: Implement the constructor and methods below. Feel free to add more
    // public methods such as getters and setters as necessary.

    // Competitor's constructor
    public Competitor() {
        //CODE HERE
    }

    // Set the alias name of the competitor
    public void setAlias(String name) {
        //CODE HERE
    }

    // Return the alias name of the competitor
    public String getAlias() {
        //CODE HERE
        return null; // TODO: Change me
    }

    // Record into the member variable poppingDanceScores a list of scores from the
    // judges (given as an array).
    public void setPoppingDanceScore(double[] scores) {
        //CODE HERE
    }

    // Record into the member variable hipHopDanceScores a list of scores from the
    // judges (given as an array).
    public void setHipHopDanceScore(double[] scores) {
        //CODE HERE
    }

    // Set the competition to the given DanceCompetition object
    public void setDanceCompetition(DanceCompetition dc) {
        this.competition = dc;
    }

    // Print all recorded popping dance scores. The format is
    // [10.0, 9.0, 9.0, 9.0, 9.5, 9.5].
    public void printPoppingDanceScore() {
        //CODE HERE
    }

    // Print all recorded hip-hop dance scores. The format is
    // [10.0, 9.0, 9.0, 9.0, 9.5, 9.5].
    public void printHipHopDanceScore() {
        //CODE HERE
    }

    // Return the average popping-dance score after excluding the minimum and
    // maximum. (Hint: Conveniently, the ScoreKeeper class has a matching method.)
    public double getPoppingDanceAverage() {
        //CODE HERE
        return 0.0; // TODO: CHANGE ME
    }

    // Return the average hip-hop score after excluding the minimum and
    // maximum. (Hint: Conveniently, the ScoreKeeper class has a matching method.)
    public double getHipHopDanceAverage() {
        //CODE HERE
        return 0.0; // TODO: CHANGE ME
    }

    // Return the weighted average of the dance scores. The weights are specified
    // in competition object as follows:
    //      p = competition.getPoppingDanceFraction();
    //      h = competition.getHipHopFraction();
    // If, however, the competition object is null, use p = 0.6 and h = 0.4.
    // Then, the weighted average is simply:
    //     p * (popping dance calibrated average) + h * (hip-hop dance calibrated average)
    // Remember that the calibrated average of a dance is the average computed after
    // excluding the min and the max.
    public double getTotalDanceScore() {
        //CODE HERE
        return 0.0; // TODO: CHANGE ME
    }


    public static void main(String[] args) {

        Competitor c1 = new Competitor();
        c1.setAlias("Peter C");
        c1.setPoppingDanceScore(new double[] {9.0,8.0,8.5,9.5,8.0,7.5});
        c1.setHipHopDanceScore(new double[] {10.0,9.0,9.5,8.0,8.5,9.0});

        System.out.println("=== "+c1.getAlias()+": Scores ===");
        System.out.println("[Popping]");
        c1.printPoppingDanceScore();
        System.out.println("AVG Popping Dance Score: "+c1.getPoppingDanceAverage());
        System.out.println("\n[Hip Hop]");
        c1.printHipHopDanceScore();
        System.out.println("AVG Hip Hop Dance Score: "+c1.getHipHopDanceAverage());
        System.out.println("\nTotal Score: " + c1.getTotalDanceScore());
        System.out.println("========================");


        Competitor c2 = new Competitor();
        c2.setAlias("Monkey D");
        c2.setPoppingDanceScore(new double[] {10.0,9.0,9.5,10.0,9.0,9.5});
        c2.setHipHopDanceScore(new double[] {10.0,9.0,9.0,9.0,9.5,9.5});

        System.out.println("\n=== "+c2.getAlias()+": Scores ===");
        System.out.println("[Popping]");
        c2.printPoppingDanceScore();
        System.out.println("AVG Popping Dance Score: "+c2.getPoppingDanceAverage());
        System.out.println("[Hip Hop]");
        c2.printHipHopDanceScore();
        System.out.println("AVG Hip Hop Dance Score: "+c2.getHipHopDanceAverage());
        System.out.println("\nTotal Score: " + c2.getTotalDanceScore());
        System.out.println("========================");
    }
}
