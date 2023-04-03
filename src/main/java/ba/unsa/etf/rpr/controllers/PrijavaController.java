package ba.unsa.etf.rpr.controllers;


import ba.unsa.etf.rpr.business.PrijavaManager;
import ba.unsa.etf.rpr.domain.Korisnik;
import ba.unsa.etf.rpr.exceptions.NeispravniPodaciException;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


public class PrijavaController {

    private PrijavaManager prijavaManager = new PrijavaManager();

    public TextField korisnickoIme;
    public TextField sifra;

    public void pritisniPrijaviteSe(ActionEvent actionEvent) throws IOException {
        try {
            Korisnik uspjesnaPrijava = prijavaManager.prijaviKorisnika(korisnickoIme.getText(), sifra.getText());
            if(uspjesnaPrijava != null) {
                Stage stage = (Stage) korisnickoIme.getScene().getWindow();
                stage.close();
                Stage appStage = new Stage();
                AppController appController = new AppController(uspjesnaPrijava);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/app.fxml"));
                loader.setController(appController);
                Parent root = loader.load();
                appStage.setTitle("Todo aplikacija");
                appStage.setScene(new Scene(root));
                appStage.setResizable(false);
                appStage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška");
                alert.setHeaderText("Neuspjela prijava");
                alert.setContentText("Niste unijeli ispravno korisničko ime i šifru");
                alert.show();
            }
        } catch (NeispravniPodaciException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Neuspjela prijava");
            alert.setContentText("Niste unijeli ispravno korisničko ime i šifru");
            alert.show();
        }
    }

    public void pritisniRegistrujteSe(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) korisnickoIme.getScene().getWindow();
        stage.close();
        Stage registrujteSeStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/register.fxml"));
        registrujteSeStage.setTitle("Todo aplikacija - Registracija");
        registrujteSeStage.setScene(new Scene(root));
        registrujteSeStage.setResizable(false);
        registrujteSeStage.show();
    }
}
