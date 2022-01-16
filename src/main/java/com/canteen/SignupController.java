package com.canteen;

import static com.canteen.App.dbHost;
import static com.canteen.App.dbLogin;
import static com.canteen.App.dbName;
import static com.canteen.App.dbPassword;
import java.io.IOException;
import javafx.fxml.FXML;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class SignupController {

    @FXML
    private void switchToSignin() throws IOException {
        App.setRoot("signin");
    }

    @FXML
    private DatePicker date_of_birth;

    @FXML
    private TextField fio;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField password_repeat;

    @FXML
    private Label hidden_label;

    @FXML
    private RadioButton admin;

    @FXML
    private void signup() throws IOException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + dbHost + "/" + dbName, dbLogin, dbPassword);
            Statement statement = conn.createStatement();
            if (fio.getText().equals("") || date_of_birth.getValue() == null || login.getText().equals("") || password.getText().equals("")) {
                hidden_label.setText("Все поля обязательны для заполнения!");
                hidden_label.setVisible(true);
            } else {
                ResultSet loginCheck = statement.executeQuery("SELECT `login` FROM `users` WHERE `login` LIKE" + "'" + login.getText() + "'");
                if (loginCheck.next()) {
                    hidden_label.setText("Такой логин уже есть!");
                    hidden_label.setVisible(true);
                } else if (login.getText().length() > 12) {
                    hidden_label.setText("Логин не может содержать больше 12 символов!");
                    hidden_label.setVisible(true);
                } else if (!(password.getText().equals(password_repeat.getText()))) {
                    hidden_label.setText("Пароли не совпадают!");
                    hidden_label.setVisible(true);
                } else {
                    int role = 0;
                    if (admin.isSelected()) {
                        role = 1;
                    }
                    String date_string = date_of_birth.getValue().toString();
                    User.addUser(fio.getText(), date_string, login.getText(), password.getText(), role);
                    App.setRoot("signin");
                }
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }

}
