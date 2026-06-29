package model;

public class Paciente extends Persona {

    private int idPaciente;
    private String obraSocial;

    // Constructor vacío (lo necesita JDBC)
    public Paciente() {
        super("", 0);
    }

    // Constructor sin ID (para guardar)
    public Paciente(String nombre, int dni, String obraSocial) {
        super(nombre, dni);
        this.obraSocial = obraSocial;
    }

    // Constructor completo (para leer desde la BD)
    public Paciente(int idPaciente, String nombre, int dni, String obraSocial) {
        super(nombre, dni);
        this.idPaciente = idPaciente;
        this.obraSocial = obraSocial;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    @Override
    public String mostrarDatos() {
        return getNombre();
    }
    
    @Override
    public String toString() {

        return getNombre();

    }

}