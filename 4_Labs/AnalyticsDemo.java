import java.util.List;
import java.util.Map;

/**
 * Демонстрационный класс для тестирования функциональности.
 */
public class AnalyticsDemo {
    public static void main(String[] args) {
        System.out.println("=== Лабораторная работа №4: Stream API и динамическая группировка ===\n");

        // Генерация тестовых данных
        List<Transaction> transactions = TransactionGenerator.generateTransactions(50);
        System.out.println("Сгенерировано транзакций: " + transactions.size());
        System.out.println("Первые 3 транзакции:");
        transactions.stream().limit(3).forEach(System.out::println);
        System.out.println();

        // Тест 1: Группировка по месяцу и категории + базовая статистика
        System.out.println("=== Тест 1: Группировка по месяцу и категории ===");
        Map<String, Map<String, Object>> result1 = DynamicGroupingEngine.groupAndAggregate(
                transactions,
                TransactionGroupingStrategies.byMonthAndCategory(),
                TransactionAggregationStrategies.basicStatistics()
        );

        result1.forEach((key, stats) -> {
            System.out.println(key + ": " + stats);
        });
        System.out.println();

        // Тест 2: Группировка по диапазонам сумм + расширенная статистика
        System.out.println("=== Тест 2: Группировка по диапазонам сумм ===");
        Map<String, Map<String, Object>> result2 = DynamicGroupingEngine.groupAndAggregate(
                transactions,
                TransactionGroupingStrategies.byAmountRange(),
                TransactionAggregationStrategies.advancedStatistics()
        );

        result2.forEach((key, stats) -> {
            System.out.println(key + ": " + stats);
        });
        System.out.println();

        // Тест 3: Группировка по времени суток + базовая статистика
        System.out.println("=== Тест 3: Группировка по времени суток ===");
        Map<String, Map<String, Object>> result3 = DynamicGroupingEngine.groupAndAggregate(
                transactions,
                TransactionGroupingStrategies.byTimeOfDay(),
                TransactionAggregationStrategies.basicStatistics()
        );

        result3.forEach((key, stats) -> {
            System.out.println(key + ": " + stats);
        });
        System.out.println();

        // Доп. тест
        System.out.println("=== Тест 4: Проверка обработки null ===");
        transactions.add(null); // Добавляем null для теста
        transactions.add(new Transaction("null-test", null, null, 0.0, null));

        Map<String, Map<String, Object>> result4 = DynamicGroupingEngine.groupAndAggregate(
                transactions,
                TransactionGroupingStrategies.byMonthAndCategory(),
                TransactionAggregationStrategies.basicStatistics()
        );

        result4.entrySet().stream()
                .filter(entry -> entry.getKey().contains("NULL"))
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

        System.out.println("\n=== Демонстрация завершена ===");
    }
}