import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.*;

public class TestCollections {

    public static void main(String[] args) {
        List<Person> people = List.of(
                new Person("Вася", 1, 1, "IAIT"),
                new Person("Петя", 2, 2, "IAIT"),
                new Person("Серега", 3, 3, "AUTP"),
                new Person("Паша", 4, 4, "IAIT"),
                new Person("Андрей", 5, 5, "IAIT"),
                new Person("Артем", 6, 6, "AUTP"),
                new Person("Маша", 7, 7, "AUTP")
        );
        task1(people);
        task1Modified1(people);
        task1Modified2(people);

        task2(List.of('a', 'a', 't', 'e', 'f', 'i', 'j'), List.of('t', 'g', 'g', 'i', 'k', 'f'));

        task2Modified(List.of('a', 'a', 't', 'e', 'f', 'i', 'j'), List.of('t', 'g', 'g', 'i', 'k', 'f'));

        ArrayList<String> santasList = new ArrayList<>();
        ArrayList<String> children = new ArrayList<>();
        for (String s : new String[]{"Jason", "Jackson", "Jordan", "Johnny"})
            santasList.add(s);
        for (String s : new String[]{"Jason", "Jordan", "Jennifer"})
            children.add(s);
        List<String> goodChilds = task3(santasList, children);

        int allTime = task4(new int[]{9,3,11,7,2}, 4);
        System.out.println();

    }


    //Сгруппировать студентов по факультету
    public static void task1(List<Person> people) {
        Map<String, List<Person>> personMap = new HashMap<>();
        for (Person person : people) {
            String faculty = person.getFaculty();
            List<Person> persons;
            if (!personMap.containsKey(faculty)) {
                persons = new ArrayList<>();
            } else {
                persons = personMap.get(faculty);
            }
            persons.add(person);
            personMap.put(faculty, persons);
        }
        System.out.println();
    }


    //Найдите разницу между двумя коллекциями. Разница заключается в том, что либо персонаж присутствует в одной коллекции,
    // либо присутствует в другой, но не в обеих одновременно. Вернуть отсортированный список с разницей.
    //
    //Коллекции могут содержать любой символ и могут содержать дубликаты.
    //
    //Пример
    //A = [a, a, t, e, f, i, j]
    //
    //B = [t, g, g, i, k, f]
    //
    //difference = [a, e, g, j, k]
    public static void task2(Collection<Character> a, Collection<Character> b) {
        a = new HashSet<>(a);
        b = new HashSet<>(b);
        Set<Character> result = new TreeSet<>();

        for (Character character : a) {
            if (!b.contains(character)) {
                result.add(character);
            }
        }
        for (Character character : b) {
            if (!a.contains(character)) {
                result.add(character);
            }
        }
        System.out.println();
    }

    //Приближается Рождество, и у Санты есть длинный список, чтобы найти тех,
    // кто заслуживает подарков к этому важному дню. Просмотрите список детей и верните список, содержащий всех детей,
    // которые появились в списке Санты. Не добавляйте дочерние элементы более одного раза. Вывод должен быть отсортирован.
    //Сравнение должно быть чувствительным к регистру, а возвращаемый список должен содержать только одну копию каждого имени:
    // "Sam"и "sam"различаются, но "sAm"и "sAm"не являются.
    public static List<String> task3(List<String> santasList, List<String> children) {
        List<String> c = new ArrayList<>();
        for (String child : children) {
            if (santasList.contains(child) && !c.contains(child)) {
                c.add(child);
            }
        }
        Collections.sort(c);
        return c;
    }

    public static int task4(int[] customers, int n) {
        int result = 0;
        Integer[] casses = new Integer[n];
        Arrays.fill(casses, 0);
        Queue<Integer> sorted = new PriorityQueue<>();
        for (int customer : customers) {
            sorted.add(customer);
        }
        while (!sorted.isEmpty()) {
            Arrays.sort(casses);
            casses[0] += sorted.remove();
        }
        Arrays.sort(casses, Comparator.reverseOrder());
        return casses[0];
    }

    public static void task2Modified(Collection<Character> a, Collection<Character> b) {
        Set<Character> s1 = new TreeSet<>(a), s2 = new HashSet<>(b);
        s1.removeAll(s2);
        s2.removeAll(new HashSet<>(a));
        s1.addAll(s2);
        System.out.println(s1);
    }

    //Сгруппировать студентов по факультету
    public static void task1Modified1(List<Person> people) {
        Map<String, List<Person>> personMap = new HashMap<>();
        for (Person person : people) {
            String faculty = person.getFaculty();
            List<Person> persons = personMap.getOrDefault(faculty, new ArrayList<>());
            persons.add(person);
            personMap.put(faculty, persons);
        }
        System.out.println();
    }

    //Сгруппировать студентов по факультету отсортировать по курсу
    public static void task1Modified2(List<Person> people) {
        Map<String, List<Person>> personMap = new HashMap<>();
        for (Person person : people) {
            String faculty = person.getFaculty();
            List<Person> persons = personMap.getOrDefault(faculty, new ArrayList<>());
            persons.add(person);
            persons.sort(Comparator.comparingInt(Person::getCourseNum));
            personMap.put(faculty, persons);
        }
        System.out.println();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class Person {
        private String firstName;
        private int number;
        private int courseNum;
        private String faculty;


    }

}
