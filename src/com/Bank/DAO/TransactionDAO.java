package com.Bank.DAO;

import java.util.List;
import com.Bank.DTO.Transaction;

public interface TransactionDAO {
    boolean insertTransaction(Transaction t);
    List<Transaction> getTransaction(long user); // Method to get transactions for a specific user
    List<Transaction> getTransaction(); // Method to get all transactions (if needed)
}