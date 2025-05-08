package view;

import java.util.Scanner;
import javax.sound.sampled.SourceDataLine;
public class PedidoView {
    private Scanner sc = new Scanner(System.in);

    public void gestionPedidos() {
        int opcion;
        do { 
            System.out.println("Pedidos");
            System.out.println("------------------");
            System.out.println("1. Ver pedidos realizados");
            System.out.println("2. Realizar un nuevo pedido");
            System.out.println("3. Cancelar un pedido");
            System.out.println("3. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> verPedidosRealizados();
                case 2 -> realizarNuevoPedido();
                case 3 -> cancelarPedido();
                case 4 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 4);
        
    }
}
