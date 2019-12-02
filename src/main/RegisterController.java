package main;

import javafx.fxml.FXML;
import javafx.collections.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.*;
import javafx.scene.control.Button;
import javafx.util.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.css.*;
import javafx.scene.control.*;
import javafx.scene.control.ComboBox;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class RegisterController extends Main {

    String [] l =new String[5];

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField university;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passWordField;

    @FXML
    private ChoiceBox prefix;

    @FXML
    private ChoiceBox roles;

    String [] sql ={"Drop"};

    public void initialize() {
        List<String> list = new ArrayList<String>();
        list.add("Mr.");
        list.add("Ms.");
        list.add("Dr.");
        list.add("Prof.");
        ObservableList obList = FXCollections.observableList(list);
        prefix.setItems(obList);

        List<String> rolesList = new ArrayList<String>();
        rolesList.add("Author");
        rolesList.add("Editor");
        ObservableList rlList = FXCollections.observableList(rolesList);
        roles.setItems(rlList);
    }

        public void handleRegisterSuccess(ActionEvent action) throws IOException {
        // first name
        if (firstName.getText().isEmpty()){
            firstName.setStyle("-fx-prompt-text-fill : red;");
        }
        else
            l[0]=firstName.getText();
        // last name
        if (lastName.getText().isEmpty()){
            lastName.setStyle("-fx-prompt-text-fill : red;");
        }
        else
            l[1]=lastName.getText();
        // university
        if (university.getText().isEmpty()){
            university.setStyle("-fx-prompt-text-fill : red;");
        }
        else
            l[2]=university.getText();

        //emailField
        if (emailField.getText().isEmpty()){
            emailField.setStyle("-fx-prompt-text-fill : red;");
        }
        else if (Pattern.matches("[a-zA-Z]+[@][a-zA-z]+[.][A-Za-z.]+",emailField.getText())){
            l[3]=emailField.getText();
        }
        else if (!Pattern.matches("[a-zA-Z]+[@][a-zA-z]+[.][A-Za-z.]+",emailField.getText())){
            emailField.setStyle("-fx-prompt-text-fill : red;");
            emailField.clear();
            emailField.setPromptText("not valid email type");
        }
        // password
        if (passWordField.getText().isEmpty()){
            passWordField.setStyle("-fx-prompt-text-fill : red;");
        }
        else if (Pattern.matches("[a-zA-Z0-9[^\\dA-Za-zA-Za-z0-9]]{6,}",passWordField.getText())){
            l[4]=passWordField.getText();
           // System.out.println("N");
        }
        else if (!Pattern.matches("[a-zA-Z0-9[^\\dA-Za-zA-Za-z0-9]]{6,}",passWordField.getText())) {
            passWordField.setStyle("-fx-prompt-text-fill : red;");
            passWordField.clear();
            passWordField.setPromptText("must be 6 letters and above");
        }

        String prefixValue = (String) prefix.getValue();
        String roleValue = (String) roles.getValue();

        if (!l[0].isEmpty() && !l[1].isEmpty() && !l[2].isEmpty() && !l[3].isEmpty()&& !l[4].isEmpty()){

            if (roleValue == "Author") {
                try {
                    AuthorTable.Insert(prefixValue, firstName.getText(), lastName.getText(), university.getText(), emailField.getText(), Integer.toString(passWordField.getText().hashCode()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    EditorTable.Insert(prefixValue, firstName.getText(), lastName.getText(), university.getText(), emailField.getText(), Integer.toString(passWordField.getText().hashCode()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            URL url = new File("src/resources/login.fxml").toURI().toURL();
            Parent view = FXMLLoader.load(url);
            Scene viewScene = new Scene(view);

            Stage window = (Stage)((Node)action.getSource()).getScene().getWindow();
            window.setResizable(true);
            window.setScene(viewScene);

        }


    }



}
