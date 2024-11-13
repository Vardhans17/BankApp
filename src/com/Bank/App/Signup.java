package com.Bank.App;

import java.util.Scanner;
import com.Bank.DAO.CustomerDAO;
import com.Bank.DAO.CustomerDAOImpl;
import com.Bank.DTO.Customer;

public class Signup {
    public static void signup() {
        Customer c = new Customer();
        CustomerDAO cdao = new CustomerDAOImpl();
        Scanner sc = new Scanner(System.in);
        System.out.println("<===Application Form===>");
        System.out.println("Enter your name");
        c.setName(sc.next());
        System.out.println("Enter your phone");
        c.setPhone(sc.nextLong());
        System.out.println("Enter your mail ID");
        c.setMail(sc.next());
        System.out.println("Set a Pin");
        int pin = sc.nextInt();
        System.out.println("Confirm the Pin");
        int confirm = sc.nextInt();
        if (pin == confirm) {
            c.setPin(pin);
            boolean res = cdao.insertCustomer(c);
            if (res) {
                System.out.println("Data added successfully!");
                c = cdao.getCustomer(c.getPhone(), c.getMail());
                System.out.println("Your account number is " + c.getAccno());
            } else {
                System.out.println("Failed to create an account");
            }
        } else {
            System.out.println("Pins do not match. Please try again.");
        }
    }
}