package model;

public class Banca {
    private double balance;

    public Banca() {
        this.balance = 45000000; // Balance inicial de la empresa
    }

    public double getBalance() {return balance;}

    public void addBalance(double amount) {this.balance += amount;}

    public void subtractBalance(double amount) {this.balance -= amount;}

    @Override
    public String toString() {
        return "Banca{" +
                "balance=" + balance +
                '}';
    }
}
