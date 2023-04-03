package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.RegistracijaManager;
import ba.unsa.etf.rpr.domain.Korisnik;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistracijaController {

    private RegistracijaManager registracijaManager = new RegistracijaManager();
    public TextField korisnickoIme;
    public TextField sifra;
    public TextField potrvdaSifre;

    public void pritisniRegistrujteSe(ActionEvent actionEvent) {
        try {
            Korisnik uspjesnaRegistracija = registracijaManager.registrujKorisnika(korisnickoIme.getText(), sifra.getText(), potrvdaSifre.getText());
            Stage stage = (Stage) korisnickoIme.getScene().getWindow();
            stage.close();
            Stage appStage = new Stage();
            AppController appController = new AppController(uspjesnaRegistracija);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/app.fxml"));
            loader.setController(appController);
            Parent root = loader.load();
            appStage.setTitle("Todo aplikacija");
            appStage.setScene(new Scene(root));
            appStage.setResizable(false);
            appStage.show();
        } catch (Exception e) {
            String poruka = e.getMessage();
            if(e.getMessage().contains("Duplicate entry"))
                poruka = "Korisnik sa navedenim korisničkim imenom već postoji";
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Neuspjela registracija");
            alert.setContentText(poruka);
            alert.show();
        }
    }

    public void pritisniPrijaviteSe(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) korisnickoIme.getScene().getWindow();
        stage.close();
        Stage prijaviteSeStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        prijaviteSeStage.setTitle("Todo aplikacija - Prijava");
        prijaviteSeStage.setScene(new Scene(root));
        prijaviteSeStage.setResizable(false);
        prijaviteSeStage.show();
    }
}
