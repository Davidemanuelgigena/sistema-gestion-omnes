public class Profesional extends Persona {

    private String especialidad;
    private String matricula;

    public Profesional(String nombre,
                       String dni,
                       String especialidad,
                       String matricula) {

        super(nombre, dni);

        this.especialidad = especialidad;
        this.matricula = matricula;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String mostrarDatos() {

        return "Profesional: " + getNombre()
                + " | Especialidad: " + especialidad
                + " | Matrícula: " + matricula;
    }
}