package view;

import dao.DescuentoDAO;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import model.Descuento;

public class DescuentoView {
    private final DescuentoDAO descuentoDAO = new DescuentoDAO();
    private final Scanner sc = new Scanner(System.in);

    public void gestionarDescuentosUsuario() {
        int opcion;
        do {
            System.out.println("Gestión de Descuentos (Usuario)");
            System.out.println("1. Ver descuentos disponibles");
            System.out.println("2. Buscar descuento por nombre");
            System.out.println("3. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> listarDescuentosActivos();
                case 2 -> buscarDescuento();
                case 3 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 3);
    }

    public void gestionarDescuentosAdmin() {
        int opcion;
        do {
            System.out.println("Gestión de Descuentos (Administrador)");
            System.out.println("1. Ver descuentos disponibles");
            System.out.println("2. Buscar descuento por nombre");
            System.out.println("3. Agregar descuento");
            System.out.println("4. Modificar descuento");
            System.out.println("5. Activar/Desactivar descuento");
            System.out.println("6. Eliminar descuento");
            System.out.println("7. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> listarDescuentosActivos();
                case 2 -> buscarDescuento();
                case 3 -> agregarDescuento();
                case 4 -> actualizarDescuento();
                case 5 -> activarDescuento();
                case 6 -> eliminarDescuento();
                case 7 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 7);
    }

    private void listarDescuentosActivos() {
        List<Descuento> descuentos = descuentoDAO.obtenerActivos();
        if (descuentos.isEmpty()) {
            System.out.println("No hay descuentos disponibles.");
        } else {
            System.out.println("Descuentos disponibles:");
            for (Descuento descuento : descuentos) {
                System.out.println(descuento);
            }
        }
    }

    private void buscarDescuento() {
        System.out.print("Introduce el nombre del descuento: ");
        String nombre = sc.nextLine();
        Descuento descuento = descuentoDAO.buscarPorNombre(nombre);
        if (descuento == null) {
            System.out.println("No se encontró ningún descuento con ese nombre.");
        } else {
            System.out.println("Descuento encontrado:");
            System.out.println(descuento);
        }
    }

    private void agregarDescuento() {
        try {
            System.out.println("Introduce los datos del nuevo descuento:");

            System.out.print("Nombre: ");
            String nombre = sc.nextLine();

            System.out.print("Descripción: ");
            String descripcion = sc.nextLine();

            System.out.print("Porcentaje de descuento: ");
            double porcentajeDescuento = sc.nextDouble();
            sc.nextLine(); // Limpiar el buffer

            System.out.print("Fecha de inicio (YYYY-MM-DD): ");
            String fechaInicioStr = sc.nextLine();
            LocalDate fechaInicio = LocalDate.parse(fechaInicioStr);

            System.out.print("Fecha de fin (YYYY-MM-DD): ");
            String fechaFinStr = sc.nextLine();
            LocalDate fechaFin = LocalDate.parse(fechaFinStr);

            System.out.print("¿El descuento está activo? (true/false): ");
            boolean activo = sc.nextBoolean();

            Descuento nuevoDescuento = new Descuento(nombre, descripcion, porcentajeDescuento, fechaInicio, fechaFin, activo);
            descuentoDAO.insertar(nuevoDescuento);
            System.out.println("Descuento agregado correctamente.");
        } catch (DateTimeParseException e) {
            System.out.println("Error: Formato de fecha incorrecto. Intente de nuevo.");
        } catch (Exception e) {
            System.out.println("Error al agregar el descuento: " + e.getMessage());
        }
    }

    private void actualizarDescuento() {
        System.out.print("Introduce el nombre del descuento que deseas actualizar: ");
        String nombre = sc.nextLine();
        Descuento descuento = descuentoDAO.buscarPorNombre(nombre);

        if (descuento == null) {
            System.out.println("No se encontró ningún descuento con ese nombre.");
        } else {
            try {
                System.out.print("Nueva descripción (deja en blanco para mantener): ");
                String descripcion = sc.nextLine();
                if (!descripcion.isEmpty()) {
                    descuento.setDescripcion(descripcion);
                }

                System.out.print("Nuevo porcentaje de descuento (deja en blanco para mantener): ");
                String porcentajeStr = sc.nextLine();
                if (!porcentajeStr.isEmpty()) {
                    descuento.setPorcentajeDescuento(Double.parseDouble(porcentajeStr));
                }

                System.out.print("Nueva fecha de inicio (YYYY-MM-DD, deja en blanco para mantener): ");
                String fechaInicioStr = sc.nextLine();
                if (!fechaInicioStr.isEmpty()) {
                    descuento.setFechaInicio(LocalDate.parse(fechaInicioStr));
                }

                System.out.print("Nueva fecha de fin (YYYY-MM-DD, deja en blanco para mantener): ");
                String fechaFinStr = sc.nextLine();
                if (!fechaFinStr.isEmpty()) {
                    descuento.setFechaFin(LocalDate.parse(fechaFinStr));
                }

                System.out.print("¿El descuento está activo? (true/false, deja en blanco para mantener): ");
                String activoStr = sc.nextLine();
                if (!activoStr.isEmpty()) {
                    descuento.setActivo(Boolean.parseBoolean(activoStr));
                }

                descuentoDAO.actualizar(descuento);
                System.out.println("Descuento actualizado correctamente.");
            } catch (DateTimeParseException e) {
                System.out.println("Error: Formato de fecha incorrecto. Intente de nuevo.");
            } catch (Exception e) {
                System.out.println("Error al actualizar el descuento: " + e.getMessage());
            }
        }
    }

    private void activarDescuento() {
        System.out.print("Introduce el ID del descuento a activar/desactivar: ");
        int idDescuento = sc.nextInt();
        System.out.print("¿Activar (true) o desactivar (false)? ");
        boolean activo = sc.nextBoolean();
        descuentoDAO.activarDescuento(idDescuento, activo);
        System.out.println("Estado del descuento actualizado.");
    }

    private void eliminarDescuento() {
        System.out.print("Introduce el nombre del descuento a eliminar: ");
        String nombre = sc.nextLine();
        descuentoDAO.eliminar(nombre);
        System.out.println("Descuento eliminado.");
    }
}