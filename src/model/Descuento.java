package model;

public class Descuento {
    //Atributos
    private int idDescuento; //Id del descuento 
    private String descripcion; //Descripción del descuento
    private boolean porcentaje_fija; //Si el descuento es porcentaje o cantidad fija
    private Double porcentaje; //Porcentaje de descuento
    private Double cantidad_fija;
    
    //Constructor
    public Descuento(String descripcion, boolean porcentaje_fija, Double porcentaje, Double cantidad_fija) {
        this.descripcion = descripcion;
        this.porcentaje_fija = porcentaje_fija;
        this.porcentaje = porcentaje;
        this.cantidad_fija = cantidad_fija;
    }
    
    //Getters
    public int getIdDescuento() { return idDescuento; }
    public String getDescripcion() { return descripcion; }
    public boolean isPorcentaje_fija() { return porcentaje_fija; }
    public Double getPorcentaje() { return porcentaje; }
    public Double getCantidad_fija() { return cantidad_fija; }

    //Setters
    public void setIdDescuento(int idDescuento) { this.idDescuento = idDescuento; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setPorcentaje_fija(boolean porcentaje_fija) { this.porcentaje_fija = porcentaje_fija; }
    public void setPorcentaje(Double porcentaje) { this.porcentaje = porcentaje; }
    public void setCantidad_fija(Double cantidad_fija) { this.cantidad_fija = cantidad_fija; }
    
    //tostring

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Descuento{");
        sb.append("idDescuento=").append(idDescuento);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", porcentaje_fija=").append(porcentaje_fija);
        sb.append(", porcentaje=").append(porcentaje);
        sb.append(", cantidad_fija=").append(cantidad_fija);
        sb.append('}');
        return sb.toString();
    }

}
