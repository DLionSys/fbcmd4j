import java.time.LocalDateTime;
import java.util.ArrayList;

public class SistemaCitas {

    private ArrayList<Doctor> doctores;
    private ArrayList<Paciente> pacientes;
    private ArrayList<Cita> citas;
    private ArrayList<Administrador> administradores;

    public SistemaCitas() {

        ArchivoCSV.inicializarArchivos();

        doctores = ArchivoCSV.cargarDoctores();
        pacientes = ArchivoCSV.cargarPacientes();
        administradores = ArchivoCSV.cargarAdministradores();

        citas = ArchivoCSV.cargarCitas(
                doctores,
                pacientes
        );
    }

    // -------------------------------------------------
    // DOCTORES
    // -------------------------------------------------

    public void registrarDoctor(
            String id,
            String nombreCompleto,
            String especialidad) {

        if (id == null || id.isBlank()
                || nombreCompleto == null || nombreCompleto.isBlank()
                || especialidad == null || especialidad.isBlank()) {

            System.out.println(
                    "Error: Todos los datos del doctor son obligatorios."
            );

            return;
        }

        if (buscarDoctor(id) != null) {

            System.out.println(
                    "Ya existe un doctor con ese identificador."
            );

            return;
        }

        Doctor doctor = new Doctor(
                id,
                nombreCompleto,
                especialidad
        );

        doctores.add(doctor);

        ArchivoCSV.guardarDoctor(doctor);

        System.out.println(
                "Doctor registrado correctamente."
        );
    }

    public Doctor buscarDoctor(String id) {

        for (Doctor doctor : doctores) {

            if (doctor.getId().equals(id)) {
                return doctor;
            }
        }

        return null;
    }

    public void mostrarDoctores() {

        System.out.println("\n--- DOCTORES ---");

        if (doctores.isEmpty()) {

            System.out.println(
                    "No hay doctores registrados."
            );

            return;
        }

        for (Doctor doctor : doctores) {

            System.out.println(
                    "ID: " + doctor.getId()
                            + " | Nombre: "
                            + doctor.getNombreCompleto()
                            + " | Especialidad: "
                            + doctor.getEspecialidad()
            );
        }
    }

    // -------------------------------------------------
    // PACIENTES
    // -------------------------------------------------

    public void registrarPaciente(
            String id,
            String nombreCompleto) {

        if (id == null || id.isBlank()
                || nombreCompleto == null || nombreCompleto.isBlank()) {

            System.out.println(
                    "Error: Todos los datos del paciente son obligatorios."
            );

            return;
        }

        if (buscarPaciente(id) != null) {

            System.out.println(
                    "Ya existe un paciente con ese identificador."
            );

            return;
        }

        Paciente paciente = new Paciente(
                id,
                nombreCompleto
        );

        pacientes.add(paciente);

        ArchivoCSV.guardarPaciente(paciente);

        System.out.println(
                "Paciente registrado correctamente."
        );
    }

    public Paciente buscarPaciente(String id) {

        for (Paciente paciente : pacientes) {

            if (paciente.getId().equals(id)) {
                return paciente;
            }
        }

        return null;
    }

    public void mostrarPacientes() {

        System.out.println("\n--- PACIENTES ---");

        if (pacientes.isEmpty()) {

            System.out.println(
                    "No hay pacientes registrados."
            );

            return;
        }

        for (Paciente paciente : pacientes) {

            System.out.println(
                    "ID: " + paciente.getId()
                            + " | Nombre: "
                            + paciente.getNombreCompleto()
            );
        }
    }

    // -------------------------------------------------
    // ADMINISTRADORES
    // -------------------------------------------------

    public void registrarAdministrador(
            String id,
            String password) {

        if (id == null || id.isBlank()
                || password == null || password.isBlank()) {

            System.out.println(
                    "Error: ID y contrasena son obligatorios."
            );

            return;
        }

        for (Administrador administrador : administradores) {

            if (administrador.getId().equals(id)) {

                System.out.println(
                        "Ya existe un administrador con ese identificador."
                );

                return;
            }
        }

        Administrador administrador =
                new Administrador(
                        id,
                        password
                );

        administradores.add(administrador);

        ArchivoCSV.guardarAdministrador(
                administrador
        );

        System.out.println(
                "Administrador registrado correctamente."
        );
    }

    public boolean autenticarAdministrador(
            String id,
            String password) {

        for (Administrador administrador : administradores) {

            if (administrador.autenticar(
                    id,
                    password)) {

                return true;
            }
        }

        return false;
    }

    public boolean tieneAdministradores() {

        return !administradores.isEmpty();
    }

    // -------------------------------------------------
    // CITAS
    // -------------------------------------------------

    public void crearCita(
            String id,
            LocalDateTime fechaHora,
            String motivo,
            String idDoctor,
            String idPaciente) {

        // Validar que todos los datos sean proporcionados
        if (id == null || id.isBlank()
                || fechaHora == null
                || motivo == null || motivo.isBlank()
                || idDoctor == null || idDoctor.isBlank()
                || idPaciente == null || idPaciente.isBlank()) {

            System.out.println(
                    "Error: Todos los datos de la cita son obligatorios."
            );

            return;
        }

        // Validar identificador único
        for (Cita cita : citas) {

            if (cita.getId().equals(id)) {

                System.out.println(
                        "Ya existe una cita con ese identificador."
                );

                return;
            }
        }

        // Buscar doctor
        Doctor doctor =
                buscarDoctor(idDoctor);

        if (doctor == null) {

            System.out.println(
                    "El doctor indicado no existe."
            );

            return;
        }

        // Buscar paciente
        Paciente paciente =
                buscarPaciente(idPaciente);

        if (paciente == null) {

            System.out.println(
                    "El paciente indicado no existe."
            );

            return;
        }

        Cita cita = new Cita(
                id,
                fechaHora,
                motivo,
                doctor,
                paciente
        );

        citas.add(cita);

        ArchivoCSV.guardarCita(cita);

        System.out.println(
                "Cita registrada correctamente."
        );
    }

    public void mostrarCitas() {

        System.out.println("\n--- CITAS ---");

        if (citas.isEmpty()) {

            System.out.println(
                    "No hay citas registradas."
            );

            return;
        }

        for (Cita cita : citas) {

            System.out.println(
                    "ID: " + cita.getId()
                            + " | Fecha y hora: "
                            + cita.getFechaHora()
                            + " | Motivo: "
                            + cita.getMotivo()
                            + " | Doctor: "
                            + cita.getDoctor().getNombreCompleto()
                            + " | Paciente: "
                            + cita.getPaciente().getNombreCompleto()
            );
        }
    }
}