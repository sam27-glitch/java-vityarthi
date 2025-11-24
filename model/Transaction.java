package model;

public class Transaction {
    private String type; // "income" or "expense"
    private String category;
    private double amount;
    private String date;
    private String description;

    public Transaction(String type, String category, double amount, String date, String description) {
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public String getType() { return type; }
    public String getCategory() { return category; }
    public double getAmount() { return amount; }
    public String getDate() { return date; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return String.format("%s | %s | %.2f | %s | %s", type, category, amount, date, description);
    }
}
