package view;

import dao.ProductoDAO;
import java.util.ArrayList;
import java.util.Scanner;
import model.Producto;

public class ProductoView {
    Scanner sc = new Scanner(System.in);
    ProductoDAO productoDAO = new ProductoDAO();

    public void gestionarInventario() {
        int opcion;
        do { 
            System.out.println("Gestión de inventario");
            System.out.println("1. Hacer restock de productos");
            System.out.println("2. Modifcar producto");
            System.out.println("3. Eliminar producto");
            System.out.println("4. Listar piezas");
            System.out.println("5. Volver al menú principal");
            System.out.print("Selecciona una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            
            switch (opcion) {
                case 1 -> restockProductos();
                case 2 -> modificarProducto();
                case 3 -> eliminarProducto();
                case 4 -> listarProductos();
                
                case 5 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 5);
        
    }

    private void restockProductos() {
        System.out.print("Ingrese el ID del producto a restockear: ");
        int idProducto = sc.nextInt();
        System.out.print("Ingrese la cantidad a restockear: ");
        int cantidad = sc.nextInt();
        productoDAO.modificarStockProducto(cantidad, idProducto); 
        System.out.println("Producto restockeado con éxito.");
    }

    public void modificarProducto() {
        System.out.println("Modificar producto");
        Producto producto = this.getIdProducto();
        int idProducto = producto.getIdProducto();
        int opcion;
        do { 
            System.out.println("1. Modificar nombre");
            System.out.println("2. Modificar descripción");
            System.out.println("3. Modificar precio");
            System.out.println("4. Modificar stock");
            System.out.println("5. Modificar talla");
            System.out.println("6. Modificar color");
            System.out.println("7. Modificar marca");
            System.out.println("8. Modificar categoría");
            System.out.println("9. Volver al menú de gestión de inventario");
            System.out.print("Selecciona una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese el nuevo nombre: ");
                    String nombre = sc.nextLine();
                    sc.nextLine(); // Limpiar el buffer
                    productoDAO.modificarNombreProducto(nombre, idProducto);
                    System.out.println("Nombre modificado con éxito.");
                }
                case 2 -> {
                    System.out.print("Ingrese la nueva descripción: ");
                    String descripcion = sc.nextLine();
                    sc.nextLine(); // Limpiar el buffer
                    productoDAO.modificarDescripcionProducto(descripcion, idProducto);
                    System.out.println("Descripción modificada con éxito.");
                }
                case 3 -> {
                    System.out.print("Ingrese el nuevo precio: ");
                    double precio = sc.nextDouble();
                    sc.nextLine(); // Limpiar el buffer
                    productoDAO.modificarPrecioProducto(precio, idProducto);
                    System.out.println("Precio modificado con éxito.");
                }
                case 4 -> {
                    System.out.print("Ingrese el nuevo stock: ");
                    int stock = sc.nextInt();
                    sc.nextLine(); // Limpiar el buffer
                    productoDAO.modificarStockProducto(stock, idProducto);
                    System.out.println("Stock modificado con éxito.");
                }
                case 5 -> {
                    System.out.print("Ingrese la nueva talla: ");
                    String talla = sc.nextLine();
                    sc.nextLine(); // Limpiar el buffer
                    productoDAO.modificarTallaProducto(talla, idProducto);
                    System.out.println("Talla modificada con éxito.");
                }
                case 6 -> {
                    System.out.print("Ingrese el nuevo color: ");
                    String color = sc.nextLine();
                    sc.nextLine(); // Limpiar el buffer
                    productoDAO.modificarColorProducto(color, idProducto);
                    System.out.println("Color modificado con éxito.");
                }
                case 7 -> {
                    System.out.print("Ingrese la nueva marca: ");
                    String marca = sc.nextLine();
                    sc.nextLine(); // Limpiar el buffer
                    productoDAO.modificarMarcaProducto(marca, idProducto);
                    System.out.println("Marca modificada con éxito.");
                }
                case 8 -> {
                    System.out.print("Ingrese la nueva categoría: ");
                    String categoria = sc.nextLine();
                    sc.nextLine(); // Limpiar el buffer
                    productoDAO.modificarCategoriaProducto(categoria, idProducto);
                    System.out.println("Categoría modificada con éxito.");
                }
                case 9 -> System.out.println("Volviendo al menú de gestión de inventario...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 9);

    }

    public void eliminarProducto() {
        Producto producto = this.getIdProducto();
        productoDAO.eliminarProducto(producto);
        System.out.println("Producto eliminado con éxito.");
    }

    public void listarProductos() {
        ArrayList<Producto> productos = productoDAO.listarProductos();
        System.out.println("Lista de productos:");
        for (Producto producto : productos) {
            System.out.println(producto.toString());
        }
        
        
    }

    public Producto getIdProducto() {
        System.out.println("Introduzca el ID del producto: ");
        int idProducto = sc.nextInt();
        return productoDAO.getProductoId(idProducto);
    }
}
