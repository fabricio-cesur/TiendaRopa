import dao.ConexionDB;
import dao.UserDAO;
import java.awt.event.AdjustmentListener;
import java.sql.Connection;
import java.util.Scanner;
import view.VentaView;
import view.ClienteView;
import view.UserView;
public class App {
    public static void main(String[] args) throws Exception {
        Connection conexion = ConexionDB.conectar();
        ClienteView clienteView = new ClienteView();
        VentaView venta = new VentaView();
        UserView userView = new UserView();
        UserDAO userDAO = new UserDAO();
        
        //Establecer dependencias
        clienteView.setVentaView(venta);
        venta.setClienteView(clienteView);

        Scanner sc = new Scanner(System.in);

        String usuario = "tienda";
        String passwd = "GestorTiendaRopa8743";

        int opcion;
        if (conexion != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        } else {
            System.out.println("Error al conectar a la base de datos.");
        }
        
        do {
            System.out.println("Bienvenido a nombreTienda");
            System.out.println("--------------------------");
            System.out.println("1. Iniciar sesion");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> userView.iniciarSesion();
                case 2 -> userView.registrarUser();
                case 3 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (opcion != 3);
    }
}
