package view;

import java.util.List;
import java.util.Scanner;

public class ProveedorView {
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("-------------------------------------");
        System.out.println("       GESTION DE PROVEEDORES        ");
        System.out.println("-------------------------------------");
        System.out.println("1. Registrar nuevo proveedor");
        System.out.println("2. Buscar proveedor");
        System.out.println("3. Listar todos los proveedores");
        System.out.println("4. Actualizar proveedor");
        System.out.println("5. Eliminar proveedor");
        System.out.println("6. Volver al menu principal");
        System.out.println("-------------------------------------");
        System.out.print("Seleccione una opcion: ");
    }

    public String[] solicitarDatosProveedor() {
        String[] datos = new String[6];
        
        System.out.println("-------------------------------------");
        System.out.println("      REGISTRO DE NUEVO PROVEEDOR    ");
        System.out.println("-------------------------------------");
        
        System.out.print("Nombre: ");
        datos[0] = scanner.nextLine();
        
        System.out.print("Direccion: ");
        datos[1] = scanner.nextLine();
        
        System.out.print("Telefono: ");
        datos[2] = scanner.nextLine();
        
        System.out.print("Email: ");
        datos[3] = scanner.nextLine();
        
        System.out.print("Productos que suministra: ");
        datos[4] = scanner.nextLine();
        
        System.out.print("Cuenta bancaria: ");
        datos[5] = scanner.nextLine();
        
        System.out.println("-------------------------------------");
        return datos;
    }

    public String solicitarNombreProveedor() {
        System.out.print("Ingrese el nombre del proveedor: ");
        return scanner.nextLine();
    }

    public void mostrarProveedor(ProveedorView proveedor) {
        if (proveedor == null) {
            System.out.println("-------------------------------------");
            System.out.println("     PROVEEDOR NO ENCONTRADO         ");
            System.out.println("-------------------------------------");
            return;
        }
        
        System.out.println("-------------------------------------");
        System.out.println("       DETALLES DEL PROVEEDOR        ");
        System.out.println("-------------------------------------");
        System.out.println("Nombre: " + proveedor.getNombre());
        System.out.println("Direccion: " + proveedor.getDireccion());
        System.out.println("Telefono: " + proveedor.getTelefono());
        System.out.println("Email: " + proveedor.getEmail());
        System.out.println("Productos: " + proveedor.getProductos());
        System.out.println("Cuenta bancaria: " + proveedor.getCuentaBancaria());
        System.out.println("-------------------------------------");
    }

    public void mostrarListaProveedores(List<ProveedorView> proveedores) {
        System.out.println("-------------------------------------");
        System.out.println("       LISTA DE PROVEEDORES          ");
        System.out.println("-------------------------------------");
        
        if (proveedores.isEmpty()) {
            System.out.println("No hay proveedores registrados");
        } else {
            for (ProveedorView p : proveedores) {
                System.out.println("Nombre: " + p.getNombre() + " | Telefono: " + p.getTelefono());
            }
        }
        
        System.out.println("-------------------------------------");
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println("-------------------------------------");
        System.out.println(mensaje);
        System.out.println("-------------------------------------");
    }

    public boolean solicitarConfirmacion(String mensaje) {
        System.out.print(mensaje + " (s/n): ");
        String respuesta = scanner.nextLine().toLowerCase();
        return respuesta.equals("s") || respuesta.equals("si");
    }
}