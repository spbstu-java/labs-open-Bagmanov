import java.util.*;
import java.util.stream.Collectors;

public final class StreamAPIUtils {

    // 1. Метод для среднего значения списка целых чисел
    public static double getAverage(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElseThrow(() -> new IllegalArgumentException("List cannot be empty"));
    }

    // 2. Метод для преобразования строк с префиксом и верхним регистром
    public static List<String> addPrefixAndUpper(List<String> strings) {
        return strings.stream()
                .map(s -> "_new_" + s.toUpperCase())
                .collect(Collectors.toList());
    }

    // 3. Метод для получения квадратов уникальных элементов
    public static List<Integer> getUniqueSquares(List<Integer> numbers) {
        return numbers.stream()
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(entry -> entry.getKey() * entry.getKey())
                .collect(Collectors.toList());
    }

    // 4. Метод для получения последнего элемента коллекции
    public static <T> T getLastElement(Collection<T> collection) {
        return collection.stream()
                .reduce((first, second) -> second)
                .orElseThrow(() -> new NoSuchElementException("Collection is empty"));
    }

    // 5. Метод для суммы чётных чисел массива
    public static int getEvenSum(int[] numbers) {
        return Arrays.stream(numbers)
                .filter(n -> n % 2 == 0)
                .sum();
    }

    // 6. Метод для преобразования строк в Map
    public static Map<Character, String> stringsToMap(List<String> strings) {
        return strings.stream()
                .collect(Collectors.toMap(
                        s -> s.charAt(0),
                        s -> s.substring(1),
                        (existing, replacement) -> existing
                ));
    }

    private static void testException(String methodName, Runnable action) {
        try {
            action.run();
            System.out.println("TEST FAILED: " + methodName + " did not throw exception");
        } catch (Exception e) {
            System.out.println("TEST PASSED: " + methodName + " threw " + e.getClass().getSimpleName());
        }
    }

    public static void main(String[] args) {
        // Тестовые данные
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 2, 3);
        List<String> strings = Arrays.asList("apple", "banana", "cat", "dog");
        int[] array = {1, 2, 3, 4, 5, 6};

        System.out.println("Average: " + getAverage(numbers));
        System.out.println("Prefix Upper: " + addPrefixAndUpper(strings));
        System.out.println("Unique Squares: " + getUniqueSquares(numbers));
        System.out.println("Last Element: " + getLastElement(strings));
        System.out.println("Even Sum: " + getEvenSum(array));
        System.out.println("Strings to Map: " + stringsToMap(strings));

        // Тестирование исключений
        testException("getAverage", () -> getAverage(Collections.emptyList()));
        testException("getLastElement", () -> getLastElement(Collections.emptyList()));
    }
}