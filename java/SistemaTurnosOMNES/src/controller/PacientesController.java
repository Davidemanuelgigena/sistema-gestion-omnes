package controller;

import dao.PacienteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Paciente;

import java.net.URL;
import java.util.ResourceBundle;

public class PacientesController implements Initializable {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDni;

    @FXML
    private TextField txtObraSocial;

    @FXML
    private TableView<Paciente> tblPacientes;

    @FXML
    private TableColumn<Paciente, String> colNombre;

    @FXML
    private TableColumn<Paciente, Integer> colDni;

    @FXML
    private TableColumn<Paciente, String> colObraSocial;

    private final PacienteDAO dao = new PacienteDAO();

    private Paciente pacienteSeleccionado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        colObraSocial.setCellValueFactory(new PropertyValueFactory<>("obraSocial"));

        cargarPacientes();

        tblPacientes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, paciente) -> {

                    if (paciente != null) {

                        pacienteSeleccionado = paciente;

                        txtNombre.setText(paciente.getNombre());
                        txtDni.setText(String.valueOf(paciente.getDni()));
                        txtObraSocial.setText(paciente.getObraSocial());

                    }

                });

    }

    @FXML
    private void guardarPaciente(ActionEvent event) {

        try {

            String nombre = txtNombre.getText();
            int dni = Integer.parseInt(txtDni.getText());
            String obraSocial = txtObraSocial.getText();

            Paciente paciente = new Paciente(nombre, dni, obraSocial);

            if (dao.guardar(paciente)) {

                mostrarMensaje("Paciente guardado correctamente.");

                cargarPacientes();

                limpiarCampos(null);

            } else {

                mostrarError("No fue posible guardar el paciente.");

            }

        } catch (Exception e) {

            mostrarError("Verifique los datos ingresados.");

        }

    }

    private void cargarPacientes() {

        ObservableList<Paciente> lista =
                FXCollections.observableArrayList(dao.listar());

        tblPacientes.setItems(lista);

    }

    @FXML
    private void limpiarCampos(ActionEvent event) {

        txtNombre.clear();
        txtDni.clear();
        txtObraSocial.clear();

        pacienteSeleccionado = null;

        tblPacientes.getSelectionModel().clearSelection();

        txtNombre.requestFocus();

    }

    @FXML
    private void modificarPaciente(ActionEvent event) {

        if (pacienteSeleccionado == null) {

            mostrarError("Seleccione un paciente.");

            return;

        }

        try {

            pacienteSeleccionado.setNombre(txtNombre.getText());
            pacienteSeleccionado.setDni(Integer.parseInt(txtDni.getText()));
            pacienteSeleccionado.setObraSocial(txtObraSocial.getText());

            if (dao.modificar(pacienteSeleccionado)) {

                mostrarMensaje("Paciente modificado correctamente.");

                cargarPacientes();

                limpiarCampos(null);

            }

        } catch (Exception e) {

            mostrarError("Error al modificar el paciente.");

        }

    }

    @FXML
    private void eliminarPaciente(ActionEvent event) {

        if (pacienteSeleccionado == null) {

            mostrarError("Seleccione un paciente.");

            return;

        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);

        confirmacion.setTitle("Eliminar paciente");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText("¿Desea eliminar el paciente seleccionado?");

        if (confirmacion.showAndWait().get().getButtonData().isDefaultButton()) {

            if (dao.eliminar(pacienteSeleccionado.getIdPaciente())) {

                mostrarMensaje("Paciente eliminado correctamente.");

                cargarPacientes();

                limpiarCampos(null);

            } else {

                mostrarError("No se puede eliminar el paciente porque tiene registros asociados.");

            }

        }

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

}