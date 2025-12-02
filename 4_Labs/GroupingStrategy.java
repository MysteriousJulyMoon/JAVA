import java.util.function.Function;

/**
 * Функциональный интерфейс для стратегий группировки.
 * @param <T> тип элементов
 * @param <K> тип ключа группировки
 */
@FunctionalInterface
public interface GroupingStrategy<T, K> extends Function<T, K> {
    // Наследует метод K apply(T t) от Function
}