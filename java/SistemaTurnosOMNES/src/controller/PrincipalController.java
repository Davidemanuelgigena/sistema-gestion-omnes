package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.PacienteDAO;
import dao.ProfesionalDAO;
import dao.TurnoDAO;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.stage.Modality;
import javafx.stage.Stage;

import model.Paciente;
import model.Profesional;
import model.Turno;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ButtonType;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PrincipalController implements Initializable {

    // ================= COMPONENTES =================

    @FXML
    private ComboBox<Paciente> cmbPaciente;

    @FXML
    private ComboBox<Profesional> cmbProfesional;

    @FXML
    private ComboBox<String> cmbHora;

    @FXML
    private ComboBox<Integer> cmbDuracion;

    @FXML
    private ComboBox<String> cmbEstado;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private TableView<Turno> tblTurnos;

    @FXML
    private TableColumn<Turno, String> colPaciente;

    @FXML
    private TableColumn<Turno, String> colProfesional;

    @FXML
    private TableColumn<Turno, String> colFecha;

    @FXML
    private TableColumn<Turno, String> colHora;

    @FXML
    private TableColumn<Turno, Integer> colDuracion;

    @FXML
    private TableColumn<Turno, String> colEstado;

    // ================= DAO =================

    private final PacienteDAO pacienteDAO = new PacienteDAO();

    private final ProfesionalDAO profesionalDAO = new ProfesionalDAO();

    private final TurnoDAO turnoDAO = new TurnoDAO();
    
    private Turno turnoSeleccionado;

    // ================= INITIALIZE =================

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Image logo = new Image(
            getClass().getResourceAsStream("/resources/img/logo_omnes.png"));

        imgLogo.setImage(logo);
        
        cargarPacientes();

        cargarProfesionales();

        cargarHoras();

        cargarDuraciones();

        cargarEstadosNuevoTurno();

        cargarTurnos();

        tblTurnos.getSelectionModel().selectedItemProperty().addListener(
                (obs, anterior, actual) -> {

                    if (actual != null) {

                        turnoSeleccionado = actual;

                        cmbPaciente.setValue(actual.getPaciente());

                        cmbProfesional.setValue(actual.getProfesional());

                        dpFecha.setValue(actual.getFechaHora().toLocalDate());

                        cmbHora.setValue(
                                actual.getFechaHora()
                                        .toLocalTime()
                                        .toString());

                        cmbDuracion.setValue(actual.getDuracion());

                        cargarEstadosEdicion();

                        cmbEstado.setValue(actual.getEstado());

                    }

                });

    }

    private void cargarPacientes() {

        cmbPaciente.setItems(
                FXCollections.observableArrayList(
                        pacienteDAO.listar()));

    }

    private void cargarProfesionales() {

        cmbProfesional.setItems(
                FXCollections.observableArrayList(
                        profesionalDAO.listar()));

    }

    private void cargarHoras() {

        cmbHora.getItems().addAll(
                "08:00", "08:30", "09:00", "09:30",
                "10:00", "10:30", "11:00", "11:30",
                "12:00", "12:30", "13:00", "13:30",
                "14:00", "14:30", "15:00", "15:30",
                "16:00", "16:30", "17:00", "17:30",
                "18:00", "18:30", "19:00");

    }

    private void cargarDuraciones() {

        cmbDuracion.getItems().addAll(15, 30, 45, 60);

    }

    private void cargarEstadosNuevoTurno() {

        cmbEstado.getItems().setAll(
                "Programado"
                );

        cmbEstado.setValue("Programado");

    }

    private void cargarEstadosEdicion() {

        cmbEstado.getItems().setAll(
                "Reprogramado",
                "Atendido",
                "Ausente");

    }

    // ================= VENTANAS =================

    @FXML
    private void abrirPacientes() throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/Pacientes.fxml"));

        Parent root = loader.load();

        Stage stage = new Stage();

        stage.setTitle("Gestión de Pacientes");

        stage.setScene(new Scene(root));

        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);

        stage.showAndWait();
        
        cargarPacientes();
        
        cargarProfesionales();

    }

    @FXML
    private void abrirProfesionales() throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/Profesionales.fxml"));

        Parent root = loader.load();

        Stage stage = new Stage();

        stage.setTitle("Gestión de Profesionales");

        stage.setScene(new Scene(root));

        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);

        stage.showAndWait();
        
        cargarPacientes();
        
        cargarProfesionales();
        

    }
    @FXML
    private void guardarTurno(ActionEvent event) {

        try {

            Paciente paciente = cmbPaciente.getValue();

            Profesional profesional = cmbProfesional.getValue();

            LocalDate fecha = dpFecha.getValue();

            LocalTime hora = LocalTime.parse(cmbHora.getValue());

            LocalDateTime fechaHora = LocalDateTime.of(fecha, hora);

            int duracion = cmbDuracion.getValue();

            String estado = cmbEstado.getValue();

            Turno turno = new Turno(
                    fechaHora,
                    duracion,
                    estado,
                    paciente,
                    profesional);

            if (turnoDAO.guardar(turno)) {

                mostrarMensaje("Turno guardado correctamente.");

                cargarTurnos();

                limpiarFormulario(null);

            } else {

                mostrarError("No fue posible guardar el turno.");

            }

        } catch (Exception e) {

            mostrarError("Complete todos los datos correctamente.");

        }

    }

    @FXML
    private void modificarTurno(ActionEvent event) {

        if (turnoSeleccionado == null) {

            mostrarError("Seleccione un turno.");

            return;

        }

        try {

            turnoSeleccionado.setPaciente(cmbPaciente.getValue());

            turnoSeleccionado.setProfesional(cmbProfesional.getValue());

            LocalDate fecha = dpFecha.getValue();

            LocalTime hora = LocalTime.parse(cmbHora.getValue());

            turnoSeleccionado.setFechaHora(
                    LocalDateTime.of(fecha, hora));

            turnoSeleccionado.setDuracion(cmbDuracion.getValue());

            turnoSeleccionado.setEstado(cmbEstado.getValue());

            if (turnoDAO.modificar(turnoSeleccionado)) {

                mostrarMensaje("Turno modificado correctamente.");

                cargarTurnos();

                limpiarFormulario(null);

            } else {

                mostrarError("No fue posible modificar el turno.");

            }

        } catch (Exception e) {

            mostrarError("Complete todos los datos.");

        }

    }   

    @FXML
    private void eliminarTurno(ActionEvent event) {

        if (turnoSeleccionado == null) {

            mostrarError("Seleccione un turno.");

            return;

        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);

        confirmacion.setHeaderText(null);

        confirmacion.setContentText("¿Desea cancelar el turno seleccionado?");

        if (confirmacion.showAndWait().get() != ButtonType.OK) {

            return;

        }

        turnoSeleccionado.setEstado("Cancelado");

        if (turnoDAO.modificar(turnoSeleccionado)) {

            mostrarMensaje("Turno cancelado correctamente.");

            cargarTurnos();

            limpiarFormulario(null);

        } else {

            mostrarError("No fue posible cancelar el turno.");

        }

    }

    @FXML
    private void limpiarFormulario(ActionEvent event) {
        
        turnoSeleccionado = null;

        cmbPaciente.getSelectionModel().clearSelection();

        cmbProfesional.getSelectionModel().clearSelection();

        cmbHora.getSelectionModel().clearSelection();

        cmbDuracion.getSelectionModel().clearSelection();

        cmbEstado.getSelectionModel().clearSelection();

        dpFecha.setValue(null);
        
        cargarEstadosNuevoTurno();

    }

    private void mostrarMensaje(String mensaje) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setHeaderText(null);

        alert.setContentText(mensaje);

        alert.showAndWait();

    }

    private void mostrarError(String mensaje) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setHeaderText(null);

        alert.setContentText(mensaje);

        alert.showAndWait();

    }
    private void cargarTurnos() {

    colPaciente.setCellValueFactory(
            new PropertyValueFactory<>("nombrePaciente"));

    colProfesional.setCellValueFactory(
            new PropertyValueFactory<>("nombreProfesional"));

    colFecha.setCellValueFactory(
            new PropertyValueFactory<>("fecha"));

    colHora.setCellValueFactory(
            new PropertyValueFactory<>("hora"));

    colDuracion.setCellValueFactory(
            new PropertyValueFactory<>("duracion"));

    colEstado.setCellValueFactory(
            new PropertyValueFactory<>("estado"));

    tblTurnos.setItems(
            FXCollections.observableArrayList(
                    turnoDAO.listar()));

    }
    
    @FXML
    private ImageView imgLogo;
    
}