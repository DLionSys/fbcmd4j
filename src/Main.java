import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner =
                new Scanner(System.in);

        SistemaCitas sistema =
                new SistemaCitas();

        // Crear administrador inicial únicamente
        // si no existe ninguno guardado
        if (!sistema.tieneAdministradores()) {

            sistema.registrarAdministrador(
                    "admin",
                    "admin123"
            );
        }

        System.out.println(
                "=================================="
        );

        System.out.println(
                "   SISTEMA DE ADMINISTRACION"
        );

        System.out.println(
                "        DE CITAS MEDICAS"
        );

        System.out.println(
                "=================================="
        );

        // -------------------------------------
        // INICIO DE SESIÓN
        // -------------------------------------

        System.out.println(
                "\n--- INICIO DE SESION ---"
        );

        System.out.print(
                "Identificador: "
        );

        String idAdministrador =
                scanner.nextLine();

        System.out.print(
                "Contrasena: "
        );

        String password =
                scanner.nextLine();

        if (!sistema.autenticarAdministrador(
                idAdministrador,
                password)) {

            System.out.println(
                    "\nAcceso denegado."
            );

            scanner.close();

            return;
        }

        System.out.println(
                "\nAcceso concedido."
        );

        // -------------------------------------
        // MENÚ
        // -------------------------------------

        int opcion = -1;

        DateTimeFormatter formatoFecha =
                DateTimeFormatter.ofPattern(
                        "yyyy-MM-dd HH:mm"
                );

        do {

            System.out.println(
                    "\n========= MENU ========="
            );

            System.out.println(
                    "1. Registrar doctor"
            );

            System.out.println(
                    "2. Registrar paciente"
            );

            System.out.println(
                    "3. Crear cita"
            );

            System.out.println(
                    "4. Mostrar doctores"
            );

            System.out.println(
                    "5. Mostrar pacientes"
            );

            System.out.println(
                    "6. Mostrar citas"
            );

            System.out.println(
                    "0. Salir"
            );

            System.out.println(
                    "========================"
            );

            System.out.print(
                    "Seleccione una opcion: "
            );

            try {

                opcion =
                        Integer.parseInt(
                                scanner.nextLine()
                        );

                switch (opcion) {

                    // -------------------------
                    // REGISTRAR DOCTOR
                    // -------------------------

                    case 1:

                        System.out.println(
                                "\n--- REGISTRAR DOCTOR ---"
                        );

                        System.out.print(
                                "ID del doctor: "
                        );

                        String idDoctor =
                                scanner.nextLine();

                        System.out.print(
                                "Nombre completo: "
                        );

                        String nombreDoctor =
                                scanner.nextLine();

                        System.out.print(
                                "Especialidad: "
                        );

                        String especialidad =
                                scanner.nextLine();

                        sistema.registrarDoctor(
                                idDoctor,
                                nombreDoctor,
                                especialidad
                        );

                        break;

                    // -------------------------
                    // REGISTRAR PACIENTE
                    // -------------------------

                    case 2:

                        System.out.println(
                                "\n--- REGISTRAR PACIENTE ---"
                        );

                        System.out.print(
                                "ID del paciente: "
                        );

                        String idPaciente =
                                scanner.nextLine();

                        System.out.print(
                                "Nombre completo: "
                        );

                        String nombrePaciente =
                                scanner.nextLine();

                        sistema.registrarPaciente(
                                idPaciente,
                                nombrePaciente
                        );

                        break;

                    // -------------------------
                    // CREAR CITA
                    // -------------------------

                    case 3:

                        System.out.println(
                                "\n--- CREAR CITA ---"
                        );

                        System.out.print(
                                "ID de la cita: "
                        );

                        String idCita =
                                scanner.nextLine();

                        System.out.print(
                                "Fecha y hora "
                                        + "(yyyy-MM-dd HH:mm): "
                        );

                        String fechaTexto =
                                scanner.nextLine();

                        LocalDateTime fechaHora =
                                LocalDateTime.parse(
                                        fechaTexto,
                                        formatoFecha
                                );

                        System.out.print(
                                "Motivo de la cita: "
                        );

                        String motivo =
                                scanner.nextLine();

                        System.out.print(
                                "ID del doctor: "
                        );

                        String doctorCita =
                                scanner.nextLine();

                        System.out.print(
                                "ID del paciente: "
                        );

                        String pacienteCita =
                                scanner.nextLine();

                        sistema.crearCita(
                                idCita,
                                fechaHora,
                                motivo,
                                doctorCita,
                                pacienteCita
                        );

                        break;

                    // -------------------------
                    // MOSTRAR DOCTORES
                    // -------------------------

                    case 4:

                        sistema.mostrarDoctores();

                        break;

                    // -------------------------
                    // MOSTRAR PACIENTES
                    // -------------------------

                    case 5:

                        sistema.mostrarPacientes();

                        break;

                    // -------------------------
                    // MOSTRAR CITAS
                    // -------------------------

                    case 6:

                        sistema.mostrarCitas();

                        break;

                    // -------------------------
                    // SALIR
                    // -------------------------

                    case 0:

                        System.out.println(
                                "\nCerrando sistema..."
                        );

                        System.out.println(
                                "Hasta luego."
                        );

                        break;

                    default:

                        System.out.println(
                                "Opcion invalida."
                        );
                }

            } catch (Exception e) {

                System.out.println(
                        "\nError: "
                                + e.getMessage()
                );

                System.out.println(
                        "El programa continuara ejecutandose."
                );

                opcion = -1;
            }

        } while (opcion != 0);

        scanner.close();
    }
}