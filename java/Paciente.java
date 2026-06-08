public class Paciente extends Persona {

    private String obraSocial;

    public Paciente(String nombre, String dni, String obraSocial) {
        super(nombre, dni);
        this.obraSocial = obraSocial;
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    @Override
    public String mostrarDatos() {
        return "Paciente: " + getNombre()
                + " | DNI: " + getDni()
                + " | Obra Social: " + obraSocial;
    }
}