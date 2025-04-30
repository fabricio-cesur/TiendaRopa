package model;
import java.util.ArrayList;

public class Venta {
    //Atributos
    private int idVenta;
    private Cliente cliente;
    private String fecha_venta;
    private Double total; //Importe total de venta
    private Empleado empleado; //Empleado que realizó la venta
    private boolean estado; //Estado de la venta (realizada o !realizada)
    private boolean cancelado; //Cancelada o no
    private int id_descuento; //Id del descuento aplicado a la venta
    ArrayList<Producto> productos = new ArrayList<>(); // Lista de productos vendidos
    
    //Constructor
    public Venta(Cliente cliente, String fecha_venta, Double total, Empleado empleado, boolean estado, boolean cancelado, int id_descuento) {
        this.cliente = cliente;
        this.fecha_venta = fecha_venta;
        this.total = total;
        this.empleado = empleado;
        this.estado = estado;
        this.cancelado = cancelado;
        this.id_descuento = id_descuento;
    }

    //Getters
    public int getIdVenta() { return idVenta; }
    public Cliente getCliente() { return cliente; }
    public String getFecha_venta() { return fecha_venta; }
    public Double getTotal() { return total; }
    public Empleado getEmpleado() { return empleado; }
    public boolean getEstado() { return estado; }
    public boolean getCancelado() { return cancelado; }
    public int getIdDescuento() { return id_descuento; } //Get id del descuento aplicado a la venta
    public ArrayList<Producto> getProductos() { return productos; }

    //Setters
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public void setFecha_venta(String fecha_venta) { this.fecha_venta = fecha_venta; }
    public void setTotal(Double total) { this.total = total; }
    public void setEmpleado(Empleado empleado) { this.empleado = empleado; }
    public void setEstado(boolean estado) { this.estado = estado; }
    public void setCancelado(boolean cancelado) { this.cancelado = cancelado; }
    public void setIdDescuento(int idDescuento) { this.id_descuento = idDescuento; } //Set id del descuento aplicado a la venta
    public void setProductos(ArrayList<Producto> productos) { this.productos = productos; } //Setea la lista de productos vendidos

    //toString

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Venta{");
        sb.append("idVenta=").append(idVenta);
        sb.append(", cliente=").append(cliente);
        sb.append(", fecha_venta=").append(fecha_venta);
        sb.append(", total=").append(total);
        sb.append(", empleado=").append(empleado);
        sb.append(", estado=").append(estado);
        sb.append(", cancelado=").append(cancelado);
        sb.append(", id_descuento=").append(id_descuento);
        sb.append(", productos=").append(productos);
        sb.append('}');
        return sb.toString();
    }
    


}
