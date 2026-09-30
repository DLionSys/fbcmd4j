import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ArchivoCSV {

    private static final String CARPETA = "data";

    private static File obtenerArchivo(String nombreArchivo) {

        File carpeta = new File(CARPETA);

        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        return new File(carpeta, nombreArchivo);
    }

    public static void guardarDoctor(Doctor doctor) {

        File archivo = obtenerArchivo("doctores.csv");

        try (FileWriter writer = new FileWriter(archivo, true)) {

            writer.write(
                    doctor.getId() + "," +
                            doctor.getNombreCompleto() + "," +
                            doctor.getEspecialidad() +
                            System.lineSeparator()
            );

            System.out.println(
                    "Doctor guardado en: " + archivo.getAbsolutePath()
            );

        } catch (IOException e) {

            System.out.println(
                    "Error al guardar el doctor: " + e.getMessage()
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

            System.out.println(
                    "Paciente guardado en: " + archivo.getAbsolutePath()
            );

        } catch (IOException e) {

            System.out.println(
                    "Error al guardar el paciente: " + e.getMessage()
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

            System.out.println(
                    "Cita guardada en: " + archivo.getAbsolutePath()
            );

        } catch (IOException e) {

            System.out.println(
                    "Error al guardar la cita: " + e.getMessage()
            );
        }
    }

    public static void guardarAdministrador(Administrador administrador) {

        File archivo = obtenerArchivo("administradores.csv");

        try (FileWriter writer = new FileWriter(archivo, true)) {

            writer.write(
                    administrador.getId() +
                            System.lineSeparator()
            );

            System.out.println(
                    "Administrador guardado en: " + archivo.getAbsolutePath()
            );

        } catch (IOException e) {

            System.out.println(
                    "Error al guardar el administrador: " + e.getMessage()
            );
        }
    }
}