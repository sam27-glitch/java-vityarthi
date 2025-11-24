import model.Transaction;
import service.TransactionService;
import db.FileDatabase;
import util.InputHelper;

import java.util.List;

public class Main {
    private static final String DATA_FILE = "transactions.txt";
    private static TransactionService transactionService;
    private static FileDatabase fileDatabase;

    public static void main(String[] args) {
        transactionService = new TransactionService();
        fileDatabase = new FileDatabase(DATA_FILE);

        // Load existing data
        transactionService.getTransactions().addAll(fileDatabase.load());

        boolean exit = false;

        while (!exit) {
            System.out.println("\nPersonal Finance Tracker");
            System.out.println("1. Add Transaction");
            System.out.println("2. List Transactions");
            System.out.println("3. Summary");
            System.out.println("4. Delete Transaction");
            System.out.println("5. Exit");

            int choice = (int) InputHelper.readDouble("Choose an option: ");

            switch (choice) {
                case 1 -> addTransaction();
                case 2 -> listTransactions();
                case 3 -> showSummary();
                case 4 -> deleteTransaction();
                case 5 -> {
                    exit = true;
                    fileDatabase.save(transactionService.getTransactions());
                    System.out.println("Data saved. Exiting...");
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addTransaction() {
        String type;
        while (true) {
            type = InputHelper.readString("Enter type (income/expense): ").toLowerCase();
            if (type.equals("income") || type.equals("expense")) break;
            System.out.println("Invalid type. Please enter 'income' or 'expense'.");
        }
        String category = InputHelper.readString("Enter category: ");
        double amount = InputHelper.readDouble("Enter amount: ");
        String date = InputHelper.readString("Enter date (YYYY-MM-DD): ");
        String description = InputHelper.readString("Enter description: ");

        Transaction transaction = new Transaction(type, category, amount, date, description);
        transactionService.addTransaction(transaction);
        System.out.println("Transaction added successfully.");
    }

    private static void listTransactions() {
        List<Transaction> transactions = transactionService.getTransactions();
        if (transactions.isEmpty()) {
            System.out.println("No transactions recorded.");
            return;
        }

        System.out.println("Transactions:");
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println((i + 1) + ". " + transactions.get(i));
        }
    }

    private static void showSummary() {
        double totalIncome = transactionService.getTotalIncome();
        double totalExpenses = transactionService.getTotalExpenses();
        double balance = totalIncome - totalExpenses;

        System.out.println("Summary:");
        System.out.printf("Total Income: %.2f\n", totalIncome);
        System.out.printf("Total Expenses: %.2f\n", totalExpenses);
        System.out.printf("Balance: %.2f\n", balance);
    }

    private static void deleteTransaction() {
        listTransactions();
        if (transactionService.getTransactions().isEmpty()) return;

        int idx = (int) InputHelper.readDouble("Enter transaction number to delete: ") - 1;
        if (idx < 0 || idx >= transactionService.getTransactions().size()) {
            System.out.println("Invalid transaction number.");
            return;
        }
        transactionService.deleteTransaction(idx);
        System.out.println("Transaction deleted.");
    }
}
