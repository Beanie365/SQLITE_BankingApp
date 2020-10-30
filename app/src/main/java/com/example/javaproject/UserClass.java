package com.example.javaproject;

public class UserClass {
    private static String firstname,lastname,password,email,mobile;
    private static int id;
    private static Double balance, savings;

    public void setId(int id) { this.id = id; }

    public int getId() { return id; }

    public Double getBalance() { return balance; }

    public Double getSavings() { return savings; }

    public void setBalance(Double balance) { this.balance = balance; }

    public void setSavings(Double savings) { this.savings = savings; }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public void setMobile(String mobile){ this.mobile= mobile;}

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getMobile(){return mobile;}
}
