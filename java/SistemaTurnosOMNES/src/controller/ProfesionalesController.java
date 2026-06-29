package controller;

import dao.ProfesionalDAO;
import java.net.URL;
import java.util.ResourceBundle;
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
import model.Profesional;

public class ProfesionalesController implements Initializable {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtEspecialidad;

    @FXML
    private TextField txtMatricula;

    @FXML
    private TextField txtCuit;

    @FXML
    private TextField txtPorcentajeAporte;

    @FXML
    private TableView<Profesional> tblProfesionales;

    @FXML
    private TableColumn<Profesional, String> colNombre;

    @FXML
    private TableColumn<Profesional, String> colEspecialidad;

    @FXML
    private TableColumn<Profesional, String> colMatricula;

    @FXML
    private TableColumn<Profesional, String> colCuit;

    @FXML
    private TableColumn<Profesional, Double> colPorcentajeAporte;

    private final ProfesionalDAO dao = new ProfesionalDAO();

    private Profesional profesionalSeleccionado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEspecialidad.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
        colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        colCuit.setCellValueFactory(new PropertyValueFactory<>("cuit"));
        colPorcentajeAporte.setCellValueFactory(new PropertyValueFactory<>("porcentajeAporte"));

        cargarProfesionales();

        tblProfesionales.getSelectionModel().selectedItemProperty().addListener(
                (obs, anterior, profesional) -> {

                    if (profesional != null) {

                        profesionalSeleccionado = profesional;

                        txtNombre.setText(profesional.getNombre());
                        txtEspecialidad.setText(profesional.getEspecialidad());
                        txtMatricula.setText(profesional.getMatricula());
                        txtCuit.setText(profesional.getCuit());
                        txtPorcentajeAporte.setText(
                                String.valueOf(profesional.getPorcentajeAporte()));

                    }

                });

    }

    @FXML
    private void guardarProfesional(ActionEvent event) {

        try {

            Profesional profesional = new Profesional(
                txtNombre.getText(),
                txtEspecialidad.getText(),
                txtMatricula.getText(),
                Double.parseDouble(txtPorcentajeAporte.getText()),
                txtCuit.getText());

            if (dao.guardar(profesional)) {

                mostrarMensaje("Profesional guardado correctamente.");

                cargarProfesionales();

                limpiarCampos(null);

            }

        } catch (Exception e) {

            mostrarError("Verifique los datos ingresados.");

        }

    }

    @FXML
    private void modificarProfesional(ActionEvent event) {

        if (profesionalSeleccionado == null) {

            mostrarError("Seleccione un profesional.");

            return;

        }

        profesionalSeleccionado.setNombre(txtNombre.getText());
        profesionalSeleccionado.setEspecialidad(txtEspecialidad.getText());
        profesionalSeleccionado.setMatricula(txtMatricula.getText());
        profesionalSeleccionado.setCuit(txtCuit.getText());
        profesionalSeleccionado.setPorcentajeAporte(
                Double.parseDouble(txtPorcentajeAporte.getText()));

        if (dao.modificar(profesionalSeleccionado)) {

            mostrarMensaje("Profesional modificado.");

            cargarProfesionales();

            limpiarCampos(null);

        }

    }

    @FXML
    private void eliminarProfesional(ActionEvent event) {

        if (profesionalSeleccionado == null) {

            mostrarError("Seleccione un profesional.");

            return;

        }

        if (dao.eliminar(profesionalSeleccionado.getIdProfesional())) {

            mostrarMensaje("Profesional eliminado correctamente.");

            cargarProfesionales();

            limpiarCampos(null);

        } else {

            mostrarError("No se puede eliminar el profesional porque posee registros asociados.");

        }

    }

    @FXML
    private void limpiarCampos(ActionEvent event) {

        txtNombre.clear();
        txtEspecialidad.clear();
        txtMatricula.clear();
        txtCuit.clear();
        txtPorcentajeAporte.clear();

        profesionalSeleccionado = null;

        tblProfesionales.getSelectionModel().clearSelection();

    }

    private void cargarProfesionales() {

        ObservableList<Profesional> lista =
                FXCollections.observableArrayList(dao.listar());

        tblProfesionales.setItems(lista);

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