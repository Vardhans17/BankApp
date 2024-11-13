package com.Bank.App;

import java.util.Scanner;
import com.Bank.DAO.CustomerDAO;
import com.Bank.DAO.CustomerDAOImpl;
import com.Bank.DTO.Customer;

public class Login {
    public static void login() {
        Scanner sc = new Scanner(System.in);
        CustomerDAO cdao = new CustomerDAOImpl();
        System.out.println("Enter your Account number");
        long accno = sc.nextLong();
        System.out.println("Enter your PIN");
        int pin = sc.nextInt();
        Customer c = cdao.getCustomer(accno, pin);
        if (c != null) {
            if (c.getAccno() == 1100110011) {
                System.out.println("Welcome admin :" + c.getName());
                Admin.admin(c);
            } else {
                System.out.println("Login successful!");
                App.options(c);
            }
        } else {
            System.out.println("Failed to login!");
        }
    }
}