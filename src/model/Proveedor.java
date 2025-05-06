package model;

public class Proveedor {
    
    //Atributos
    private String nombre;
    private String direccion;
    private String numero;
    private String email;
    private String productos; 
    private String cuenta;
    
    //Constructor
    public Proveedor(String nombre, String direccion, String numero, String email, String productos, String cuenta) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.cuenta = cuenta;
        this.numero = numero;
        this.email = email;
        this.productos = productos;
    }
    
    //Geter y seter
    public String getNombre() { return this.nombre; }
    public String getDireccion() { return this.direccion; }
    public String getNumero() { return this.numero; }
    public String getEmail() { return this.email; }
    public String getProductos() { return this.productos; }
    public String getCuenta() { return this.cuenta; }
    
    public void setNombre(String nombre) {  this.nombre = nombre; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setNumero(String numero) { this.numero = numero; }
    public void setEmail(String email) { this.email = email; }
    public void setProductos(String productos) { this.productos = productos; }
    public void setCuenta(String cuenta) {  this.cuenta = cuenta;}   
        
    //Método que se utiliza a imprimir el objeto
    @Override
    public String toString() { 
        return "Nombre: " + this.getNombre() + ", dirección: " + this.getDireccion()
        + ", número de cuenta: " + this.getCuenta();
    }
}
