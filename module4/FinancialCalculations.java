package module4;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

class Transaction {

    float amount;
    String category;
    String tag;

    Transaction() {}

    Transaction(float amount, String category, String tag) {
        this.amount = amount;
        this.category = category;
        this.tag = tag;
    }
    
    float getAmount() { return amount; }
    String getCategory() { return category; }
    String getTag() { return tag; }
}

public class FinancialCalculations {
    public static void main(String[] args) {
        
        List<Transaction> transactions = Arrays.asList(
                new Transaction(45.20f, "groceries", "purchase"),
                new Transaction(250.00f, "rent", "purchase"),
                new Transaction(12.50f, "groceries", "purchase"),
                new Transaction(500.00f, "savings", "transfer"),
                new Transaction(30.00f, "dining", "purchase"),
                new Transaction(8.99f, "subscriptions", "purchase"),
                new Transaction(22.40f, "dining", "purchase"),
                new Transaction(1200.00f, "rent", "purchase"),
                new Transaction(15.75f, "groceries", "purchase"),
                new Transaction(100.00f, "savings", "transfer"),
                new Transaction(4.99f, "subscriptions", "purchase"),
                new Transaction(9.99f, "subscriptions", "purchase")
        );

        printTopThreeCategoriesWithMaxAmount(transactions);
    }

    static void printTopThreeCategoriesWithMaxAmount(List<Transaction> transactionList) {

        if (transactionList == null) return;

        Map<String, Float> transactionMap = transactionList.stream()
            .filter(transaction -> !transaction.getTag().equals("transfer"))
            .collect(Collectors.toMap(
                Transaction::getCategory, 
                Transaction::getAmount,
                Float::sum
            ));

        TreeMap<String, Float> sortedByAmountMap = new TreeMap<>((key1, key2) -> {
                int amount = Float.compare(transactionMap.get(key2), transactionMap.get(key1));
                if (amount != 0) {
                    return amount;
                }
                return key1.compareTo(key2);
        });
        
        transactionMap.entrySet().stream()
            .sorted(Map.Entry.<String, Float>comparingByValue().reversed())
            .limit(3)
            .forEach(entry -> sortedByAmountMap.put(entry.getKey(), entry.getValue()));

        System.out.println(sortedByAmountMap);
                
    }

    public static LinkedHashMap<String, Float> getTopCategoriesInline(List<Transaction> transactions, int limit) {
        return transactions.stream()
            // 1. Filter out "transfer" transactions
            .filter(t -> t.getType() != null && !"transfer".equalsIgnoreCase(t.getType()))
            
            // 2. Group by category and sum amounts using collectors
            .collect(Collectors.groupingBy(
                Transaction::getCategory,
                Collectors.reducing(0.0f, Transaction::getAmount, Float::sum)
            ))
            
            // 3. Open a stream over the resulting map's entries
            .entrySet().stream()
            
            // 4. Sort by total value in descending order
            .sorted(Map.Entry.<String, Float>comparingByValue().reversed())
            
            // 5. Keep only the top N categories
            .limit(limit)
            
            // 6. Collect directly into a LinkedHashMap to lock the order in place
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (existing, replacement) -> existing, // Merge function (not hit, but required)
                LinkedHashMap::new                  // Maintains insertion order
            ));
    }
}