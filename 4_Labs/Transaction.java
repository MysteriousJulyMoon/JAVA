import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Класс, представляющий финансовую транзакцию.
 */
public class Transaction {
    private String id;
    private LocalDateTime timestamp;
    private String category;
    private double amount;
    private String currency;

    public Transaction(String id, LocalDateTime timestamp, String category, double amount, String currency) {
        this.id = id;
        this.timestamp = timestamp;
        this.category = category;
        this.amount = amount;
        this.currency = currency;
    }

    // Геттеры
    public String getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    // Сеттеры (опционально)
    public void setId(String id) {
        this.id = id;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", timestamp=" + timestamp +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(amount, that.amount) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(category, that.category) &&
                Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp, category, amount, currency);
    }
}