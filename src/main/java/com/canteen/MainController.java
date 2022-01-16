package com.canteen;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private void switchToCreateOrder() throws IOException {
        App.setRoot("order_main");
    }

    @FXML
    private void switchToOrders() throws IOException {
        App.setRoot("user_orders");
    }

    @FXML
    private void logout() throws IOException {
        Stage stage = new Stage();
        App.backToLogin(stage);
    }

}
