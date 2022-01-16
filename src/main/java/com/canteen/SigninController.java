package com.canteen;

import javafx.scene.control.Button;
import static com.canteen.App.dbHost;
import static com.canteen.App.dbLogin;
import static com.canteen.App.dbName;
import static com.canteen.App.dbPassword;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SigninController {

    public static String currentUserLogin;

    @FXML
    private void switchToSignup() throws IOException {
        App.setRoot("signup");
    }
    @FXML
    private Button loginSignupButton;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Label hidden_label;

    @FXML
    private Button loginButton;

    @FXML
    private void signin() throws IOException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + dbHost + "/" + dbName, dbLogin, dbPassword);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `users` WHERE `login` LIKE" + "'" + login_field.getText() + "'");
            if (resultSet.next()) {
                if (resultSet.getString(5).equals("1")) {
                    if (password_field.getText().equals(resultSet.getString(4))) {
                        Stage stage = new Stage();
                        App.switchToAdmin(stage);
                    } else {
                        hidden_label.setText("Неверный пароль!");
                        hidden_label.setVisible(true);
                    }
                } else {
                    if (password_field.getText().equals(resultSet.getString(4))) {
                        Stage stage = new Stage();
                        App.switchToMain(stage);
                        currentUserLogin = resultSet.getString(3);
                    } else {
                        hidden_label.setText("Неверный пароль!");
                        hidden_label.setVisible(true);
                    }
                }
            } else {
                hidden_label.setText("Такого пользователя не существует!");
                hidden_label.setVisible(true);
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }

}
