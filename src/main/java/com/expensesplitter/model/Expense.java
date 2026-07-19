package com.expensesplitter.model;

import java.util.List;

public class Expense {
    private String paidBy;
    private double amount;
    private List<String> splitAmong; // people sharing this expense equally

    public Expense(String paidBy, double amount, List<String> splitAmong) {
        this.paidBy = paidBy;
        this.amount = amount;
        this.splitAmong = splitAmong;
    }

    public String getPaidBy() { return paidBy; }
    public double getAmount() { return amount; }
    public List<String> getSplitAmong() { return splitAmong; }
}