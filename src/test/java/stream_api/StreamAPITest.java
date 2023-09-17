package stream_api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.aston.mineev_ia.task4.sql.models.User;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamAPITest {

    @BeforeEach
    void before() {
        System.out.println("|---------------------------------START----------------------------------|");
    }

    @AfterEach
    void after() {
        System.out.println("|--------------------------------END TEST--------------------------------|");
    }

    @DisplayName("Вывести все четные числа в диапазоне от 1 до 100 (включительно)")
    @Test
    void testStreamAPI1() {
        IntStream.rangeClosed(1, 100)
                .filter(integer -> integer % 2 == 0)
                .forEach(System.out::println);
    }

    @DisplayName("Пропустить первые 10 элементов списка [0, 1, 2,.., 99] и начать выводить с 11-го элемента, выводя каждый 10-й элемент")
    @Test
    void testStreamAPI2() {
        List<Integer> numbers = IntStream.range(0, 100).boxed().toList();

        IntStream.iterate(10, i -> i + 10)
                .limit((numbers.size() - 10) / 10)
                .forEach(System.out::println);
    }

    @DisplayName("Отфильтруйте объекты по определенному свойству, например, выведите все записи из базы данных, у которых значение поля равно 1.")
    @Test
    void testStreamAPI3() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Иван", "Андреевич", "Минеев", "89126144959", "ivanmineev52@gmail.com"));
        users.add(new User(1, "Иван", "Андреевич", "Минеев", "89126144959", "ivanmineev52@gmail.com"));
        users.add(new User(2, "Артем", "Васильевич", "Шматков", "89552332054", "nisare1337@gmail.com"));
        users.add(new User(3, "Александр", "Викторович", "Вергус", "89045135358", "polarxtreame@gmail.com"));

         users.stream()
                 .filter(user -> user.getId() == 1)
                 .forEach(System.out::println);
    }

    @DisplayName("Собрать элементы Stream в карту, где ключом будет первая буква каждого слова, а значением — само слово. Отсортировать ключи в алфавитном порядке.")
    @Test
    void testStreamAPI4() {
        List<String> strings = List.of("UserTest OrderTest", "CollectionTest Sql", "Test");
        strings.stream()
                .collect(Collectors.toMap(
                        key -> {
                            StringBuilder result = new StringBuilder();
                            String[] words = key.split("\\s+");
                            for (String word : words) {
                                if (!word.isEmpty()) {
                                    result.append(word.charAt(0));
                                }
                            }
                            return result.toString();
                        },
                        value -> value,
                        ((o1, o2) -> o1),
                        TreeMap::new
                ))
                .forEach((k, v) -> System.out.println(k + ": " + v));
    }

    @DisplayName("Создайте два Stream-а: один из массива чисел 1…5, второй из массива 5…10. Объедините эти два Stream-а в один и выведите на экран.")
    @Test
    void testStreamAPI5() {
        // 1-й вариант
        int[] ints = {1,2,3,4,5};
        int[] ints2 = {5,6,7,8,9,10};

        Stream<Integer> stream = Arrays.stream(ints).boxed();
        Stream<Integer> stream2 = Arrays.stream(ints2).boxed();

        Stream<Stream<Integer>> streams = Stream.of(stream, stream2);
        streams.flatMap(intStream -> intStream)
                .forEach(System.out::println);

        System.out.println("-------------------------------------------------------------");

        // 2-й вариант
        IntStream i1 = IntStream.rangeClosed(1, 5);
        IntStream i2 = IntStream.rangeClosed(5, 10);

        IntStream.concat(i1,i2).forEach(System.out::println);
    }

    @DisplayName("Создать стрим чисел от 0 до 100. Умножить их на 2 и вывести на экран результат, ограничиться первыми 10 результатами.")
    @Test
    void testStreamAPI6() {
        IntStream i1 = IntStream.rangeClosed(0, 100);
        i1
                .map(i -> i * 2)
                .limit(10)
                .forEach(System.out::println);
    }

    @DisplayName("Разделить даты в Stream на четыре группы по временам года, посчитать количество событий в каждом времени года.")
    @Test
    void testStreamAPI7() {
        List<LocalDate> dates = Arrays.asList(
                LocalDate.of(2023, Month.JANUARY, 15),
                LocalDate.of(2023, Month.APRIL, 5),
                LocalDate.of(2023, Month.JULY, 20),
                LocalDate.of(2023, Month.OCTOBER, 8),
                LocalDate.of(2023, Month.DECEMBER, 30),
                LocalDate.of(2023, Month.FEBRUARY, 10),
                LocalDate.of(2023, Month.JUNE, 3),
                LocalDate.of(2023, Month.SEPTEMBER, 17)
        );

        dates.stream()
                .collect(Collectors.groupingBy(
                        date -> {
                            int month = date.getMonthValue();
                            String season;

                            if (month >= 3 && month <= 5) {
                                season = "Весна";
                            } else if (month >= 6 && month <= 8) {
                                season = "Лето";
                            } else if (month >= 9 && month <= 11) {
                                season = "Осень";
                            } else {
                                season = "Зима";
                            }

                            return season;
                        },
                        Collectors.counting()
                ))
                .forEach((season, count) -> System.out.println(season + ": " + count + " событий"));
    }

    @DisplayName("Создание Stream символов и поиск всех строк, состоящих из строчных букв и цифр.")
    @Test
    void testStreamAPI8() {
        String[] input = {"Привет", "youngtort52", "Это тест номер 8"};
        IntStream characterStream = Arrays.toString(input).chars();

        String result = characterStream
                .filter(c -> Character.isLowerCase(c) || Character.isDigit(c))
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());

        System.out.println(result);
    }

    @DisplayName("Сгруппировать элементы Stream по их чётности, посчитать размер каждой группы и вывести результаты.")
    @Test
    void testStreamAPI9() {
        List<Integer> integers = IntStream.rangeClosed(0, 100).boxed().toList();
        integers.stream()
                .collect(Collectors.groupingBy(
                        number -> {
                            String name;

                            if (number % 2 == 0){
                                name = "Четные";
                            } else name = "Нечетные";

                            return name;
                        },
                        Collectors.summingInt(value -> value)
                ))
                .forEach((name, count) -> System.out.println(name + ": " + count + " - сумма чисел"));
    }

    @DisplayName("Собрать все уникальные элементы Stream в список и отсортировать их.")
    @Test
    void testStreamAPI10() {
        List<Integer> integers = List.of(1,6,3,1,67,4,2,8,2);

        integers.stream()
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }
}
