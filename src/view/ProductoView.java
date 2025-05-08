package view;

import java.util.Locale;
import java.util.Scanner;
import model.Producto;
// import dao.ProductoDao;

public class ProductoView {
    
    //Usando Locale.US para ingresar decimales con . envés de , así teniendo consistencia en el programa
    private final Scanner sc = new Scanner(System.in).useLocale(Locale.US);
    // private final ProductoDao dao = new ProductoDao();

    public void gestor() {
        String opcion;

        do {
            System.out.println(""); //Añadir separación de línea
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
                case "3", "eliminar" -> {/* eliminar(); */}
                case "4", "mostrar" -> {/* mostrar(); */}
                case "0", "regresar" -> { System.out.println("Regresando al menú anterior"); }
                default -> {
                    System.out.println("No se reconoció esa opción, por favor ingresar el número.");
                }
            }
        } while (!opcion.equals("0"));
    }

    public void agregar() {
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
        System.out.print("Ingresar el precio");
        double precio = sc.nextDouble();
        
        Producto producto = new Producto(tipo, nombre, marca, talla, precio);
        
        //TODO: dao para insertar producto
        /* if (dao.insertar(producto)) {
            System.out.println("Producto agregado exitosamente");
        } else {
            System.out.println("No se pudo agregar el cliente");
        } */
    }

    public void modificar() {
        String opcion;

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
    //TODO: Terminar el view según cómo lo hagan los demás
}
