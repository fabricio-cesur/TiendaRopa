package view;

import dao.ProductoDAO;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import model.Producto;

public class ProductoVIEW {
    
    //Usando Locale.US para ingresar decimales con . envés de , así teniendo consistencia en el programa
    private final Scanner sc = new Scanner(System.in).useLocale(Locale.US);
    private final ProductoDAO dao = new ProductoDAO();

    public void gestor() { //gestor envés de menu para diferenciar a la vista de admin con la de usuario
        String opcion;

        do {
            System.out.println("");
            System.out.println("+---Menu Producto---+");
            System.out.println("1. Agregar");
            System.out.println("2. Modificar");
            System.out.println("3. Eliminar");
            System.out.println("4. Mostrar");
            System.out.println("0. Regresar");
            System.out.print(">>> ");
            opcion = sc.nextLine().toLowerCase();

            switch (opcion) {
                case "1", "agregar" -> { agregar(); }
                case "2", "modificar" -> { modificar(); }
                case "3", "eliminar" -> { eliminar(); }
                case "4", "mostrar" -> {/* mostrar(); */}
                case "0", "regresar" -> { System.out.println("Regresando al menú anterior"); }
                default -> {
                    System.out.println("No se reconoció esa opción, por favor ingresar el número.");
                }
            }
        } while (!opcion.equals("0"));
    }

    public void agregar() {
        System.out.print("");
        System.out.println("+--Agregar--+");
        //TODO: Añadir validaciones a las entradas para saber que existen y que están bien escritas
        System.out.print("Ingresar el tipo");
        String tipo = sc.nextLine();
        System.out.print("Ingresar el nombre");
        String nombre = sc.nextLine();
        System.out.print("Ingresar la marca");
        String marca = sc.nextLine();
        System.out.print("Ingresar la talla");
        String talla = sc.nextLine();
        System.out.print("Ingresar el color");
        String color = sc.nextLine();
        System.out.print("Ingresar el precio");
        double precio = sc.nextDouble();
        
        Producto producto = new Producto(tipo, nombre, marca, talla, color, precio);
        
        if (dao.insertar(producto)) {
            System.out.println("Producto agregado exitosamente");
        } else {
            System.out.println("No se pudo agregar el cliente");
        }
    }
    public void modificar() {
        String opcion;
        //TODO: Terminar el modificar al hacer el dao
        do {
            System.out.println("+--Modificar--+");
            System.out.println("1. Tipo");
            System.out.println("2. Nombre");
            System.out.println("3. Marca");
            System.out.println("4. Talla");
            System.out.println("5. Precio");
            System.out.println("0. Regresar");
            System.out.print(">>> ");
            opcion = sc.nextLine().toLowerCase();

            switch(opcion) {
                case "1", "tipo" -> {}
                case "2", "nombre" -> {}
                case "3", "marca" -> {}
                case "4", "talla" -> {}
                case "5", "precio" -> {}
                case "0", "regresar" -> {}
                default -> {}
            }

        } while (!opcion.equals("0"));
    }
    public void eliminar() {
        System.out.println("");    
        System.out.println("+--Eliminar--+");
        System.out.print("Ingrese el id del Producto a elimar: ");
        int id = sc.nextInt(); //TODO: Verificar que el producto existe
        Producto producto = dao.obtenerPorId(id);
        
        System.out.println("Está por eliminar el siguiente producto");
        System.out.println(producto);
        System.out.println("¿Está seguro? (SI) / (NO)");
        System.out.print("--> ");
        boolean bucle;
        do {
            switch (sc.next().toLowerCase()) {
                case "1", "si", "s" -> {
                    System.out.println("Eliminando...");
                    if (dao.eliminar(id)) {
                        System.out.println("Se eliminó el objeto exitosamente");
                    } else {
                        System.out.println("No se pudo eliminar el objeto");
                    }
                    bucle = false;
                }
                case "2", "no", "n" -> {
                    System.out.println("Abortando operación..");    
                    bucle = false;
                }
                default -> {
                    System.out.println("No se reconoció la opción, ingrese SI o NO");
                    bucle = true;
                }
            }
        } while (bucle);
    }
    public void mostrar() {
        System.out.println("");
        System.out.println("+--Mostrar--+");
        System.out.println("¿Qué desea mostrar?");
        System.out.println("1. Producto por su id");
        System.out.println("2. Todos los productos");
        System.out.println("0. Regresar");
        System.out.print(">>> ");
        String opcion;
        do {
            opcion = sc.nextLine().toLowerCase();

            switch (opcion) {
                case "1", "id" -> {
                    System.out.println("Ingrese el id del Producto a mostrar");
                    System.out.print("--> ");
                    Producto producto = dao.obtenerPorId(sc.nextInt());
                    System.out.println("\n" + producto);
                    return;
                }
                case "2", "todos" -> {
                    System.out.println("Productos:");
                    ArrayList<Producto> productos = dao.obtenerTodos();

                    for (Producto producto : productos) {
                        System.out.println(producto);
                    }
                    return;
                }
                case "0", "regresar" -> { System.out.println("Regresando al menú anterior"); }
                default -> {
                    System.out.println("No se reconoció esa opción");
                }
            }
        } while (!opcion.equals("0") || !opcion.equals("0"));
    }
}
