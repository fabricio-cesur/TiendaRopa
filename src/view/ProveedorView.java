package view;

import java.util.List;
import java.util.Scanner;

public class ProveedorView {
    private Scanner scanner;

    public ProveedorView() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Muestra el menú de opciones para proveedores
     */
    public void mostrarMenu() {
        System.out.println("\n--- GESTIÓN DE PROVEEDORES ---");
        System.out.println("1. Registrar nuevo proveedor");
        System.out.println("2. Buscar proveedor");
        System.out.println("3. Listar todos los proveedores");
        System.out.println("4. Actualizar proveedor");
        System.out.println("5. Eliminar proveedor");
        System.out.println("6. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Solicita los datos para un nuevo proveedor
     * @return Un array con los datos del proveedor
     */
    public String[] solicitarDatosProveedor() {
        String[] datos = new String[6];
        
        System.out.println("\n--- REGISTRO DE NUEVO PROVEEDOR ---");
        System.out.print("Nombre: ");
        datos[0] = scanner.nextLine();
        
        System.out.print("Dirección: ");
        datos[1] = scanner.nextLine();
        
        System.out.print("Número de teléfono: ");
        datos[2] = scanner.nextLine();
        
        System.out.print("Email: ");
        datos[3] = scanner.nextLine();
        
        System.out.print("Productos que suministra (separados por comas): ");
        datos[4] = scanner.nextLine();
        
        System.out.print("Número de cuenta bancaria: ");
        datos[5] = scanner.nextLine();
        
        return datos;
    }

    /**
     * Solicita el nombre de un proveedor para búsqueda/eliminación
     * @return El nombre del proveedor
     */
    public String solicitarNombreProveedor() {
        System.out.print("\nIngrese el nombre del proveedor: ");
        return scanner.nextLine();
    }

    /**
     * Muestra los detalles de un proveedor
     * @param proveedor El objeto Proveedor a mostrar
     */
    public void mostrarProveedor(ProveedorView proveedor) {
        if (proveedor != null) {
            System.out.println("\n--- DETALLES DEL PROVEEDOR ---");
            System.out.println("Nombre: " + proveedor.getNombre());
            System.out.println("Dirección: " + proveedor.getDireccion());
            System.out.println("Teléfono: " + proveedor.getNumero());
            System.out.println("Email: " + proveedor.getEmail());
            System.out.println("Productos suministrados: " + proveedor.getProductos());
            System.out.println("Cuenta bancaria: " + proveedor.getCuenta());
        } else {
            System.out.println("\nProveedor no encontrado.");
        }
    }

    /**
     * Muestra una lista de todos los proveedores
     * @param proveedores Lista de proveedores
     */
    public void mostrarListaProveedores(List<ProveedorView> proveedores) {
        System.out.println("\n--- LISTA DE PROVEEDORES ---");
        if (proveedores.isEmpty()) {
            System.out.println("No hay proveedores registrados.");
        } else {
            for (ProveedorView p : proveedores) {
                System.out.println(p.toString());
            }
        }
    }

    /**
     * Muestra un mensaje de confirmación
     * @param mensaje El mensaje a mostrar
     */
    public void mostrarMensaje(String mensaje) {
        System.out.println("\n" + mensaje);
    }

    /**
     * Muestra un mensaje de error
     * @param mensaje El mensaje de error
     */
    public void mostrarError(String mensaje) {
        System.err.println("\nERROR: " + mensaje);
    }

    /**
     * Solicita confirmación para una operación
     * @param mensaje El mensaje de confirmación
     * @return true si el usuario confirma
     */
    public boolean solicitarConfirmacion(String mensaje) {
        System.out.print("\n" + mensaje + " (s/n): ");
        String respuesta = scanner.nextLine().toLowerCase();
        return respuesta.equals("s") || respuesta.equals("si");
    }
}