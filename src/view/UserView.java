package view;

import dao.UserDAO;
import dao.ClienteDAO;
import java.awt.desktop.UserSessionEvent;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import model.User;
import model.Cliente;

public class UserView {

    private Scanner sc = new Scanner(System.in);
    private ClienteDAO clienteDAO = new ClienteDAO();
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private UserDAO userDAO = new UserDAO();
    private ArrayList<User> users = new ArrayList<>();
    String usuario = "tienda";
    String passwd = "GestorTiendaRopa8743";
    String usuarioEntrada;
    String passwdEntrada;

    public UserView() {}

    //Metodo para establecer depencia de
    public void gestionUser() {
        int opcion;

    }

    public void iniciarSesion() {
        Cliente cliente = this.getClienteId();
        System.out.println("Iniciar sesion");
        System.out.println("------------------");
        System.out.println("Introduce el usuario: ");
        usuarioEntrada = sc.nextLine();
        System.out.println("Introduce la contraseña: ");
        passwdEntrada = sc.nextLine();

        if (usuarioEntrada.equals(usuario) && passwdEntrada.equals(passwd)) {
            System.out.println("Hola admin (º ͜ʖº)/ ");
        }
        if (usuarioEntrada.equals(cliente.getUsername()) && passwdEntrada.equals(cliente.getPassword())) {
            System.out.println("Hola " + cliente.getNombre() + " (º ͜ʖº)/ ");
        } else {
            System.out.println("Usuario o contraseña incorrectos");
        }

    }

    public void registrarUser() {
        System.out.println("Registrase");
        System.out.println("------------------");
        System.out.println("Introduce el nombre de usuario: ");
        String username = sc.nextLine();
        System.out.println("Introduce la contraseña: ");
        String password = sc.nextLine();
        System.out.println("Datos personales");
        System.out.println("------------------");
        System.out.println("Introduce su nombre");
        String nombre = sc.nextLine();
        System.out.println("Introduce su apellido");
        String apellido = sc.nextLine();
        System.out.println("Introduce su email");
        String email = sc.nextLine();
        System.out.println("Introduce su nº de telefono");
        String telefono = sc.nextLine();
        User user = new User(username, password, false);
        userDAO.insertarUser(user);
        users.add(user);
        Cliente cliente = new Cliente(nombre, apellido, email, telefono);
        clienteDAO.insertarCliente(cliente);
        clientes.add(cliente);
    }

    public User getUsername() {
        String username = usuarioEntrada;
        User user = UserDAO.getUserUsername(username);
        return user;

    }

    public User getPassword() {
        String password = passwdEntrada;
        User user = UserDAO.getUserPassword(password);
        return user; 
    }

}
