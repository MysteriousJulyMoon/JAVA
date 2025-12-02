import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Генератор тестовых данных для транзакций.
 */
public class TransactionGenerator {
    private static final Random random = new Random();
    private static final String[] CATEGORIES = {"Food", "Transport", "Entertainment", "Utilities", "Shopping", "Healthcare", "Education", null};
    private static final String[] CURRENCIES = {"USD", "EUR", "RUB", "GBP", "JPY"};

    /**
     * Генерирует список транзакций заданного размера.
     * @param count количество транзакций
     * @return список транзакций
     */
    public static List<Transaction> generateTransactions(int count) {
        List<Transaction> transactions = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String id = UUID.randomUUID().toString();
            LocalDateTime timestamp = generateRandomTimestamp();
            String category = CATEGORIES[random.nextInt(CATEGORIES.length)];
            double amount = Math.round(random.nextDouble() * 1000 * 100.0) / 100.0; // до 1000 с 2 знаками
            String currency = CURRENCIES[random.nextInt(CURRENCIES.length)];

            transactions.add(new Transaction(id, timestamp, category, amount, currency));
        }

        return transactions;
    }

    private static LocalDateTime generateRandomTimestamp() {
        int year = 2024 + random.nextInt(2); // 2024-2025
        Month month = Month.of(1 + random.nextInt(12));
        int day = 1 + random.nextInt(28);
        int hour = random.nextInt(24);
        int minute = random.nextInt(60);
        int second = random.nextInt(60);

        return LocalDateTime.of(year, month, day, hour, minute, second);
    }
}