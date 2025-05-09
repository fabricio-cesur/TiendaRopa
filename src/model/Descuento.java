package model;

import java.time.LocalDate;

public class Descuento {
    
    //Atributos
    private int idDescuento; // Id del descuento
    private double porcentajeDescuento; // % descuento
    private String nombre; // Nombre del descuento
    private String descripcion; // Descripción del descuento
    private LocalDate fechaInicio; // Fecha de inicio del descuento
    private LocalDate fechaFin; // Fecha de fin de descuento
    private boolean activo; // Nuevo atributo

    public Descuento(String nombre, String descripcion, double porcentajeDescuento, LocalDate fechaInicio, LocalDate fechaFin, boolean activo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.porcentajeDescuento = porcentajeDescuento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.activo = activo;
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Descuento{" +
                "idDescuento=" + idDescuento +
                ", porcentajeDescuento=" + porcentajeDescuento +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", activo=" + activo +
                '}';
    }
}