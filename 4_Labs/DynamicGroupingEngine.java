import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class DynamicGroupingEngine {

    /**
     * Выполняет группировку и агрегацию данных.
     * @param data список данных
     * @param groupingStrategy стратегия группировки
     * @param aggregationStrategy стратегия агрегации
     * @return Map с результатами группировки и агрегации
     * @param <T> тип элементов данных
     * @param <K> тип ключа группировки
     */
    public static <T, K> Map<K, Map<String, Object>> groupAndAggregate(
            List<T> data,
            GroupingStrategy<T, K> groupingStrategy,
            AggregationStrategy<T> aggregationStrategy) {  // Изменено здесь

        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }

        // Группируем данные
        Map<K, List<T>> groupedData = data.stream()
                .collect(Collectors.groupingBy(groupingStrategy));

        // Применяем агрегацию к каждой группе
        return groupedData.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> aggregationStrategy.apply(entry.getValue())
                ));
    }
}