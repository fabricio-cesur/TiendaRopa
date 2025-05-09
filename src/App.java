import dao.ConexionDB;
import java.sql.Connection;
import java.util.Scanner;
import utils.EnvLoader;
import view.PedidoView;
import view.UserView;

public class App {
    public static void main(String[] args) throws Exception {
        // Cargar las variables de entorno desde el archivo .env
        String usuario = EnvLoader.get("APP_USER");
        String passwd = EnvLoader.get("APP_PASSWORD");

        Connection conexion = ConexionDB.conectar();
        PedidoView pedido = new PedidoView();
        UserView user = new UserView();

        // Establecer dependencias
        user.setPedidoView(pedido);
        pedido.setUserView(user);

        Scanner sc = new Scanner(System.in);

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
                case 1 -> user.iniciarSesion();
                case 2 -> user.registrarUser();
                case 3 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (opcion != 3);
    }
}
