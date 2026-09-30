import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ArchivoCSV {

    private static final String CARPETA = "db";

    // Obtiene un archivo y lo crea si no existe
    private static File obtenerArchivo(String nombreArchivo) {

        File carpeta = new File(CARPETA);

        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        File archivo = new File(carpeta, nombreArchivo);

        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                System.out.println(
                        "Error al crear el archivo "
                                + nombreArchivo + ": "
                                + e.getMessage()
                );
            }
        }

        return archivo;
    }

    // Crea todos los archivos necesarios si no existen
    public static void inicializarArchivos() {

        obtenerArchivo("doctores.csv");
        obtenerArchivo("pacientes.csv");
        obtenerArchivo("citas.csv");
        obtenerArchivo("administradores.csv");
    }

    // -------------------------
    // GUARDAR INFORMACIÓN
    // -------------------------

    public static void guardarDoctor(Doctor doctor) {

        File archivo = obtenerArchivo("doctores.csv");

        try (FileWriter writer = new FileWriter(archivo, true)) {

            writer.write(
                    doctor.getId() + "," +
                            doctor.getNombreCompleto() + "," +
                            doctor.getEspecialidad() +
                            System.lineSeparator()
            );

        } catch (IOException e) {

            System.out.println(
                    "Error al guardar el doctor: "
                            + e.getMessage()
            );
        }
    }

    public static void guardarPaciente(Paciente paciente) {

        File archivo = obtenerArchivo("pacientes.csv");

        try (FileWriter writer = new FileWriter(archivo, true)) {

            writer.write(
                    paciente.getId() + "," +
                            paciente.getNombreCompleto() +
                            System.lineSeparator()
            );

        } catch (IOException e) {

            System.out.println(
                    "Error al guardar el paciente: "
                            + e.getMessage()
            );
        }
    }

    public static void guardarCita(Cita cita) {

        File archivo = obtenerArchivo("citas.csv");

        try (FileWriter writer = new FileWriter(archivo, true)) {

            writer.write(
                    cita.getId() + "," +
                            cita.getFechaHora() + "," +
                            cita.getMotivo() + "," +
                            cita.getDoctor().getId() + "," +
                            cita.getPaciente().getId() +
                            System.lineSeparator()
            );

        } catch (IOException e) {

            System.out.println(
                    "Error al guardar la cita: "
                            + e.getMessage()
            );
        }
    }

    public static void guardarAdministrador(
            Administrador administrador) {

        File archivo =
                obtenerArchivo("administradores.csv");

        try (FileWriter writer =
                     new FileWriter(archivo, true)) {

            writer.write(
                    administrador.getId() + "," +
                            administrador.getPassword() +
                            System.lineSeparator()
            );

        } catch (IOException e) {

            System.out.println(
                    "Error al guardar el administrador: "
                            + e.getMessage()
            );
        }
    }

    // -------------------------
    // CARGAR INFORMACIÓN
    // -------------------------

    public static ArrayList<Doctor> cargarDoctores() {

        ArrayList<Doctor> doctores = new ArrayList<>();

        File archivo = obtenerArchivo("doctores.csv");

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(archivo))) {

            String linea;

            while ((linea = reader.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(",", 3);

                if (datos.length == 3) {

                    Doctor doctor = new Doctor(
                            datos[0],
                            datos[1],
                            datos[2]
                    );

                    doctores.add(doctor);
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Error al cargar doctores: "
                            + e.getMessage()
            );
        }

        return doctores;
    }

    public static ArrayList<Paciente> cargarPacientes() {

        ArrayList<Paciente> pacientes =
                new ArrayList<>();

        File archivo =
                obtenerArchivo("pacientes.csv");

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(archivo))) {

            String linea;

            while ((linea = reader.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(",", 2);

                if (datos.length == 2) {

                    Paciente paciente = new Paciente(
                            datos[0],
                            datos[1]
                    );

                    pacientes.add(paciente);
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Error al cargar pacientes: "
                            + e.getMessage()
            );
        }

        return pacientes;
    }

    public static ArrayList<Administrador>
    cargarAdministradores() {

        ArrayList<Administrador> administradores =
                new ArrayList<>();

        File archivo =
                obtenerArchivo("administradores.csv");

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(archivo))) {

            String linea;

            while ((linea = reader.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(",", 2);

                if (datos.length == 2) {

                    Administrador administrador =
                            new Administrador(
                                    datos[0],
                                    datos[1]
                            );

                    administradores.add(administrador);
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Error al cargar administradores: "
                            + e.getMessage()
            );
        }

        return administradores;
    }

    public static ArrayList<Cita> cargarCitas(
            ArrayList<Doctor> doctores,
            ArrayList<Paciente> pacientes) {

        ArrayList<Cita> citas = new ArrayList<>();

        File archivo = obtenerArchivo("citas.csv");

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(archivo))) {

            String linea;

            while ((linea = reader.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(",", 5);

                if (datos.length == 5) {

                    Doctor doctor =
                            buscarDoctor(
                                    doctores,
                                    datos[3]
                            );

                    Paciente paciente =
                            buscarPaciente(
                                    pacientes,
                                    datos[4]
                            );

                    if (doctor != null &&
                            paciente != null) {

                        LocalDateTime fechaHora =
                                LocalDateTime.parse(
                                        datos[1]
                                );

                        Cita cita = new Cita(
                                datos[0],
                                fechaHora,
                                datos[2],
                                doctor,
                                paciente
                        );

                        citas.add(cita);
                    }
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error al cargar citas: "
                            + e.getMessage()
            );
        }

        return citas;
    }

    // -------------------------
    // MÉTODOS AUXILIARES
    // -------------------------

    private static Doctor buscarDoctor(
            ArrayList<Doctor> doctores,
            String id) {

        for (Doctor doctor : doctores) {

            if (doctor.getId().equals(id)) {
                return doctor;
            }
        }

        return null;
    }

    private static Paciente buscarPaciente(
            ArrayList<Paciente> pacientes,
            String id) {

        for (Paciente paciente : pacientes) {

            if (paciente.getId().equals(id)) {
                return paciente;
            }
        }

        return null;
    }
}