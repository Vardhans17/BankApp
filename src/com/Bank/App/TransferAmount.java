package com.Bank.App;

import java.util.Scanner;
import com.Bank.DAO.CustomerDAO;
import com.Bank.DAO.CustomerDAOImpl;
import com.Bank.DAO.TransactionDAO;
import com.Bank.DAO.TransactionDAOImpl;
import com.Bank.DTO.Customer;
import com.Bank.DTO.Transaction;
import com.Bank.DTO.TransactionID;

public class TransferAmount {
	public static void transferAmount(Customer c) { // c -> sender account which you logged in
	    CustomerDAO cdao = new CustomerDAOImpl();
	    Transaction t1 = null; // sender transaction details
	    Transaction t2 = null; // receiver transaction details
	    TransactionDAO tdao = new TransactionDAOImpl();

	    Scanner sc = new Scanner(System.in);
	    System.out.println("Enter the amount to be transferred");
	    double amount = sc.nextDouble();
	    System.out.println("Enter the beneficiary account number");
	    long receiver_accno = sc.nextLong(); // receiver -> receiver

	    Customer receiver = cdao.getCustomer(receiver_accno);

//	    // Check if receiver is null
//	    if (receiver == null) {
//	        System.out.println("Invalid beneficiary account number. Please check and try again.");
//	        return; // Exit the method if the receiver does not exist
//	    }

	    if (c.getAccno() != receiver_accno && c.getBal() > 0 && c.getBal() >= amount && amount > 0) {
	        System.out.println("Enter the PIN");
	        int pin = sc.nextInt();
	        if (pin == c.getPin()) {
	            c.setBal(c.getBal() - amount);
	            boolean c_res = cdao.updateCustomer(c); // updateCustomer() already overridden in deposit module inside CustomerDAOImpl

	            if (c_res) {
	                t1 = new Transaction();
	                t1.setTransactionId(TransactionID.generateTransactionId());
	                t1.setUser (c.getAccno());
	                t1.setRec_acc(receiver.getAccno());
	                t1.setTransaction("DEBITED");
	                t1.setAmount(amount);
	                t1.setBalance(c.getBal());
	                boolean res1 = tdao.insertTransaction(t1); // insertTransaction() already overridden in deposit module inside TransactionDAOImpl
	            }

	            // Amount is getting credited to receiver's account
	            receiver.setBal(receiver.getBal() + amount);
	            boolean receiver_res = cdao.updateCustomer(receiver);

	            if (receiver_res) {
	                t2 = new Transaction();
	                t2.setTransactionId(t1.getTransactionId());
	                t2.setUser (receiver.getAccno());
	                t2.setRec_acc(c.getAccno());
	                t2.setTransaction("CREDITED");
	                t2.setAmount(amount);
	                t2.setBalance(receiver.getBal());
	                boolean res2 = tdao.insertTransaction(t2); // Insert transaction for receiver
	            }

	            // Check if both transactions were successful
	            if (c_res && receiver_res) {
	                System.out.println("Transaction successful");
	            } else {
	                System.out.println("Transaction failed!!!");
	            }
	        } else {
	            System.out.println("Incorrect PIN. Transaction failed!!!");
	        }
	    } else {
	        System.out.println("Transaction failed!!!");
	    }
	}
	
}