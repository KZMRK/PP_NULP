package com.kazmiruk.graphical_interface.controlers;

import com.kazmiruk.constans.AlertMessages;
import com.kazmiruk.constans.Paths;
import com.kazmiruk.constans.StageNames;
import com.kazmiruk.db.QueryExecute;
import com.kazmiruk.graphical_interface.dialog.AlertDialog;
import com.kazmiruk.enums.TicketType;
import com.kazmiruk.enums.Transport;
import com.kazmiruk.enums.TypeOfFood;
import com.kazmiruk.ticket.TouristTicket;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable {
    @FXML
    private TableView<TouristTicket> table;
    @FXML
    private TableColumn<TouristTicket, Integer> id;
    @FXML
    private TableColumn<TouristTicket, String> name;
    @FXML
    private TableColumn<TouristTicket, String> surname;
    @FXML
    private TableColumn<TouristTicket, TicketType> ticketType;
    @FXML
    private TableColumn<TouristTicket, String> country;
    @FXML
    private TableColumn<TouristTicket, String> destination;
    @FXML
    private TableColumn<TouristTicket, Transport> transport;
    @FXML
    private TableColumn<TouristTicket, TypeOfFood> typeOfFood;
    @FXML
    private TableColumn<TouristTicket, LocalDate> date;
    @FXML
    private TableColumn<TouristTicket, Integer> numOfDays;
    @FXML
    private Stage addTicketStage, editTicketStage;

    private QueryExecute queryExecute;

    /**
     * Initializes the columns of the table, creates a stage
     * for adding and editing a ticket and enters data from
     * database into the table
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        queryExecute = new QueryExecute();
        id.setCellValueFactory(new PropertyValueFactory<>("ticketId"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        ticketType.setCellValueFactory(new PropertyValueFactory<>("ticketType"));
        country.setCellValueFactory(new PropertyValueFactory<>("country"));
        destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        transport.setCellValueFactory(new PropertyValueFactory<>("transport"));
        typeOfFood.setCellValueFactory(new PropertyValueFactory<>("typeOfFood"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        numOfDays.setCellValueFactory(new PropertyValueFactory<>("numberOfDays"));

        tryToCreateAddTicketStage();
        tryToCreateEditTicketStage();
        tryToRefreshTable();
    }

    @FXML
    private void tryToCreateAddTicketStage() {
        try {
            addTicketStage = createStageThatWorksWithTicket(StageNames.ADD_TICKET);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void tryToCreateEditTicketStage() {
        try {
            editTicketStage = createStageThatWorksWithTicket(StageNames.EDIT_TICKET);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private Stage createStageThatWorksWithTicket(String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.WORK_WITH_TICKET_SCENE));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        Image icon = new Image(Paths.ICON);
        stage.getIcons().add(icon);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        return stage;
    }

    @FXML
    private void tryToRefreshTable() {
        try {
            refreshTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Selects all tickets from the database and
     * lists them in the TableView
     *
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */
    @FXML
    public void refreshTable() throws SQLException {
        ObservableList <TouristTicket> data = queryExecute.selectTickets();
        table.setItems(data);
    }

    /**
     * Removes the selected ticket from the TableView and the database
     * If the ticket is not selected, a message window appears and the method terminates
     *
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */
    @FXML
    public void removeTicket() throws SQLException {
        TouristTicket selectedTicket = table.getSelectionModel().getSelectedItem();

        if (selectedTicket == null) {
            AlertDialog.display(StageNames.INFO, AlertMessages.DELETE_INFO_MSG);
            return;
        }
        queryExecute.deleteTicketByID(selectedTicket.getTicketId());
        refreshTable();
        AlertDialog.display(StageNames.INFO, AlertMessages.TICKET_DELETED);
    }

    /**
     * Opens the editing window for the ticket selected from the table
     * If the ticket is not selected, a message box appears and the method terminates
     *
     * @throws IOException An exception that provides information on a database access error or other errors.
     */
    @FXML
    public void editTicket() throws IOException {
        TouristTicket selectedTicket = table.getSelectionModel().getSelectedItem();

        if (selectedTicket == null) {
            AlertDialog.display(StageNames.INFO, AlertMessages.EDIT_INFO_MSG);
            return;
        }

        defineEditTicketStage(selectedTicket);
        editTicketStage.show();
    }

    /**
     * Defines the label name of the edit ticket stage
     * and calls a method to define all elements of the scene
     *
     * @param touristTicket Selected ticket for editing
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     */
    @FXML
    private void defineEditTicketStage(TouristTicket touristTicket) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.WORK_WITH_TICKET_SCENE));
        Parent root = loader.load();
        WorkWithTicketSceneController workWithTicketSceneController = loader.getController();
        workWithTicketSceneController.defineFields(touristTicket);
        workWithTicketSceneController.setTitleText(StageNames.EDIT_TICKET);
        editTicketStage.setScene(new Scene(root));
    }

    /**
     * Opens the add ticket window
     *
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     */
    @FXML
    public void addTicket() throws IOException {
        defineAddTicketScene();
        addTicketStage.show();
    }

    /**
     * Defines the label name of the ticket add stage
     *
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     */
    @FXML
    private void defineAddTicketScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.WORK_WITH_TICKET_SCENE));
        Parent root = loader.load();
        WorkWithTicketSceneController workWithTicketSceneController = loader.getController();
        workWithTicketSceneController.setTitleText(StageNames.ADD_TICKET);
        addTicketStage.setScene(new Scene(root));
        addTicketStage.show();
    }
}
