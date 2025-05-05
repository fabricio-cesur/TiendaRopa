package model;

public class User {
    //Atributos
    private int id;
    private String username;
    private String password;
    private boolean admin;

    //Constructor
    public User(String username, String password, boolean admin) {
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    //Getters
    public int getUserId() {return id;}
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public boolean isAdmin() {return admin;}

    //Setters
    public void setId(int id) {this.id = id;}
    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}
    public void setAdmin(boolean admin) {this.admin = admin;}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                '}';
    }
}
