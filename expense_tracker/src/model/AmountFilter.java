package model;

import java.util.ArrayList;
import java.util.List;

public class AmountFilter implements TransactionFilter {
    private double minAmount;
    private double maxAmount;

    public AmountFilter(double minAmount, double maxAmount) {
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        System.out.println("minAmount: " + minAmount);
        System.out.println("maxAmount: " + maxAmount);
    }

    @Override
    public List<Transaction> filter(List<Transaction> transactions) {
        List<Transaction> filteredTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() >= this.minAmount && transaction.getAmount() <= this.maxAmount) {
                filteredTransactions.add(transaction);
            }
        }
        System.out.println(filteredTransactions);
        return filteredTransactions;
    }
}
