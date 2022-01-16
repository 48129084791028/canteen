package com.canteen;

import static com.canteen.App.dbHost;
import static com.canteen.App.dbLogin;
import static com.canteen.App.dbName;
import static com.canteen.App.dbPassword;
import static com.canteen.SigninController.currentUserLogin;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class OrderMainController {

    @FXML
    private TableView table;

    @FXML
    private TableColumn<Price, String> name;

    @FXML
    private TableColumn<Price, Integer> price;

    @FXML
    private ChoiceBox choiceName;

    @FXML
    private TextField cont;

    @FXML
    private Label hidden_label;

    @FXML
    private void switchToMain() throws IOException {
        App.setRoot("main");
    }

    @FXML
    private void switchToOrders() throws IOException {
        App.setRoot("user_orders");
    }

    @FXML
    private void initialize() throws IOException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + dbHost + "/" + dbName, dbLogin, dbPassword);
            Statement statement = conn.createStatement();
            ResultSet prices = statement.executeQuery("SELECT `name`, `price` FROM `price`");
            ObservableList<Price> price_list = FXCollections.observableArrayList();
            while (prices.next()) {
                Price e = new Price(prices.getString(1), prices.getInt(2));
                price_list.add(e);
            }
            table.setItems(price_list);
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            price.setCellValueFactory(new PropertyValueFactory<>("price"));

            //ChoiceBox
            ResultSet nameSet = statement.executeQuery("SELECT `name`, `price` FROM `price`");
            ObservableList<String> names = FXCollections.observableArrayList();
            while (nameSet.next()) {
                names.add(nameSet.getString(1));
            }
            choiceName.setItems(names);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }

    @FXML
    private void addOrderToDatabase() throws SQLException, IOException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + dbHost + "/" + dbName, dbLogin, dbPassword);
            Statement statement = conn.createStatement();

            String name = choiceName.getValue().toString();
            ResultSet price = statement.executeQuery("SELECT `price` FROM `price` WHERE `name` LIKE" + "'" + name + "'");
            price.next();
            statement.executeUpdate("INSERT INTO `orders` (`name`, `price`, `status`, `user`) VALUES (" + "'" + name + "'," + "'" + price.getString(1) + "'," + "'Ожидает'," + "'" + currentUserLogin + "'" + "); ");
            App.setRoot("user_orders");

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }

    @FXML
    private void logout() throws IOException {
        Stage stage = new Stage();
        App.backToLogin(stage);
    }

}
