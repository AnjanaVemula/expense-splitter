package org.example;

import com.expensesplitter.model.Expense;
import com.expensesplitter.service.BalanceCalculator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Recreating our trip example: Alice, Bob, Charlie, Dave
        Expense hotel = new Expense("Alice", 4000, Arrays.asList("Alice", "Bob", "Charlie", "Dave"));
        Expense food = new Expense("Bob", 1200, Arrays.asList("Alice", "Bob", "Charlie", "Dave"));
        Expense cabs = new Expense("Charlie", 800, Arrays.asList("Alice", "Bob", "Charlie", "Dave"));

        List<Expense> expenses = Arrays.asList(hotel, food, cabs);

        BalanceCalculator calculator = new BalanceCalculator();
        Map<String, Double> balances = calculator.calculateNetBalances(expenses);

        System.out.println("Net balances:");
        for (Map.Entry<String, Double> entry : balances.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}