package com.kazmiruk.validators;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Validator {

    private static final String NAME_REG_EXP = "^[A-Z][a-z]+(-[A-Z][a-z]+)?$";

    @FXML
    public boolean numberOfDaysValidation(TextField numOfDaysTextField) {
        try {
            int numOfDays = Integer.parseInt(numOfDaysTextField.getText());
            if (numOfDays > 0 && numOfDays <= 30) {
                numOfDaysTextField.setStyle("-fx-border-color: #8dff8d; -fx-border-width: 2px");
                return true;
            } else {
                numOfDaysTextField.setStyle("-fx-border-color: #ff5757; -fx-border-width: 2px");
                return false;
            }
        } catch (NumberFormatException e) {
            numOfDaysTextField.setStyle("-fx-border-color: #ff5757; -fx-border-width: 2px");
            return false;
        }
    }

    @FXML
    public boolean textFieldValidation(TextField textField) {
        if (!textField.getText().matches(NAME_REG_EXP)) {
            textField.setStyle("-fx-border-color: #ff5757; -fx-border-width: 2px");
            return false;
        } else {
            textField.setStyle("-fx-border-color: #8dff8d; -fx-border-width: 2px");
            return true;
        }
    }

    
}
