package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.DodajIliAzurirajTodoStatusManager;
import ba.unsa.etf.rpr.business.DodajIliAzurirajTodoStavkuManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Korisnik;
import ba.unsa.etf.rpr.domain.TodoStatus;
import ba.unsa.etf.rpr.domain.TodoStavka;
import ba.unsa.etf.rpr.exceptions.NeispravniPodaciException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class DodajIliAzurirajTodoStavkuController {

    public DodajIliAzurirajTodoStavkuManager dodajIliAzurirajTodoStavkuManager = new DodajIliAzurirajTodoStavkuManager();
    private TodoStavka todoStavka = null;
    private TodoStavka novaTodoStavka = null;
    public Button dodajDugme;
    public TextField naziv;
    public TextField tip;
    public ChoiceBox<TodoStatus> todoStatusi;
    private boolean zavrseno = false;

    private Korisnik korisnik = null;

    public boolean jeLiZavrseno() {
        return zavrseno;
    }

    public DodajIliAzurirajTodoStavkuController(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public DodajIliAzurirajTodoStavkuController(Korisnik korisnik, TodoStavka todoStavka) {
        this.todoStavka = todoStavka;
        this.korisnik = korisnik;
    }

    @FXML
    public void initialize() throws NeispravniPodaciException {
        List<TodoStatus> temp = dodajIliAzurirajTodoStavkuManager.ucitajTodoStatuse();
        ObservableList<TodoStatus> observableTemp = FXCollections.observableArrayList();
        observableTemp.addAll(temp);
        todoStatusi.setItems(observableTemp);
        if(todoStavka != null) {
            naziv.setText(todoStavka.getNaziv());
            tip.setText(todoStavka.getTip());
            todoStatusi.getSelectionModel().select(todoStavka.getStatus());
            dodajDugme.setText("Ažuriraj");
        }
    }

    public void klikniDodajIliAzuriraj(ActionEvent actionEvent) {
        if(todoStavka == null) {
            try {
                novaTodoStavka = dodajIliAzurirajTodoStavkuManager.dodajTodoStavku(new TodoStavka(naziv.getText(), tip.getText(), todoStatusi.getSelectionModel().getSelectedItem(), korisnik));
                zavrseno = true;
                Stage stage = (Stage) naziv.getScene().getWindow();
                stage.close();
            }catch (NeispravniPodaciException e) {
                String poruka = e.getMessage();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška");
                alert.setHeaderText("Neuspjelo kreiranje Todo stavke");
                alert.setContentText(poruka);
                alert.show();
            }
        } else {
            try {
                TodoStavka temp = new TodoStavka(naziv.getText(), tip.getText(), todoStatusi.getSelectionModel().getSelectedItem(), korisnik);
                temp.setId(todoStavka.getId());
                dodajIliAzurirajTodoStavkuManager.azurirajTodoStavku(temp);
                zavrseno = true;
                Stage stage = (Stage) naziv.getScene().getWindow();
                stage.close();
            }catch (NeispravniPodaciException e) {
                String poruka = e.getMessage();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška");
                alert.setHeaderText("Neuspjelo kreiranje Todo stavke");
                alert.setContentText(poruka);
                alert.show();
            }
        }
    }


    public TodoStavka dajTodoStavku() {
        if(todoStavka != null) {
            todoStavka.setNaziv(naziv.getText());
            todoStavka.setTip(tip.getText());
            todoStavka.setStatus(todoStatusi.getSelectionModel().getSelectedItem());
            return todoStavka;
        }

        return novaTodoStavka;
    }

}
