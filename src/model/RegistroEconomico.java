package model;

import java.util.ArrayList;

public class RegistroEconomico {
    //Atributos
    private int idRegistroEconomico;
    private String fechaRegistro;
    private Double ingresoDiario;
    private Double gastosDiarios;
    ArrayList<Producto> Productos = new ArrayList<>();

    //Constructor
    public RegistroEconomico(String fechaRegistro, Double ingresoDiario, Double gastosDiarios) {
        this.fechaRegistro = fechaRegistro;
        this.ingresoDiario = ingresoDiario;
        this.gastosDiarios = gastosDiarios;
    }

    //Getters
    public int getIdRegistroEconomico() { return this.idRegistroEconomico; }
    public String getFechaRegistro() { return this.fechaRegistro; }
    public Double getIngresoDiario() { return this.ingresoDiario; }
    public Double getGastosDiarios() { return this.gastosDiarios; }
    public ArrayList<Producto> getProductos() { return this.Productos; }

    //Setters
    public void setIdRegistroEconomico(int idRegistroEconomico) { this.idRegistroEconomico = idRegistroEconomico; }
    public void setFechaRegistro(String fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    public void setIngresoDiario(Double ingresoDiario) { this.ingresoDiario = ingresoDiario; }
    public void setGastosDiarios(Double gastosDiarios) { this.gastosDiarios = gastosDiarios; }
    public void setProductos(ArrayList<Producto> productos) { this.Productos = productos; }
    public void addProducto(Producto producto) { this.Productos.add(producto); }
    public void removeProducto(Producto producto) { this.Productos.remove(producto); }
    public void clearProductos() { this.Productos.clear(); }

    //tostring
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RegistroEconomico{");
        sb.append("idRegistroEconomico=").append(idRegistroEconomico);
        sb.append(", fechaRegistro=").append(fechaRegistro);
        sb.append(", ingresoDiario=").append(ingresoDiario);
        sb.append(", gastosDiarios=").append(gastosDiarios);
        sb.append(", Productos=").append(Productos);
        sb.append('}');
        return sb.toString();
    }

}
