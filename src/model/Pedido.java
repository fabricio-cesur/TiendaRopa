package model;
//Clase para hacer pedidos de clientes
import java.util.ArrayList;

public class Pedido {
    //Atributos
    private int idPedido;
    private Cliente cliente;
    private String fechaPedido;
    private String fechaEntrega; //Fecha de entrega
    private Boolean estado; //Entregado o no
    private Double total; //Precio total del pedido
    private String direccion; //Direccion de envio
    ArrayList<Producto> carrito = new ArrayList<>(); //Lista de productos del pedido

    //Constructor
    public Pedido(Cliente cliente, String fechaPedido, String fechaEntrega, Boolean estado, Double total, String direccion) {
        this.cliente = cliente;
        this.fechaPedido = fechaPedido;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.total = total;
        this.direccion = direccion;
    }

    //Getters
    public int getIdPedido() { return idPedido; }
    public Cliente getCliente() { return cliente; }
    public String getFechaPedido() { return fechaPedido; }
    public String getFechaEntrega() { return fechaEntrega; }
    public Boolean getEstado() { return estado; }
    public Double getTotal() { return total; }
    public String getDireccion() { return direccion; }
    public ArrayList<Producto> getProductos() { return carrito; }

    //Setters
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public void setFechaPedido(String fechaPedido) { this.fechaPedido = fechaPedido; }
    public void setFechaEntrega(String fechaEntrega) { this.fechaEntrega = fechaEntrega; }
    public void setEstado(Boolean estado) { this.estado = estado; }
    public void setTotal(Double total) { this.total = total; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setProductos(ArrayList<Producto> productos) { this.carrito = productos; } //Setea la lista de productos del pedido

    
}
