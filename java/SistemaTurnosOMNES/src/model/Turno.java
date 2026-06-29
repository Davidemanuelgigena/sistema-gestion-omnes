package model;

import java.time.LocalDateTime;

public class Turno {

    private int idTurno;

    private LocalDateTime fechaHora;

    private int duracion;

    private String estado;

    private Paciente paciente;

    private Profesional profesional;

    public Turno() {

    }

    public Turno(LocalDateTime fechaHora,
                 int duracion,
                 String estado,
                 Paciente paciente,
                 Profesional profesional) {

        this.fechaHora = fechaHora;
        this.duracion = duracion;
        this.estado = estado;
        this.paciente = paciente;
        this.profesional = profesional;

    }

    public Turno(int idTurno,
                 LocalDateTime fechaHora,
                 int duracion,
                 String estado,
                 Paciente paciente,
                 Profesional profesional) {

        this.idTurno = idTurno;
        this.fechaHora = fechaHora;
        this.duracion = duracion;
        this.estado = estado;
        this.paciente = paciente;
        this.profesional = profesional;

    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Profesional getProfesional() {
        return profesional;
    }

    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }

    // =====================
    // Métodos para TableView
    // =====================

    public String getNombrePaciente() {

        return paciente != null ? paciente.getNombre() : "";

    }

    public String getNombreProfesional() {

        return profesional != null ? profesional.getNombre() : "";

    }

    public String getFecha() {

        return fechaHora != null
                ? fechaHora.toLocalDate().toString()
                : "";

    }

    public String getHora() {

        return fechaHora != null
                ? fechaHora.toLocalTime().toString()
                : "";

    }

}