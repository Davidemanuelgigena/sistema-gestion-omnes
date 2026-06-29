package model;

public class Profesional extends Persona {

    private int idProfesional;
    private String especialidad;
    private String matricula;
    private double porcentajeAporte;
    private String cuit;

    // Constructor vacío
    public Profesional() {
        super("", 0);
    }

    // Constructor para guardar (sin DNI)
    public Profesional(String nombre,
                       String especialidad,
                       String matricula,
                       double porcentajeAporte,
                       String cuit) {

        super(nombre, 0);

        this.especialidad = especialidad;
        this.matricula = matricula;
        this.porcentajeAporte = porcentajeAporte;
        this.cuit = cuit;
    }

    // Constructor para guardar (con DNI)
    public Profesional(String nombre,
                       int dni,
                       String especialidad,
                       String matricula,
                       double porcentajeAporte,
                       String cuit) {

        super(nombre, dni);

        this.especialidad = especialidad;
        this.matricula = matricula;
        this.porcentajeAporte = porcentajeAporte;
        this.cuit = cuit;
    }

    // Constructor completo
    public Profesional(int idProfesional,
                       String nombre,
                       int dni,
                       String especialidad,
                       String matricula,
                       double porcentajeAporte,
                       String cuit) {

        super(nombre, dni);

        this.idProfesional = idProfesional;
        this.especialidad = especialidad;
        this.matricula = matricula;
        this.porcentajeAporte = porcentajeAporte;
        this.cuit = cuit;
    }

    public int getIdProfesional() {
        return idProfesional;
    }

    public void setIdProfesional(int idProfesional) {
        this.idProfesional = idProfesional;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public double getPorcentajeAporte() {
        return porcentajeAporte;
    }

    public void setPorcentajeAporte(double porcentajeAporte) {
        this.porcentajeAporte = porcentajeAporte;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    @Override
    public String mostrarDatos() {

        return getNombre()
                + " - "
                + especialidad
                + " (MP: "
                + matricula
                + ")";

    }
    
    @Override
    public String toString() {

        return getNombre()
                + " - "
                + getEspecialidad();

    }

}