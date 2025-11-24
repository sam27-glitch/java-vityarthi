package service;

import model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionService {
    private final List<Transaction> transactions;

    public TransactionService() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction tx) {
        transactions.add(tx);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void deleteTransaction(int index) {
        if (index >= 0 && index < transactions.size()) {
            transactions.remove(index);
        }
    }

    public double getTotalExpenses() {
        return transactions.stream()
                .filter(tx -> tx.getType().equalsIgnoreCase("expense"))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getTotalIncome() {
        return transactions.stream()
                .filter(tx -> tx.getType().equalsIgnoreCase("income"))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }
}
