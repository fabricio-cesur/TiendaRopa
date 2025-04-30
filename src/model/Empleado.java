package model;

public class Empleado {
private int id;
private String nombre;
private String apellido;
private String dni;
private String cargo;
private double saldo;
private String telefono;
private String email;

   
    
public Empleado(String nombre, String apellido, String dni, String cargo, double saldo,String telefono,String email) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.dni = dni;
    this.cargo = cargo;
    this.saldo = saldo;
    this.telefono=telefono;
    this.email=email;
}
public Empleado(int id,String nombre, String apellido, String dni, String cargo, double saldo,String telefono,String email) {
    this.id=id;
    this.nombre = nombre;
    this.apellido = apellido;
    this.dni = dni;
    this.cargo = cargo;
    this.saldo = saldo;
    this.telefono=telefono;
    this.email=email;
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
        return saldo;
    }

    public void setSaldo(double Saldo) {
        this.saldo = Saldo;
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
                + cargo + ", Saldo=" + saldo + ", telefono=" + telefono + ", email=" + email + "]";
    }
}  
     
   

