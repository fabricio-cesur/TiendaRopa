package view;

import dao.ProductoDAO;
import java.util.ArrayList;
import java.util.Scanner;
import model.Banca;
import model.Producto;
import model.User;
import utils.ConsoleColors;

public class ProductoView {
    Scanner sc = new Scanner(System.in);
    ProductoDAO productoDAO = new ProductoDAO();
    private Banca banca = new Banca(); // Instancia de la banca

    public void gestionarInventario() {
        int opcion;
        do { 
            System.out.println(ConsoleColors.INFO + "Gestión de inventario" + ConsoleColors.RESET);
            System.out.println("1. Hacer restock de productos");
            System.out.println("2. Modificar producto");
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
                case 5 -> System.out.println(ConsoleColors.INFO + "Volviendo al menú principal..." + ConsoleColors.RESET);
                default -> System.out.println(ConsoleColors.ERROR + "Opción no válida. Intente de nuevo." + ConsoleColors.RESET);
            }
        } while (opcion != 5);
    }

    private void restockProductos() {
        System.out.print("Ingrese el ID del producto a restockear: ");
        int idProducto = sc.nextInt();
        System.out.print("Ingrese la cantidad a restockear: ");
        int cantidad = sc.nextInt();

        Producto producto = productoDAO.getProductoId(idProducto);
        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        double precioOriginal = producto.getPrecio();
        double precioConDescuento = precioOriginal * 0.8; // 20% de descuento para el administrador
        double costoTotal = precioConDescuento * cantidad;

        if (banca.getBalance() >= costoTotal) {
            productoDAO.modificarStockProducto(cantidad, idProducto);
            banca.subtractBalance(costoTotal);
            System.out.println(ConsoleColors.SUCCESS + "Producto restockeado con éxito. Costo total: " + costoTotal + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.ERROR + "Fondos insuficientes en la banca para realizar el restock." + ConsoleColors.RESET);
        }
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
        System.out.println(ConsoleColors.INFO + "Lista de productos:" + ConsoleColors.RESET);
        for (Producto producto : productos) {
            System.out.println(producto.toString());
            verificarStock(producto); // Verificar el stock de cada producto
        }
    }

    private void verificarStock(Producto producto) {
        if (producto.getStock() < 5) {
            System.out.println(ConsoleColors.WARNING + "Advertencia: El stock del producto '" + producto.getNombre() + "' es bajo (" + producto.getStock() + "). Reabasteciendo automáticamente..." + ConsoleColors.RESET);
            productoDAO.modificarStockProducto(40, producto.getIdProducto());
            System.out.println(ConsoleColors.SUCCESS + "Se añadieron 40 unidades al stock del producto '" + producto.getNombre() + "'." + ConsoleColors.RESET);
        }
    }

    public Producto getIdProducto() {
        System.out.println("Introduzca el ID del producto: ");
        int idProducto = sc.nextInt();
        return productoDAO.getProductoId(idProducto);
    }

    public void verProductos() {
        int opcion;
        do { 
            System.out.println("Productos disponibles");
            System.out.println("1. Ver productos por categoría");
            System.out.println("2. Ver productos por marca");
            System.out.println("3. Ver productos por precio");
            System.out.println("4. Ver productos por talla");
            System.out.println("5. Ver productos por color");
            System.out.println("6. Volver al menú principal");
            System.out.print("Selecciona una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> listarProductosCategoria();
                case 2 -> listarProductosMarca();
                case 3 -> listarProductosPrecio();
                case 4 -> listarProductosTalla();
                case 5 -> listarProductosColor();
                case 6 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 5);
    }

    public void listarProductosCategoria() {
        System.out.println("Introduzca la categoría del producto: ");
        String categoria = sc.nextLine().trim(); // Elimina espacios en blanco al inicio y al final

        if (categoria.isEmpty()) {
            System.out.println("La categoría no puede estar vacía. Intente de nuevo.");
            return;
        }

        ArrayList<Producto> productos = productoDAO.listarProductosCategoria(categoria);

        if (productos.isEmpty()) {
            System.out.println("No se encontraron productos en la categoría: " + categoria);
        } else {
            System.out.println("Lista de productos de la categoría '" + categoria + "':");
            for (Producto producto : productos) {
                System.out.println(producto.toString());
            }
        }
    }

    public void listarProductosMarca() {
        System.out.println("Introduzca la marca del producto: ");
        String marca = sc.nextLine().trim();

        if (marca.isEmpty()) {
            System.out.println("La marca no puede estar vacía. Intente de nuevo.");
            return;
        }

        ArrayList<Producto> productos = productoDAO.listarProductosMarca(marca);

        if (productos.isEmpty()) {
            System.out.println("No se encontraron productos de la marca: " + marca);
        } else {
            System.out.println("Lista de productos de la marca '" + marca + "':");
            for (Producto producto : productos) {
                System.out.println(producto.toString());
            }
        }
    }

    public void listarProductosPrecio() {
        System.out.print("Introduzca el precio mínimo: ");
        Double precioMin = sc.nextDouble();
        System.out.print("Introduzca el precio máximo: ");
        Double precioMax = sc.nextDouble();
        sc.nextLine(); // Limpiar el buffer

        ArrayList<Producto> productos = productoDAO.listarProductosPrecio(precioMin, precioMax);

        if (productos.isEmpty()) {
            System.out.println("No se encontraron productos en el rango de precios: " + precioMin + " - " + precioMax);
        } else {
            System.out.println("Lista de productos en el rango de precios " + precioMin + " - " + precioMax + ":");
            for (Producto producto : productos) {
                System.out.println(producto.toString());
            }
        }
    }

    public void listarProductosTalla() {
        System.out.println("Introduzca la talla del producto: ");
        String talla = sc.nextLine().trim();

        if (talla.isEmpty()) {
            System.out.println("La talla no puede estar vacía. Intente de nuevo.");
            return;
        }

        ArrayList<Producto> productos = productoDAO.listarProductosTalla(talla);

        if (productos.isEmpty()) {
            System.out.println("No se encontraron productos de la talla: " + talla);
        } else {
            System.out.println("Lista de productos de la talla '" + talla + "':");
            for (Producto producto : productos) {
                System.out.println(producto.toString());
            }
        }
    }

    public void listarProductosColor() {
        System.out.println("Introduzca el color del producto: ");
        String color = sc.nextLine().trim();

        if (color.isEmpty()) {
            System.out.println("El color no puede estar vacío. Intente de nuevo.");
            return;
        }

        ArrayList<Producto> productos = productoDAO.listarProductosColor(color);

        if (productos.isEmpty()) {
            System.out.println("No se encontraron productos del color: " + color);
        } else {
            System.out.println("Lista de productos del color '" + color + "':");
            for (Producto producto : productos) {
                System.out.println(producto.toString());
            }
        }
    }
    
    public void venderProducto(User user) {
        System.out.print("Ingrese el ID del producto a comprar: ");
        int idProducto = sc.nextInt();
        System.out.print("Ingrese la cantidad a comprar: ");
        int cantidad = sc.nextInt();

        Producto producto = productoDAO.getProductoId(idProducto);
        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        if (producto.getStock() < cantidad) {
            System.out.println(ConsoleColors.ERROR + "Stock insuficiente para realizar la venta." + ConsoleColors.RESET);
            return;
        }

        double costoTotal = producto.getPrecio() * cantidad;

        if (user.getBalance() >= costoTotal) {
            user.subtractBalance(costoTotal);
            productoDAO.modificarStockProducto(-cantidad, idProducto);
            banca.addBalance(costoTotal);
            System.out.println(ConsoleColors.SUCCESS + "Venta realizada con éxito. Costo total: " + costoTotal + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.ERROR + "Fondos insuficientes para realizar la compra." + ConsoleColors.RESET);
        }
    }
}
