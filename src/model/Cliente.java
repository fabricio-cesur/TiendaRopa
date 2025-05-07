package model;

public class Cliente {
    //Atributos
    private int id;
    private String dni;
    private String nombre;
    private String telefono;
    private String email;

    //Constructor
    public Cliente(String dni, String nombre, String telefono, String email) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    //Getters
    public int getId() { return this.id; }
    public String getDni() { return this.dni; }
    public String getNombre() { return this.nombre; }
    public String getTelefono() { return this.telefono; }
    public String getEmail() { return this.email; }
    //Setters
    public void setId(int id) { this.id = id; }
    public void setDni(String dni) { this.dni = dni; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "ID: " + this.getId() + ", DNI: " + this.getDni() +
        "\nNombre: " + this.getNombre() + ", Teléfono: " + this.getTelefono() +
        "\nEmail: " + this.getEmail();
    }
}