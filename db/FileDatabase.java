package db;

import model.Transaction;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileDatabase {
    private final String filename;

    public FileDatabase(String filename) {
        this.filename = filename;
    }

    public void save(List<Transaction> transactions) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Transaction tx : transactions) {
                // Save fields separated by commas
                writer.write(tx.getType() + "," + tx.getCategory() + "," + tx.getAmount() + "," + tx.getDate() + "," + tx.getDescription());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public List<Transaction> load() {
        List<Transaction> transactions = new ArrayList<>();
        File file = new File(filename);

        if (!file.exists()) {
            return transactions; // Return empty list if no file
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 5);
                if (parts.length == 5) {
                    String type = parts[0];
                    String category = parts[1];
                    double amount = Double.parseDouble(parts[2]);
                    String date = parts[3];
                    String description = parts[4];
                    transactions.add(new Transaction(type, category, amount, date, description));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
        return transactions;
    }
}
