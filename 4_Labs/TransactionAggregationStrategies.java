import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Стратегии агрегации для транзакций.
 */
public class TransactionAggregationStrategies {

    /**
     * Базовая статистика: сумма, среднее, количество.
     */
    public static AggregationStrategy<Transaction> basicStatistics() {
        return group -> {
            Map<String, Object> result = new HashMap<>();

            if (group == null || group.isEmpty()) {
                result.put("count", 0);
                result.put("total", 0.0);
                result.put("average", 0.0);
                return result;
            }

            int count = group.size();
            double total = group.stream()
                    .filter(t -> t != null)
                    .mapToDouble(Transaction::getAmount)
                    .sum();
            double average = total / count;

            result.put("count", count);
            result.put("total", Math.round(total * 100.0) / 100.0);
            result.put("average", Math.round(average * 100.0) / 100.0);

            return result;
        };
    }

    /**
     * Расширенная статистика: мин, макс, сумма, среднее, стандартное отклонение.
     */
    public static AggregationStrategy<Transaction> advancedStatistics() {
        return group -> {
            Map<String, Object> result = new HashMap<>();

            if (group == null || group.isEmpty()) {
                result.put("count", 0);
                result.put("total", 0.0);
                result.put("average", 0.0);
                result.put("min", 0.0);
                result.put("max", 0.0);
                result.put("stdDev", 0.0);
                return result;
            }

            List<Double> amounts = group.stream()
                    .filter(t -> t != null)
                    .map(Transaction::getAmount)
                    .toList();

            int count = amounts.size();
            double total = amounts.stream().mapToDouble(Double::doubleValue).sum();
            double average = total / count;
            double min = amounts.stream().mapToDouble(Double::doubleValue).min().orElse(0.0);
            double max = amounts.stream().mapToDouble(Double::doubleValue).max().orElse(0.0);


            double variance = amounts.stream()
                    .mapToDouble(a -> Math.pow(a - average, 2))
                    .sum() / count;
            double stdDev = Math.sqrt(variance);

            result.put("count", count);
            result.put("total", Math.round(total * 100.0) / 100.0);
            result.put("average", Math.round(average * 100.0) / 100.0);
            result.put("min", Math.round(min * 100.0) / 100.0);
            result.put("max", Math.round(max * 100.0) / 100.0);
            result.put("stdDev", Math.round(stdDev * 100.0) / 100.0);

            return result;
        };
    }
}