package model;

public class Empleado {
private int id;
private String nombre;
private String apellido;
private String dni;
private String cargo;
private double Saldo;
private String telefono;
private String email;

    public Empleado(double Saldo, String apellido, String cargo, String dni, String email, int id, String nombre, String telefono) {
        this.Saldo = Saldo;
        this.apellido = apellido;
        this.cargo = cargo;
        this.dni = dni;
        this.email = email;
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSaldo() {
        return Saldo;
    }

    public void setSaldo(double Saldo) {
        this.Saldo = Saldo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Empleado [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", cargo="
                + cargo + ", Saldo=" + Saldo + ", telefono=" + telefono + ", email=" + email + "]";
    }

    
     
   
}
