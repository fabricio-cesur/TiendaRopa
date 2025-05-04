package model;

public class Producto {
    //Atributos
    private int idProducto;
    private String nombre;
    private String descripcion;
    private Double precio;
    private int stock; //Cantidad de productos disponibles
    private String talla; //Talla del producto (36,38,40,42,..) (S, M, L, XL)
    private String color; //Color del producto (Rojo, Azul, Verde, Amarillo, etc.)
    private String marca;
    private String categoria; //Categoria del producto (Ropa, Calzado, Accesorios, etc.)

    //Constructor
    public Producto(String nombre, String descripcion, Double precio, int stock, String talla, String color, String marca, String categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.talla = talla;
        this.color = color;
        this.marca = marca;
        this.categoria = categoria;
    }

    //Getters
    public int getIdProducto() {return idProducto;}
    public String getNombre() {return nombre;}
    public String getDescripcion() {return descripcion;}
    public Double getPrecio() {return precio;}
    public int getStock() {return stock;}
    public String getTalla() {return talla;}
    public String getColor() {return color;}
    public String getMarca() {return marca;}
    public String getCategoria() {return categoria;}

    //Setters
    public void setIdProducto(int idProducto) {this.idProducto = idProducto;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
    public void setPrecio(Double precio) {this.precio = precio;}
    public void setStock(int stock) {this.stock = stock;}
    public void setTalla(String talla) {this.talla = talla;}
    public void setColor(String color) {this.color = color;}
    public void setMarca(String marca) {this.marca = marca;}
    public void setCategoria(String categoria) {this.categoria = categoria;}

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                ", talla='" + talla + '\'' +
                ", color='" + color + '\'' +
                ", marca='" + marca + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
    


}
