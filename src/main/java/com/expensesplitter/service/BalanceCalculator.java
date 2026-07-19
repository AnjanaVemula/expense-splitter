package com.expensesplitter.service;

import com.expensesplitter.model.Balance;
import com.expensesplitter.model.Expense;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BalanceCalculator {

    public Map<String, Double> calculateNetBalances(List<Expense> expenses) {
        Map<String, Double> netBalances = new HashMap<>();

        for (Expense expense : expenses) {
            String payer = expense.getPaidBy();
            double totalAmount = expense.getAmount();
            List<String> people = expense.getSplitAmong();
            double share = totalAmount / people.size();

            // Payer gets credited the full amount
            netBalances.put(payer, netBalances.getOrDefault(payer, 0.0) + totalAmount);

            // Everyone in the split gets debited their share
            for (String person : people) {
                netBalances.put(person, netBalances.getOrDefault(person, 0.0) - share);
            }
        }

        return netBalances;
    }
}