import dao.ConexionDB;
import java.sql.Connection;
import java.util.Scanner;
import model.Cliente;
import model.Empleado;
import view.VentaView;
import view.ClienteView;
import view.EmpleadoView;
public class App {
    public static void main(String[] args) throws Exception {
        Connection conexion = ConexionDB.conectar();
        ClienteView cliente = new ClienteView();
        VentaView venta = new VentaView();
        
        //Establecer dependencias
        cliente.setVentaView(venta);
        venta.setClienteView(cliente);

        EmpleadoView empleado = new EmpleadoView();
        Scanner sc = new Scanner(System.in);

        String usuario = "tienda";
        String passwd = "GestorTiendaRopa8743";

        int opcion;
        int subOpcion;
        if (conexion != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        } else {
            System.out.println("Error al conectar a la base de datos.");
        }
        
        do {
            System.out.println("Bienvenido a nombreTienda");
            System.out.println("--------------------------");
            System.out.println("1. Iniciar sesion como administrador");
            System.out.println("2. Iniciar sesion como cliente");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> {
                    System.out.println("introduce el usuario de administrador: ");
                    String usuarioEntrada = sc.nextLine();
                    System.out.println("introduce la contraseña de administrador: ");
                    String passwdEntrada = sc.nextLine();

                    if (usuarioEntrada.equals(usuario) && passwdEntrada.equals(passwd)) {
                        System.out.println("Hola admin (º ͜ʖº)/ ");
                    }
                }
                case 2 -> {
                    System.out.println("Bienvenido a nombreTienda como cliente");
                    System.out.println("--------------------------");
                    System.out.println("1. Iniciar sesion");
                    System.out.println("2. Registrarse");
                    System.out.println("3. Salir");
                    System.out.print("Seleccione una opción: ");
                    subOpcion = sc.nextInt();
                    sc.nextLine(); // Limpiar el buffer

                    switch (subOpcion) {
                        case 1 -> cliente.iniciarSesionCliente();
                        case 2 -> cliente.registrarCliente();
                        case 3 -> System.out.println("Saliendo...");
                        default -> System.out.println("Opción no válida. Intente de nuevo.");
                    }
                }
                case 3 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (opcion != 3);
    }
}
