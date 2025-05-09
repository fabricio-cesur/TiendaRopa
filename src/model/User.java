package model;

public class User {
    private int userId;
    private String username;
    private String password;
    private String role; // "admin" o "user"
    private double balance; // Nuevo atributo

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.balance = role.equals("admin") ? 45000000 : 500; // 45 millones para admin, 500 para usuarios normales
    }

    // Getters y Setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public void addBalance(double amount) {
        this.balance += amount;
    }

    public void subtractBalance(double amount) {
        this.balance -= amount;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", balance=" + balance +
                '}';
    }
}
