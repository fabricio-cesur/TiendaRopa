package model;

public class Cliente {
    //Atributos
    private int idCliente;
    private String nombre;
    private String apellido;
    private String email;
    private String direccion;
    private String telefono;
    private String fechaRegistro; //Fecha de registro del cliente

    //Constructor
    public Cliente(String nombre, String apellido, String email, String direccion, String telefono, String fechaRegistro) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
    }

    //Getters
    public int getIdCliente() { return idCliente; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getEmail() { return email; }
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }
    public String getFechaRegistro() { return fechaRegistro; }

    //Setters
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setEmail(String email) { this.email = email; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fechaRegistro='" + fechaRegistro + '\'' +
                '}';
    }

}
