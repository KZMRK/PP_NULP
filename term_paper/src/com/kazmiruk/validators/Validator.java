package com.kazmiruk.validators;


import javafx.scene.control.TextField;

public class Validator {

    private static final String nameRegExp = "^[A-Z][a-z]+(-[A-Z][a-z]+)?$";

    public boolean numberOfDaysValidation(TextField numOfDaysTextField) {
        try {
            int numOfDays = Integer.parseInt(numOfDaysTextField.getText());
            if (numOfDays > 0 && numOfDays < 30) {
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

    public boolean textFieldValidation(TextField textField) {
        if (!textField.getText().matches(nameRegExp)) {
            textField.setStyle("-fx-border-color: #ff5757; -fx-border-width: 2px");
            return false;
        } else {
            textField.setStyle("-fx-border-color: #8dff8d; -fx-border-width: 2px");
            return true;
        }
    }

    
}
