package model;

import java.util.Date;

public class Descuento {
    //Atributos
    private int idDescuento; // Id del descuento
    private double porcentajeDescuento; // % descuento
    private String nombre; // Nombre del descuento
    private String descripcion; // Descripción del descuento
    private Date fechaInicio; // Fecha de inicio del descuento
    private Date fechaFin; // Fecha de fin de descuento

    public Descuento(double porcentajeDescuento, String nombre, String descripcion, Date fechaInicio, Date fechaFin) {
        this.porcentajeDescuento = porcentajeDescuento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getIdDescuento() {
        return idDescuento;
    }

    public void setIdDescuento(int idDescuento) {
        this.idDescuento = idDescuento;
    }
    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }



    @Override
    public String toString() {
        return "Descuento{" +
                "idDescuento=" + idDescuento +
                ", porcentajeDescuento='" + porcentajeDescuento + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", fechaFin=" + fechaFin + '\'' +
                '}';
    }

}
