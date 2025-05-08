package view;

import dao.DescuentoDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import model.Descuento;

public class DescuentoVIEW {    
private DescuentoDAO descuentoDAO = new DescuentoDAO();

    public void menuDescuento() {

        int opcionDescuentos;
        do { 
            System.out.println("¿Qué acción deseas realizar?");
            System.out.println("1. Ver Descuentos");
            System.out.println("2. Buscar descuentos");
            System.out.println("3. Agregar descuento");
            System.out.println("4. Modificar descuento");
            System.out.println("5. Eliminar descuento");
            System.out.println("0. Volver al menú inicial");
            opcionDescuentos = sc.nextInt();

            switch (opcionDescuentos) {
                case 1: // Ver lista de descuentos
                listarDescuentos();
                break;

                case 2: // Buscar descuentos
                buscarDescuentos();                                     
                break;
     
                case 3: // Agregar descuentos
                agregarDescuentos();
                break;
    
                case 4: // Modificar descuentos
                actualizarDescuentos();       
                break;

                case 5: // Eliminar descuentos
                eliminarDescuentos();
                break;
    
                case 0: System.out.println("Volviendo al menú inicial");
                break;

            }

        } while (opcionDescuentos != 0);
    }

    Scanner sc = new Scanner(System.in);

    private void listarDescuentos() {
        List<Descuento> lista = descuentoDAO.obtenerTodos();
        for (Descuento c : lista) {
            System.out.println(c);
        }        
    }

    private void buscarDescuentos() {

        System.out.println("Introduce el nombre del descuento que deseas buscar:");
        String nombre = sc.next();

            Descuento descuento = descuentoDAO.buscarPorNombre(nombre);

            if (descuento == null) {
                System.out.println("No se encontró ningún descuento con ese nombre.");
            } else {
                System.out.println("Descuento encontrado:");
                System.out.println(descuento);
            }

    }

    private void agregarDescuentos() {

        System.out.println("Introduce los datos del nuevo descuento:");

        System.out.println("Introduce el nombre del descuento. Puede ser, por ejemplo, la temporada a la que pertenece");
        String nombre = sc.nextLine();

        System.out.println("Añade una descripción del descuento");
        String descripcion = sc.nextLine();

        System.out.println("Ingresa el porcentaje que se aplicará con este descuento");
        double porcentajeDescuento = sc.nextDouble();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE; 
        LocalDate fechaInicio = null;
        boolean fechaInicioValida = false;
        while (!fechaInicioValida) {

            System.out.println("Ingresa la fecha de inicio (formato YYYY-MM-DD):");
            String fechaInicioStr = sc.nextLine();
            try {
                fechaInicio = LocalDate.parse(fechaInicioStr, dateFormatter);
                fechaInicioValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto. Por favor, usa el formato YYYY-MM-DD.");
            }

        }

        LocalDate fechaFin = null;
        boolean fechaFinValida = false;
        while (!fechaFinValida) {

            System.out.println("Ingresa la fecha de fin (formato YYYY-MM-DD):");
            String fechaFinStr = sc.nextLine();
            try {
                fechaFin = LocalDate.parse(fechaFinStr, dateFormatter);
                fechaFinValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto. Por favor, usa el formato YYYY-MM-DD.");
            }

        }

        Descuento nuevoDescuento = new Descuento(nombre, descripcion, porcentajeDescuento, fechaInicio , fechaFin);

        descuentoDAO.insertar(nuevoDescuento);
        System.out.println("Descuento agregado correctamente.");

    }

    private void actualizarDescuentos() {

        System.out.println("Introduce el nombre del descuento que deseas actualizar:");
        String nombre = sc.nextLine();
        Descuento descuento = descuentoDAO.buscarPorNombre(nombre);

        if (descuento == null) {
            System.out.println("No se encontró ningún descuento con ese nombre.");
            return;
                
        } else {
            System.out.println("Introduce los nuevos datos del descuento:");

            System.out.println("Ingresa la nueva descripción del descuento (deja en blanco para mantener el actual):");
            String descripcion = sc.nextLine();
            if (!descripcion.isEmpty()) {
                descuento.setDescripcion(descripcion);
            }

            System.out.println("Ingresa el nuevo porcentaje de descuento (deja en blanco para mantener el actual):");
            String porcentajeStr = sc.nextLine();
            if (!porcentajeStr.isEmpty()) {
                try {
                    double nuevoPorcentajeDescuento = Double.parseDouble(porcentajeStr);
                    descuento.setPorcentajeDescuento(nuevoPorcentajeDescuento);
                } catch (NumberFormatException e) {
                    System.out.println("Formato de porcentaje incorrecto. Se mantendrá el valor actual.");
                }
            }

            DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;
            System.out.println("Nueva fecha de inicio (formato YYYY-MM-DD):");
            String fechaInicioStr = sc.nextLine();
            if (!fechaInicioStr.isEmpty()) {
                try {
                    LocalDate nuevaFechaInicio = LocalDate.parse(fechaInicioStr, dateFormatter);
                    descuento.setFechaInicio(nuevaFechaInicio);
                } catch (DateTimeParseException e) {
                    System.out.println("Formato de fecha incorrecto. Se mantendrá la fecha actual.");
                }
            }

            System.out.println("Nueva fecha de fin (formato YYYY-MM-DD):");
            String fechaFinStr = sc.nextLine();
            if (!fechaFinStr.isEmpty()) {
                try {
                    LocalDate nuevaFechaFin = LocalDate.parse(fechaFinStr, dateFormatter);
                    descuento.setFechaFin(nuevaFechaFin);
                } catch (DateTimeParseException e) {
                    System.out.println("Formato de fecha incorrecto. Se mantendrá la fecha actual.");
                }
            }

            descuentoDAO.actualizar(descuento);
            System.out.println("Descuento actualizado correctamente.");


        }
    }

    private void eliminarDescuentos() {
        System.out.println("Nombre del descuento que deseas eliminar: ");
        String nombre = sc.nextLine();
        descuentoDAO.eliminar(nombre);
    }
}
