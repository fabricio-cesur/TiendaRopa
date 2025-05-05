package model;
public class Cliente {
    //Atributos
    private User idCliente;
    private String nombre;
    private String apellido;
    private String email;
    private String direccion;
    private String telefono;

    //Constructor
    public Cliente(String nombre, String apellido, String email, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }

    //Getters
    public User getIdCliente() { return idCliente; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getEmail() { return email; }
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }

    //Setters
    public void setIdCliente(User idCliente) { this.idCliente = idCliente; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setEmail(String email) { this.email = email; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente.getId() +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }

}
