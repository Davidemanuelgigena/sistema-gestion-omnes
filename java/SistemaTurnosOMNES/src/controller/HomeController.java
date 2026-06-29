package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;

public class HomeController implements Initializable {

    @FXML
    private ImageView imgLogo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Image logo = new Image(
                getClass().getResourceAsStream(
                        "/resources/img/Logo_omnes_3d.png"));

        imgLogo.setImage(logo);

    }

    @FXML
    private void iniciarSistema(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/Principal.fxml"));

        Parent root = loader.load();

        // Reutiliza la misma ventana (Stage)
        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.setScene(new Scene(root));
        stage.setTitle("Sistema Integral Fundación OMNES");
        stage.setResizable(false);
        stage.show();

    }

}