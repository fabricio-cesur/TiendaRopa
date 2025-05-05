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
    ArrayList<Producto> productos = new ArrayList<>(); //Lista de productos del pedido

    
}
