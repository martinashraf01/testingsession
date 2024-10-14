package org.example.account;

import lombok.Data;

@Data

public class Customer {

    private String name;
    private int balance;
    private boolean creditAllowed;
//    private int maxCredit = 0;
    private boolean vip;

    public Customer() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }



    public boolean isCreditAllowed() {
        return creditAllowed;
    }

    public void setCreditAllowed(boolean creditAllowed) {
        this.creditAllowed = creditAllowed;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public void setBalnce(int i) {
        this.balance = balance;
    }

    public void setBalance(int i) {
        this.balance = balance;
    }
}
