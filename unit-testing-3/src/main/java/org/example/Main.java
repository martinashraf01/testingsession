package org.example;

import org.example.account.Customer;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Customer c = new Customer();
        c.setBalnce(500);
        System.out.println(c.getBalance());
    }
}