package com.kazmiruk.graphical_interface.controlers;
import com.kazmiruk.constans.AlertMessages;
import com.kazmiruk.constans.StageNames;
import com.kazmiruk.db.QueryExecute;
import com.kazmiruk.enums.TicketType;
import com.kazmiruk.enums.Transport;
import com.kazmiruk.enums.TypeOfFood;
import com.kazmiruk.graphical_interface.dialog.AlertDialog;
import com.kazmiruk.ticket.TouristTicket;
import com.kazmiruk.validators.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class WorkWithTicketSceneController implements Initializable {
    @FXML
    private Label title;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private ChoiceBox<TicketType> ticketTypeChoiceBox;
    @FXML
    private ChoiceBox<TypeOfFood> typeOfFoodChoiceBox;
    @FXML
    private ChoiceBox<String> countryChoiceBox;
    @FXML
    private ChoiceBox<String> destinationChoiceBox;
    @FXML
    private DatePicker ticketDate;
    @FXML
    private ChoiceBox<Transport> transportChoiceBox;
    @FXML
    private TextField numOfDaysTextField;
    private QueryExecute queryExecute;

    private int ticketID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        queryExecute = new QueryExecute();
        ticketTypeChoiceBox.setOnAction(this::tryToInitializeCountriesChoiceBox);
        countryChoiceBox.setOnAction(this::tryToInitializeDestinationsChoiceBox);
        typeOfFoodChoiceBox.getItems().addAll(TypeOfFood.values());
        transportChoiceBox.getItems().addAll(Transport.values());
        ticketTypeChoiceBox.getItems().addAll(TicketType.values());
        ticketDate.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(LocalDate.now()) < 0);
            }
        });
    }

    /**
     * The function of the button depends on which stage is open,
     * if it is add Ticket Stage, the ticket will be added to the database,
     * if it is edit ticket stage, the ticket will be overwritten in the database.
     * If there are unfilled fields or TextField whose values are entered incorrectly,
     * a message window appears and the method terminates
     *
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */
    @FXML
    public void confirm(ActionEvent event) throws SQLException {
        if (!isFieldsFilledCorrectly()) {
            AlertDialog.display(StageNames.WARNING, AlertMessages.CHECK_CORRECTNESS_WARNING);
            return;
        } else if (isEmptyFields()) {
            AlertDialog.display(StageNames.WARNING, AlertMessages.FIELDS_MUST_BE_FIELD_WARNING);
            return;
        }

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
        if (stage.getTitle().equals(StageNames.ADD_TICKET)) {
            addTicket();
        } else {
            updateTicket();
        }
    }

    /**
     * Checks whether there are empty fields
     *
     * @return whether there are emptty fields
     */
    @FXML
    private boolean isEmptyFields() {
        return nameTextField.getText().isEmpty()
                || surnameTextField.getText().isEmpty()
                || ticketTypeChoiceBox.getValue() == null
                || countryChoiceBox.getValue() == null || countryChoiceBox.getValue().isEmpty()
                || destinationChoiceBox.getValue() == null || destinationChoiceBox.getValue().isEmpty()
                || ticketDate.getValue() == null
                || transportChoiceBox.getValue() == null
                || typeOfFoodChoiceBox.getValue() == null
                || numOfDaysTextField.getText().isEmpty();
    }

    /**
     * Сhecks whether the values
     * entered in the TextField are correct
     *
     * @return are the values in the TextFields correct
     */
    @FXML
    private boolean isFieldsFilledCorrectly() {
        Validator validator = new Validator();
        return validator.textFieldValidation(nameTextField)
                && validator.textFieldValidation(surnameTextField)
                && validator.numberOfDaysValidation(numOfDaysTextField);
    }

    @FXML
    public void addTicket() throws SQLException {
        TouristTicket touristTicket = createTouristTicketFromFields();
        queryExecute.addTicket(touristTicket);
        AlertDialog.display(StageNames.INFO, AlertMessages.TICKET_ADDED);
    }

    @FXML
    public void updateTicket() throws SQLException {
        TouristTicket touristTicket = createTouristTicketFromFields();
        queryExecute.updateTicketByID(ticketID, touristTicket);
        AlertDialog.display(StageNames.INFO, AlertMessages.TICKED_CHANGED);
    }

    /**
     * Creates a tourist ticket object and initializes its fields with values from the scene fields
     *
     * @return An object representing a tourist ticket
     */
    @FXML
    private TouristTicket createTouristTicketFromFields() {
        return TouristTicket.builder()
                .setName(nameTextField.getText())
                .setSurname(surnameTextField.getText())
                .setTicketType(ticketTypeChoiceBox.getValue())
                .setCountry(countryChoiceBox.getValue())
                .setDestination(destinationChoiceBox.getValue())
                .setTransport(transportChoiceBox.getValue())
                .setTypeOfFood(typeOfFoodChoiceBox.getValue())
                .setDate(ticketDate.getValue())
                .setNumberOfDays(Integer.valueOf(numOfDaysTextField.getText()))
                .build();
    }

    @FXML
    public void tryToInitializeCountriesChoiceBox(ActionEvent event)  {
        countryChoiceBox.setValue("");
        countryChoiceBox.setDisable(false);
        destinationChoiceBox.setValue("");
        destinationChoiceBox.setDisable(true);

        try {
            initializeCountryChoiceBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void initializeCountryChoiceBox() throws SQLException {
        int ticketTypeID = queryExecute.getTicketTypeIDByValue(ticketTypeChoiceBox.getValue().toString());
        countryChoiceBox.getItems().clear();
        countryChoiceBox.getItems().addAll(queryExecute.getCountriesByTicketID(ticketTypeID));
    }

    @FXML
    public void tryToInitializeDestinationsChoiceBox(ActionEvent event) {
        if (countryChoiceBox.getValue().equals(""))
            return;
        destinationChoiceBox.setDisable(false);
        destinationChoiceBox.setValue("");

        try {
            initializeDestinationsChoiceBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Initializes the DestinationChoiceBox depending on what values
     * were selected in TicketTypeChoiceBox and CountryChoiceBox
     *
     * @throws SQLException An exception that provides information on a database access error or other errors.
     */
    @FXML
    private void initializeDestinationsChoiceBox() throws SQLException {
        int ticketTypeID = queryExecute.getTicketTypeIDByValue(ticketTypeChoiceBox.getValue().toString());
        int countryID = queryExecute.getCountryIDByTicketTypeIDAndValue(ticketTypeID, countryChoiceBox.getValue());
        destinationChoiceBox.getItems().clear();
        destinationChoiceBox.getItems().addAll(queryExecute.getDestinationsByCountryID(countryID));
    }


    /**
     * Sets the values of scene elements
     *
     * @param touristTicket The object from which data is taken to determine the elements
     */
    @FXML
    public void defineFields(TouristTicket touristTicket) {
        ticketID = touristTicket.getTicketId();
        nameTextField.setText(touristTicket.getName());
        surnameTextField.setText(touristTicket.getSurname());
        ticketTypeChoiceBox.setValue(touristTicket.getTicketType());
        countryChoiceBox.setValue(touristTicket.getCountry());
        destinationChoiceBox.setValue(touristTicket.getDestination());
        ticketDate.setValue(touristTicket.getDate());
        transportChoiceBox.setValue(touristTicket.getTransport());
        typeOfFoodChoiceBox.setValue(touristTicket.getTypeOfFood());
        numOfDaysTextField.setText(touristTicket.getNumberOfDays().toString());
    }

    @FXML
    public void nameValidation() {
        new Validator().textFieldValidation(nameTextField);
    }

    @FXML
    public void surnameValidation() {
        new Validator().textFieldValidation(surnameTextField);
    }

    @FXML
    public void numberOfDaysValidation() { new Validator().numberOfDaysValidation(numOfDaysTextField); }

    @FXML
    public void setTitleText(String text) { title.setText(text);}
}
