public class Turno {

    private String fecha;
    private String hora;

    private Paciente paciente;
    private Profesional profesional;

    public Turno(String fecha,
                 String hora,
                 Paciente paciente,
                 Profesional profesional) {

        this.fecha = fecha;
        this.hora = hora;
        this.paciente = paciente;
        this.profesional = profesional;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String mostrarTurno() {

        return "Fecha: " + fecha
                + " Hora: " + hora
                + "\nPaciente: " + paciente.getNombre()
                + "\nProfesional: " + profesional.getNombre();
    }
}