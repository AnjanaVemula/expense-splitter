package com.expensesplitter.service;

import com.expensesplitter.model.Expense;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class BalanceCalculatorTest {

    private final BalanceCalculator calculator = new BalanceCalculator();

    @Test
    void testSimpleThreePersonSplit() {
        Expense expense = new Expense("Alice", 300, Arrays.asList("Alice", "Bob", "Charlie"));
        Map<String, Double> balances = calculator.calculateNetBalances(List.of(expense));

        assertEquals(200.0, balances.get("Alice"), 0.01);
        assertEquals(-100.0, balances.get("Bob"), 0.01);
        assertEquals(-100.0, balances.get("Charlie"), 0.01);
    }

    @Test
    void testAlreadySettledGroup() {
        // Everyone pays their own share exactly - no net debt
        Expense e1 = new Expense("Alice", 100, List.of("Alice"));
        Expense e2 = new Expense("Bob", 100, List.of("Bob"));

        Map<String, Double> balances = calculator.calculateNetBalances(Arrays.asList(e1, e2));

        assertEquals(0.0, balances.get("Alice"), 0.01);
        assertEquals(0.0, balances.get("Bob"), 0.01);
    }

    @Test
    void testBalancesSumToZero() {
        Expense hotel = new Expense("Alice", 4000, Arrays.asList("Alice", "Bob", "Charlie", "Dave"));
        Expense food = new Expense("Bob", 1200, Arrays.asList("Alice", "Bob", "Charlie", "Dave"));
        Expense cabs = new Expense("Charlie", 800, Arrays.asList("Alice", "Bob", "Charlie", "Dave"));

        Map<String, Double> balances = calculator.calculateNetBalances(Arrays.asList(hotel, food, cabs));

        double sum = balances.values().stream().mapToDouble(Double::doubleValue).sum();
        assertEquals(0.0, sum, 0.01);
    }

    @Test
    void testTwoPersonSplit() {
        Expense expense = new Expense("Alice", 500, Arrays.asList("Alice", "Bob"));
        Map<String, Double> balances = calculator.calculateNetBalances(List.of(expense));

        assertEquals(250.0, balances.get("Alice"), 0.01);
        assertEquals(-250.0, balances.get("Bob"), 0.01);
    }
}