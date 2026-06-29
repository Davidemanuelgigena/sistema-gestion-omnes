import java.util.ArrayList;
import java.util.Scanner;

public class SistemaTurnos {

    private ArrayList<Paciente> pacientes;
    private ArrayList<Profesional> profesionales;
    private ArrayList<Turno> turnos;

    private Scanner teclado;

    public SistemaTurnos() {

        pacientes = new ArrayList<>();
        profesionales = new ArrayList<>();
        turnos = new ArrayList<>();

        teclado = new Scanner(System.in);
    }

    public void registrarPaciente() {

        System.out.println("\n=== REGISTRO DE PACIENTE ===");

        System.out.print("Nombre: ");
        String nombre = teclado.nextLine();

        System.out.print("DNI: ");
        String dni = teclado.nextLine();

        System.out.print("Obra Social: ");
        String obraSocial = teclado.nextLine();

        Paciente paciente = new Paciente(nombre, dni, obraSocial);

        pacientes.add(paciente);

        System.out.println("Paciente registrado correctamente.");
    }

    public void registrarProfesional() {

        System.out.println("\n=== REGISTRO DE PROFESIONAL ===");

        System.out.print("Nombre: ");
        String nombre = teclado.nextLine();

        System.out.print("DNI: ");
        String dni = teclado.nextLine();

        System.out.print("Especialidad: ");
        String especialidad = teclado.nextLine();

        System.out.print("Matrícula: ");
        String matricula = teclado.nextLine();

        Profesional profesional =
                new Profesional(nombre, dni, especialidad, matricula);

        profesionales.add(profesional);

        System.out.println("Profesional registrado correctamente.");
    }

    public void mostrarPacientes() {

        System.out.println("\n=== PACIENTES ===");

        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }

        for (Paciente p : pacientes) {
            System.out.println(p.mostrarDatos());
        }
    }

    public void mostrarProfesionales() {

        System.out.println("\n=== PROFESIONALES ===");

        if (profesionales.isEmpty()) {
            System.out.println("No hay profesionales registrados.");
            return;
        }

        for (Profesional p : profesionales) {
            System.out.println(p.mostrarDatos());
        }
    }

    public void asignarTurno() {

        if (pacientes.isEmpty() || profesionales.isEmpty()) {

            System.out.println(
                    "Debe registrar al menos un paciente y un profesional.");
            return;
        }

        System.out.println("\n=== ASIGNAR TURNO ===");

        System.out.println("\nPacientes:");

        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println(
                    (i + 1) + " - " + pacientes.get(i).getNombre());
        }

        System.out.print("Seleccione paciente: ");
        int indicePaciente = Integer.parseInt(teclado.nextLine()) - 1;

        System.out.println("\nProfesionales:");

        for (int i = 0; i < profesionales.size(); i++) {
            System.out.println(
                    (i + 1) + " - " + profesionales.get(i).getNombre());
        }

        System.out.print("Seleccione profesional: ");
        int indiceProfesional = Integer.parseInt(teclado.nextLine()) - 1;

        System.out.print("Fecha: ");
        String fecha = teclado.nextLine();

        System.out.print("Hora: ");
        String hora = teclado.nextLine();

        Turno turno = new Turno(
                fecha,
                hora,
                pacientes.get(indicePaciente),
                profesionales.get(indiceProfesional));

        turnos.add(turno);

        System.out.println("Turno asignado correctamente.");
    }

    public void mostrarTurnos() {

        System.out.println("\n=== TURNOS ===");

        if (turnos.isEmpty()) {

            System.out.println("No hay turnos registrados.");
            return;
        }

        for (Turno t : turnos) {

            System.out.println("------------------------");
            System.out.println(t.mostrarTurno());
        }
    }

    public void menu() {

        int opcion;

        do {

            System.out.println("\n========================");
            System.out.println(" SISTEMA TURNOS OMNES ");
            System.out.println("========================");
            System.out.println("1 - Registrar paciente");
            System.out.println("2 - Registrar profesional");
            System.out.println("3 - Asignar turno");
            System.out.println("4 - Mostrar pacientes");
            System.out.println("5 - Mostrar profesionales");
            System.out.println("6 - Mostrar turnos");
            System.out.println("7 - Salir");

            System.out.print("Opción: ");

            try {

                opcion = Integer.parseInt(teclado.nextLine());

                switch (opcion) {

                    case 1:
                        registrarPaciente();
                        break;

                    case 2:
                        registrarProfesional();
                        break;

                    case 3:
                        asignarTurno();
                        break;

                    case 4:
                        mostrarPacientes();
                        break;

                    case 5:
                        mostrarProfesionales();
                        break;

                    case 6:
                        mostrarTurnos();
                        break;

                    case 7:
                        System.out.println("Fin del programa.");
                        break;

                    default:
                        System.out.println("Opción inválida.");
                }

            } catch (Exception e) {

                System.out.println(
                        "Error: debe ingresar un valor válido.");

                opcion = 0;
            }

        } while (opcion != 7);
    }

    public static void main(String[] args) {

        SistemaTurnos sistema = new SistemaTurnos();

        sistema.menu();
    }
}