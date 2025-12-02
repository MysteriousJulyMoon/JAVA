import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 * Стратегии группировки для транзакций.
 */
public class TransactionGroupingStrategies {

    /**
     * Группировка по месяцу и категории.
     * Формат ключа: "2024-January-Food" или "2024-January-NULL"
     */
    public static GroupingStrategy<Transaction, String> byMonthAndCategory() {
        return transaction -> {
            if (transaction == null) return "NULL-Transaction";

            LocalDateTime timestamp = transaction.getTimestamp();
            String category = transaction.getCategory();

            String monthPart = (timestamp != null)
                    ? timestamp.getYear() + "-" + timestamp.getMonth().toString()
                    : "NULL-Date";

            String categoryPart = (category != null && !category.isEmpty())
                    ? category
                    : "NULL-Category";

            return monthPart + "-" + categoryPart;
        };
    }

    /**
     * Группировка по диапазонам сумм.
     */
    public static GroupingStrategy<Transaction, String> byAmountRange() {
        return transaction -> {
            if (transaction == null) return "NULL-Transaction";

            double amount = transaction.getAmount();
            if (amount < 0) return "Negative";
            else if (amount <= 100) return "0-100";
            else if (amount <= 500) return "100-500";
            else if (amount <= 1000) return "500-1000";
            else return "1000+";
        };
    }

    /**
     * Группировка по времени суток.
     */
    public static GroupingStrategy<Transaction, String> byTimeOfDay() {
        return transaction -> {
            if (transaction == null || transaction.getTimestamp() == null) {
                return "NULL-Time";
            }

            int hour = transaction.getTimestamp().getHour();
            if (hour >= 6 && hour < 12) return "Morning";
            else if (hour >= 12 && hour < 18) return "Afternoon";
            else if (hour >= 18 && hour < 24) return "Evening";
            else return "Night";
        };
    }
}