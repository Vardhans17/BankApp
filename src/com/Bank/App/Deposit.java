package com.Bank.App;

import java.util.Scanner;
import com.Bank.DAO.CustomerDAO;
import com.Bank.DAO.CustomerDAOImpl;
import com.Bank.DAO.TransactionDAO;
import com.Bank.DAO.TransactionDAOImpl;
import com.Bank.DTO.Customer;
import com.Bank.DTO.Transaction;
import com.Bank.DTO.TransactionID;

public class Deposit {
    public static void deposit(Customer c) {
        CustomerDAO cdao = new CustomerDAOImpl();
        TransactionDAO tdao = new TransactionDAOImpl();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the amount to be deposited");
        double amount = sc.nextDouble();
        c.setBal(c.getBal() + amount);
        boolean res = cdao.updateCustomer(c); // updateCustomer() already overridden in deposit module inside CustomerDAOImpl
        if (res) {
            Transaction t = new Transaction();
            t.setTransactionId(TransactionID.generateTransactionId());
            t.setUser (c.getAccno());
            t.setRec_acc(c.getAccno());
            t.setTransaction("CREDITED");
            t.setAmount(amount);
            t.setBalance(c.getBal());
            boolean res1 = tdao.insertTransaction(t); // insertTransaction() already overridden in deposit module inside TransactionDAOImpl
            if (res1) {
                System.out.println("Amount of Rs." + amount + " has been added successfully!");
                System.out.println("Your updated balance is Rs." + c.getBal());
            } else {
                System.out.println("Failed to deposit! Try again later");
            }
        } else {
            System.out.println("Failed to update balance!");
        }
    }
}