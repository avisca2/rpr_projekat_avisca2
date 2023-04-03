package ba.unsa.etf.rpr.controllers;


import ba.unsa.etf.rpr.business.AppManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Korisnik;
import ba.unsa.etf.rpr.domain.TodoStatus;
import ba.unsa.etf.rpr.domain.TodoStavka;
import ba.unsa.etf.rpr.exceptions.NeispravniPodaciException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


public class AppController {

    private AppManager appManager = new AppManager();
    public Label todoStavkaPoruka;
    public Button todoStavkaDodajDugme;
    public Button todoStavkaAzurirajDugme;
    public Button todoStavkaObrisiDugme;
    public TableView<TodoStatus> todoStatusiTabela = new TableView<>();
    public TableColumn<TodoStatus, Integer> todoStatusIdKolona = new TableColumn<>();
    public TableColumn<TodoStatus, String> todoStatusNazivKolona = new TableColumn<>();
    public List<TodoStatus> todoStatusi;
    public ObservableList<TodoStatus> listaTodoStatusi = FXCollections.observableArrayList();
    public TableView<TodoStavka> todoStavkeTabela = new TableView<>();
    public TableColumn<TodoStavka, Integer> todoStavkaIdKolona = new TableColumn<>();
    public TableColumn<TodoStavka, String> todoStavkaNazivKolona = new TableColumn<>();
    public TableColumn<TodoStavka, String> todoStavkaTipKolona = new TableColumn<>();
    public TableColumn<TodoStavka, TodoStatus> todoStavkaStatusKolona = new TableColumn<>();
    public List<TodoStavka> todoStavke;
    public ObservableList<TodoStavka> listaTodoStavke = FXCollections.observableArrayList();
    private Korisnik korisnik;

    public AppController(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    private void todoStavkaTabPoruka() {
        if(listaTodoStatusi.size() == 0) {
            todoStavkaPoruka.setText("Nije moguće kreirati Todo stavku dok god nema barem jednog Todo statusa");
            todoStavkaPoruka.getStyleClass().add("crveni-tekst");
            todoStavkaDodajDugme.setDisable(true);
            todoStavkaAzurirajDugme.setDisable(true);
            todoStavkaObrisiDugme.setDisable(true);
        } else {
            todoStavkaPoruka.setText("Panel za kreiranje Todo stavki");
            todoStavkaPoruka.getStyleClass().removeAll("crveni-tekst");
            todoStavkaDodajDugme.setDisable(false);
            todoStavkaAzurirajDugme.setDisable(false);
            todoStavkaObrisiDugme.setDisable(false);
        }
    }

    @FXML
    public void initialize() throws NeispravniPodaciException {
        todoStatusIdKolona.setCellValueFactory(new PropertyValueFactory<>("id"));
        todoStatusNazivKolona.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        todoStatusi = appManager.ucitajTodoStatuse();
        listaTodoStatusi.addAll(todoStatusi);
        todoStatusiTabela.setItems(listaTodoStatusi);
        todoStavkaTabPoruka();

        todoStavkaIdKolona.setCellValueFactory(new PropertyValueFactory<>("id"));
        todoStavkaNazivKolona.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        todoStavkaTipKolona.setCellValueFactory(new PropertyValueFactory<>("tip"));
        todoStavkaStatusKolona.setCellValueFactory(new PropertyValueFactory<>("status"));
        todoStavke = appManager.ucitajTodoStavke(korisnik.getId());
        listaTodoStavke.addAll(todoStavke);
        todoStavkeTabela.setItems(listaTodoStavke);
    }
    public void klikniOdjava(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) todoStatusiTabela.getScene().getWindow();
        stage.close();
        Stage appStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        appStage.setTitle("Todo aplikacija - prijava");
        appStage.setScene(new Scene(root));
        appStage.setResizable(false);
        appStage.show();
    }

