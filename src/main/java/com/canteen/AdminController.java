package com.canteen;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AdminController {

    public static int idFromSelected;

    @FXML
    private TableView table;

    @FXML
    private TableColumn<Order, String> name, status, user;

    @FXML
    private TableColumn<Order, Integer> id, price;

    @FXML
    private ChoiceBox name_capt, status_capt;

    @FXML
    private void logout() throws IOException {
        Stage stage = new Stage();
        App.backToLogin(stage);
    }

    @FXML
    private void initialize() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + dbHost + "/" + dbName, dbLogin, dbPassword);
            Statement statement = conn.createStatement();
            ResultSet orders = statement.executeQuery("SELECT * FROM `orders`");
            ObservableList<Order> order_list = FXCollections.observableArrayList();
            while (orders.next()) {;
                Order e = new Order(orders.getInt(1), orders.getString(2), orders.getInt(3), orders.getString(4), orders.getString(5));
                order_list.add(e);
            }
            table.setItems(order_list);
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            price.setCellValueFactory(new PropertyValueFactory<>("price"));
            status.setCellValueFactory(new PropertyValueFactory<>("status"));
            user.setCellValueFactory(new PropertyValueFactory<>("user"));
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }

    @FXML
    private void setLabelsText() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + dbHost + "/" + dbName, dbLogin, dbPassword);
            Statement statement = conn.createStatement();
            Order selectedOrder = (Order) table.getSelectionModel().getSelectedItem();
            idFromSelected = selectedOrder.getId();
            ObservableList<String> statuses = FXCollections.observableArrayList("Ожидает", "Готовится", "Выдан");
            status_capt.setItems(statuses);
            status_capt.setValue(selectedOrder.getStatus());
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }

    @FXML
    private void updateOrder() throws SQLException, IOException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + dbHost + "/" + dbName, dbLogin, dbPassword);
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE `orders` SET `status` = " + "'" + status_capt.getValue() + "'" + " WHERE `orders`.`id` = " + "'" + idFromSelected + "';");
            App.setRoot("admin");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }

    @FXML
    private void deleteOrder() throws SQLException, IOException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/canteen", "root", "1234");
            Statement statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM `orders` WHERE `orders`.`id` = " + "'" + idFromSelected + "';");
            App.setRoot("admin");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }

}
