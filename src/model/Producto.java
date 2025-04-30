package model;

public class Producto {
    //Atributos
    private String id;
    private String tipo;
    private String nombre;
    private String marca;
    private String talla;
    private double precio;
    
    //Constructor
    public Producto (String tipo, String nombre, String marca, String talla, double precio) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.marca = marca;
        this.talla = talla;
        this.precio = precio;
    }

    //Getters
    public String getId() { return this.id; }
    public String getTipo() { return this.tipo; }
    public String getNombre() { return this.nombre; }
    public String getMarca() { return this.marca; }
    public String getTalla() { return this.talla; }
    public double getPrecio() { return this.precio; }
    //Setters
    public void setId(String id) { this.id = id; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setTalla(String talla) { this.talla = talla; }
    public void setPrecio(double precio) { this.precio = precio; }

    @Override
    //toString
    public String toString() {
        return "ID: " + this.getId() + " tipo: " + this.getTipo() +
        "\nNombre: " + this.getNombre() + " Marca: " + this.getMarca() + 
        "\nTalla: " + this.getTalla() + " Precio: " + this.getPrecio();
    }
}
