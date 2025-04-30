package vista;

import dao.EmpleadoDAO;
import java.util.List;
import java.util.Scanner;
import model.Empleado;

public class EmpleadoView {
    
 public static void gestionarEmpleados(Scanner sc) {
    
        System.out.println("Gestión de Empleados:");
        System.out.println("1. Registrar empleado");
        System.out.println("2. Modificar empleado");
        System.out.println("3. Eliminar empleado");
        System.out.println("4. Revisar empleado");
        System.out.println("5. Listar todos los empleados");
        System.out.println("6. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
        int opcion = sc.nextInt();
        sc.nextLine(); // Consumir la nueva línea

        switch (opcion) {
            case 1 -> {
                System.out.println("Introduce nombre:");
                String nombre = sc.nextLine();
                System.out.println("Introduce apellido:");
                String apellido = sc.nextLine();
                System.out.println("Introduce DNI:");
                String dni = sc.nextLine();
                System.out.println("Introduce puesto:");
                String puesto = sc.nextLine();
                System.out.println("Introduce salario:");
                double salario = sc.nextDouble();
                System.out.println("Introduce telefono");
                String telefono= sc.nextLine();
                System.out.println("Introduce email");
                String email= sc.nextLine();
                sc.nextLine(); // Consumir la nueva línea
                Empleado nuevoEmpleado = new Empleado(nombre, apellido, dni, puesto, salario,telefono,email);
                EmpleadoDAO.agregarEmpleado(nuevoEmpleado);
                System.out.println("Empleado registrado correctamente.");
            }
                  case 2 ->{
                     // Código para modificar un empleado existente
                System.out.print("Introduce el ID del empleado a modificar: ");
                int idModificar = sc.nextInt();
                sc.nextLine(); // Consumir la nueva línea

                Empleado empleadoExistente = EmpleadoDAO.obtenerEmpleadoPorId(idModificar);
                if (empleadoExistente != null) {
                    System.out.println("Datos actuales del empleado:");
                    System.out.println(empleadoExistente);

                    System.out.println("Introduce el nuevo nombre (o deja en blanco para mantener el actual):");
                    String nuevoNombre = sc.nextLine();
                    if (!nuevoNombre.isEmpty()) {
                        empleadoExistente.setNombre(nuevoNombre);
                    }

                    System.out.println("Introduce el nuevo apellido (o deja en blanco para mantener el actual):");
                    String nuevoApellido = sc.nextLine();
                    if (!nuevoApellido.isEmpty()) {
                        empleadoExistente.setApellido(nuevoApellido);
                    }

                    System.out.println("Introduce el nuevo DNI (o deja en blanco para mantener el actual):");
                    String nuevoDni = sc.nextLine();
                    if (!nuevoDni.isEmpty()) {
                        empleadoExistente.setDni(nuevoDni);
                    }

                    System.out.println("Introduce el nuevo puesto (o deja en blanco para mantener el actual):");
                    String nuevoPuesto = sc.nextLine();
                    if (!nuevoPuesto.isEmpty()) {
                        empleadoExistente.setCargo(nuevoPuesto);
                    }

                    System.out.println("Introduce el nuevo salario (o introduce el mismo salario para mantenerlo):");
                    String nuevoSalarioStr = sc.nextLine();
                    if (!nuevoSalarioStr.isEmpty()) {
                        try {
                            double nuevoSalario = Double.parseDouble(nuevoSalarioStr);
                            empleadoExistente.setSaldo(nuevoSalario);
                        } catch (NumberFormatException e) {
                            System.out.println("Salario no válido, se mantiene el valor anterior.");
                        }
                    }
                    System.out.println("Introduce el nuevo telefono (o deja en blanco para mantener el actual):");
                    String nuevoTelefono = sc.nextLine();
                    if (!nuevoTelefono.isEmpty()) {
                        empleadoExistente.setTelefono(nuevoTelefono);
                    }

                    System.out.println("Introduce el nuevo email (o deja en blanco para mantener el actual):");
                    String nuevoEmail = sc.nextLine();
                    if (!nuevoEmail.isEmpty()) {
                        empleadoExistente.setEmail(nuevoEmail);
                    }
                    EmpleadoDAO.actualizarEmpleado(empleadoExistente);
                    System.out.println("Empleado modificado correctamente.");
                } else {
                    System.out.println("No se encontró ningún empleado con el ID proporcionado.");
                }
            }
                  
              
            case 3 -> {
                System.out.println("Introduce ID del empleado a eliminar:");
                int idEliminar = sc.nextInt();
                sc.nextLine(); // Consumir la nueva línea
                EmpleadoDAO.eliminarEmpleado(idEliminar);
                System.out.println("Empleado eliminado correctamente.");
            }
            case 4 -> {
                System.out.println("Introduce ID del empleado a revisar:");
                int idRevisar = sc.nextInt();
                sc.nextLine(); // Consumir la nueva línea
                Empleado empleadoRevisado = EmpleadoDAO.obtenerEmpleadoPorId(idRevisar);
                if (empleadoRevisado != null) {
                    System.out.println("Empleado encontrado: " + empleadoRevisado);
                } else {
                    System.out.println("Empleado no encontrado.");
                }
            }
            case 5 -> {
                List<Empleado> empleados = EmpleadoDAO.obtenerTodosEmpleados();
                if (empleados.isEmpty()) {
                    System.out.println("No hay empleados registrados.");
                } else {
                    System.out.println("Lista de todos los empleados:");
                    for (Empleado empleado : empleados) {
                        System.out.println(empleado);
                    }
                }
            }
            case 6 -> {
                System.out.println("Volviendo al menú principal...");
            }
            default -> System.out.println("Opción no válida.");
        }
    }
}
