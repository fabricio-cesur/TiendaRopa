package view;

import java.util.List;
import java.util.Scanner;
import model.Producto;

public class ProductoView {
    private final Scanner scanner;

    public ProductoView() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarProducto(Producto producto) {
        if (producto != null) {
            System.out.println("\n--- DETALLES DEL PRODUCTO ---");
            System.out.println(producto.toString());
            System.out.println("-----------------------------\n");
        } else {
            System.out.println("\nProducto no encontrado.\n");
        }
    }

    public void mostrarTodosProductos(List<Producto> productos) {
        if (productos.isEmpty()) {
            System.out.println("\nNo hay productos disponibles.\n");
        } else {
            System.out.println("\n--- LISTA DE PRODUCTOS ---");
            for (Producto p : productos) {
                System.out.println(p.toString());
                System.out.println("-------------------------");
            }
        }
    }

    public Producto obtenerDatosNuevoProducto() {
        System.out.println("\n--- NUEVO PRODUCTO ---");
        
        System.out.print("Tipo (ej. Camiseta, Pantalón, etc.): ");
        String tipo = scanner.nextLine();
        
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        
        System.out.print("Talla: ");
        String talla = scanner.nextLine();
        
        System.out.print("Precio: ");
        double precio = Double.parseDouble(scanner.nextLine());
        
        return new Producto(tipo, nombre, marca, talla, precio);
    }

    public Producto obtenerDatosActualizacionProducto(Producto productoExistente) {
        System.out.println("\n--- ACTUALIZAR PRODUCTO ---");
        System.out.println("Deja en blanco los campos que no quieras modificar.");
        
        System.out.print("Tipo [" + productoExistente.getTipo() + "]: ");
        String tipo = scanner.nextLine();
        if (!tipo.isEmpty()) {
            productoExistente.setTipo(tipo);
        }
        
        System.out.print("Nombre [" + productoExistente.getNombre() + "]: ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) {
            productoExistente.setNombre(nombre);
        }
        
        System.out.print("Marca [" + productoExistente.getMarca() + "]: ");
        String marca = scanner.nextLine();
        if (!marca.isEmpty()) {
            productoExistente.setMarca(marca);
        }
        
        System.out.print("Talla [" + productoExistente.getTalla() + "]: ");
        String talla = scanner.nextLine();
        if (!talla.isEmpty()) {
            productoExistente.setTalla(talla);
        }
        
        System.out.print("Precio [" + productoExistente.getPrecio() + "]: ");
        String precioStr = scanner.nextLine();
        if (!precioStr.isEmpty()) {
            productoExistente.setPrecio(Double.parseDouble(precioStr));
        }
        
        return productoExistente;
    }

    public String obtenerIdProducto() {
        System.out.print("\nIngrese el ID del producto: ");
        return scanner.nextLine();
    }

    public String obtenerBusquedaNombre() {
        System.out.print("\nIngrese nombre o parte del nombre a buscar: ");
        return scanner.nextLine();
    }

    public String obtenerBusquedaTipo() {
        System.out.print("\nIngrese el tipo de producto a buscar: ");
        return scanner.nextLine();
    }

    public String obtenerBusquedaMarca() {
        System.out.print("\nIngrese la marca a buscar: ");
        return scanner.nextLine();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarError(String error) {
        System.err.println("Error: " + error);
    }

    public void mostrarExito(String mensaje) {
        System.out.println("Éxito: " + mensaje);
    }
}