    public void klikniDodajTodoStatus(ActionEvent actionEvent) throws IOException {

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/dodajTodoStatus.fxml"));
            DodajIliAzurirajTodoStatusController dodajTodoStatusController = new DodajIliAzurirajTodoStatusController();
            loader.setController(dodajTodoStatusController);
            Parent root = loader.load();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Todo aplikacija - Dodaj Todo status");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setOnHidden(windowEvent -> {
                if(dodajTodoStatusController.jeLiZavrseno()) {
                    TodoStatus dodaniTodoStatus = dodajTodoStatusController.dajTodoStatus();
                    todoStatusi.add(dodaniTodoStatus);
                    listaTodoStatusi.clear();
                    listaTodoStatusi.addAll(todoStatusi);
                    todoStatusiTabela.setItems(listaTodoStatusi);
                    todoStavkaTabPoruka();
                }
            });
            stage.show();
    }

    public void klikniAzurirajTodoStatus(ActionEvent actionEvent) throws IOException {

        TodoStatus todoStatus = todoStatusiTabela.getSelectionModel().getSelectedItem();

        if(todoStatus == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Ažuriranje nije moguće");
            alert.setContentText("Nije odabrana niti jedan Todo status");
            alert.show();
        } else {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/dodajTodoStatus.fxml"));
            DodajIliAzurirajTodoStatusController dodajTodoStatusController = new DodajIliAzurirajTodoStatusController(todoStatus);
            loader.setController(dodajTodoStatusController);
            Parent root = loader.load();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Todo aplikacija - Azuriraj Todo status");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setOnHidden(windowEvent -> {
                if(dodajTodoStatusController.jeLiZavrseno()) {
                    TodoStatus azuriraniTodoStatus = dodajTodoStatusController.dajTodoStatus();
                    for(int i = 0; i < todoStatusi.size(); i++) {
                        if(todoStatusi.get(i).getId() == azuriraniTodoStatus.getId()) {
                            todoStatusi.set(i, azuriraniTodoStatus);
                        }
                    }
                    listaTodoStatusi.clear();
                    listaTodoStatusi.addAll(todoStatusi);
                    todoStatusiTabela.setItems(listaTodoStatusi);
                    todoStavkaTabPoruka();
                }
            });
            stage.show();
        }
    }

    public void klikniObrisiTodoStatus(ActionEvent actionEvent) {
        TodoStatus todoStatus = todoStatusiTabela.getSelectionModel().getSelectedItem();

        if(todoStatus == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Brisanje nije moguće");
            alert.setContentText("Nije odabran niti jedan Todo status");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Potvrda");
            alert.setHeaderText(todoStatus.getNaziv());
            alert.setContentText("Jeste li sigurni da želite obrisati ovaj Todo status?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK) {
                try {
                    appManager.obrisiTodoStatus(todoStatus.getId());
                    for(int i = 0; i < todoStatusi.size(); i++) {
                        if(todoStatusi.get(i).getId() == todoStatus.getId()) {
                            todoStatusi.remove(i);
                        }
                    }
                    listaTodoStatusi.clear();
                    listaTodoStatusi.addAll(todoStatusi);
                    todoStatusiTabela.setItems(listaTodoStatusi);
                    todoStavkaTabPoruka();
                } catch (NeispravniPodaciException e) {
                    String poruka = e.getMessage();
                    if(e.getMessage().contains("foreign key constraint"))
                        poruka = "Todo status u upotrebi - nije moguće brisanje";
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Greška");
                    a.setHeaderText("Neuspjelo brisanje Todo statusa");
                    a.setContentText(poruka);
                    a.show();
                }
            }
        }
    }

    public void klikniDodajTodoStavku(ActionEvent actionEvent) throws IOException {

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/dodajTodoStavku.fxml"));
        DodajIliAzurirajTodoStavkuController dodajIliAzurirajTodoStavkuController = new DodajIliAzurirajTodoStavkuController(korisnik);
        loader.setController(dodajIliAzurirajTodoStavkuController);
        Parent root = loader.load();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Todo aplikacija - Dodaj Todo stavku");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setOnHidden(windowEvent -> {
            if(dodajIliAzurirajTodoStavkuController.jeLiZavrseno()) {
                TodoStavka dodanaTodoStavka = dodajIliAzurirajTodoStavkuController.dajTodoStavku();
                todoStavke.add(dodanaTodoStavka);
                listaTodoStavke.clear();
                listaTodoStavke.addAll(todoStavke);
                todoStavkeTabela.setItems(listaTodoStavke);
            }
        });
        stage.show();
    }

    public void klikniAzurirajTodoStavku(ActionEvent actionEvent) throws IOException {

        TodoStavka todoStavka = todoStavkeTabela.getSelectionModel().getSelectedItem();

        if(todoStavka == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Ažuriranje nije moguće");
            alert.setContentText("Nije odabrana niti jedna Todo stavka");
            alert.show();
        } else {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/dodajTodoStavku.fxml"));
            DodajIliAzurirajTodoStavkuController dodajIliAzurirajTodoStavkuController = new DodajIliAzurirajTodoStavkuController(korisnik, todoStavka);
            loader.setController(dodajIliAzurirajTodoStavkuController);
            Parent root = loader.load();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Todo aplikacija - Azuriraj Todo stavku");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setOnHidden(windowEvent -> {
                if(dodajIliAzurirajTodoStavkuController.jeLiZavrseno()) {
                    TodoStavka azuriranaTodoStavka = dodajIliAzurirajTodoStavkuController.dajTodoStavku();
                    for(int i = 0; i < todoStavke.size(); i++) {
                        if(todoStavke.get(i).getId() == azuriranaTodoStavka.getId()) {
                            todoStavke.set(i, azuriranaTodoStavka);
                        }
                    }
                    listaTodoStavke.clear();
                    listaTodoStavke.addAll(todoStavke);
                    todoStavkeTabela.setItems(listaTodoStavke);
                }
            });
            stage.show();
        }
    }

    public void klikniObrisiTodoStavku(ActionEvent actionEvent) throws NeispravniPodaciException {
        TodoStavka todoStavka = todoStavkeTabela.getSelectionModel().getSelectedItem();

        if(todoStavka == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Brisanje nije moguće");
            alert.setContentText("Nije odabrana niti jedna Todo stavka");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Potvrda");
            alert.setHeaderText(todoStavka.getNaziv());
            alert.setContentText("Jeste li sigurni da želite obrisati ovu Todo stavku?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK) {
                appManager.obrisiTodoStavku(todoStavka.getId());
                for(int i = 0; i < todoStavke.size(); i++) {
                    if(todoStavke.get(i).getId() == todoStavka.getId()) {
                        todoStavke.remove(i);
                    }
                }
                listaTodoStavke.clear();
                listaTodoStavke.addAll(todoStavke);
                todoStavkeTabela.setItems(listaTodoStavke);
            }
        }
    }
}
