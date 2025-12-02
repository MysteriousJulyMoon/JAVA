import java.util.List;
import java.util.Map;

/**
 * Функциональный интерфейс для стратегий агрегации.
 * @param <T> тип элементов в группе
 */
@FunctionalInterface
public interface AggregationStrategy<T> {
    /**
     * Применяет стратегию агрегации к группе элементов.
     * @param group группа элементов
     * @return результат агрегации в виде Map<String, Object>
     */
    Map<String, Object> apply(List<T> group);
}