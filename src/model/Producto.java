package model;

public class Producto {
    //Atributos
    private int id;
    private String tipo;
    private String nombre;
    private String marca;
    private String talla;
    private String color;
    private double precio;
    private int stock;
    
    //Constructor
    public Producto (String tipo, String nombre, String marca, String talla, String color, double precio) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.marca = marca;
        this.talla = talla;
        this.color = color;
        this.precio = precio;
    }

    //Getters
    public int getId() { return this.id; }
    public String getTipo() { return this.tipo; }
    public String getNombre() { return this.nombre; }
    public String getMarca() { return this.marca; }
    public String getTalla() { return this.talla; }
    public String getColor() { return this.color; }
    public double getPrecio() { return this.precio; }
    public int getStock() { return this.stock; }
    //Setters
    public void setId(int id) { this.id = id; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setTalla(String talla) { this.talla = talla; }
    public void setColor(String color) { this.color = color; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setStock(int stock) { this.stock = stock; }

    @Override
    //toString
    public String toString() {
        return "ID: " + this.getId() + " tipo: " + this.getTipo() +
        "\nNombre: " + this.getNombre() + " Marca: " + this.getMarca() + 
        "\nTalla: " + this.getTalla() + " Precio: " + this.getPrecio();
    }
}
