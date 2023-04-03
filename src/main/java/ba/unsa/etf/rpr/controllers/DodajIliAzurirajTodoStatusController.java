package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.DodajIliAzurirajTodoStatusManager;
import ba.unsa.etf.rpr.domain.TodoStatus;
import ba.unsa.etf.rpr.exceptions.NeispravniPodaciException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DodajIliAzurirajTodoStatusController {

    public DodajIliAzurirajTodoStatusManager dodajIliAzurirajTodoStatusManager = new DodajIliAzurirajTodoStatusManager();
    private TodoStatus todoStatus = null;
    private TodoStatus noviTodoStatus = null;
    public Button dodajDugme;
    public TextField naziv;
    private boolean zavrseno = false;

    public boolean jeLiZavrseno() {
        return zavrseno;
    }

    public DodajIliAzurirajTodoStatusController() {

    }

    public DodajIliAzurirajTodoStatusController(TodoStatus todoStatus) {
        this.todoStatus = todoStatus;
    }

    @FXML
    public void initialize() {
        if(todoStatus != null) {
            naziv.setText(todoStatus.getNaziv());
            dodajDugme.setText("Ažuriraj");
        }
    }

    public void klikniDodajIliAzuriraj(ActionEvent actionEvent) {
        if(todoStatus == null) {
            try {
                noviTodoStatus = dodajIliAzurirajTodoStatusManager.dodajTodoStatus(naziv.getText());
                zavrseno = true;
                Stage stage = (Stage) naziv.getScene().getWindow();
                stage.close();
            }catch (NeispravniPodaciException e) {
                String poruka = e.getMessage();
                if(e.getMessage().contains("Duplicate entry"))
                    poruka = "Todo status sa navedenim nazivom već postoji";
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška");
                alert.setHeaderText("Neuspjelo kreiranje Todo statusa");
                alert.setContentText(poruka);
                alert.show();
            }
        } else {
            try {
                TodoStatus temp = new TodoStatus(naziv.getText());
                temp.setId(todoStatus.getId());
                dodajIliAzurirajTodoStatusManager.azurirajTodoStatus(temp);
                zavrseno = true;
                Stage stage = (Stage) naziv.getScene().getWindow();
                stage.close();
            }catch (NeispravniPodaciException e) {
                String poruka = e.getMessage();
                if(e.getMessage().contains("Duplicate entry"))
                    poruka = "Todo status sa navedenim nazivom već postoji";
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška");
                alert.setHeaderText("Neuspjelo ažuriranje Todo statusa");
                alert.setContentText(poruka);
                alert.show();
            }
        }
    }


    public TodoStatus dajTodoStatus() {
        if(todoStatus != null) {
            todoStatus.setNaziv(naziv.getText());
            return todoStatus;
        }

        return noviTodoStatus;
    }

}
