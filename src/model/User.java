package model;

public class User {
    //Atributos
    private int id;
    private String username;
    private String password;
    private String role; //Rol del usuario (admin, empleado, cliente, etc.)

    //Constructor
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    //Getters
    public int getUserId() {return id;}
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getRole() {return role;}

    //Setters
    public void setId(int id) {this.id = id;}
    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}
    public void setRole(String role) {this.role = role;}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role=" + role +
                '}';
    }
}
